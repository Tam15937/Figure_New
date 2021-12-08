package figures;

import src.Point;

import java.util.ArrayList;
import java.util.Scanner;

public class Square extends Poligon {


    public Square(ArrayList<Point> points) {
        super(points);
    }


    public static Square input() {
        ArrayList<Point> points = new ArrayList<Point>();
        System.out.println("Введите координаты фигуры");
        Scanner in = new Scanner(System.in);
        int n = 4;
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + " точка ");
            int x = in.nextInt();
            int y = in.nextInt();
            points.add(i, new Point(x, y));
        }
        System.out.println(" Фигура построена! ");
        return new Square(points);
    }

}
