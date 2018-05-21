package functionaltestpractice;

import static org.junit.Assert.*;

public class GreatPartyTest {

    GreatParty party = new GreatParty();

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void test30False() {
        assertFalse(party.greatParty(30, false));
    }

    @org.junit.Test
    public void test50False() {
        assertTrue(party.greatParty(50, true));
    }

    @org.junit.Test
    public void test70True() {
        assertTrue(party.greatParty(70, true));
    }

    @org.junit.Test
    public void test39True() {
        assertFalse(party.greatParty(39, false));
    }

    @org.junit.Test
    public void test39False() {
        assertFalse(party.greatParty(39, false));
    }

    @org.junit.Test
    public void test40True() {
        assertTrue(party.greatParty(40, true));
    }

    @org.junit.Test
    public void test40False() {
        assertTrue(party.greatParty(40, false));
    }

    @org.junit.Test
    public void test60True() {
        assertTrue(party.greatParty(60, true));
    }

    @org.junit.Test
    public void test60False() {
        assertTrue(party.greatParty(60, false));
    }

    @org.junit.Test
    public void test61True() {
        assertTrue(party.greatParty(61, true));
    }

    @org.junit.Test
    public void test61False() {
        assertFalse(party.greatParty(61, false));
    }


}