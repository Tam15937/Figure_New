package creatorFigure;

import figures.Figure;
import src.Point;

import java.util.ArrayList;

public abstract class FigureCreator {
    public abstract Figure createFigure();

    public abstract Figure createFigure(ArrayList<Point> points);
}