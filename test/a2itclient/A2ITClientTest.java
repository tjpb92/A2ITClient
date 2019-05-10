/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2itclient;

import bkgpi2a.Identifiants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import utils.ApplicationProperties;

/**
 *
 * @author Thierry Baribaud
 */
public class A2ITClientTest {
    
    public A2ITClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of setDebugMode method, of class A2ITClient.
     */
    @Test
    public void testSetDebugMode() {
        System.out.println("setDebugMode");
        boolean debugMode = false;
        A2ITClient instance = null;
        instance.setDebugMode(debugMode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTestMode method, of class A2ITClient.
     */
    @Test
    public void testSetTestMode() {
        System.out.println("setTestMode");
        boolean testMode = false;
        A2ITClient instance = null;
        instance.setTestMode(testMode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebugMode method, of class A2ITClient.
     */
    @Test
    public void testGetDebugMode() {
        System.out.println("getDebugMode");
        A2ITClient instance = null;
        boolean expResult = false;
        boolean result = instance.getDebugMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestMode method, of class A2ITClient.
     */
    @Test
    public void testGetTestMode() {
        System.out.println("getTestMode");
        A2ITClient instance = null;
        boolean expResult = false;
        boolean result = instance.getTestMode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWebId method, of class A2ITClient.
     */
    @Test
    public void testGetWebId() {
        System.out.println("getWebId");
        A2ITClient instance = null;
        Identifiants expResult = null;
        Identifiants result = instance.getWebId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWebId method, of class A2ITClient.
     */
    @Test
    public void testSetWebId_Identifiants() {
        System.out.println("setWebId");
        Identifiants webId = null;
        A2ITClient instance = null;
        instance.setWebId(webId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWebId method, of class A2ITClient.
     */
    @Test
    public void testSetWebId_ApplicationProperties() throws Exception {
        System.out.println("setWebId");
        ApplicationProperties applicationProperties = null;
        A2ITClient instance = null;
        instance.setWebId(applicationProperties);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDbId method, of class A2ITClient.
     */
    @Test
    public void testGetDbId() {
        System.out.println("getDbId");
        A2ITClient instance = null;
        Identifiants expResult = null;
        Identifiants result = instance.getDbId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDbId method, of class A2ITClient.
     */
    @Test
    public void testSetDbId_Identifiants() {
        System.out.println("setDbId");
        Identifiants dbId = null;
        A2ITClient instance = null;
        instance.setDbId(dbId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDbId method, of class A2ITClient.
     */
    @Test
    public void testSetDbId_ApplicationProperties() throws Exception {
        System.out.println("setDbId");
        ApplicationProperties applicationProperties = null;
        A2ITClient instance = null;
        instance.setDbId(applicationProperties);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class A2ITClient.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        A2ITClient.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usage method, of class A2ITClient.
     */
    @Test
    public void testUsage() {
        System.out.println("usage");
        A2ITClient.usage();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class A2ITClient.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        A2ITClient instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
