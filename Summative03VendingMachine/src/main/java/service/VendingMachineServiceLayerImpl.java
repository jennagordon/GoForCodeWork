package service;

import dao.VendingMachineDao;
import dao.VendingMachineDaoAudit;
import dao.VendingMachineDaoFileImpl;
import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;


public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineDaoAudit auditDao;

    public BigDecimal moneyInMachine = new BigDecimal(0);


    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineDaoAudit auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {

        return dao.retrieveAllItems();
    }

    @Override
    public void addMoneyToMachine(BigDecimal userMoneyFromView) {
        // store userMoney as what user entered
        moneyInMachine = moneyInMachine.add(userMoneyFromView);

    }

    @Override
    public BigDecimal retrieveMoneyInMachine() {

        return moneyInMachine;
    }

    @Override
    public Item purchaseItem(String id) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineItemNotAvailableException {
        // get item from list
       Item purchasedItem = dao.retrieveItemById(id);
       // if item is not found or quantity is zero throw exception

//        validateItemAvailability(purchasedItem);
       if (purchasedItem == null) {
           throw new VendingMachineItemNotAvailableException("Item is not in machine");
       }

       if(purchasedItem.getQuantity() <= 0) {
           throw new VendingMachineItemNotAvailableException("Item is not in machine");
       }


       // if user doesn't have enough money throw error
       if ((moneyInMachine.compareTo(purchasedItem.getPrice()) < 0)) {
           throw new VendingMachineInsufficientFundsException("You need more money");
       }
        // deduct quantity by 1
        int updatedQuantity = purchasedItem.getQuantity();
        purchasedItem.setQuantity(updatedQuantity - 1);
        // let the dao know we've updated an item
        dao.updateItem(purchasedItem);
        // update money in the machine amount
        moneyInMachine = moneyInMachine.subtract(purchasedItem.getPrice());
        // return updated item
        return purchasedItem;
    }

    @Override
    public Change calculateChange() {
        // get current amount of money in machine
        BigDecimal moneyToChangeBigDecimal = retrieveMoneyInMachine();
        // convert into pennies
        int moneyInPennies = (moneyToChangeBigDecimal.multiply(BigDecimal.valueOf(100))).intValueExact();
        // change money in machine to int
        // calculate that amount into pennies
        Change change = new Change();


       // want to know how many quarters
        int totalQuarters = moneyInPennies / 25;
        change.setNumQuarters(totalQuarters);
        int newMoneyInPennies = moneyInPennies % 25;

        // want to know how many dimes if leftover is greater than 10
        if (newMoneyInPennies >= 10) {
            int totalDimes = newMoneyInPennies / 10;
            change.setNumDimes(totalDimes);
            newMoneyInPennies = newMoneyInPennies % 10;
        }
        // want to know how many nickles if leftover is greater than 5
        if (newMoneyInPennies >= 5) {
            int totalNickles = newMoneyInPennies / 5;
            change.setNumNickles(totalNickles);
            newMoneyInPennies = newMoneyInPennies % 5;
        }
        // want to know how many pennies if leftover is less than 5
        if(newMoneyInPennies <= 4) {
            int totalPennies = newMoneyInPennies / 1;
            change.setNumPennies(totalPennies);
        }
        // update money in machine
        moneyInMachine = moneyInMachine.subtract(moneyToChangeBigDecimal);

        return change;
    }

//    public void validateItemAvailability(Item purchasedItem) throws VendingMachineItemNotAvailableException {
//        if (purchasedItem == null) {
//            throw new VendingMachineItemNotAvailableException("Item is not in machine");
//        }
//
//        if(purchasedItem.getQuantity() <= 0) {
//            throw new VendingMachineItemNotAvailableException("Item is not in machine");
//        }
//    }


}
