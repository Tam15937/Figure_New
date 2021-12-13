package creatorFigure;

import figures.Figure;
import figures.Triangle;
import src.Point;

import java.util.ArrayList;

public class TriangleCreator extends FigureCreator {
    @Override
    public Figure createFigure() {
        return Triangle.input();
    }

    @Override
    public Figure createFigure(ArrayList<Point> points) {
        return new Triangle(points);
    }
}
