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
 * @version 1.30
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

    /**
     * Test of serialization from and to a file in Json format, of class Asset.
     */
    @Test
    public void testAssetJsonSerialization() {
        System.out.println("AssetJsonSerialization");
        HALLink self = new HALLink("https://apisandbox.hubintent.com/api/assets/v1/assets/14435");
        Entity owner = new Entity("Nexity PM", "http://hubintent.com/intent/entities/logos/5fa25ed5-693d-402d-b266-84a0ffde1fc1");
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
        
        Map<String, HALLink> test = new HashMap<>();
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
            Logger.getLogger(HALLinksTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(asset);
        assertNotNull(expAsset);
        assertNotNull(anotherAsset);
    }
    
}
