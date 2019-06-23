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
import ticketCommands.UpdateTicket;
import ticketEvents.TicketUpdated;

/**
 * Jeux de tests pour la classe UpdateTicket
 *
 * @author Thierry Baribaud
 * @version 1.32
 */
public class UpdateTicketTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UpdateTicket updateTicket;

    public UpdateTicketTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * UpdateTicket.
     */
    @Test
    public void testOpenTicketJsonSerialization() {
        Object expOpenTicket;
        TicketUpdated ticketUpdated;
        CallPurpose callPurpose;
        Contract2 currentContract;

        System.out.println("OpenTicketJsonSerialization");
        expOpenTicket = null;
        try {
            ticketUpdated = objectMapper.readValue(new File("TicketUpdated.json"), TicketUpdated.class);
            System.out.println(ticketUpdated);
            
            callPurpose = objectMapper.readValue(new File("CallPurpose.json"), CallPurpose.class);
            System.out.println(callPurpose);
            
            currentContract = objectMapper.readValue(new File("Contract2.json"), Contract2.class);
            System.out.println(currentContract);
            
            updateTicket = new UpdateTicket(ticketUpdated, callPurpose, currentContract);
            System.out.println(updateTicket);
            
            objectMapper.writeValue(new File("testUpdateTicket.json"), updateTicket);
            
            expOpenTicket = objectMapper.readValue(new File("testUpdateTicket.json"), UpdateTicket.class);
            System.out.println(expOpenTicket);
        } catch (IOException ex) {
            Logger.getLogger(UpdateTicketTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expOpenTicket);
    }
}
