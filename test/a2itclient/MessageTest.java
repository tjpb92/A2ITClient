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
 * @version 1.30
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

    /**
     * Test of serialization to and from a file in Json format, of class Message.
     */
    @Test
    public void testMessageJsonSerialization() {
        System.out.println("MessageJsonSerialization");
        String fileName = "NotFound.json";

        Message message = new Message();
        Message expMessage = new Message();
        
        try {
            message = objectMapper.readValue(new File(fileName), Message.class);
            System.out.println(message);
            objectMapper.writeValue(new File("testMessage.json"), message);
            expMessage = objectMapper.readValue(new File("testMessage.json"), Message.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(message);
        assertNotNull(expMessage);
        assertEquals(message.toString(), expMessage.toString());
    }
    
}
