package ui;

import dto.Change;
import dto.Item;
import service.VendingMachineInsufficientFundsException;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndPromptForSelection () {
        // print menu options to user
        io.print("1. Add Money");
        io.print("2. Purchase An Item");
        io.print("3. Get Change");
        io.print("4. Exit");

        // return the users choice stored as a int
        return io.readInt("Please Select an Option from the Menu", 1, 4);
    }

    public BigDecimal promptUserForMoneyToAdd() {
        // Ask for money amount
        // return string converted into BigDecimal
        return io.readBigDecimal("How much money do you want to add?");

    }

    public String promptUserForItemToPurchase() {
        // Ask for item to purchase via ID
        // return id string
        return io.readString("What Item Would You Like To Purchase (Item ID)");
    }

    public void printOutMoneyInMachine(BigDecimal moneyInMachine) {
        io.print("Money In The Machine: " + moneyInMachine);
    }

    public void printOutWelcomeBanner() {
        io.print("WELCOME!");
    }

    public void displayAllItemsList(List<Item> itemList) {
        io.print("*********************");

        for(Item tempItem: itemList) {
            if(tempItem.getQuantity() > 0) {
                io.print(tempItem.getId() + " : "
                        + tempItem.getName() + " $"
                        + tempItem.getPrice() + " "
                        + tempItem.getQuantity());
            }
        }

        io.print("*********************");

    }

    public void printOutMainMenuBanner(){
        io.print("MAIN MENU");
        io.print("----------");
    }

    public void printOutSuccessBanner() {
        io.print("Action Completed Successfully");
    }

    public void printOutSuccessfulItemVendedBanner(String name) {
        io.print("Here are your " + name);
    }

    public void displayChange(Change moneyInChange) {
        io.print("You have " + moneyInChange.getNumQuarters() + " quarters");
        io.print("You have " + moneyInChange.getNumDimes()  + " dimes");
        io.print("You have " + moneyInChange.getNumNickles()  + " nickles");
        io.print("You have " + moneyInChange.getNumPennies()  + " pennies");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("===ERROR===");
        io.print(errorMsg);
    }
}
