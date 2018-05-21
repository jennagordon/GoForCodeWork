package dao;

import dto.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import service.VendingMachineItemNotAvailableException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineDaoTest {

    VendingMachineDao dao = new VendingMachineDaoFileImpl();

    @Before
    public void setUp() throws Exception {
        // get all items in file
        List<Item> itemList = dao.retrieveAllItems();
        // cycle through each item and remove them
        // this creates our clean state to run our tests
        for(Item tempItem : itemList) {
            dao.removeItem(tempItem.getId());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveAllItems() throws VendingMachinePersistenceException {
        // creating a new item(item1)
        Item item1 = new Item("A1");
        item1.setName("Fruit Snacks");
        item1.setPrice(BigDecimal.valueOf(1.50));
        item1.setQuantity(4);
        // adding new item to the file
        dao.createItem(item1.getId(), item1);
        // creating a new item(item2)
        Item item2 = new Item("B1");
        item2.setName("Chips");
        item2.setPrice(BigDecimal.valueOf(1.00));
        item2.setQuantity(2);
        // adding new item to the file
        dao.createItem(item2.getId(), item2);
        // confirming there are 2 items in my list
        assertEquals(2, dao.retrieveAllItems().size());
    }

    @Test
    public void testRetrieveItemById() throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException {
        // creating a new item(item1)
        Item item1 = new Item("A1");
        item1.setName("Fruit Snacks");
        item1.setPrice(BigDecimal.valueOf(1.50));
        item1.setQuantity(4);
        // adding new item to the file
        dao.createItem(item1.getId(), item1);
        // confirming item i created is the same that is in the file
        assertEquals(item1, dao.retrieveItemById(item1.getId()));
    }


    // do we really need this? testing the same thing in multiple tests
    @Test
    public void testUpdateItem() throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException {
        // creating a new item(item1)
        Item item1 = new Item("C1");
        item1.setName("Fruit Snacks");
        item1.setPrice(BigDecimal.valueOf(1.50));
        item1.setQuantity(4);
        // adding new item to the file
        dao.createItem(item1.getId(), item1);

        // confirming item i created is the same that is in the file
        assertEquals(item1, dao.retrieveItemById(item1.getId()));
    }

// DON'T NEED BECAUSE WE ARE TESTING THIS IN RETRIEVE ITEM
//    @Test
//    public void testCreateItem() {
//    }

    @Test
    public void testRemoveItem() throws VendingMachinePersistenceException {
// creating a new item(item1)
        Item item1 = new Item("A1");
        item1.setName("Fruit Snacks");
        item1.setPrice(BigDecimal.valueOf(1.50));
        item1.setQuantity(4);
        // adding new item to the file
        dao.createItem(item1.getId(), item1);
        // creating a new item(item2)
        Item item2 = new Item("B1");
        item2.setName("Chips");
        item2.setPrice(BigDecimal.valueOf(1.00));
        item2.setQuantity(2);
        // adding new item to the file
        dao.createItem(item2.getId(), item2);
        // remove item 1
        dao.removeItem(item1.getId());
        assertEquals(1, dao.retrieveAllItems().size());
        // remove item 2
        dao.removeItem(item2.getId());
        assertEquals(0, dao.retrieveAllItems().size());

    }
}