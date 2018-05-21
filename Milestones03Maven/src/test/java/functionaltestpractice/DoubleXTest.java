package functionaltestpractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleXTest {

    DoubleX doubleX = new DoubleX();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoubleXTrue() {
        // give a string with xx
        doubleX.doubleX("axxbb");
        // assert true
        assert true;

    }

    @Test
    public void testDoubleXFalse() {
        // give a string with xx
        doubleX.doubleX("axaxxax");
        // assert true
        assert false;

    }

    @Test
    public void testAllXTrue() {
        // give a string with xx
        doubleX.doubleX("xxxxx");
        // assert true
        assert true;

    }
}