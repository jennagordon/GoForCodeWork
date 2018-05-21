package functionaltestpractice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbbaTest {

    public Abba abba = new Abba();

    public void AbbaTest() {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAbbaHiBye() {
        String expectedResult = "HiByeByeHi";
        String actualResult = abba.abba("Hi", "Bye");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAbbaYoAlice() {
        String expectedResult = "YoAliceAliceYo";
        String actualResult = abba.abba("Yo", "Alice");

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAbbaWhatUp() {
        String expectedResult = "WhatUpUpWhat";
        String actualResult = abba.abba("What", "Up");

        assertEquals(expectedResult, actualResult);
    }
}