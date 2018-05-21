package controller;

import dao.FlooringPersistenceException;
import dto.Order;
import dto.Product;
import dto.Tax;
import service.FlooringServiceLayer;
import service.OrderNotFoundException;
import service.ProductMaterialNotFoundException;
import service.TaxStateNotFoundException;
import ui.FlooringView;

import java.time.LocalDate;
import java.util.List;

public class FlooringController {

    private FlooringServiceLayer service;
    private FlooringView view;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        int menuOption = 0;
        boolean keepGoing = true;

        try {

            while (keepGoing) {
                menuOption = printMenuAndRetrieveSelection();
                switch (menuOption) {

                    case 1:
                        // display orders
                        displayAllOrdersByDate();
                        break;
                    case 2:
                        // add an order
                        addNewOrder();
                        break;
                    case 3:
                        // edit an order
                        editOrder();
                        break;
                    case 4:
                        // remove an order
                        removeOrder();
                        break;
                    case 5:
                        // save current work
                        saveAllOrders();
                        break;
                    case 6:
                        // training mode
                        trainingMode();
                        break;
                    case 7:
                        // Quit
                        quit();
                        keepGoing = false;
                    default:

                }

            }
        } catch (FlooringPersistenceException e) {
            view.displayErrorBanner(e.getMessage());
        }

    }

    private int printMenuAndRetrieveSelection() {
        view.menuBanner();
        // print the menu and return selection
        return view.displayMenuAndPromptForSelection();
    }

    private void displayAllOrdersByDate() throws FlooringPersistenceException {
        // prompt for date
        LocalDate orderDate = view.promptForDate();
        // give date to service so it can retrieve all orders
        try {
            List<Order> orderList = service.retrieveAllOrdersByDate(orderDate);
            view.displayAllOrdersByDate(orderList);
            view.promptUserToHitEnter();

        } catch (OrderNotFoundException e) {
            view.displayErrorBanner(e.getMessage());
        }

    }

    private void quit() throws FlooringPersistenceException {
        // prompt to save
        boolean save = view.promptUserForSave();

        if (save) {
            service.saveAllOrders();
            view.displaySuccessBanner();
        }
        view.displayExitMessage();
    }

    private void addNewOrder() throws FlooringPersistenceException {
        boolean invalidData = true;

        // if the user enters invalid data keep prompting
        while (invalidData) {

            // display tax info
            List<Tax> taxList = service.retrieveAllTaxes();
            view.displayAllTaxes(taxList);

            // display product info
            List<Product> productList = service.retrieveAllProducts();
            view.displayAllProducts(productList);

            // display order banner
            view.displayNewOrderBanner();

            // prompt for order details
            Order order = view.promptForNewOrderDetails();
            // display order summary
            view.displayOrderSummary(order);

            // if user says yes to commit
            boolean commit = view.promptForCommit();

            if (commit) {
                try {
                    // pass order to service
                    service.addOrder(order);
                    view.displaySuccessBanner();
                    view.promptUserToHitEnter();
                    invalidData = false;

                } catch (TaxStateNotFoundException | ProductMaterialNotFoundException e) {
                    view.displayErrorBanner(e.getMessage());
                    invalidData = true;
                }
            } else {
                // if they say no we want to stop the loop
                invalidData = false;
            }

        }
    }

    private void saveAllOrders() throws FlooringPersistenceException {
        boolean save = view.promptUserForSave();

        if (save) {
            service.saveAllOrders();
            view.displaySuccessBanner();
        }
        view.promptUserToHitEnter();

    }

    private void removeOrder() throws FlooringPersistenceException {
        // prompt for date
        LocalDate date = view.promptForDate();
        // display all orders for that date
        try {
            List<Order> orderList = service.retrieveAllOrdersByDate(date);
            view.displayAllOrdersByDate(orderList);

            // prompt for orderNumber
            String orderNumber = view.promptForOrderNumber();

            // display that order to user
            Order order = service.retrieveOrderByDateAndId(date, orderNumber);
            view.displayOrderByDateAndId(order);

            // if user says yes to commit
            boolean commit = view.promptForCommit();
            view.promptUserToHitEnter();
            // call service to remove item
            if (commit) {
                service.removeOrder(date, orderNumber);
                view.displaySuccessBanner();
            }

        } catch (OrderNotFoundException e) {
            view.displayErrorBanner(e.getMessage());
        }

    }

    private void trainingMode() {
        // prompt user to enter training mode
        boolean userSelection = view.promptForTrainingMode();
        view.promptUserToHitEnter();
        // print mode banners
        if (userSelection) {
            view.displayTrainingBanner();
        } else {
            view.displayProdBanner();
        }
        // pass service string
        service.activateTrainingMode(userSelection);
    }

    private void editOrder() throws FlooringPersistenceException {
        // prompt for date
        LocalDate date = view.promptForDate();
        Order order = new Order();
        order.setOrderDate(date);
        boolean invalidData = true;

        // return all orders with that date
        try {
            List<Order> orderList = service.retrieveAllOrdersByDate(date);
            view.displayAllOrdersByDate(orderList);

            view.promptUserToHitEnter();

            // ask for orderNumber to edit
            String orderNumber = view.promptForOrderNumber();

            // get order to edit by id
            order = service.retrieveOrderByDateAndId(date, orderNumber);
            view.displayOrderByDateAndId(order);

            view.promptUserToHitEnter();

            // need a loop to re-prompt if invalid data
            while (invalidData) {
                // prompt for changes
                // prompt for commit (inside of below method)
                view.promptForOrderUpdates(order);

                view.promptUserToHitEnter();

                // call service to remove item
                try {
                    service.editOrder(date, order);
                    view.displaySuccessBanner();
                    invalidData = false;
                } catch (TaxStateNotFoundException | ProductMaterialNotFoundException e) {
                    view.displayErrorBanner(e.getMessage());
                    invalidData = true;
                } // end of catch for tax/product

            } // end of while invalid data

        } catch (OrderNotFoundException e) {
            view.displayErrorBanner(e.getMessage());
        }

    } // end of edit


}
