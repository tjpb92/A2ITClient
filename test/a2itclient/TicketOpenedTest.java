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

/**
 * Jeux de tests pour la classe TicketOpened
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class TicketOpenedTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private TicketOpened ticketOpened;

    public TicketOpenedTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * TicketOpened.
     */
    @Test
    public void testTicketOpenedJsonSerialization() {
        Object expTicketOpened;

        System.out.println("TicketOpenedJsonSerialization");
        expTicketOpened = null;
        try {
            expTicketOpened = objectMapper.readValue(new File("TicketOpened.json"), TicketOpened.class);
            System.out.println(expTicketOpened);
        } catch (IOException ex) {
            Logger.getLogger(TicketOpenedTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expTicketOpened);
    }
}
