package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 06.04.2017.
 */
public class EquationTests {

   @Test
   public void test0() {
      Equation e = new Equation(1, 1, 1);
      Assert.assertEquals(e.getRootNumber(), 0);
   }

   @Test
   public void test1() {
      Equation e = new Equation(1, 2, 1);
      Assert.assertEquals(e.getRootNumber(), 1);
   }

   @Test
   public void test2() {
      Equation e = new Equation(1, 5, 6);
      Assert.assertEquals(e.getRootNumber(), 2);
   }

   @Test
   public void testLinear() {
      Equation e = new Equation(0, 1, 1);
      Assert.assertEquals(e.getRootNumber(), 1);
   }

   @Test
   public void testConstant() {
      Equation e = new Equation(0, 0, 1);
      Assert.assertEquals(e.getRootNumber(), 0);
   }

   @Test
   public void testZero() {
      Equation e = new Equation(0, 0, 0);
      Assert.assertEquals(e.getRootNumber(), -1);
   }

}
