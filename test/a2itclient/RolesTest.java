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
 * @version 1.03
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
     * Test of serialization to and from a file in Json format, of class Roles.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Roles.jsonSerialization");
        String fileName = "testRoles.json";
        Role admin = new Role("Admin");
        Role user = new Role("User");
        Roles instance = new Roles();
        instance.add(admin);
        instance.add(user);
        Roles expResult = new Roles();

        try {
            objectMapper.writeValue(new File(fileName), instance);
            expResult = objectMapper.readValue(new File(fileName), Roles.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), instance.toString());
    }

}
