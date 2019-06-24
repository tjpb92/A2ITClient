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
import ticketCommands.TicketCommand;
import ticketEvents.TicketOpened;

/**
 * Jeux de tests pour la classe OpenTicket
 *
 * @author Thierry Baribaud
 * @version 1.33
 */
public class OpenTicketTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static TicketOpened ticketOpened;
    private static OpenTicket openTicket;
    private static Contract2 currentContract;
    private static CallPurpose callPurpose;
    
    public OpenTicketTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        try {
            ticketOpened = objectMapper.readValue(new File("TicketOpened.json"), TicketOpened.class);
            System.out.println(ticketOpened);
            callPurpose = objectMapper.readValue(new File("CallPurpose.json"), CallPurpose.class);
            System.out.println(callPurpose);
            
            currentContract = objectMapper.readValue(new File("Contract2.json"), Contract2.class);
            System.out.println(currentContract);
            
            openTicket = new OpenTicket(ticketOpened, callPurpose, currentContract);
            System.out.println(openTicket);
        } catch (IOException ex) {
            Logger.getLogger(OpenTicketTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test de la conservation du numéro de ticket
     */
    @Test
    public void testTicketReference() {
        System.out.println("testTicketReference : conservation du numéro de ticket");
        assertEquals(ticketOpened.getTicketInfos().getClaimNumber().getCallCenterClaimNumber(), openTicket.getReference());
    }

    /**
     * Test de la conservation de la description de la demande
     */
    @Test
    public void testTicketDescription() {
        System.out.println("testTicketDescription : conservation de la description de la demande");
        assertEquals(ticketOpened.getTicketInfos().getRequest(), openTicket.getDescription());
    }

    /**
     * Test de la conservation de la référence du contrat
     */
    @Test
    public void testTicketContractReference() {
        System.out.println("testTicketContractReference : conservation de la référence du contrat");
        assertEquals(currentContract.getReference(), openTicket.getContractReference());
    }

    /**
     * Test de la validité du status du ticket
     */
    @Test
    public void testTicketStatus() {
        System.out.println("testTicketStatus : validité du status du ticket");
        assertEquals(openTicket.getStatus(),"open");
    }

    /**
     * Test de la validité de l'événement courant du ticket
     */
    @Test
    public void testTicketEvent() {
        System.out.println("testTicketEvent : validité de l'événement courant du ticket");
        assertEquals(openTicket.getEvent(),"requested");
    }

    /**
     * Test de la conservation de la date de l'événement courant
     */
    @Test
    public void testTicketEventDate() {
        System.out.println("testTicketEventDate : conservation de la date de l'événement courant");
        assertEquals(ticketOpened.getDate(), openTicket.getEventDate());
    }

    /**
     * Test de la conservation de la date de l'envoi de l'événement courant
     */
    @Test
    public void testTicketLogDate() {
        System.out.println("testTicketLogDate : conservation de la date de l'envoi de l'événement courant");
        assertEquals(ticketOpened.getOpenedDate(), openTicket.getLogDate());
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
        TicketInfos ticketInfos;
        TicketCommand ticketCommand;

        System.out.println("OpenTicketJsonSerialization");
        expOpenTicket = null;
        try {
//            ticketOpened = objectMapper.readValue(new File("TicketOpened.json"), TicketOpened.class);
//            System.out.println(ticketOpened);
//            ticketInfos = ticketOpened.getTicketInfos();
//            
//            callPurpose = objectMapper.readValue(new File("CallPurpose.json"), CallPurpose.class);
//            System.out.println(callPurpose);
//            
//            currentContract = objectMapper.readValue(new File("Contract2.json"), Contract2.class);
//            System.out.println(currentContract);
//            
//            openTicket = new OpenTicket(ticketOpened, callPurpose, currentContract);
//            System.out.println(openTicket);

//            assertEquals(ticketInfos.getClaimNumber().getCallCenterClaimNumber(), openTicket.getReference());
            assertEquals(openTicket.getOrigin(),"other");
            assertEquals(openTicket.getWorkType(),"corrective");
            
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
