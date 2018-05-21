package functionaltestpractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MischeviousChildrenTest {

    public MischeviousChildren children = new MischeviousChildren();

    public void MischeviousChildrenTest() {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAreWeInTroubleTrue() throws Exception {
        assertTrue(children.areWeInTrouble(true, true));
    }

    @Test
    public void testAreWeInTroubleTrue2() throws Exception {
        assertTrue(children.areWeInTrouble(false, false));
    }

    @Test
    public void testAreWeInTroubleFalse() throws Exception {
        assertFalse(children.areWeInTrouble(true, false));
    }
}