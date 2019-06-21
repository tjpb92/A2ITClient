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
 * Programme permettant de tester la classe Roles
 * @author Thierry Baribaud
 * @version 1.30
 */
public class RolesTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public RolesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class Roles.
     */
    @Test
    public void testRolesJsonSerialization() {
        System.out.println("RolesJsonSerialization");

        String filename = "Roles.json";
        String testFilename = "testRoles.json";
        Roles roles = null;
        Roles expRoles = null;

        try {
            roles = objectMapper.readValue(new File(filename), Roles.class);
            objectMapper.writeValue(new File(testFilename), roles);
            expRoles = objectMapper.readValue(new File(filename), Roles.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(roles);
        assertNotNull(expRoles);
        assertEquals(roles.toString(), expRoles.toString());
    }
}
