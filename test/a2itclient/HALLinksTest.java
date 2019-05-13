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
 * Jeux de tests pour la classe HALLinks
 * @author Thierry Baribaud
 * @version 1.06
 */
public class HALLinksTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public HALLinksTest() {
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
        HALLink anstel = new HALLink("http://www.anstel.com");
        HALLink excelia = new HALLink("http://excelia.fr");
        HALLinks links = new HALLinks();
        links.put("Excelia", excelia);
        HALLinks oneLinkList = new HALLinks();
        HALLinks twoLinksList = new HALLinks();
        
        try {
            objectMapper.writeValue(new File("LinksOfOneLink.json"), links);
            oneLinkList = objectMapper.readValue(new File("LinksOfOneLink.json"), HALLinks.class);
            
            links.put("Anstel", anstel);
            objectMapper.writeValue(new File("LinksOfTwoLinks.json"), links);
            twoLinksList = objectMapper.readValue(new File("LinksOfTwoLinks.json"), HALLinks.class);
        } catch (IOException ex) {
            Logger.getLogger(HALLinksTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(links);
        assertNotNull(oneLinkList);
        assertNotNull(twoLinksList);
    }
    
}
