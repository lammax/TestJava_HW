package ru.stqa.pft.sandbox;

public class Rectangle {

   private double a;
   private double b;

   public Rectangle(double a, double b) {
      this.a = a;
      this.b = b;
   }

   public double getArea() {
      return this.a * this.b;
   }

}
