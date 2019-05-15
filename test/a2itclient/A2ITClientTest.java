package a2itclient;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import utils.DBServerException;

/**
 * Jeux de test pour tester la classe A2ITClient.
 *
 * @author Thierry Baribaud
 * @version 1.10
 */
public class A2ITClientTest {

    public A2ITClientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied() {
        String test = null;
        test.length();
    }

    /**
     * Test of bad API server type.
     */
    @Test
    public void testBadAPIServerType() {
        String[] args = {"-apiserver", "badapi"};

        System.out.println("A2ITClient.badAPIServerType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Mauvais serveur API"));
        }
    }

    /**
     * Test of undefined API server type.
     */
    @Test
    public void testUndefinedAPIServerType() {
        String[] args = {"-apiserver"};

        System.out.println("A2ITClient.undefinedAPIServerType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Serveur API non défini"));
        }
    }

    /**
     * Test of bad Informix server type.
     */
    @Test
    public void testBadIfxServerType() {
        String[] args = {"-ifxserver", "badifx"};

        System.out.println("A2ITClient.badIfxServerType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Mauvais serveur Informix"));
        }
    }

    /**
     * Test of undefined Informix server type.
     */
    @Test
    public void testUndefinedIfxServerType() {
        String[] args = {"-ifxserver"};

        System.out.println("A2ITClient.undefinedIfxServerType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Serveur Informix non défini"));
        }
    }

    /**
     * Test of bad Mongo server type.
     */
    @Test
    public void testBadMgoServerType() {
        String[] args = {"-mgoserver", "badmgo"};

        System.out.println("A2ITClient.badMgoServerType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Mauvais serveur Mongo"));
        }
    }

    /**
     * Test of undefined Mongo server type.
     */
    @Test
    public void testUndefinedMgoServerType() {
        String[] args = {"-mgoserver"};

        System.out.println("A2ITClient.undefinedMgoerverType");

        A2ITClient instance = null;
        try {
            instance = new A2ITClient(args);
            fail("Expected GetArgsException");
        } catch (IOException | DBServerException | A2ITClient.GetArgsException | 
                APIREST.APIServerException | HttpsClientException | ClassNotFoundException |
                SQLException ex) {
            assertTrue(ex.getMessage().contains("Serveur Mongo non défini"));
        }
    }
}
