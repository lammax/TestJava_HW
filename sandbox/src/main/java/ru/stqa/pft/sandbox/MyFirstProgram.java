package ru.stqa.pft.sandbox;

class MyFirstProgram {

    public static void main(String[] args) {

        System.out.println("Hello JAVA! =)");


        double l = 8.0;
        double m = l * l;

        System.out.println("Произведение равно = " + m);

        Square s = new Square(5);
        System.out.println("Площадь квадрата равна = " + s.getArea());

        Rectangle r = new Rectangle(5, 10);
        System.out.println("Площадь прямоугольника равна = " + r.getArea());

        Point p1 = new Point(1, 5);
        Point p2 = new Point(10, 30);
        System.out.println("Функция: Расстояние между точками p1 = " + p1.toString() + " и р2 = " + p2.toString() + " равно = " + distance(p1, p2));
        System.out.println("Метод: Расстояние между точками p1 = " + p1.toString() + " и р2 = " + p2.toString() + " равно = " + p1.getDistance(p2));

    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

}