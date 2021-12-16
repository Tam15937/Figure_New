package figures;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import figureInterface.IMovable;
import figureInterface.IRotateble;
import figureInterface.IScaleble;
import src.Point;
import utils.Utils;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@JsonAutoDetect
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
        @JsonSubTypes.Type(value = Triangle.class, name = "Triangle"),
        @JsonSubTypes.Type(value = Square.class, name = "Rectangle")})

public abstract class Figure implements IMovable, IRotateble, IScaleble , Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    ArrayList<Point> points;
    Point center;
    public double area;
    public double perimetr;

    public Figure(ArrayList<Point> points) {
        this.points = points;
    }


    public ArrayList<Point> getPoints() {
        return this.points;
    }

    public double calculateSide(Point coordinate1, Point coordinate2) {
        return (Math.pow(Math.pow(coordinate2.getX() - coordinate1.getX(), 2) + Math.pow(coordinate2.getY() - coordinate1.getY(), 2), 0.5));
    }

    public void calculateCenter() {
        double sumX = 0, sumY = 0;
        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        center = new Point(sumX / points.size(), sumY / points.size());
    }

    @Override
    public void move(Point step) {
        for (int i = 0; i < points.size(); i++) {
            double x = this.points.get(i).getX() + step.getX();
            double y = this.points.get(i).getY() + step.getY();
            points.get(i).setX(x);
            points.get(i).setY(y);
        }
        calculateCenter();
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String name=getClass().getSimpleName();
        result.append(name);
        for (int i = 0; i < points.size(); i++) {

            result
                    .append("\nКоординаты вершины № ")
                    .append(i + 1)
                    .append(": x=")
                    .append(Utils.decimalFormat.format(points.get(i).getX()))
                    .append(", y=")
                    .append(Utils.decimalFormat.format(points.get(i).getY()))
                    .append(";");
        }
        result.append("\nПлощадь: ").append(area).append("\nПериметр: ").append(perimetr).append("\n");
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return Objects.equals(points, figure.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }

    public Point getCenter() {
        return center;
    }

    public double getArea() {
        return area;
    }

    public double getPerimetr() {
        return perimetr;
    }

    public abstract boolean containPoint (int x, int y, double  multiplierX, double multiplierY);
}
