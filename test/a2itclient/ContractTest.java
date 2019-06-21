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
 * Jeux de tests pour la classe Contract
 *
 * @author Thierry Baribaud
 * @version 1.30
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
     * Test of serialization from and to a file in Json format, of class
     * Contract.
     */
    @Test
    public void testContractJsonSerialization() {
        System.out.println("ContractJsonSerialization");
        String filename = "Contract.json";
        String testFilename = "testContract.json";

        Contract contract = new Contract();
        Contract expContract = new Contract();

        try {
            contract = objectMapper.readValue(new File(filename), Contract.class);
            objectMapper.writeValue(new File(testFilename), contract);
            expContract = objectMapper.readValue(new File(testFilename), Contract.class);
        } catch (IOException ex) {
            Logger.getLogger(RoleTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        assertNotNull(contract);
        assertNotNull(expContract);
//        assertEquals(contract.toString(), expContract.toString());
    }
}
