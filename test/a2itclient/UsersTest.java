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
 * @author Thierry Baribaud
 * @version 1.03
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
     * Test of serialization to and from a file in Json format, of class Users.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Users.jsonSerialization");

        String fileName = "testUsers.json";
        User tb = new User();
        User sl = new User();
        Users expResult = new Users();
        
        tb.setId("4b4d2861-569e-4d13-8a33-922009d2e16b");
        tb.setEmail("thierry.baribaud@anstel.com");
        tb.setUsername("thierry.baribaud@anstel.com");
        tb.setFirstname("Thierry");
        tb.setLastname("Baribaud");
        tb.setCreationDate("2019-05-06T17:09:27.857Z");
        Role adminRole = new Role("admin");
        Roles userRoles = tb.getUserRoles();
        userRoles.add(adminRole);
        tb.setUserRoles(userRoles);
        tb.setJid("urn:anstel.intent:012eba63-83ae-4345-9d0e-bb04a4997ae0");
        
        sl.setId("c0243cee-b0c4-4f85-962c-eb68ac0f4fd6");
        sl.setEmail("stephane.lafont@anstel.com");
        sl.setUsername("stephane.lafont@anstel.com");
        sl.setFirstname("stephane");
        sl.setLastname("lafont");
        sl.setCreationDate("2019-05-07T07:56:53.213Z");
        userRoles.add(adminRole);
        sl.setUserRoles(userRoles);
        sl.setJid("urn:anstel.intent:6ce853bd-44a0-418d-8bd3-61bef6ec1218");
        
        Users users = new Users();
        users.add(tb);
        users.add(sl);

        try {
            objectMapper.writeValue(new File(fileName), users);
            expResult = objectMapper.readValue(new File(fileName), Users.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), users.toString());
    }    
    
}
