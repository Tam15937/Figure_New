package creatorFigure;

import figures.Figure;
import figures.Triangle;

public class TriangleCreator extends FigureCreator {
    public Figure createFigure() {
        return Triangle.input();
    }
}
