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
 * Programme permettant de tester la classe Users
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class UsersTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UsersTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of serialization from and to a file in Json format, of class Users.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Users.jsonSerialization");

        String filename = "Users.json";
        String testFilename = "testUsers.json";
        Users users = null;
        Users expUsers = null;

        try {
            users = objectMapper.readValue(new File(filename), Users.class);
            objectMapper.writeValue(new File(testFilename), users);
            expUsers = objectMapper.readValue(new File(filename), Users.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(users);
        assertNotNull(expUsers);
//        assertEquals(users.toString(), expUsers.toString());
    }
}
