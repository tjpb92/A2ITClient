package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de test pour la classe Link
 * @author Thierry Baribaud
 * @version 1.05
 */
public class LinkTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public LinkTest() {
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
     * Test of serialization from and to a file in Json format, of class
     * Link.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Link.jsonSerialization");
        String filename = "Link.json";
        String testFilename = "testLink.json";

        Link link = new Link();
        Link expLink = new Link();

        try {
            link = objectMapper.readValue(new File(filename), Link.class);
            objectMapper.writeValue(new File(testFilename), link);
            expLink = objectMapper.readValue(new File(testFilename), Link.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(link);
        assertNotNull(expLink);
        assertEquals(link.toString(), expLink.toString());
    }
}
