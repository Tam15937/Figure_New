package figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.Point;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CircleTest {

    @org.junit.jupiter.api.Test
    void calculateAreaForCirclePointN() {
        //для круга:
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(0, 0), new Point(1, 0));
        Circle figure = new Circle(circlePoints);
        figure.calculateArea();
        Assertions.assertEquals(figure.area, Math.PI/2);
    }

    @Test
    void calculateAreaForCirclePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(0, 0), new Point(0, 0));
        Circle figure0 = new Circle(circlePoints);
        figure0.calculateArea();
        Assertions.assertEquals(figure0.area, 0);
    }

    @org.junit.jupiter.api.Test
    void calculatePerimetrForCirclePointN() {
        //для круга:
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(0, 0), new Point(1, 0));
        Circle figure = new Circle(circlePoints);
        figure.calculateArea();
        Assertions.assertEquals(figure.perimetr, Math.PI*2);
    }

    @Test
    void calculatePerimetrForCirclePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(0, 0), new Point(0, 0));
        Circle figure0 = new Circle(circlePoints);
        figure0.calculateArea();
        Assertions.assertEquals(figure0.perimetr, 0);
    }
}