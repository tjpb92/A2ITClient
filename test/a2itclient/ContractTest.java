package a2itclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
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
 * Jeux de tests pour la classe Contract
 * @author Thierry Baribaud
 * @version 1.04
 */
public class ContractTest {
    
    /**
     * Common Jackson object mapper
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ContractTest() {
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
     * Test of getLinks method, of class Contract.
     */
    @Test
    public void testGetLinks() {
        System.out.println("getLinks");
        Contract instance = new Contract();
        Map<String, Link> expResult = null;
        Map<String, Link> result = instance.getLinks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLinks method, of class Contract.
     */
    @Test
    public void testSetLinks() {
        System.out.println("setLinks");
        Map<String, Link> links = null;
        Contract instance = new Contract();
        instance.setLinks(links);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastUpdateDate method, of class Contract.
     */
    @Test
    public void testGetLastUpdateDate() {
        System.out.println("getLastUpdateDate");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getLastUpdateDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastUpdateDate method, of class Contract.
     */
    @Test
    public void testSetLastUpdateDate() {
        System.out.println("setLastUpdateDate");
        String lastUpdateDate = "";
        Contract instance = new Contract();
        instance.setLastUpdateDate(lastUpdateDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreationDate method, of class Contract.
     */
    @Test
    public void testGetCreationDate() {
        System.out.println("getCreationDate");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getCreationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCreationDate method, of class Contract.
     */
    @Test
    public void testSetCreationDate() {
        System.out.println("setCreationDate");
        String creationDate = "";
        Contract instance = new Contract();
        instance.setCreationDate(creationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of serialization from and to a file in Json format, of class Contract.
     */
    @Test
    public void testJsonSerialization() {
        System.out.println("Contract.jsonSerialization");

        String filename = "Contract.json";
        String testFilename = "testContract.json";
        Contract contract = null;
        Contract expContract = null;

        try {
            contract = objectMapper.readValue(new File(filename), Contract.class);
            objectMapper.writeValue(new File(testFilename), contract);
            expContract = objectMapper.readValue(new File(filename), Contract.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(contract);
        assertNotNull(expContract);
//        assertEquals(contract.toString(), expContract.toString());
    }

    /**
     * Test of getId method, of class Contract.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Contract.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Contract instance = new Contract();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabel method, of class Contract.
     */
    @Test
    public void testGetLabel() {
        System.out.println("getLabel");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabel method, of class Contract.
     */
    @Test
    public void testSetLabel() {
        System.out.println("setLabel");
        String label = "";
        Contract instance = new Contract();
        instance.setLabel(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTopic method, of class Contract.
     */
    @Test
    public void testGetTopic() {
        System.out.println("getTopic");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getTopic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTopic method, of class Contract.
     */
    @Test
    public void testSetTopic() {
        System.out.println("setTopic");
        String topic = "";
        Contract instance = new Contract();
        instance.setTopic(topic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReference method, of class Contract.
     */
    @Test
    public void testGetReference() {
        System.out.println("getReference");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getReference();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setReference method, of class Contract.
     */
    @Test
    public void testSetReference() {
        System.out.println("setReference");
        String reference = "";
        Contract instance = new Contract();
        instance.setReference(reference);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThirdParty method, of class Contract.
     */
    @Test
    public void testGetThirdParty() {
        System.out.println("getThirdParty");
        Contract instance = new Contract();
        ThirdParty expResult = null;
        ThirdParty result = instance.getThirdParty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setThirdParty method, of class Contract.
     */
    @Test
    public void testSetThirdParty() {
        System.out.println("setThirdParty");
        ThirdParty thirdParty = null;
        Contract instance = new Contract();
        instance.setThirdParty(thirdParty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartDate method, of class Contract.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getStartDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartDate method, of class Contract.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        String startDate = "";
        Contract instance = new Contract();
        instance.setStartDate(startDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDate method, of class Contract.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        Contract instance = new Contract();
        String expResult = "";
        String result = instance.getEndDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndDate method, of class Contract.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        String endDate = "";
        Contract instance = new Contract();
        instance.setEndDate(endDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
