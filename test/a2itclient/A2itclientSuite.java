package a2itclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import ticketCommands.UpdateTicket;
import ticketEvents.TicketUpdated;

/**
 * Jeux de tests pour tester toute les classes du projet
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({APIRESTTest.class, AssetTest.class, 
    CallPurposeTest.class,
    ContactListTest.class, ContactTest.class, Contract2Test.class,
    Contract2Test.class, DurationTest.class, EntityTest.class, HALLinkTest.class,
    HALLinksTest.class, HttpsClientTest.class, MailServerTest.class, 
    MessageTest.class, OpenTicketTest.class, 
    RoleTest.class, RolesTest.class, SLATest.class,
    ThirdPartyTest.class, TokenTest.class,
    UpdateTicket.class, UserTest.class, 
    TicketOpenedTest.class, TicketUpdated.class, TokenTest.class, UsersTest.class})
public class A2itclientSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
