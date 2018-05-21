package controller;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import service.VendingMachineInsufficientFundsException;
import service.VendingMachineItemNotAvailableException;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineController {

    private VendingMachineServiceLayer service;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

     public void run()  {
        // print out welcome banner
         view.printOutWelcomeBanner();

         int menuOption = 0;
         boolean keepGoing = true;
         try {
             while (keepGoing) {
                 // print out all items in vending machine
                 // using the map
                 printAllItemsList();

                 // print out money in machine
                 printOutMoneyInMachine();


                 // print out main menu banner
                 view.printOutMainMenuBanner();
                 //print out main menu options
                 menuOption = printMenuOptions();

                 switch (menuOption) {
                     case 1:
                         // add money to machine
                         addMoney();
                         break;
                     case 2:
                         // purchase an item
                         purchaseItem();
                         break;
                     case 3:
                         returnChange();
                         break;
                     case 4:
                         keepGoing = false;
                         break;
                     default:

                 }
             }
         } catch (VendingMachinePersistenceException e) {
             view.displayErrorMessage(e.getMessage());
         }

     }

    private int printMenuOptions() {
        return view.printMenuAndPromptForSelection();
    }

    private void addMoney() {
        // prompt user for amount
        BigDecimal userMoneyFromView = view.promptUserForMoneyToAdd();
        // pass amount to service
        service.addMoneyToMachine(userMoneyFromView);

    }

    private void printAllItemsList() throws VendingMachinePersistenceException{
        // get the list of items from the service
        List<Item> itemList = service.retrieveAllItems();

        // the view to print out that list
        view.displayAllItemsList(itemList);
    }

    private void printOutMoneyInMachine() {
        BigDecimal moneyInMachine = service.retrieveMoneyInMachine();
        view.printOutMoneyInMachine(moneyInMachine);
    }

    private void purchaseItem() throws VendingMachinePersistenceException {

        // prompt user for item
        String itemId = view.promptUserForItemToPurchase();
        try {

            // pass item to service
            Item vendedItem = service.purchaseItem(itemId);

            // print item vended
            view.printOutSuccessfulItemVendedBanner(vendedItem.getName());
            // print success banner
            view.printOutSuccessBanner();

        } catch(VendingMachineItemNotAvailableException | VendingMachineInsufficientFundsException e){
                view.displayErrorMessage(e.getMessage());
        }
    }


    private void returnChange() {
        // get change from service
        Change moneyInChange = service.calculateChange();

        // print change amount
        view.displayChange(moneyInChange);


        // print success banner
        view.printOutSuccessBanner();
    }
}

