package creatorFigure;

import figures.Circle;
import figures.Figure;

public class CircleCreator extends FigureCreator {
    public Figure createFigure() {
        return Circle.input();
    }
}
