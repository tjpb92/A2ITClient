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

/**
 * Jeux de tests pour la classe CallPurpose
 *
 * @author Thierry Baribaud
 * @version 1.25
 */
public class CallPurposeTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private CallPurpose callPurpose;

    public CallPurposeTest() {
        callPurpose = new CallPurpose();
        callPurpose.setName("Toto Lito");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Toto Lito";
        String result = callPurpose.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Contact.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String expResult = "Turlu Tutu";
        callPurpose.setName(expResult);
        String result = callPurpose.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * Contact.
     */
    @Test
    public void testJsonSerialization() {
        Object expCallPurpose;

        System.out.println("CallPurpose.jsonSerialization");
        expCallPurpose = null;
        try {
            expCallPurpose = objectMapper.readValue(new File("CallPurpose.json"), CallPurpose.class);
            System.out.println(expCallPurpose);
        } catch (IOException ex) {
            Logger.getLogger(CallPurposeTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expCallPurpose);
    }
}
