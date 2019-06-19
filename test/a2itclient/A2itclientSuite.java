package a2itclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Jeux de tests pour tester toute les classes du projet
 *
 * @author Thierry Baribaud
 * @version 1.28
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({A2ITClient.class, APIRESTTest.class, AssetTest.class, 
    CallPurposeTest.class,
    ContactListTest.class, ContactTest.class, Contract2Test.class,
    Contract2Test.class, DurationTest.class, EntityTest.class, HALLinkTest.class,
    HALLinksTest.class, HttpsClientTest.class, MailServerTest.class, 
    MessageTest.class, RoleTest.class, RolesTest.class, SLATest.class,
    ThirdPartyTest.class, TokenTest.class,
    UserTest.class, TokenTest.class, UsersTest.class})
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
