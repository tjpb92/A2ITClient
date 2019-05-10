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
 * Programme permettant de tester la classe User
 *
 * @author Thierry Baribaud
 * @version 1.05
 */
public class UserTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserTest() {
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
     * Test of serialization from and to a file in Json format, of class User.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("User.jsonSerialization");

        String filename = "User.json";
        String testFilename = "testUser.json";
        User user = null;
        User expUser = null;

        try {
            user = objectMapper.readValue(new File(filename), User.class);
            objectMapper.writeValue(new File(testFilename), user);
            expUser = objectMapper.readValue(new File(filename), User.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(user);
        assertNotNull(expUser);
        assertEquals(user.toString(), expUser.toString());
    }
}
