package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by user on 08.04.2017.
 */
public class PrimeTests {

   @Test
   public void testPrimes1() {
      Assert.assertEquals((new Primes()).isPrimeFor(4), false);
   }

   @Test
   public void testPrimesFor() {
      Assert.assertEquals((new Primes()).isPrimeFor(Integer.MAX_VALUE), true);
   }

   @Test
   public void testPrimesForFast() {
      Assert.assertEquals((new Primes()).isPrimeForFast(Integer.MAX_VALUE), true);
   }

   @Test
   public void testPrimesWhile() {
      Assert.assertEquals((new Primes()).isPrimeWhile(Integer.MAX_VALUE), true);
   }

   @Test
   public void testPrimesForLong() {
      long n = Integer.MAX_VALUE;
      Assert.assertEquals((new Primes()).isPrimeFor(n), true);
   }

   @Test
   public void testPrimesWhileLong() {
      long n = Integer.MAX_VALUE;
      Assert.assertEquals((new Primes()).isPrimeWhile(n), true);
   }

   @Test
   public void testPrimes3() {
      Assert.assertEquals((new Primes()).isPrimeFor(23), true);
   }


}
