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
 * Jeux de tests pour la classe Contact
 *
 * @author Thierry Baribaud
 * @version 1.22
 */
public class ContactTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Contact contact;

    public ContactTest() {
        contact = new Contact();
        contact.setName("Toto Lito");
        contact.setQuality("Appelant");
        contact.setRole("Gardien");
        contact.setPhone("01.02.03.04.05");
        contact.setMail("toto.lito@gmail.com");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getName method, of class Contact.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Toto Lito";
        String result = contact.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Contact.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String expResult = "Turlu Tutu";
        contact.setName(expResult);
        String result = contact.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getQuality method, of class Contact.
     */
    @Test
    public void testGetQuality() {
        System.out.println("getQuality");
        String expResult = "Appelant";
        String result = contact.getQuality();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuality method, of class Contact.
     */
    @Test
    public void testSetQuality() {
        System.out.println("setQuality");
        String expResult = "Locataire";
        contact.setQuality(expResult);
        String result = contact.getQuality();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRole method, of class Contact.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        String expResult = "Gardien";
        String result = contact.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRole method, of class Contact.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        String expResult = "Responsable";
        contact.setRole(expResult);
        String result = contact.getRole();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPhone method, of class Contact.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        String expResult = "01.02.03.04.05";
        String result = contact.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class Contact.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String expResult = "06.07.08.09.10";
        contact.setPhone(expResult);
        String result = contact.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMail method, of class Contact.
     */
    @Test
    public void testGetMail() {
        System.out.println("getMail");
        String expResult = "toto.lito@gmail.com";
        String result = contact.getMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMail method, of class Contact.
     */
    @Test
    public void testSetMail() {
        System.out.println("setMail");
        String expResult = "turlu.tutu@gmail.com";
        contact.setMail(expResult);
        String result = contact.getMail();
        assertEquals(expResult, result);
    }

    /**
     * Test of toVCard method, of class Contact.
     */
    @Test
    public void testToVCard() {
        System.out.println("toVCard");
        System.out.println(contact);
        String expResult = contact.toVCard();
        System.out.println("vCard:" + expResult);
        assertNotNull(expResult);
    }

    /**
     * Test of toString method, of class Contact.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        System.out.println(contact);
        String expResult = "contact:{name:Toto Lito, quality:Appelant, role:Gardien, phone:01.02.03.04.05, mail:toto.lito@gmail.com}";
        String result = contact.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * Contact.
     */
    @Test
    public void testJsonSerialization() {
        Object expContact;

        System.out.println("Contact.jsonSerialization");
        expContact = null;
        try {
            objectMapper.writeValue(new File("ContactFromObject.json"), contact);
            expContact = objectMapper.readValue(new File("ContactFromObject.json"), Contact.class);
        } catch (IOException ex) {
            Logger.getLogger(ContactTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expContact);
    }
}
