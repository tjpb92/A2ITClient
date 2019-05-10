package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Jeux de tests pour la classe Asset
 * @author Thierry Baribaud
 * @version 1.04
 */
public class AssetTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public AssetTest() {
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

//    /**
//     * Test of getCategory method, of class Asset.
//     */
//    @Test
//    public void testGetCategory() {
//        System.out.println("getCategory");
//        Asset instance = new Asset();
//        String expResult = "";
//        String result = instance.getCategory();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCategory method, of class Asset.
//     */
//    @Test
//    public void testSetCategory() {
//        System.out.println("setCategory");
//        String category = "";
//        Asset instance = new Asset();
//        instance.setCategory(category);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLinks method, of class Asset.
//     */
//    @Test
//    public void testGetLinks() {
//        System.out.println("getLinks");
//        Asset instance = new Asset();
//        Map<String, Link> expResult = null;
//        Map<String, Link> result = instance.getLinks();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setLinks method, of class Asset.
//     */
//    @Test
//    public void testSetLinks() {
//        System.out.println("setLinks");
//        Map<String, Link> links = null;
//        Asset instance = new Asset();
//        instance.setLinks(links);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOwner method, of class Asset.
//     */
//    @Test
//    public void testGetOwner() {
//        System.out.println("getOwner");
//        Asset instance = new Asset();
//        Owner expResult = null;
//        Owner result = instance.getOwner();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setOwner method, of class Asset.
//     */
//    @Test
//    public void testSetOwner() {
//        System.out.println("setOwner");
//        Owner owner = null;
//        Asset instance = new Asset();
//        instance.setOwner(owner);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of serialization from and to a file in Json format, of class Asset.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Asset.jsonSerialization");
        Link self = new Link("https://apisandbox.hubintent.com/api/assets/v1/assets/14435");
        Owner owner = new Owner("Nexity PM", "http://hubintent.com/intent/entities/logos/5fa25ed5-693d-402d-b266-84a0ffde1fc1");
        Asset asset = new Asset();
        asset.setCategory("Immeuble");
        asset.setCode("14435");
        asset.setFullPath("/14435");
        asset.setLabel("Batiment ILEO");
        
        Map<String, String> tags = new HashMap<>();
        tags.put("intent_type", "Immeuble Bureau");
        tags.put("intent_reference", "14435");
        asset.setTags(tags);
        asset.setType("building");
        List<String> contracts = new ArrayList<>();
        contracts.add("NMP_ANSTEL");
        asset.setContracts(contracts);
        
        Map<String, Link> test = new HashMap<>();
        test.put("self", self);
        asset.setLinks(test);
        
        asset.setLastUpdateDate("2019-05-06T19:47:53+02:00");
        asset.setCreationDate("2019-05-06T19:47:53+02:00");
        asset.setParentPath("/");
        
        asset.setOwner(owner);
        Asset expAsset = new Asset();
        Asset anotherAsset = new Asset();
        
        try {
            objectMapper.writeValue(new File("AssetFromObject.json"), asset);
            expAsset = objectMapper.readValue(new File("AssetFromObject.json"), Asset.class);
            
            anotherAsset = objectMapper.readValue(new File("Asset.json"), Asset.class);
            
        } catch (IOException ex) {
            Logger.getLogger(LinksTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(asset);
        assertNotNull(expAsset);
        assertNotNull(anotherAsset);
    }
    
}
