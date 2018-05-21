package ui;

import dto.Order;
import dto.Product;
import dto.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EmptyStackException;
import java.util.List;

public class FlooringView {
    private UserIO io;
    private static final String EMPTY_STRING = "";

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void menuBanner() {
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        io.print("*  <<Flooring Program>>");
    }

    public int displayMenuAndPromptForSelection() {
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Program Mode");
        io.print("* 7. Quit");
        io.print("* ");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");

        return io.readInt("Please Select an Option from the Menu", 1, 7);

    }

    public void displayErrorBanner(String errorMsg) {
        io.print("ERROR");
        io.print(errorMsg);
    }

    public LocalDate promptForDate() {

        return io.readLocalDate("Please Enter A Date");
    }

    public void displayAllOrdersByDate(List<Order> orderList) {
        io.print("Orders For " + orderList.get(0).getOrderDate());
        for (Order tempOrder : orderList) {
            io.print("Order Number: " + tempOrder.getOrderNumber() +
                    "\n" + "Customer Name: " + tempOrder.getCustomerName() +
                    "\n" + "State: " + tempOrder.getTaxObject().getState() +
                    "\n" + "Material: " + tempOrder.getProductObject().getProductType() +
                    "\n" + "Area: " + tempOrder.getArea() +
                    "\n" + "Total Material Cost: " + tempOrder.getTotalMaterialCost() +
                    "\n" + "Total Labor Cost: " + tempOrder.getTotalLaborCost() +
                    "\n" + "Total Taxes: " + tempOrder.getTotalTax() +
                    "\n" + "Total Cost: " + tempOrder.getTotalCost() +
                    "\n");
        }

    }

    public void promptUserToHitEnter() {
        io.readString("Please Hit Enter To Continue");
    }

    public void displayExitMessage() {
        io.print("Goodbye!");
    }

    public void displayAllTaxes(List<Tax> taxList) {
        io.print("STATE : TAX RATE");
        for (Tax tempTax : taxList) {
            io.print(tempTax.getState() + " : " + tempTax.getTaxRate());
        }
    }

    public void displayAllProducts(List<Product> productList) {
        io.print("MATERIAL : MATERIAL COST : LABOR COST");
        for (Product tempProduct : productList) {
            io.print(tempProduct.getProductType() + " : " + tempProduct.getMaterialCostPerSquareFoot() +
                    " : " + tempProduct.getLaborCostPerSquareFoot());
        }
    }

    public Order promptForNewOrderDetails() {
        LocalDate date = io.readLocalDate("Date");
        String name = io.readString("Customer Name");
        String state = io.readString("State");
        String material = io.readString("Material");
        BigDecimal area = io.readBigDecimal("Area");

        Tax taxOrder = new Tax(state);
        Product productOrder = new Product(material);

        Order orderObject = new Order();
        orderObject.setOrderDate(date);
        orderObject.setCustomerName(name);
        orderObject.setArea(area);
        orderObject.setTaxObject(taxOrder);
        orderObject.setProductObject(productOrder);

        return orderObject;
    }

    public void displayNewOrderBanner() {
        io.print("=== CREATE NEW ORDER ===");
    }

    public void displaySuccessBanner() {
        io.print("Action Completed Successfully");
    }

    public boolean promptUserForSave() {

        boolean save;
        String answer = io.readString("Want to Save? (Y/N)");

        if (answer.equalsIgnoreCase("y")) {
            save = true;
        } else {
            save = false;
        }
        return save;
    }

    public String promptForOrderNumber() {
        return io.readString("What is the Order Number");
    }

    public boolean promptForCommit() {
        boolean commit;
        String answer = io.readString("Commit These Changes? (Y/N)");

        if (answer.equalsIgnoreCase("y")) {
            commit = true;
            io.print("Committing Your Changes");
        } else {
            commit = false;
            io.print("Your Changes Will Not Be Committed");
        }
        return commit;
    }

    public boolean promptForTrainingMode() {
        String answer = io.readString("What Mode? (Prod/Train)");

        if (answer.equalsIgnoreCase("train")) {
            return true;
        } else {
            return false;
        }
    }

    public void displayOrderByDateAndId(Order orderObject) {

        io.print("Order Number: " + orderObject.getOrderNumber() +
                "\n" + "Customer Name: " + orderObject.getCustomerName() +
                "\n" + "State: " + orderObject.getTaxObject().getState() +
                "\n" + "Material: " + orderObject.getProductObject().getProductType() +
                "\n" + "Area: " + orderObject.getArea() +
                "\n" + "Total Material Cost: " + orderObject.getTotalMaterialCost() +
                "\n" + "Total Labor Cost: " + orderObject.getTotalLaborCost() +
                "\n" + "Total Taxes: " + orderObject.getTotalTax() +
                "\n" + "Total Cost: " + orderObject.getTotalCost());
    }

    public Order promptForOrderUpdates(Order orderToBeUpdated) {

        // prompt for user updates
        String updatedDateString = io.readString("Please enter the date (" + orderToBeUpdated.getOrderDate() + "): ");
        String updatedCustomerName = io.readString("Please enter the Customer Name (" + orderToBeUpdated.getCustomerName() + "): ");
        String updatedState = io.readString("Please enter the state (" + orderToBeUpdated.getTaxObject().getState() + "): ");
        String updatedMaterial = io.readString("Please enter the material (" + orderToBeUpdated.getProductObject().getProductType() + "): ");
        String updatedAreaString = io.readString("Please enter the area (" + orderToBeUpdated.getArea() + "): ");

        // call commit method
        boolean commit = promptForCommit();

        // if they want to commit set values on object
        if (commit) {

            // logic for if they hit enter don't update that field
            if (!EMPTY_STRING.equals(updatedDateString)) {
                // convert the date from string to LocalDate
                LocalDate updatedDate = LocalDate.parse(updatedDateString, DateTimeFormatter.ofPattern("MMddyyyy"));
                // set new date on order
                orderToBeUpdated.setOrderDate(updatedDate);
            }

            if (!EMPTY_STRING.equals(updatedCustomerName)) {
                orderToBeUpdated.setCustomerName(updatedCustomerName);
            }

            if (!EMPTY_STRING.equals(updatedState)) {
                orderToBeUpdated.getTaxObject().setState(updatedState);
            }

            if (!EMPTY_STRING.equals(updatedMaterial)) {
                orderToBeUpdated.getProductObject().setProductType(updatedMaterial);
            }

            if (!EMPTY_STRING.equals(updatedAreaString)) {
                // convert string to BigDecimal
                BigDecimal updatedArea = new BigDecimal(updatedAreaString);
                updatedArea.setScale(2, RoundingMode.HALF_UP);
                // set area on orderToBeUpdated
                orderToBeUpdated.setArea(updatedArea);
            }

        }

        return orderToBeUpdated;
    }

    public void displayOrderSummary(Order orderObject) {

        io.print("Customer Name: " + orderObject.getCustomerName() +
                "\n" + "State: " + orderObject.getTaxObject().getState() +
                "\n" + "Material: " + orderObject.getProductObject().getProductType() +
                "\n" + "Area: " + orderObject.getArea());

    }

    public void displayTrainingBanner() {
        io.print("=== TRAINING MODE ===");
    }

    public void displayProdBanner() {
        io.print("=== PRODUCTION MODE ===");
    }

}
