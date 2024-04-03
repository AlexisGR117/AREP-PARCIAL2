package edu.eci.arep;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

public class MathServicesTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MathServicesTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(MathServicesTest.class);
    }

    public void testPrimesPositiveValue() {
        ArrayList<Integer> expectedPrimes = new ArrayList<>(Arrays.asList(2, 3, 5, 7));
        assertEquals(expectedPrimes, MathServices.primes(10));
    }

    public void testPrimesZeroValue() {
        assertNull(MathServices.primes(0));
    }

    public void testPrimesNegativeValue() {
        assertNull(MathServices.primes(-5));
    }

    public void testFactorsPositiveValue() {
        ArrayList<Integer> expectedFactors = new ArrayList<>(Arrays.asList(1, 2, 4));
        assertEquals(expectedFactors, MathServices.factors(4));
    }

    public void testFactorsZeroValue() {
        assertNull(MathServices.factors(0));
    }

    public void testFactorsNegativeValue() {
        assertNull(MathServices.factors(-3));
    }


    public void testArrayListToStringSingleElement() {
        ArrayList<Integer> singleElementList = new ArrayList<>(Arrays.asList(10));
        assertEquals("10", MathServices.arrayListToString(singleElementList));
    }

    public void testArrayListToStringMultipleElements() {
        ArrayList<Integer> multipleElementsList = new ArrayList<>(Arrays.asList(2, 3, 5));
        assertEquals("2, 3, 5", MathServices.arrayListToString(multipleElementsList));
    }

    public void testJsonResponseHappyPath() {
        String expectedResponse = "{" +
                " \"operation\": \"factors\"," +
                " \"input\": 5," +
                " \"output\":  \"1, 5\"" +
                "}";
        assertEquals(expectedResponse, MathServices.jsonResponse("factors", 5, "1, 5"));
    }

    public void testJsonResponseEmptyOperation() {
        String expectedResponse = "{" +
                " \"operation\": \"\"," +
                " \"input\": 0," +
                " \"output\":  \"\"" +
                "}";
        assertEquals(expectedResponse, MathServices.jsonResponse("", 0, ""));
    }

    public void testJsonResponseNullOutput() {
        String expectedResponse = "{" +
                " \"operation\": \"cualquieroperacion\"," +
                " \"input\": 10," +
                " \"output\":  \"null\"" +
                "}";
        assertEquals(expectedResponse, MathServices.jsonResponse("cualquieroperacion", 10, null));
    }
}
