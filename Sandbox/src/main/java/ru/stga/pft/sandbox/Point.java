package ru.stga.pft.sandbox;

public class Point {

  public double a ;
  public double b ;
  public double c ;
  public double d ;


  public Point(double a, double b, double a1, double b1)
  {
    this.a = a;
    this.b = b;
    this.c = a1;
    this.d = b1;
  }
  public double area()
  {
    return Math.sqrt(Math.pow(this.c - this.a, 2) + Math.pow(this.d - this.b, 2));
  }
}