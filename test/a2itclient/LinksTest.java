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
 * Jeux de tests pour la classe Links
 * @author Thierry Baribaud
 * @version 1.04
 */
public class LinksTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public LinksTest() {
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
     * Test of serialization from and to a file in Json format, of class Owner.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Links.jsonSerialization");
        Link anstel = new Link("http://www.anstel.com");
        Link excelia = new Link("http://excelia.fr");
        Links links = new Links();
        links.put("Excelia", excelia);
        Links oneLinkList = new Links();
        Links twoLinksList = new Links();
        
        try {
            objectMapper.writeValue(new File("LinksOfOneLink.json"), links);
            oneLinkList = objectMapper.readValue(new File("LinksOfOneLink.json"), Links.class);
            
            links.put("Anstel", anstel);
            objectMapper.writeValue(new File("LinksOfTwoLinks.json"), links);
            twoLinksList = objectMapper.readValue(new File("LinksOfTwoLinks.json"), Links.class);
        } catch (IOException ex) {
            Logger.getLogger(LinksTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(links);
        assertNotNull(oneLinkList);
        assertNotNull(twoLinksList);
    }
    
}
