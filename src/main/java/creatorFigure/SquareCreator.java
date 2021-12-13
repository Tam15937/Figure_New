package creatorFigure;

import figures.Figure;
import figures.Square;
import src.Point;

import java.util.ArrayList;

public class SquareCreator extends FigureCreator {
    @Override
    public Figure createFigure() {
        return Square.input();
    }

    @Override
    public Figure createFigure(ArrayList<Point> points) {
        return new Square(points);
    }
}
