package dao;

import dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlooringDaoTaxesStubImpl implements FlooringDaoTaxes {

    Tax stubTestTax;
    List<Tax> taxesList = new ArrayList<>();

    public FlooringDaoTaxesStubImpl() throws FlooringPersistenceException {
        stubTestTax = new Tax("OH", new BigDecimal("6.25"));
        taxesList.add(stubTestTax);
    }

    @Override
    public Tax retrieveTaxByState(String state) throws FlooringPersistenceException {
        if (stubTestTax.getState().equals(state)) {
            return stubTestTax;
        } else {
           return null;
        }
    }

    @Override
    public List<Tax> retrieveAllTaxes() throws FlooringPersistenceException {
        return taxesList;
    }

    @Override
    public void createTax(Tax taxObject) throws FlooringPersistenceException {

    }

    @Override
    public void updateTax(Tax taxObject) throws FlooringPersistenceException {

    }

    @Override
    public void removeTax(Tax taxObject) throws FlooringPersistenceException {

    }
}
