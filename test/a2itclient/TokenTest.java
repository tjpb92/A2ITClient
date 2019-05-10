/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2itclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Thierry Baribaud
 */
public class TokenTest {
    
    public TokenTest() {
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
     * Test of getAccess_token method, of class Token.
     */
    @Test
    public void testGetAccess_token() {
        System.out.println("getAccess_token");
        Token instance = new Token();
        String expResult = "";
        String result = instance.getAccess_token();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccess_token method, of class Token.
     */
    @Test
    public void testSetAccess_token() {
        System.out.println("setAccess_token");
        String access_token = "";
        Token instance = new Token();
        instance.setAccess_token(access_token);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToken_type method, of class Token.
     */
    @Test
    public void testGetToken_type() {
        System.out.println("getToken_type");
        Token instance = new Token();
        String expResult = "";
        String result = instance.getToken_type();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToken_type method, of class Token.
     */
    @Test
    public void testSetToken_type() {
        System.out.println("setToken_type");
        String token_type = "";
        Token instance = new Token();
        instance.setToken_type(token_type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExpires_in method, of class Token.
     */
    @Test
    public void testGetExpires_in() {
        System.out.println("getExpires_in");
        Token instance = new Token();
        int expResult = 0;
        int result = instance.getExpires_in();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExpires_in method, of class Token.
     */
    @Test
    public void testSetExpires_in() {
        System.out.println("setExpires_in");
        int expires_in = 0;
        Token instance = new Token();
        instance.setExpires_in(expires_in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Token.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Token instance = new Token();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
