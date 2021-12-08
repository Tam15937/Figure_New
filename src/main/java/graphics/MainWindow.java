package graphics;

import figures.Circle;
import figures.Figure;
import figures.Square;
import figures.Triangle;
import src.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MainWindow extends JFrame implements ActionListener {


    public static ArrayList<Figure> initialize() {
        ArrayList<Figure> figures = new ArrayList<Figure>();

        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 30), new Point(40, 30));
        Triangle triangle = new Triangle(trianglePoints);

        ArrayList<Point> squarePoints = new ArrayList<>();
        Collections.addAll(squarePoints, new Point(30, 20), new Point(30, 80), new Point(100, 80), new Point(100, 20));
        Square square = new Square(squarePoints);

        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(20, 20), new Point(20, 40));
        Circle circle = new Circle(circlePoints);

        Collections.addAll(figures, triangle, square, circle);
        return figures;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
