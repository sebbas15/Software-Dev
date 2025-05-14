// Sebastian Rodriguez

// Error comments are at function header comments

//
// Example JUnit testing class for CustomerList
//

// Import all assertions and all annotations
// - see docs for lists and descriptions
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class CLTest
{
   
// Data you need for each test case
private CustomerList customerList;

// Stuff you want to do before each test case
@BeforeEach
public void setup()
{
   System.out.println("\nCustomerList tests starting...");
   customerList = new CustomerList();
}

// Stuff you want to do after each test case
@AfterEach
public void teardown()
{
   System.out.println("\nCustomerList tests finished.");
}

@Test
public void testAddCustomer()
{
   customerList.addCustomer("Ana",1);
   assertTrue(customerList.find("Ana").equals("Ana(1)"));
}

@Test
public void testPresentCustomer()
{
   customerList.addCustomer("Ana",1);
   customerList.addCustomer("Jonny",2);
   customerList.addCustomer("Jon",3);
   assertTrue(customerList.find("Jon").equals("Jon(3)"));
}

@Test
public void testPresentCustomer_subNameTest1()
{
   customerList.addCustomer("Jon",2);
   customerList.addCustomer("Jonny",3);
   customerList.addCustomer("Ana",1);
   assertTrue(customerList.find("Jonny").equals("Jonny(3)"));
}

@Test
public void testPresentCustomer_subNameTest2()
{
   customerList.addCustomer("Jonny",3);
   customerList.addCustomer("Jon",2);
   customerList.addCustomer("Ana",1);
   assertTrue(customerList.find("Jon").equals("Jon(2)"));
}

@Test
public void testNOTPresentCustomer()
{
   customerList.addCustomer("Ana",1);
   customerList.addCustomer("Jane",2);
   customerList.addCustomer("Jon",3);
   assertTrue(customerList.find("Alex") == null);
}

@Test
public void countOne_oneOccurence()
{
   customerList.addCustomer("Ana",1);
   customerList.addCustomer("Jane",2);
   customerList.addCustomer("Jon",3);
   int count = customerList.countBeginsWith("A");
   assertTrue(count == 1);
}

@Test
public void countTwo_twoOccurences()
{
   customerList.addCustomer("Ana",1);
   customerList.addCustomer("Jane",2);
   customerList.addCustomer("Jon",3);
   int count = customerList.countBeginsWith("J");
   assertTrue(count == 2);
}

@Test
public void countTwo_notInList()
{
   customerList.addCustomer("Ana",1);
   customerList.addCustomer("Jane",2);
   customerList.addCustomer("Jon",3);
   int count = customerList.countBeginsWith("K");
   assertTrue(count == 0);
}

/*
 * 
 * Each test method has a MC/DC pair that has one true decision and one false decision with only changing the test condition.
 * 
 * Table of test cases for countOutliers
 * Test  LowerID(a)  HigherID(b)  OddStart(c) OddEnd(d)  (a|b)|(c&d))
 *   a       0            0           0           0           0
 *           1            0           0           0           1
 * 
 *   b       0            0           0           0           0
 *           0            1           0           0           1
 * 
 *   c       0            0           0           1           0
 *           0            0           1           1           1
 * 
 *   d       0            0           1           0           0
 *           0            0           1           1           1
 */

// Note:  
// (1) for the "CustomerList Testing - Junit & Jacoco" assignment, 
//     please keep this method commented. 
//     You do not need to test this method. 
// (2) For the "CustomerList Testing - MCDC" assignment, 
//     please uncomment this method and follow assignment instructions to test it. 
@Test
public void findOutliersSimple1()
{
   customerList.addCustomer("Joe",10);
   customerList.addCustomer("Zachy",12);
   customerList.addCustomer("Jon",13);
   int count = customerList.countOutliers(10,13,"Z","y");
   assertEquals(1,count);
}


// Test for LowerID
// MCDC row: 16 to 8

@Test
public void findOutliersTestLowerID()
{
   customerList.addCustomer("Joe",11);
   customerList.addCustomer("Anne",5);
   int count = customerList.countOutliers(10,13,"Z","y");
   assertEquals(1,count);
}

// Test for HigherID
// MCDC row: 16 and 12
@Test
public void findOutliersTestHigherID()
{
   customerList.addCustomer("Joe",11);
   customerList.addCustomer("Anne",50);
   int count = customerList.countOutliers(10,13,"Z","y");
   assertEquals(1,count);
}

// Test for OddStart
// MCDC row: 15 and 13
@Test
public void findOutliersTestOddStart()
{
   customerList.addCustomer("Joey",11);
   customerList.addCustomer("Zoey",12);
   int count = customerList.countOutliers(10,13,"Z","y");
   assertEquals(1,count);
}

// Test for OddEnd
// MCDC row: 14 and 13
@Test
public void findOutliersTestOddEnd()
{
   customerList.addCustomer("Zoe",11);
   customerList.addCustomer("Zoey",12);
   int count = customerList.countOutliers(10,13,"Z","y");
   assertEquals(1,count);
}

}

