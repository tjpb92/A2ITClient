package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.EmailType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.StructuredName;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de tests pour les listes de contacts
 *
 * @author Thierry Baribaud
 * @version 1.22
 */
public class ContactListTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ContactList contactList;
    private Contact contact;

    public ContactListTest() {
        contactList = new ContactList();

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

    @Test
    public void testAddOneContact() {
        System.out.println("addOneContact");
        contactList.add(contact);
        System.out.println(contactList);
        assertNotNull(contactList);
    }

    @Test
    public void testVCard() {
        VCard vcard;
        StructuredName structuredName;
        String value;
        List<String> contacts;

        System.out.println("testVCard");
        contactList.add(contact);
        contacts = new ArrayList<>();

        for (Contact myContact : contactList) {
            vcard = new VCard();
            structuredName = new StructuredName();
            structuredName.setFamily(myContact.getName());
            vcard.setStructuredName(structuredName);
            if ((value = myContact.getPhone()) != null) {
                vcard.addTelephoneNumber(value, TelephoneType.VOICE);
            }
            if ((value = myContact.getMail()) != null) {
                vcard.addEmail(value, EmailType.INTERNET);
            }
            if ((value = myContact.getQuality()) != null) {
                vcard.addTitle(value);
            }
            if ((value = myContact.getRole()) != null) {
                vcard.addRole(value);
            }
            contacts.add(Ezvcard.write(vcard).version(VCardVersion.V4_0).go());
        }
        System.out.println(contacts);
        assertNotNull(contacts);
    }

    /**
     * Test of serialization from and to an object in Json format, of class
     * Contact.
     */
    @Test
    public void testJsonSerializationFromObjec() {
        ContactList expContactList;
        Contact anotherContact;

        System.out.println("ContactList.jsonSerialization(from Object)");
        expContactList = null;
        contactList.add(contact);
        anotherContact = new Contact();
        anotherContact.setName("Turlu Tutu");
        anotherContact.setQuality("Locataire");
        anotherContact.setRole("Responsable");
        anotherContact.setPhone("06.07.08.09.10");
        anotherContact.setMail("turlu.tutu@gmail.com");
        contactList.add(anotherContact);
        try {
            objectMapper.writeValue(new File("ContactListFromObject.json"), contactList);
            expContactList = objectMapper.readValue(new File("ContactListFromObject.json"), ContactList.class);
        } catch (IOException ex) {
            Logger.getLogger(ContactListTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(expContactList);
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * Contact.
     */
    @Test
    public void testJsonSerialization() {
        ContactList anotherContactList;

        System.out.println("ContactList.jsonSerialization(from File)");
        anotherContactList = null;
        try {
            anotherContactList = objectMapper.readValue(new File("ContactList.json"), ContactList.class);
            System.out.println(anotherContactList.size() + " contact(s) in list");
            for (Contact contact : anotherContactList) {
                System.out.println(contact);
                System.out.println(contact.toVCard());
            }
        } catch (IOException ex) {
            Logger.getLogger(ContactListTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(anotherContactList);
    }
}
