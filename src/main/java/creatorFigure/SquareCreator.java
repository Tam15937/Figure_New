package creatorFigure;

import figures.Figure;
import figures.Square;

public class SquareCreator extends FigureCreator {
    public Figure createFigure() {
        return Square.input();
    }

}
