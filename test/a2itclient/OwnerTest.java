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
 * Programme pour tester la classe Owner
 * @author Thierry Baribaud
 * @version 1.04
 */
public class OwnerTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public OwnerTest() {
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
     * Test of getLabel method, of class Owner.
     */
    @Test
    public void testGetLabel() {
        System.out.println("Owner.getLabel");
        Owner instance = new Owner();
        String result = instance.getLabel();
        assertNull(result);
    }

    /**
     * Test of setLabel method, of class Owner.
     */
    @Test
    public void testSetLabel() {
        System.out.println("Owner.setLabel");
        String label = "Anstel";
        Owner instance = new Owner();
        instance.setLabel(label);
        assertEquals(label, instance.getLabel());
    }

    /**
     * Test of getLogoUrl method, of class Owner.
     */
    @Test
    public void testGetLogoUrl() {
        System.out.println("Owner.getLogoUrl");
        Owner instance = new Owner();
        String result = instance.getLogoUrl();
        assertNull(result);
    }

    /**
     * Test of setLogoUrl method, of class Owner.
     */
    @Test
    public void testSetLogoUrl() {
        System.out.println("Owner.setLogoUrl");
        String logoUrl = "http://hubintent.com/intent/entities/logos/xxxx";
        Owner instance = new Owner();
        instance.setLogoUrl(logoUrl);
        assertEquals(logoUrl, instance.getLogoUrl());
    }

    /**
     * Test of toString method, of class Owner.
     */
    @Test
    public void testToString() {
        System.out.println("Owner.toString");
        String label = "Anstel";
        String logoUrl = "http://hubintent.com/intent/entities/logos/xxxx";
        Owner instance = new Owner(label, logoUrl);
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
     * Test of serialization from and to a file in Json format, of class Owner.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Owner.jsonSerialization");

        String filename = "Owner.json";
        String testFilename = "testOwner.json";
        Owner owner = null;
        Owner expOwner = null;

        try {
            owner = objectMapper.readValue(new File(filename), Owner.class);
            objectMapper.writeValue(new File(testFilename), owner);
            expOwner = objectMapper.readValue(new File(filename), Owner.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(owner);
        assertNotNull(expOwner);
        assertEquals(owner.toString(), expOwner.toString());
    }

}
