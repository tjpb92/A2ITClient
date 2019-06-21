package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ticketCommands.OpenTicket;
import ticketEvents.TicketOpened;

/**
 * Jeux de tests pour la classe OpenTicket
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class OpenTicketTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private OpenTicket openTicket;

    public OpenTicketTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * OpenTicket.
     */
    @Test
    public void testOpenTicketJsonSerialization() {
        Object expOpenTicket;
        TicketOpened ticketOpened;
        CallPurpose callPurpose;
        Contract2 currentContract;

        System.out.println("OpenTicketJsonSerialization");
        expOpenTicket = null;
        try {
            ticketOpened = objectMapper.readValue(new File("TicketOpened.json"), TicketOpened.class);
            System.out.println(ticketOpened);
            
            callPurpose = objectMapper.readValue(new File("CallPurpose.json"), CallPurpose.class);
            System.out.println(callPurpose);
            
            currentContract = objectMapper.readValue(new File("Contract2.json"), Contract2.class);
            System.out.println(currentContract);
            
            openTicket = new OpenTicket(ticketOpened, callPurpose, currentContract);
            System.out.println(openTicket);
            
            objectMapper.writeValue(new File("testOpenTicket.json"), openTicket);
            
            expOpenTicket = objectMapper.readValue(new File("testOpenTicket.json"), OpenTicket.class);
            System.out.println(expOpenTicket);
        } catch (IOException ex) {
            Logger.getLogger(OpenTicketTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expOpenTicket);
    }
}
