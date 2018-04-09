package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Lubov");

    double l = 5;
    System.out.println("Площадь квадрата со стороной " +l+ " = "+ area(l));

    double a = 4;
    double b = 6;
    System.out.println("Площадь прямоугольника со сторонами " +a+ " и "+b+" = "+ area(a,b));

    /*ДЗ 2

    Point p1 = new Point (10,3);
    Point p2 = new Point (0,8);

    System.out.println("Расстояение между P1 с координатами ("+p1.x+","+p1.y+") и P2 с координатами ("+p2.x+","+p2.y+") составляет "+p1.distance(p2));

    /*При использовании функции
    System.out.println("Расстояение между P1 с координатами ("+p1.x+","+p1.y+") и P2 с координатами ("+p2.x+","+p2.y+") составляет "+distance(p1,p2));
     */
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " +somebody+"!");
  }

  public static double area(double len) {
    return len*len;
  }

  public static double area(double a, double b) {
    return a*b;
  }

  /* Функция Distance
    public static double distance(Point p1, Point p2) {
	  return Math.sqrt((Math.pow((p2.x-p1.x),2))+(Math.pow((p2.y-p1.y),2)));
  */
}