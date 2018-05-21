package service;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException;

    public void addMoneyToMachine(BigDecimal userMoneyFromView);

    public BigDecimal retrieveMoneyInMachine();

    public Item purchaseItem(String id) throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException, VendingMachineInsufficientFundsException;

    public Change calculateChange();
}
