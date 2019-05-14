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
 * @version 1.09
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({AssetTest.class, ContractTest.class, DurationTest.class, MessageTest.class, ThirdPartyTest.class,
    TokenTest.class, SLATest.class, A2ITClient.class, HttpsClientTest.class,
    HALLinksTest.class, RolesTest.class, RoleTest.class, UserTest.class, HALLinkTest.class, TokenTest.class, UsersTest.class, EntityTest.class})
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
