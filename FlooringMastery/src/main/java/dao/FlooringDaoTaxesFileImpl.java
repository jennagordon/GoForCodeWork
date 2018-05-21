package dao;

import dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FlooringDaoTaxesFileImpl implements FlooringDaoTaxes {

    private Map<String, Tax> taxRateByStateMap = new HashMap<>();
    private static String FILE_NAME;
    private static final String STRING_DELIMITER = ",";

    public FlooringDaoTaxesFileImpl(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;

    }

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {
        loadTaxes();
        return taxRateByStateMap.get(state);
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        loadTaxes();
        return new ArrayList<>(taxRateByStateMap.values());
    }

    @Override
    public void createTax(Tax taxObject) throws FlooringPersistenceException {

        taxRateByStateMap.put(taxObject.getState(), taxObject);
        writeTaxes();

    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException {

        taxRateByStateMap.put(taxObject.getState(), taxObject);
        writeTaxes();

    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {
        taxRateByStateMap.remove(taxObject.getState());
        writeTaxes();

    }

    private void loadTaxes() throws FlooringPersistenceException {
        // extracting the data from the file and adding to the map so we
        // can use this data

        // creating a instance of Scanner
        Scanner scanner;
        try {
            // giving file reader our file (taxes) and giving that to buffered reader
            // which scanner gets
            scanner = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("Could not load tax data into memory", e);
        }

        // creating new variables
        String currentLine;
        String[] currentTokens;

        // while scanner has a next line
        while (scanner.hasNextLine()) {
            // currentLine is equal to the full line
            currentLine = scanner.nextLine();
            // split line at each , and turn each piece into an array
            currentTokens = currentLine.split(STRING_DELIMITER);

            // create new instance of our Tax Object
            // set index 0 to our state (string) and set index 1 to our tax rate
            Tax currentTaxObject = new Tax(currentTokens[0], new BigDecimal(currentTokens[1]));
            // put info into our map. key is state
            taxRateByStateMap.put(currentTaxObject.getState(), currentTaxObject);
        }
        scanner.close();

    }

    private void writeTaxes() throws FlooringPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE_NAME));
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not load tax data into memory", e);
        }

        List<Tax> taxList = this.retrieveAllTaxes();

        for(Tax tempTax : taxList) {
            out.println(tempTax.getState() + STRING_DELIMITER + tempTax.getTaxRate());

            out.flush();
        }
        out.close();
    }
}
