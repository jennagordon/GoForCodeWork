package functionaltestpractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTimesTest {

    public StringTimes sTimes = new StringTimes();

    public StringTimesTest () {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringTimes1() throws Exception {
        // Arrange
        String expectedResult = "Hi";

        String actualResult = sTimes.stringTimes("Hi", 1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testStringTimes2() throws Exception {
        String expectedResult = "HiHi";

        String actualResult = sTimes.stringTimes("Hi", 2);

        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void testStringTimes3() throws Exception {
        String expectedResult = "HiHiHi";

        String actualResult = sTimes.stringTimes("Hi", 3);

        assertEquals(expectedResult, actualResult);
    }
}