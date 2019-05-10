package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de test pour la classe Message
 * @author Thierry Baribaud
 * @version 1.04
 */
public class MessageTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public MessageTest() {
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

//    /**
//     * Test of getMessage method, of class Message.
//     */
//    @Test
//    public void testGetMessage() {
//        System.out.println("getMessage");
//        Message instance = new Message();
//        String expResult = "";
//        String result = instance.getMessage();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMessage method, of class Message.
//     */
//    @Test
//    public void testSetMessage() {
//        System.out.println("setMessage");
//        String message = "";
//        Message instance = new Message();
//        instance.setMessage(message);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCode method, of class Message.
//     */
//    @Test
//    public void testGetCode() {
//        System.out.println("getCode");
//        Message instance = new Message();
//        String expResult = "";
//        String result = instance.getCode();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCode method, of class Message.
//     */
//    @Test
//    public void testSetCode() {
//        System.out.println("setCode");
//        String code = "";
//        Message instance = new Message();
//        instance.setCode(code);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class Message.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        Message instance = new Message();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of serialization to and from a file in Json format, of class Message.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Message.jsonSerialization");
        String fileName = "NotFound.json";

        Message message = new Message();
        
        try {
            message = objectMapper.readValue(new File(fileName), Message.class);
            System.out.println(message);
            objectMapper.writeValue(new File("testMessage.json"), message);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(message);
    }
    
}
