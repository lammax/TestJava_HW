package ru.stqa.pft.sandbox;

import java.io.Serializable;

public class Point implements Serializable {

   private double x;
   private double y;

   public Point (double x, double y) {
      this.x = x;
      this.y = y;
   }

   public double getDistance(Point p) {
      return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
   }

   public double getX() {
      return x;
   }

   public double getY() {
      return y;
   }

   @Override
   public String toString() {
      return "Point{" +
              "x=" + x +
              ", y=" + y +
              '}';
   }

}
