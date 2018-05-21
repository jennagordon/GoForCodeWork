package functionaltestpractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanHazTableTest {

    public CanHazTable table = new CanHazTable();

    public CanHazTableTest () {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCanHazTable5And10Expect2() {
        int expectedResult = 2;
        int actualResult = table.canHazTable(5, 10);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCanHazTable5And5Expect1() {
        int expectedResult = 1;
        int actualResult = table.canHazTable(5, 5);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCanHazTable5And2Expect0() {
        int expectedResult = 0;
        int actualResult = table.canHazTable(5, 2);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCanHazTable9And1BoundExpect0() {
        int expectedResult = 0;
        int actualResult = table.canHazTable(9, 1);
        assertEquals(expectedResult, actualResult);
    }

}