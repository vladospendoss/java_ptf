package ru.stga.pft.sandbox;

import java.sql.SQLOutput;

public class MyFirstProgram {

    public static void main(String[] args) {
//        hello("world");
//        hello("user");
//        hello("Pendos");

//        Square s = new Square(5);
//        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

//        Rectangle r = new Rectangle(4, 6);
//        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p =  new Point (10, 15 , 15 , 20);
        System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p.area());

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}