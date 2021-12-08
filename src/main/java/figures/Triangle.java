package figures;

import src.Point;

import java.util.ArrayList;
import java.util.Scanner;

public class Triangle extends Poligon {


    public Triangle(ArrayList<Point> points) {
        super(points);
    }

    public static Triangle input() {
        ArrayList<Point> points = new ArrayList<Point>();
        System.out.println("Введите координаты фигуры");
        Scanner in = new Scanner(System.in);
        int n = 3;
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " точка ");
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(i, new Point(x, y));
        }
        System.out.println(" Фигура построена! ");
        return new Triangle(points);
    }


}
