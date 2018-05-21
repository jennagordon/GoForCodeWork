package dao;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FlooringDaoOrderProdFileImpl implements FlooringDaoOrder {

    private Map<LocalDate, HashMap<String, Order>> ordersByDateMap = new HashMap<>();
    private static final String STRING_DELIMITER = ",";

    @Override
    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {

        // isolated the loads
        if(!ordersByDateMap.containsKey(orderDate)) {
            loadOrders(orderDate);
        }

//        if(ordersByDateMap.get(orderDate) == null) {
//            throw new FlooringPersistenceException("No Orders For Given Date.");
//        }
        return new ArrayList<>(ordersByDateMap.get(orderDate).values());
    }

    @Override
    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
        if(!ordersByDateMap.containsKey(orderDate)) {
            loadOrders(orderDate);
        }


        // need to move to service layer
//        if(!ordersByDateMap.get(orderDate).containsKey(orderNumber)) {
//            throw new FlooringPersistenceException("Order Does Not Exist For This Date");
//        }

        return ordersByDateMap.get(orderDate).get(orderNumber);
    }

    @Override
    public Order createOrder(LocalDate orderDate,Order orderObject) throws FlooringPersistenceException {
        if(orderObject.getOrderNumber() == null) {
            // get order number through method
            String orderNumber = generateOrderNumber(orderDate);
            // set order number on order Object
            orderObject.setOrderNumber(orderNumber);
            // add full order Object to map
        }

        // if the date isn't in the map load orders
        if(!ordersByDateMap.containsKey(orderDate)) {
            loadOrders(orderDate);
        }

        // the date is a new date create an entry in the map
        if (ordersByDateMap.get(orderDate) == null) {
            ordersByDateMap.put(orderDate, new HashMap<>());
            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
        } else {
            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
        }

        return orderObject;
    }

    @Override
    public void updateOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException {

        // date doesn't doesn't exist yet add new map for that date
        // put that on the map
        if (ordersByDateMap.get(orderDate) == null) {
            ordersByDateMap.put(orderDate, new HashMap<>());
            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
        } else {
            ordersByDateMap.get(orderDate).replace(orderObject.getOrderNumber(), orderObject);
        }


    }

    @Override
    public void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
        ordersByDateMap.get(orderDate).remove(orderNumber);


    }

    @Override
    public void saveOrder() throws FlooringPersistenceException {
        writeOrders();

    }

    private String generateOrderNumber(LocalDate orderDate) throws FlooringPersistenceException {
//        int highestUsedOrderNumber = 0;

        String orderNumber = UUID.randomUUID().toString();


        // get all orders
//       List<Order> orderList = this.retrieveAllOrdersByDate(orderDate);

       // if there are more than one order in the file enter this for loop
        // before unique ids
//       if (orderList.size() != 0) {
//           for (Order tempOrder : orderList) {
//               if(tempOrder.getOrderNumber() > highestUsedOrderNumber) {
//                   highestUsedOrderNumber = tempOrder.getOrderNumber();
//               }
//           }
//       }

        return orderNumber;
    }

    private void loadOrders(LocalDate orderDate) throws FlooringPersistenceException {
        Scanner scanner = null;
        String fileName = ("Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt");

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));

        } catch (FileNotFoundException e) {
            ordersByDateMap.put(orderDate, new HashMap<>());
//            throw new FlooringPersistenceException("No Orders Exist For Given Date");
        }

        String currentLine;
        String[] currentTokens;

        ordersByDateMap.put(orderDate, new HashMap<>());

        if(scanner != null) {
            while (scanner.hasNextLine()) {

                currentLine = scanner.nextLine();
                currentTokens = currentLine.split(STRING_DELIMITER);

//            OrderNumber
//            CustomerName
//            State,TaxRate (tax object)
//            ProductType,
//            Area,
//            materialCostPerSquareFoot,LaborCostPerSquareFoot (part of product object)
//            totalMaterialCost
//            totalLaborCost
//            totalTax
//            totalTotal
                Order orderObject = new Order();

                orderObject.setOrderNumber(currentTokens[0]);
                orderObject.setCustomerName(currentTokens[1]);
                orderObject.setTaxObject(new Tax(currentTokens[2], new BigDecimal(currentTokens[3])));
                orderObject.setProductObject(new Product(currentTokens[4], new BigDecimal(currentTokens[6]), new BigDecimal(currentTokens[7])));
                orderObject.setArea(new BigDecimal(currentTokens[5]));
                orderObject.setTotalMaterialCost(new BigDecimal(currentTokens[8]));
                orderObject.setTotalLaborCost(new BigDecimal(currentTokens[9]));
                orderObject.setTotalTax(new BigDecimal(currentTokens[10]));
                orderObject.setTotalCost(new BigDecimal(currentTokens[11]));
                orderObject.setOrderDate(orderDate);

                ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);

            }
            scanner.close();
        }
    }



    private void writeOrders() throws FlooringPersistenceException {

        PrintWriter out;
        String fileName;

        // get all the local dates from the outer map
        Set<LocalDate> orderDateSet = this.ordersByDateMap.keySet();

        // for each date we want to name the files with this pattern
        // so cycle through all the dates in the set
        for(LocalDate orderDate : orderDateSet) {

                fileName = ("Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt");


                try {
                    out = new PrintWriter(new FileWriter(fileName));

                } catch (IOException e) {
                    throw new FlooringPersistenceException("Could not load order data into memory");
                }

                List<Order> orderList = this.retrieveAllOrdersByDate(orderDate);

                for (Order tempOrder : orderList) {
                    out.println(tempOrder.getOrderNumber() + STRING_DELIMITER
                            + tempOrder.getCustomerName() + STRING_DELIMITER
                            + tempOrder.getTaxObject().getState() + STRING_DELIMITER + tempOrder.getTaxObject().getTaxRate() + STRING_DELIMITER
                            + tempOrder.getProductObject().getProductType() + STRING_DELIMITER
                            + tempOrder.getArea() + STRING_DELIMITER
                            + tempOrder.getProductObject().getMaterialCostPerSquareFoot() + STRING_DELIMITER
                            + tempOrder.getProductObject().getLaborCostPerSquareFoot() + STRING_DELIMITER
                            + tempOrder.getTotalMaterialCost() + STRING_DELIMITER
                            + tempOrder.getTotalLaborCost() + STRING_DELIMITER
                            + tempOrder.getTotalTax() + STRING_DELIMITER
                            + tempOrder.getTotalCost());

                    out.flush();
                }
                out.close();
        }

    }

}
