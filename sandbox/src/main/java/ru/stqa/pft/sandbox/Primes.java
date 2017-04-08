package ru.stqa.pft.sandbox;

/**
 * Created by user on 08.04.2017.
 */
public class Primes {

   public static boolean isPrimeFor(int n) {

      for (int i = 2; i <= (n / 2); i++) {
         if (n % i == 0) {
            return false;
         }
      }

      return true;

   }

   public static boolean isPrimeForFast(int n) {
      int m = (int) Math.sqrt(n);
      for (int i = 2; i <= m; i++) {
         if (n % i == 0) {
            return false;
         }
      }

      return true;

   }


   public static boolean isPrimeFor(long n) {

      for (long i = 2; i <= (n / 2); i++) {
         if (n % i == 0) {
            return false;
         }
      }

      return true;

   }

   public static boolean isPrimeWhile(int n) {

      int i = 2;
      int limit = n / 2 + 1;

      while (i <= limit && n % i != 0) {
         i++;
      }

      return i == limit + 1;

   }

   public static boolean isPrimeWhile(long n) {

      long i = 2;
      long limit = n / 2 + 1;

      while (i <= limit && n % i != 0) {
         i++;
      }

      return i == limit + 1;

   }

}
