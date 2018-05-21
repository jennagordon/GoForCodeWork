package dao;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import service.VendingMachineInsufficientFundsException;
import service.VendingMachineItemNotAvailableException;
import service.VendingMachineServiceLayer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    Item onlyItem;
    List<Item> itemList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        // instantiate item
        // add item to our list
        onlyItem = new Item("A1");
        onlyItem.setName("Chips");
        onlyItem.setPrice(BigDecimal.valueOf(1.00));
        onlyItem.setQuantity(3);

        itemList.add(onlyItem);

        int moneyInMachine = 20;
    }

    @Override
    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public Item retrieveItemById(String id) throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException {
        if(onlyItem.getId() != id) {
            throw new VendingMachineItemNotAvailableException("Item not in machine");
        }
        return onlyItem;
        }


    @Override
    public Item updateItem(Item purchasedItem) throws VendingMachinePersistenceException {
        return onlyItem;
    }

    @Override
    public Item createItem(String id, Item item) throws VendingMachinePersistenceException {
        return onlyItem;
    }

    @Override
    public void removeItem(String id) throws VendingMachinePersistenceException {

    }
}
