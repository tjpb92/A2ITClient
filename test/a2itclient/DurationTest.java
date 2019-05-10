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
 * Jeux de tests pour tester la classe Duration
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class DurationTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public DurationTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * Duration.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Duration.jsonSerialization");

        String filename = "Duration.json";
        String testFilename = "testDuration.json";
        Duration duration = null;
        Duration expDuration = null;

        try {
            duration = objectMapper.readValue(new File(filename), Duration.class);
            objectMapper.writeValue(new File(testFilename), duration);
            expDuration = objectMapper.readValue(new File(filename), Duration.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(duration);
        assertNotNull(expDuration);
        assertEquals(duration.toString(), expDuration.toString());
    }
}
