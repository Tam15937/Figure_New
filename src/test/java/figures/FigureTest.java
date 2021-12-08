package figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.Point;

import java.util.ArrayList;
import java.util.Collections;

class FigureTest {

    @org.junit.jupiter.api.Test
    void calculateSideForFiguresN() {
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(1, 1), new Point(1, 5));
        Circle circle = new Circle(circlePoints);
        circle.calculateSide(circlePoints.get(0),circlePoints.get(1));
        Assertions.assertEquals(circle.radius, 4);
    }

    @Test
    void calculateSideForFiguresNull() {
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(0, 0), new Point(0, 0));
        Circle circle = new Circle(circlePoints);
        circle.calculateSide(circlePoints.get(0),circlePoints.get(1));
        Assertions.assertEquals(circle.radius, 0);
    }
}