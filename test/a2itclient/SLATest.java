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
 * Jeux de tests pour tester la classe SLA
 * @author Thierry Baribaud
 * @version 1.05
 */
public class SLATest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public SLATest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class SLA.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("SLA.jsonSerialization");

        String filename = "SLA.json";
        String testFilename = "testSLA.json";
        SLA sla = null;
        SLA expSla = null;

        try {
            sla = objectMapper.readValue(new File(filename), SLA.class);
            objectMapper.writeValue(new File(testFilename), sla);
            expSla = objectMapper.readValue(new File(filename), SLA.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(sla);
        assertNotNull(expSla);
        assertEquals(sla.toString(), expSla.toString());
    }
}
