/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a2itclient;

import org.junit.AfterClass;
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
@Suite.SuiteClasses({RoleTest.class, RolesTest.class, UserTest.class, UsersTest.class})
public class A2itclientSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
