package a2itclient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.ApplicationProperties;
import utils.HttpsClientException;

/**
 * Jeux de tests pour tester la classe HttpsClient
 *
 * @author Thierry Baribaud
 * @version 1.38
 */
public class HttpsClientTest {

    public HttpsClientTest() {
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
     * Test pour vérifier la bonne instanciation de la classe HttpsClient
     */
    @Test
    public void testNewHttpsClient() {
        ApplicationProperties applicationProperties;
        APIREST apiRest;
        HttpsClient httpsClient = null;

        System.out.println("HttpsClientTest.NewHttpsClient");

        try {
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            apiRest = new APIREST("pre-prod", applicationProperties);
            httpsClient = new HttpsClient(apiRest, true);
        } catch (IOException | APIREST.APIServerException | HttpsClientException exception) {
            Logger.getLogger(HttpsClientTest.class.getName()).log(Level.SEVERE, null, exception);
            fail("Unexpected exception");
        }
        assertNotNull(httpsClient);
    }

    /**
     * Test pour vérifier un mauvais login dans la classe HttpsClient
     */
    @Test
    public void testBadLoginHttpsClient() {
        ApplicationProperties applicationProperties;
        APIREST apiRest;
        HttpsClient httpsClient = null;

        System.out.println("HttpsClientTest.BadLoginHttpsClient");

        try {
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            apiRest = new APIREST("pre-prod", applicationProperties);

            /*
             * Altération du login
             */
            apiRest.setLogin(apiRest.getLogin() + "xx");
            System.out.println("Bad login:" + apiRest.getLogin());

            httpsClient = new HttpsClient(apiRest, true);
            fail("Expected HttpsClientException");
        } catch (IOException | APIREST.APIServerException | HttpsClientException exception) {
            Logger.getLogger(HttpsClientTest.class.getName()).log(Level.SEVERE, null, exception);
            assertTrue(exception instanceof HttpsClientException);
        }
    }

    /**
     * Test pour vérifier un mauvais mot de passe dans la classe HttpsClient
     */
    @Test
    public void testBadPasswordHttpsClient() {
        ApplicationProperties applicationProperties;
        APIREST apiRest;
        HttpsClient httpsClient = null;

        System.out.println("HttpsClientTest.BadPasswordHttpsClient");

        try {
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            apiRest = new APIREST("pre-prod", applicationProperties);

            /*
             * Altération du mot de passe
             */
            apiRest.setPassword(apiRest.getPassword() + "xx");
            System.out.println("Bad password:" + apiRest.getPassword());

            httpsClient = new HttpsClient(apiRest, true);
            fail("Expected HttpsClientException");
        } catch (IOException | APIREST.APIServerException | HttpsClientException exception) {
            Logger.getLogger(HttpsClientTest.class.getName()).log(Level.SEVERE, null, exception);
            assertTrue(exception instanceof HttpsClientException);
        }
    }

    /**
     * Test pour vérifier une mauvaise URL dans la classe HttpsClient
     */
    @Test
    public void testBadURLHttpsClient() {
        ApplicationProperties applicationProperties;
        APIREST apiRest;
        HttpsClient httpsClient = null;

        System.out.println("HttpsClientTest.BadURLHttpsClient");

        try {
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            apiRest = new APIREST("prod", applicationProperties);

            /*
             * Altération de l'URL
             * ATTENTION : ne fonctionne pas avec la pré-prod !
             */
            apiRest.setAuthUrl(apiRest.getAuthUrl().replace("oauth", "auth"));
            System.out.println("Bad URL:" + apiRest.getAuthUrl());

            httpsClient = new HttpsClient(apiRest, true);
            fail("Expected HttpsClientException");
        } catch (IOException | APIREST.APIServerException | HttpsClientException exception) {
            Logger.getLogger(HttpsClientTest.class.getName()).log(Level.SEVERE, null, exception);
            assertTrue(exception instanceof HttpsClientException);
        }
    }
}
