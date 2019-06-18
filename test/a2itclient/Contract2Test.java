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
 * Jeux de tests pour la classe Contract2
 *
 * @author Thierry Baribaud
 * @version 1.27
 */
public class Contract2Test {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Contract2 contract2;

    public Contract2Test() {
        contract2 = new Contract2();
        contract2.setCallPurpose("Toto Lito");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getCallPurpose method, of class Contract2.
     */
    @Test
    public void testGetCallPurpose() {
        System.out.println("getCallPurpose");
        String expResult = "Toto Lito";
        String result = contract2.getCallPurpose();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCallPurpose method, of class Contract2.
     */
    @Test
    public void testSetCallPurpose() {
        System.out.println("setCallPurpose");
        String expResult = "Turlu Tutu";
        contract2.setCallPurpose(expResult);
        String result = contract2.getCallPurpose();
        assertEquals(expResult, result);
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * Contract2.
     */
    @Test
    public void testJsonSerialization() {
        Object expContract2;

        System.out.println("Contract2.jsonSerialization");
        expContract2 = null;
        try {
            expContract2 = objectMapper.readValue(new File("Contract2.json"), Contract2.class);
            System.out.println(expContract2);
        } catch (IOException ex) {
            Logger.getLogger(Contract2Test.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expContract2);
    }
}
