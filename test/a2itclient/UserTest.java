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
 * Programme permettant de tester la classe User
 * @author Thierry Baribaud
 * @version 1.03
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

//    /**
//     * Test of getId method, of class User.
//     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getId();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setId method, of class User.
//     */
//    @Test
//    public void testSetId() {
//        System.out.println("setId");
//        String id = "";
//        User instance = new User();
//        instance.setId(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getDomain method, of class User.
//     */
//    @Test
//    public void testGetDomain() {
//        System.out.println("getDomain");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getDomain();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setDomain method, of class User.
//     */
//    @Test
//    public void testSetDomain() {
//        System.out.println("setDomain");
//        String domain = "";
//        User instance = new User();
//        instance.setDomain(domain);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEmail method, of class User.
//     */
//    @Test
//    public void testGetEmail() {
//        System.out.println("getEmail");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getEmail();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEmail method, of class User.
//     */
//    @Test
//    public void testSetEmail() {
//        System.out.println("setEmail");
//        String email = "";
//        User instance = new User();
//        instance.setEmail(email);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUsername method, of class User.
//     */
//    @Test
//    public void testGetUsername() {
//        System.out.println("getUsername");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getUsername();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUsername method, of class User.
//     */
//    @Test
//    public void testSetUsername() {
//        System.out.println("setUsername");
//        String username = "";
//        User instance = new User();
//        instance.setUsername(username);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getFirstname method, of class User.
//     */
//    @Test
//    public void testGetFirstname() {
//        System.out.println("getFirstname");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getFirstname();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setFirstname method, of class User.
//     */
//    @Test
//    public void testSetFirstname() {
//        System.out.println("setFirstname");
//        String firstname = "";
//        User instance = new User();
//        instance.setFirstname(firstname);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLastname method, of class User.
//     */
//    @Test
//    public void testGetLastname() {
//        System.out.println("getLastname");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getLastname();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLastname method, of class User.
//     */
//    @Test
//    public void testSetLastname() {
//        System.out.println("setLastname");
//        String lastname = "";
//        User instance = new User();
//        instance.setLastname(lastname);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCreationDate method, of class User.
//     */
//    @Test
//    public void testGetCreationDate() {
//        System.out.println("getCreationDate");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getCreationDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCreationDate method, of class User.
//     */
//    @Test
//    public void testSetCreationDate() {
//        System.out.println("setCreationDate");
//        String creationDate = "";
//        User instance = new User();
//        instance.setCreationDate(creationDate);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPhone method, of class User.
//     */
//    @Test
//    public void testGetPhone() {
//        System.out.println("getPhone");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getPhone();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setPhone method, of class User.
//     */
//    @Test
//    public void testSetPhone() {
//        System.out.println("setPhone");
//        String phone = "";
//        User instance = new User();
//        instance.setPhone(phone);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getMobile method, of class User.
//     */
//    @Test
//    public void testGetMobile() {
//        System.out.println("getMobile");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getMobile();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setMobile method, of class User.
//     */
//    @Test
//    public void testSetMobile() {
//        System.out.println("setMobile");
//        String mobile = "";
//        User instance = new User();
//        instance.setMobile(mobile);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEntityRoles method, of class User.
//     */
//    @Test
//    public void testGetEntityRoles() {
//        System.out.println("getEntityRoles");
//        User instance = new User();
//        List<Role> expResult = null;
//        List<Role> result = instance.getEntityRoles();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEntityRoles method, of class User.
//     */
//    @Test
//    public void testSetEntityRoles() {
//        System.out.println("setEntityRoles");
//        Roles entityRoles = null;
//        User instance = new User();
//        instance.setEntityRoles(entityRoles);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserRoles method, of class User.
//     */
//    @Test
//    public void testGetUserRoles() {
//        System.out.println("getUserRoles");
//        User instance = new User();
//        Roles expResult = null;
//        Roles result = instance.getUserRoles();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setUserRoles method, of class User.
//     */
//    @Test
//    public void testSetUserRoles() {
//        System.out.println("setUserRoles");
//        Roles userRoles = null;
//        User instance = new User();
//        instance.setUserRoles(userRoles);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getJid method, of class User.
//     */
//    @Test
//    public void testGetJid() {
//        System.out.println("getJid");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.getJid();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setJid method, of class User.
//     */
//    @Test
//    public void testSetJid() {
//        System.out.println("setJid");
//        String jid = "";
//        User instance = new User();
//        instance.setJid(jid);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class User.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        User instance = new User();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of serialization to and from a file in Json format, of class User.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("User.jsonSerialization");

        String fileName = "testUser.json";
        User user = new User();
        User expResult = new User();
        
        user.setId("4b4d2861-569e-4d13-8a33-922009d2e16b");
//        user.setDomain("anstel.intent");
        user.setEmail("thierry.baribaud@anstel.com");
        user.setUsername("thierry.baribaud@anstel.com");
        user.setFirstname("Thierry");
        user.setLastname("Baribaud");
        user.setCreationDate("2019-05-06T17:09:27.857Z");
        
//        Role entityRole = new Role("serviceProvider");
//        Roles entityRoles = new Roles();
//        entityRoles.add(entityRole);
//        user.setEntityRoles(entityRoles);
        
        Role adminRole = new Role("admin");
//        System.out.println(adminRole);
        Roles userRoles = user.getUserRoles();
        userRoles.add(adminRole);
//        System.out.println(userRoles);
        user.setUserRoles(userRoles);
//        Role userRole = new Role("user");
//        Roles userRoles = new Roles();
//        userRoles.add(adminRole);
//        userRoles.add(userRole);
//        user.setUserRoles(userRoles);
        
        user.setJid("urn:anstel.intent:012eba63-83ae-4345-9d0e-bb04a4997ae0");
        
        try {
            objectMapper.writeValue(new File(fileName), user);
            expResult = objectMapper.readValue(new File(fileName), User.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(expResult.toString(), user.toString());
    }    
}
