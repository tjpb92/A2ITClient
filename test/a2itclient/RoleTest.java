package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Programme de test de la classe Role
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class RoleTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public RoleTest() {
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

    /**
     * Test of getName method, of class Role.
     */
    @Test
    public void testGetName() {
        System.out.println("Role.getName");
        Role instance = new Role();
        String result = instance.getName();
        assertNull(result);
    }

    /**
     * Test of setName method, of class Role.
     */
    @Test
    public void testSetName() {
        System.out.println("Role.setName");
        String name = "Admin";
        Role instance = new Role();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of toString method, of class Role.
     */
    @Test
    public void testToString() {
        System.out.println("Role.toString");
        String name = "Admin";
        String expResult = "role:{name:" + name + "}";
        Role instance = new Role();
        instance.setName(name);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of serialization from and to a file in Json format, of class Role.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Role.jsonSerialization");

        String filename = "Role.json";
        String testFilename = "testRole.json";
        Role role = null;
        Role expRole = null;

        try {
            role = objectMapper.readValue(new File(filename), Role.class);
            objectMapper.writeValue(new File(testFilename), role);
            expRole = objectMapper.readValue(new File(filename), Role.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(role);
        assertNotNull(expRole);
        assertEquals(role.toString(), expRole.toString());
    }
}
