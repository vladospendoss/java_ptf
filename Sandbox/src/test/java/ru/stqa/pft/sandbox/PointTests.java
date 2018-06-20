package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stga.pft.sandbox.Point;

public class PointTests
{
  @Test
  public void testArea()
  {
    Point p =  new Point (10, 15 , 15 , 20);
    Assert.assertEquals(p.area(), 7.0710678118654755);
  }
}