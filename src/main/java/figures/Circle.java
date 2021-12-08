package figures;

import src.Point;
import utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class Circle extends Figure {

    public double radius;

    public Circle(ArrayList<Point> points) {
        super(points);
        this.center = points.get(0);
        calculateRadius();
        calculateArea();
        calculatePerimetr();

    }

    public void calculateRadius() {
        this.radius = calculateSide(points.get(0), points.get(1));
    }

    public void calculatePerimetr() {
        this.perimetr = Math.PI * radius;
    }

    public void calculateArea() {
        this.area = Math.PI * radius * radius/2;
    }

    public static Circle input() {
        ArrayList<Point> points = new ArrayList<Point>();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите координаты центра круга");
        int x = in.nextInt();
        int y = in.nextInt();
        points.add(0, new Point(x, y));
        System.out.println("Введите координаты точки которая будет находиться на круге");
        x = in.nextInt();
        y = in.nextInt();
        points.add(1, new Point(x, y));
        return new Circle(points);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String name=getClass().getSimpleName();
        result
                .append(name)
                .append("\nКоординаты центра круга")
                .append(0)
                .append(": x=")
                .append(Utils.decimalFormat.format(points.get(0).getX()))
                .append(", y=")
                .append(Utils.decimalFormat.format(points.get(0).getY()))
                .append(";")
                .append("\nКоординаты точки на окружности")
                .append(1)
                .append(": x=")
                .append(Utils.decimalFormat.format(points.get(1).getX()))
                .append(", y=")
                .append(Utils.decimalFormat.format(points.get(1).getY()))
                .append(";")
                .append("\nРадиус: ")
                .append(radius)
                .append("\nПлощадь: ")
                .append(area)
                .append("\nПериметр: ")
                .append(perimetr);
        return result.toString();
    }

    @Override
    //TODO
    // не правильный поворот фигуры
    public void rotate(double angle) {
        angle = angle * Math.PI / 180;
        int i=1;
            double x = (points.get(i).getX() - center.getX()) * Math.cos(angle) - (points.get(i).getY() - center.getY()) * Math.sin(angle);
            double y = (points.get(i).getX() - center.getX()) * Math.sin(angle) + (points.get(i).getY() - center.getY()) * Math.cos(angle);
            points.set(i, new Point(x, y));
        }

    public void scale(double scale) {
        this.radius *= scale;
        calculateArea();
        calculatePerimetr();
    }

}
