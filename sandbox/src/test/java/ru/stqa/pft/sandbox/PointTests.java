package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance() {
    Point p1 = new Point (3,5);
    Point p2 = new Point (6,9);
    Assert.assertEquals(p1.distance(p2),5.0);
  }
  @Test
  public void testDistance1() {
    Point p1 = new Point (12,12);
    Point p2 = new Point (0,21);
    Assert.assertEquals(p1.distance(p2),15.0);
  }
  @Test
  public void testDistance2() {
    Point p1 = new Point (150,100);
    Point p2 = new Point (30,10);
    Assert.assertEquals(p1.distance(p2),150.0);
  }
  @Test
  public void testDistance3() {
    Point p1 = new Point (33,78);
    Point p2 = new Point (14,98);
    Assert.assertEquals(Math.round(p1.distance(p2)),28);
  }
}
