/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomial;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author J.Vedder S4397101
 */
public class PolynomialTest {
    
    public PolynomialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Polynomial.
     * Tests if the string output by the polynomial is as expected.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Polynomial instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        
        String expResult = "6.000000x + 5.000000x^2 + 4.000000x^3 + 3.000000x^4 + 2.000000x^5 + 1.000000x^6";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of plus method, of class Polynomial.
     * Will test the Polynomial p.plus(Polynomial y) by executing the method 
     * and checking if results are as expected.
     */
    @Test
    public void testPlus() {
        System.out.println("plus");
        Polynomial b = new Polynomial("1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8");
        Polynomial instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial expResult = new Polynomial("7 1 7 2 7 3 7 4 7 5 7 6 7 7 8 8");
        instance.plus(b);
        
        assertEquals(expResult, instance);
    }

    /**
     * Test of minus method, of class Polynomial.
     * Will test the Polynomial p.minus(Polynomial y) by executing the method 
     * and checking if results are as expected.
     */
    @Test
    public void testMinus() {
        System.out.println("minus");
        
        //Testing normal substraction
        Polynomial b = new Polynomial("5 1 4 2 3 3 2 4 1 5");
        Polynomial instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial expResult = new Polynomial("1 1 1 2 1 3 1 4 1 5 1 6");
        instance.minus(b);
        assertEquals(expResult, instance);
        
        //Testing substraction where term disapears
        b = new Polynomial("6 1 4 2 3 3 2 4 1 5");
        instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        expResult = new Polynomial("1 2 1 3 1 4 1 5 1 6");
        instance.minus(b);
        assertEquals(expResult, instance);
    }

    /**
     * Test of times method, of class Polynomial.
     */
    @Test
    public void testTimes() {
        //Testing normal multiplication
        System.out.println("times");
        Polynomial b = new Polynomial("5 1 4 2");
        Polynomial instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial expResult = new Polynomial("30 2 49 3 40 4 31 5 22 6 13 7 4 8");
        instance.times(b);
        assertEquals(expResult, instance);
        
        //Testing multiplication wich results in zero
        b = new Polynomial("");
        instance = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        instance.times(b);
        expResult = new Polynomial("");
        assertEquals(expResult, instance);
    }

    /**
     * Test of equals method, of class Polynomial.
     * Will test equality versus null, unequal and equal.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other_poly1 = null;
        Object other_poly2 = new Polynomial("3 4 2 5 4 6"); 
        Object other_poly3 = new Polynomial("2 3 2 4 2 5");
        Polynomial instance = new Polynomial("2 3 2 4 2 5");
        boolean expResult = false;
        
        boolean result = instance.equals(other_poly1);
        assertEquals(expResult, result);
        
        result = instance.equals(other_poly2);
        assertEquals(expResult, result);

        expResult = true;
        result = instance.equals(other_poly3);
        assertEquals(expResult, result);
    }
    
    /**
     * Testing combinations of operators that should result in the same outcome.
     */
    @Test
    public void testMultipleOperators(){
        //Subestraction via negation and addition
        System.out.println("multipleOperators");
        Polynomial instancePos = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial instanceNeg = new Polynomial(instancePos);
        Polynomial pos = new Polynomial("3 2");
        Polynomial neg = new Polynomial(pos);
        neg.times(new Polynomial("-1 0"));
        instancePos.minus(pos);
        instanceNeg.plus(neg);
        
        Polynomial expResult = new Polynomial("6 1 2 2 4 3 3 4 2 5 1 6");
        assertEquals(expResult, instancePos);
        assertEquals(expResult, instanceNeg);
        assertEquals(instancePos, instanceNeg);
    }
    
    /**
     * Testing if commutativity holds.
     */
    @Test
    public void testCommutative(){
        Polynomial a = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial b = new Polynomial("1 1 2 2 3 3 4 4 5 5 6 6");
        
        //a + b must equal b + a
        Polynomial aPlusB = new Polynomial(a);
        Polynomial bPlusA = new Polynomial(b);
        aPlusB.plus(b);
        bPlusA.plus(a);
        assertEquals(aPlusB, bPlusA);
        
        //a * b must equal b * a
        Polynomial aTimesB = new Polynomial(a);
        Polynomial bTimesA = new Polynomial(b);
        aTimesB.times(b);
        bTimesA.times(a);
        assertEquals(aTimesB, bTimesA);
    }
    
    /**
     * Testing if associativity holds.
     */
    @Test
    public void testAssociative(){
        Polynomial a = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial b = new Polynomial("1 1 2 2 3 3 4 4 5 5 6 6");
        Polynomial c = new Polynomial("3 1 2 2 1 3 3 4 4 5 5 6");
        
        //(a + b) + c must equal a + (b + c)
        Polynomial abPlusC = new Polynomial(a);
        Polynomial aPlusBC = new Polynomial(b);
        abPlusC.plus(b);
        abPlusC.plus(c);
        aPlusBC.plus(c);
        aPlusBC.plus(a);
        assertEquals(abPlusC, aPlusBC);
        
        //(a * b) * c must equal a * (b * c)
        Polynomial abTimesC = new Polynomial(a);
        Polynomial aTimesBC = new Polynomial(b);
        abTimesC.times(b);
        abTimesC.times(c);
        aTimesBC.times(c);
        aTimesBC.times(a);
        assertEquals(abTimesC, aTimesBC);
    }
    
    /**
     * Testing if Distributivity holds.
     */
    @Test
    public void testDistributive(){
        Polynomial a = new Polynomial("6 1 5 2 4 3 3 4 2 5 1 6");
        Polynomial b = new Polynomial("1 1 2 2 3 3 4 4 5 5 6 6");
        Polynomial c = new Polynomial("3 1 2 2 1 3 3 4 4 5 5 6");
        
        Polynomial aTimesBplusC = new Polynomial(b);
        aTimesBplusC.plus(c);
        aTimesBplusC.times(a);
        
        Polynomial aTimesB = new Polynomial(a);
        Polynomial aTimesC = new Polynomial(a);
        aTimesB.times(b);
        aTimesC.times(c);
        Polynomial aTimesBPlusAtimesC = new Polynomial(aTimesB);
        aTimesBPlusAtimesC.plus(aTimesC);
        
        //(a * (b + c) must equal a * b + a * c
        assertEquals(aTimesBplusC, aTimesBPlusAtimesC);
    }
}
