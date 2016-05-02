/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chuon
 */
public class SquareMatrixTest {
    
    public SquareMatrixTest() {
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
     * Test of setN method, of class SquareMatrix.
     */
    @Test
    public void testSetN() {
        System.out.println("setN");
        int n = 0;
        SquareMatrix instance = null;
        int expResult = 0;
        int result = instance.setN(n);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getN method, of class SquareMatrix.
     */
    @Test
    public void testGetN() {
        System.out.println("getN");
        SquareMatrix instance = null;
        int expResult = 0;
        int result = instance.getN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of determinant method, of class SquareMatrix.
     */
    @Test
    public void testDeterminant() {
        System.out.println("determinant");
        SquareMatrix instance = null;
        double expResult = 0.0;
        double result = instance.determinant();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cut method, of class SquareMatrix.
     */
    @Test
    public void testCut() {
        System.out.println("cut");
        int x = 0;
        int y = 0;
        SquareMatrix instance = null;
        SquareMatrix expResult = null;
        SquareMatrix result = instance.cut(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInvertible method, of class SquareMatrix.
     */
    @Test
    public void testIsInvertible() {
        System.out.println("isInvertible");
        SquareMatrix instance = null;
        boolean expResult = false;
        boolean result = instance.isInvertible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of invert method, of class SquareMatrix.
     */
    @Test
    public void testInvert() throws Exception {
        System.out.println("invert");
        SquareMatrix instance = null;
        Matrix expResult = null;
        Matrix result = instance.invert();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of adjugate method, of class SquareMatrix.
     */
    @Test
    public void testAdjugate() {
        System.out.println("adjugate");
        SquareMatrix instance = null;
        SquareMatrix expResult = null;
        SquareMatrix result = instance.adjugate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inputMatrix method, of class SquareMatrix.
     */
    @Test
    public void testInputMatrix() {
        System.out.println("inputMatrix");
        SquareMatrix expResult = null;
        SquareMatrix result = SquareMatrix.inputMatrix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
