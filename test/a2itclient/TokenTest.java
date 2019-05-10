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
 * Jeux de tests pour tester la classe Token
 * @author Thierry Baribaud
 * @version 1.05
 */
public class TokenTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public TokenTest() {
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
     * Test of serialization from and to a file in Json format, of class Token.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Token.jsonSerialization");

        String filename = "Token.json";
        String testFilename = "testToken.json";
        Token token = null;
        Token expToken = null;

        try {
            token = objectMapper.readValue(new File(filename), Token.class);
            objectMapper.writeValue(new File(testFilename), token);
            expToken = objectMapper.readValue(new File(filename), Token.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(token);
        assertNotNull(expToken);
        assertEquals(token.toString(), expToken.toString());
    }
}
