package a2itclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.ApplicationProperties;

/**
 * Jeux de test pour la classe MailServer
 * @author Thierry Baribaud
 * @version 1.20
 */
public class MailServerTest {
    
    private MailServer mailServer;
    
    public MailServerTest() {
        try {
            mailServer = new MailServer(new ApplicationProperties("A2ITClient_Example.prop"));
        } catch (IOException exception) {
            Logger.getLogger(MailServerTest.class.getName()).log(Level.SEVERE, null, exception);
        } catch (MailServer.MailServerException exception) {
            Logger.getLogger(MailServerTest.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getName method, of class MailServer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "name";
        String result = mailServer.getName();

        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class MailServer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String expResult = "myServer";
        mailServer.setName(expResult);
        String result = mailServer.getName();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getIpAddress method, of class MailServer.
     */
    @Test
    public void testGetIpAddress() {
        System.out.println("getIpAddress");
        String expResult = "5.6.7.8";
        String result = mailServer.getIpAddress();

        assertEquals(expResult, result);
    }

    /**
     * Test of setIpAddress method, of class MailServer.
     */
    @Test
    public void testSetIpAddress() {
        System.out.println("setIpAddress");
        String expResult = "1.2.3.4";
        mailServer.setIpAddress(expResult);
        String result = mailServer.getIpAddress();

        assertEquals(expResult, result);
    }

    /**
     * Test of getFromAddress method, of class MailServer.
     */
    @Test
    public void testGetFromAddress() {
        System.out.println("getFromAddress");
        String expResult = "from@mail.com";
        String result = mailServer.getFromAddress();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of setFromAddress method, of class MailServer.
     */
    @Test
    public void testSetFromAddress() {
        System.out.println("setFromAddress");
        String expResult = "from@myMail.com";
        mailServer.setFromAddress(expResult);
        String result = mailServer.getFromAddress();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getToAddress method, of class MailServer.
     */
    @Test
    public void testGetToAddress() {
        System.out.println("getToAddress");
        String expResult = "to@mail.com";
        String result = mailServer.getToAddress();

        assertEquals(expResult, result);
    }

    /**
     * Test of setToAddress method, of class MailServer.
     */
    @Test
    public void testSetToAddress() {
        System.out.println("setToAddress");
        String expResult = "to@myMail.com";
        mailServer.setToAddress(expResult);
        String result = mailServer.getToAddress();

        assertEquals(expResult, result);
    }

    /**
     * Test of getUser method, of class MailServer.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String expResult = "user";
        String result = mailServer.getUser();

        assertEquals(expResult, result);
    }

    /**
     * Test of setUser method, of class MailServer.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String expResult = "myUser";
        mailServer.setUser(expResult);
        String result = mailServer.getUser();

        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class MailServer.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "password";
        String result = mailServer.getPassword();

        assertEquals(expResult, result);
    }

    /**
     * Test of setPassword method, of class MailServer.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String expResult = "myPassword";
        mailServer.setPassword(expResult);
        String result = mailServer.getPassword();

        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class MailServer.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "MailServer:{" +
                "name:name" +
                ", ipAddress:5.6.7.8" +
                ", fromAddress:from@mail.com" +
                ", toAddress:to@mail.com" +
                ", user:user" +
                ", password:password" +
                "}";
        String result = mailServer.toString();
//        System.out.println(result);

        assertEquals(expResult, result);
    }
    
}
