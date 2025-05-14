

public class Circle2 extends Circle
{

/**
*  @param x is the x coordinate of the center
*  @param y is the y coordinate of the center
*  @param radius is the radius (assume non-negative)
**/
public Circle2(double x, double y, double radius)
{
   super(x,y,radius);
}

/**
* Test if this circle intersects another circle.
* @param other is the other circle
* @return True if the circles' outer edges intersect
*         at all, False otherwise
**/
public boolean intersects(Circle other)
{
   if (Math.abs(center.x - other.center.x) < (radius+other.radius) &&
       Math.abs(center.y - other.center.y) < (radius+other.radius))
      return true;
   return false;
}

}

