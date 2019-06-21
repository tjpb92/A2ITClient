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
import ticketEvents.TicketOpened;
import ticketEvents.TicketUpdated;

/**
 * Jeux de tests pour la classe TicketUpdated
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class TicketUpdatedTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private TicketUpdated ticketUpdated;

    public TicketUpdatedTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * TicketUpdated.
     */
    @Test
    public void testTicketUpdatedJsonSerialization() {
        Object expTicketUpdated;

        System.out.println("TicketUpdatedJsonSerialization");
        expTicketUpdated = null;
        try {
            expTicketUpdated = objectMapper.readValue(new File("TicketUpdated.json"), TicketUpdated.class);
            System.out.println(expTicketUpdated);
        } catch (IOException ex) {
            Logger.getLogger(TicketUpdatedTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expTicketUpdated);
    }
}
