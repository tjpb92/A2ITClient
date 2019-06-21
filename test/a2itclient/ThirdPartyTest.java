package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de test pour la classe ThierdParty
 *
 * @author Thierry Baribaud
 * @version 1.30
 */
public class ThirdPartyTest {

    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ThirdPartyTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEntity method, of class ThirdParty.
     */
    @Test
    public void testGetEntity() {
        System.out.println("ThirdParty.getEntity");
        ThirdParty instance = new ThirdParty();
        Entity result = instance.getEntity();
        assertNull(result);
    }

    /**
     * Test of setEntity method, of class ThirdParty.
     */
    @Test
    public void testSetEntity() {
        System.out.println("ThirdParty.setEntity");
        Entity entity = new Entity("label", "logo");
        ThirdParty instance = new ThirdParty();
        instance.setEntity(entity);
        Entity expEntity;
        expEntity = instance.getEntity();
        assertEquals(entity.toString(), expEntity.toString());
    }

    /**
     * Test of getReference method, of class ThirdParty.
     */
    @Test
    public void testGetReference() {
        System.out.println("ThirdParty.getReference");
        ThirdParty instance = new ThirdParty();
        String result = instance.getReference();
        assertNull(result);
    }

    /**
     * Test of setReference method, of class ThirdParty.
     */
    @Test
    public void testSetReference() {
        System.out.println("ThirdParty.setReference");
        String reference = "Anstel";
        ThirdParty instance = new ThirdParty();
        instance.setReference(reference);
        String expReference;
        expReference = instance.getReference();
        assertEquals(reference, expReference);
    }

    /**
     * Test of toString method, of class ThirdParty.
     */
    @Test
    public void testToString() {
        System.out.println("ThirdParty.toString");
        Entity entity = new Entity("label", "logo");
        String reference = "Anstel";
        String contacts = "toto, titi";

        ThirdParty instance = new ThirdParty();
        instance.setEntity(entity);
        instance.setContacts(contacts);
        instance.setReference(reference);

        String result = instance.toString();
//        System.out.println(result);

        String expResult = "thirdParty:{"
                + "entity:" + entity
                + ", contacts:" + contacts
                + ", reference:" + reference
                + "}";

        assertEquals(expResult, result);
    }

    /**
     * Test of serialization from and to a file in Json format, of class
     * ThirdParty.
     */
    @Test
    public void testThirdPartyJsonSerialization() {
        System.out.println("ThirdPartyJsonSerialization");
        String filename = "ThirdParty.json";
        String testFilename = "testThirdParty.json";

        ThirdParty thirdParty = new ThirdParty();
        ThirdParty expThirdParty = new ThirdParty();

        try {
            thirdParty = objectMapper.readValue(new File(filename), ThirdParty.class);
            objectMapper.writeValue(new File(testFilename), thirdParty);
            expThirdParty = objectMapper.readValue(new File(testFilename), ThirdParty.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(thirdParty);
        assertNotNull(expThirdParty);
        assertEquals(thirdParty.toString(), expThirdParty.toString());
    }
}
