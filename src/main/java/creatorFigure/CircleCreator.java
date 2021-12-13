package creatorFigure;

import figures.Circle;
import figures.Figure;
import src.Point;

import java.util.ArrayList;

public class CircleCreator extends FigureCreator {
    @Override
    public Figure createFigure() {
        return Circle.input();
    }

    @Override
    public Figure createFigure(ArrayList<Point> points) {
        return new Circle(points);
    }
}
