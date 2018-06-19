package ru.stga.pft.sandbox;
// структура объектов, хранящих информацию о квадратах
public class Square
{

 public double l;
 public Square (double l)
 {
  this.l = l;
 }

 public  double area() // метод
 {
  return this.l * this.l;
 }

}