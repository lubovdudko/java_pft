package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	public static void main(String[] args) {

    Point p1 = new Point (10,3);
    Point p2 = new Point (0,8);

    System.out.println("Расстояение между P1 с координатами ("+p1.x+","+p1.y+") и P2 с координатами ("+p2.x+","+p2.y+") составляет "+p1.distance(p2));

    /*При использовании функции
    System.out.println("Расстояение между P1 с координатами ("+p1.x+","+p1.y+") и P2 с координатами ("+p2.x+","+p2.y+") составляет "+distance(p1,p2));
     */

  }

  /*Функция Distance
    public static double distance(Point p1, Point p2) {
	  return Math.sqrt((Math.pow((p2.x-p1.x),2))+(Math.pow((p2.y-p1.y),2)));
  }
  */
}