
/***
* Example JUnit testing class for Circle2 (and Circle)
***/

// Import all assertions and all annotations
// - see docs for lists and descriptions
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class Circle2Test
{
   // Data you need for each test case
   private Circle2 circle1;
   private Circle2 circle2;
   private Circle2 circle3;
   private Circle2 circle4;
   private Circle2 circle5;

//
// Stuff you want to do before each test case
//
@BeforeEach
public void setup()
{
   System.out.println("\nTest starting...");
   circle1 = new Circle2(1,2,4);
   circle2 = new Circle2(4,5,4);
   circle3 = new Circle2(10,10,4);
   circle4 = new Circle2(-7,5,5);
   circle5 = new Circle2(-7,10,5);

}

//
// Stuff you want to do after each test case
//
@AfterEach
public void teardown()
{
   System.out.println("\nTest finished.");
}

//
// Test a simple (positive, positive) move
//
@Test
public void simpleMovePosPos()
{
   Point p;
   System.out.println("Running test simpleMovePosPos.(positive, positive)");
   p = circle1.moveBy(1,1);
   assertTrue(p.x == 2 && p.y == 3);
}

//
// Test a simple (positive, positive) move
//
@Test
public void constructorTest()
{
   System.out.println("Running test constructorTest");
   Point p;
   p = circle2.center;
   assertTrue(p.x == 4 && p.y == 5 && circle2.radius == 4);
}

//
// Test a simple (negative, positive) move
//
@Test
public void simpleMoveNegPos()
{
   Point p;
   System.out.println("Running test simpleMoveNegPos.(negative, positive)");
   p = circle1.moveBy(-1,1);
   assertTrue(p.x == 0 && p.y == 3);
}

//
// Test a simple (negative, negative) move
//
@Test
public void simpleMoveNegNeg()
{
   Point p;
   System.out.println("Running test simpleMoveNegNeg.(negative, negative)");
   p = circle1.moveBy(-1,-1);
   assertTrue(p.x == 0 && p.y == 1);
}

//
// Test a simple (positive, negative) move
//
@Test
public void simpleMovePosNeg()
{
   Point p;
   System.out.println("Running test simpleMovePosNeg.(positive, negative)");
   p = circle1.moveBy(1,-1);
   assertTrue(p.x == 2 && p.y == 1);
}

//
// Test two circles that do intersect
//
@Test
public void circlesDoIntersect1 ()
{
   System.out.println("Running test circlesDoIntersect1");
   assertTrue(true == circle1.intersects(circle2));
}

//
// Test two circles that do intersect
//
@Test
public void circlesDoIntersect2 ()
{
   System.out.println("Running test circlesDoIntersect2");
   assertTrue(true == circle1.intersects(circle4));
}

//
// Test two circles that do NOT intersect
//
@Test
public void circlesDoNOTIntersect1 ()
{
   System.out.println("Running test circlesDoIntersect1");
   assertTrue(false == circle1.intersects(circle3));
}

//
// Test two circles that do NOT intersect
//
@Test
public void circlesDoNOTIntersect2 ()
{
   System.out.println("Running test circlesDoIntersect2");
   assertTrue(false == circle1.intersects(circle3));
}

//
// Test two circles that do NOT intersect
//
@Test
public void scaleMultiply ()
{
   System.out.println("Running test scaleMultiply");
   circle1.scale(4);
   assertTrue(16 == circle1.radius);
}

//
// Test two circles that do NOT intersect
//
@Test
public void scaleFraction ()
{
   System.out.println("Running test scaleFraction");
   circle1.scale(0.5);
   assertTrue(2 == circle1.radius);
}
}

