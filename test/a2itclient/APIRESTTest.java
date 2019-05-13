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

/**
 * Jeux de tests pour la classe APIREST
 * @author Thierry Baribaud
 * @version 1.08
 */
public class APIRESTTest {
    
    public APIRESTTest() {
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
     * Test de création de l'objet APIREST à partir d'un fichier de propriétés
     */
    @Test
    public void testNeWAPIREST() {
        System.out.println("APIREST.new");
        ApplicationProperties applicationProperties = null;
        APIREST instance = null;
        
        try {
            applicationProperties = new ApplicationProperties("A2ITClient.prop");
            instance = new APIREST("pre-prod", applicationProperties);
            System.out.println(instance);
            
        } catch (IOException ex) {
            Logger.getLogger(APIRESTTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (APIREST.APIServerException ex) {
            Logger.getLogger(APIRESTTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(instance);
    }
    
}
