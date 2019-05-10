/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2itclient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Programme permettant de tester tous les objets du projet
 * @author Thierry Baribaud
 * @version 1.03
 */
@RunWith(Suite.class)
//@Suite.SuiteClasses({A2ITClientTest.class, RoleTest.class, RolesTest.class, testGetTest.class, UserTest.class, TokenTest.class, UsersTest.class})
@Suite.SuiteClasses({LinksTest.class, RolesTest.class, RoleTest.class, UserTest.class, LinkTest.class, TokenTest.class, UsersTest.class, OwnerTest.class})
public class A2itclientSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
