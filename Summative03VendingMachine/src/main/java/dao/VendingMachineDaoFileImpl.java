package dao;

import dto.Item;
import service.VendingMachineItemNotAvailableException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> itemMap = new HashMap<>();

    private static final String INVENTORY_FILE = "Inventory.txt";
    private static final String DELIMITER = "::";

    public List<Item> retrieveAllItems() throws VendingMachinePersistenceException {
        // get all items from file
        loadInventory();
        // return map
        return new ArrayList<>(itemMap.values());
    }

    @Override
    public Item retrieveItemById(String id) throws VendingMachinePersistenceException {
        // get all items from file
        loadInventory();
        // return item by id
        return itemMap.get(id);
    }

    @Override
    public Item updateItem(Item purchasedItem) throws VendingMachinePersistenceException  {
        // update file
        writeInventory();
        return null;
    }

    // not using but needed for testing
    @Override
    public Item createItem(String id, Item item) throws VendingMachinePersistenceException {
        Item newItem = itemMap.put(id, item);
        writeInventory();
        return newItem;
    }

    // not using in program but needed for testing
    @Override
    public void removeItem(String id) throws VendingMachinePersistenceException {
        // remove by id
        itemMap.remove(id);
        // save the removal
        writeInventory();

    }

    private void loadInventory() throws VendingMachinePersistenceException  {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load Inventory data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);


            currentItem.setName(currentTokens[1]);

            // converting the String from file to a BigDecimal
            BigDecimal currentTokenBigDecimal = new BigDecimal(currentTokens[2]);
            currentItem.setPrice(currentTokenBigDecimal);

            // converting the String from file to int
            int currentTokenInt = new Integer(currentTokens[3]);
            currentItem.setQuantity(currentTokenInt);

            itemMap.put(currentItem.getId(), currentItem);
        }
        scanner.close();

    }

    private void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save item data.", e);
        }


        List<Item> itemList = this.retrieveAllItems();
        for (Item tempItem : itemList) {
            out.println(tempItem.getId() + DELIMITER
                    + tempItem.getName() + DELIMITER
                    + tempItem.getPrice() + DELIMITER
                    + tempItem.getQuantity());

            out.flush();
        }
        out.close();

    }
}

