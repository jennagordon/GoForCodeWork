//package com.sg.dao;
//
//import com.sg.dto.Order;
//import com.sg.dto.Product;
//import com.sg.dto.Tax;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class FlooringDaoOrderTrainingFileImpl implements FlooringDaoOrder {
//
//    private Map<LocalDate, HashMap<String, Order>> ordersByDateMap = new HashMap<>();
//    private final String STRING_DELIMITER = ",";
//
//    @Override
//    public List<Order> retrieveAllOrdersByDate(LocalDate orderDate) throws FlooringPersistenceException {
//        if(!ordersByDateMap.containsKey(orderDate)) {
//            loadOrders(orderDate);
//        }
//        // will need to update when we use global ids
////        if(ordersByDateMap.get(orderDate) == null) {
////            throw new FlooringPersistenceException("No Orders For Given Date.");
////        }
//        return new ArrayList<>(ordersByDateMap.get(orderDate).values());
//    }
//
//    @Override
//    public Order retrieveOrderByDateAndId(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
//        if(!ordersByDateMap.containsKey(orderDate)) {
//            loadOrders(orderDate);
//        }
//
//        if(!ordersByDateMap.get(orderDate).containsKey(orderNumber)) {
//            throw new FlooringPersistenceException("Order Does Not Exist For This Date");
//        }
//
//        return ordersByDateMap.get(orderDate).get(orderNumber);
//    }
//
//    @Override
//    public Order createOrder(LocalDate orderDate, Order orderObject) throws FlooringPersistenceException {
//        // get order number through method
//        String orderNumber = generateOrderNumber(orderDate);
//        // set order number on order Object
//        orderObject.setOrderNumber(orderNumber);
//        if(!ordersByDateMap.containsKey(orderDate)) {
//            loadOrders(orderDate);
//        }
//        // add full order Object to map
//        if (ordersByDateMap.get(orderDate) == null) {
//            ordersByDateMap.put(orderDate, new HashMap<>());
//            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
//        } else {
//            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
//        }
//
//        return orderObject;
//
//    }
//
//    @Override
//    public void updateOrder(LocalDate orderDate, Order orderObject) {
//        if (ordersByDateMap.get(orderDate) == null) {
//            ordersByDateMap.put(orderDate, new HashMap<>());
//            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
//        } else {
//            ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
//        }
//
////        ordersByDateMap.get(orderDate).replace(orderObject.getOrderNumber(), orderObject);
//
//
//    }
//
//    @Override
//    public void removeOrder(LocalDate orderDate, String orderNumber) throws FlooringPersistenceException {
//        ordersByDateMap.get(orderDate).remove(orderNumber);
//
//    }
//
//    @Override
//    public void saveOrder() {
//        // don't write in training
//    }
//
//    private String generateOrderNumber(LocalDate orderDate) throws FlooringPersistenceException {
////        int highestUsedOrderNumber = 0;
//        String orderNumber = UUID.randomUUID().toString();
//        // get all orders
////        List<Order> orderList = this.retrieveAllOrdersByDate(orderDate);
//
//        // if there are more than one order in the file enter this for loop
////        if (orderList.size() != 0) {
////            for (Order tempOrder : orderList) {
////                if(tempOrder.getOrderNumber() > highestUsedOrderNumber) {
////                    highestUsedOrderNumber = tempOrder.getOrderNumber();
////                }
////            }
////        }
//        return orderNumber;
//    }
//
//    private void loadOrders(LocalDate orderDate) throws FlooringPersistenceException {
//        Scanner scanner = null;
//        String fileName = ("Orders_" + orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt");
//
//        try {
//            scanner = new Scanner(new BufferedReader(new FileReader(fileName)));
//
//        } catch (FileNotFoundException e) {
//            ordersByDateMap.put(orderDate, new HashMap<>());
////            throw new FlooringPersistenceException("No Orders Exist For Given Date");
//        }
//
//        String currentLine;
//        String[] currentTokens;
//
//        ordersByDateMap.put(orderDate, new HashMap<>());
//
//        if (scanner != null) {
//            while (scanner.hasNextLine()) {
//
//                currentLine = scanner.nextLine();
//                currentTokens = currentLine.split(STRING_DELIMITER);
//
////            OrderNumber
////            CustomerName
////            State,TaxRate (tax object)
////            ProductType,
////            Area,
////            materialCostPerSquareFoot,LaborCostPerSquareFoot (part of product object)
////            totalMaterialCost
////            totalLaborCost
////            totalTax
////            totalTotal
//                Order orderObject = new Order();
//
//                orderObject.setOrderNumber(currentTokens[0]);
//                orderObject.setCustomerName(currentTokens[1]);
//                orderObject.setTaxObject(new Tax(currentTokens[2], new BigDecimal(currentTokens[3])));
//                orderObject.setProductObject(new Product(currentTokens[4], new BigDecimal(currentTokens[6]), new BigDecimal(currentTokens[7])));
//                orderObject.setArea(new BigDecimal(currentTokens[5]));
//                orderObject.setTotalMaterialCost(new BigDecimal(currentTokens[8]));
//                orderObject.setTotalLaborCost(new BigDecimal(currentTokens[9]));
//                orderObject.setTotalTax(new BigDecimal(currentTokens[10]));
//                orderObject.setTotalCost(new BigDecimal(currentTokens[11]));
//                orderObject.setOrderDate(orderDate);
//
//                ordersByDateMap.get(orderDate).put(orderObject.getOrderNumber(), orderObject);
//
//            }
//            scanner.close();
//        }
//    }
//}
