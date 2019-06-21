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
 * Programme pour tester la classe Entity
 * @author Thierry Baribaud
 * @version 1.30
 */
public class EntityTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public EntityTest() {
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
     * Test of getLabel method, of class Entity.
     */
    @Test
    public void testGetLabel() {
        System.out.println("Owner.getLabel");
        Entity instance = new Entity();
        String result = instance.getLabel();
        assertNull(result);
    }

    /**
     * Test of setLabel method, of class Entity.
     */
    @Test
    public void testSetLabel() {
        System.out.println("Owner.setLabel");
        String label = "Anstel";
        Entity instance = new Entity();
        instance.setLabel(label);
        assertEquals(label, instance.getLabel());
    }

    /**
     * Test of getLogoUrl method, of class Entity.
     */
    @Test
    public void testGetLogoUrl() {
        System.out.println("Owner.getLogoUrl");
        Entity instance = new Entity();
        String result = instance.getLogoUrl();
        assertNull(result);
    }

    /**
     * Test of setLogoUrl method, of class Entity.
     */
    @Test
    public void testSetLogoUrl() {
        System.out.println("Owner.setLogoUrl");
        String logoUrl = "http://hubintent.com/intent/entities/logos/xxxx";
        Entity instance = new Entity();
        instance.setLogoUrl(logoUrl);
        assertEquals(logoUrl, instance.getLogoUrl());
    }

    /**
     * Test of toString method, of class Entity.
     */
    @Test
    public void testToString() {
        System.out.println("Owner.toString");
        String label = "Anstel";
        String logoUrl = "http://hubintent.com/intent/entities/logos/xxxx";
        Entity instance = new Entity(label, logoUrl);
        String expResult = "owner:{"
                + "label:" + label
                + ", logoUrl:" + logoUrl
                + "}";
        String result = instance.toString();
        System.out.println("  toString(result):" + result);
        System.out.println("  toString(expResult):" + expResult);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of serialization from and to a file in Json format, of class Entity.
     */
    @Test
    public void testEntityJsonSerialization() {
        System.out.println("EntityJsonSerialization");

        String filename = "Owner.json";
        String testFilename = "testOwner.json";
        Entity owner = null;
        Entity expOwner = null;

        try {
            owner = objectMapper.readValue(new File(filename), Entity.class);
            objectMapper.writeValue(new File(testFilename), owner);
            expOwner = objectMapper.readValue(new File(filename), Entity.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(owner);
        assertNotNull(expOwner);
        assertEquals(owner.toString(), expOwner.toString());
    }

}
