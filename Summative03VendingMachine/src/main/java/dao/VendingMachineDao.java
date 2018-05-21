package dao;

import dto.Item;
import service.VendingMachineItemNotAvailableException;

import java.util.List;

public interface VendingMachineDao {

    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException;

    public Item retrieveItemById(String id) throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException;

    public Item updateItem(Item purchasedItem) throws VendingMachinePersistenceException;

    public Item createItem(String id, Item item) throws VendingMachinePersistenceException;

    public void removeItem(String id) throws VendingMachinePersistenceException;


}
