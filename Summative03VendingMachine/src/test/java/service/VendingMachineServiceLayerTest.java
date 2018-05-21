package service;

import dao.*;
import dto.Change;
import dto.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VendingMachineServiceLayerTest {
    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
//        VendingMachineDao dao = new VendingMachineDaoStubImpl();
//        VendingMachineDaoAudit auditDao = new VendingMachineDaoAuditFileImpl();
//        service = new VendingMachineServiceLayerImpl(dao, auditDao);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VendingMachineServiceLayer.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveAllItems() throws VendingMachinePersistenceException {
        // stubbed out version only has one item so
        // we are testing that there is only one item
        assertEquals(1, service.retrieveAllItems().size());
    }

    @Test
    public void testAddMoneyToMachine() {
        service.addMoneyToMachine(BigDecimal.valueOf(20));
       assertEquals(BigDecimal.valueOf(20), service.retrieveMoneyInMachine());

    }

    @Test
    public void retrieveMoneyInMachine()  {
        // test that user can give money
        // and it saves in the variable
        service.retrieveMoneyInMachine();

    }

    @Test
    public void purchaseItem() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineItemNotAvailableException {
        // instantiate item
        // add item to our list
        List<Item> itemList = new ArrayList<>();
        Item onlyItem = new Item("A1");
        onlyItem.setName("Chips");
        onlyItem.setPrice(BigDecimal.valueOf(1.00));
        onlyItem.setQuantity(3);
        itemList.add(onlyItem);

        // give money to machine
        service.addMoneyToMachine(BigDecimal.valueOf(20));

        assertEquals((onlyItem.getQuantity())-1 , (service.purchaseItem("A1").getQuantity()));
    }

    @Test
    public void testPurchaseItemInvalidItem() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException {
        // give money to machine
        service.addMoneyToMachine(BigDecimal.valueOf(20));

        // trying to test the item is not in the machine
        try {
            service.purchaseItem("23");
            fail("Expected VendingMachineItemNotAvailableException was not thrown");

        } catch (VendingMachineItemNotAvailableException e){
            return;
        }


    }

    @Test
    public void testPurchaseItemInsufficientFunds() throws VendingMachinePersistenceException, VendingMachineItemNotAvailableException {
        // instantiate item
        // add item to our list

        // give money to machine
        service.addMoneyToMachine(BigDecimal.valueOf(0));
        try {
            // trying to catch the insufficient funds exception
            service.purchaseItem("A1");
            fail("Expected VendingMachineInsufficientFundsException was not thrown");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }


    }

    @Test
    public void calculateChange() {
        Change testChange = new Change();
        testChange.setNumQuarters(4);
        testChange.setNumDimes(1);
        testChange.setNumNickles(0);
        testChange.setNumPennies(1);

        // give money to test the method
        service.addMoneyToMachine(BigDecimal.valueOf(1.11));

        Change changeInMethod = service.calculateChange();

        //check change
        assertEquals(testChange.getNumQuarters(), changeInMethod.getNumQuarters());
        assertEquals(testChange.getNumDimes(), changeInMethod.getNumDimes());
        assertEquals(testChange.getNumNickles(), changeInMethod.getNumNickles());
        assertEquals(testChange.getNumPennies(), changeInMethod.getNumPennies());

    }
}