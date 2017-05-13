package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

   @Test
   public void testDistancePos() {
      Point p1 = new Point(2, 3);
      Point p2 = new Point(50, 3);

      Assert.assertEquals(p1.getDistance(p2), 48.0);
   }

   @Test
   public void testDistanceNeg() {
      Point p1 = new Point(2, 30);
      Point p2 = new Point(5, 30);

      Assert.assertEquals(p1.getDistance(p2), 4.0);
   }

}
