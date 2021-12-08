package figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import src.Point;

import java.util.ArrayList;
import java.util.Collections;

class PoligonTest {


    //test area

    @org.junit.jupiter.api.Test
    void calculateAreaForSquarePointN() {
        //для прямоугольника:
        ArrayList<Point> squarePoints = new ArrayList<>();
        Collections.addAll(squarePoints, new Point(0, 0), new Point(0, 5), new Point(5, 5), new Point(5, 0));
        Square square = new Square(squarePoints);
        square.calculateArea();
        Assertions.assertEquals(square.area, 25);
    }

    @Test
    void calculateAreaForSquarePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> squarePoints0 = new ArrayList<>();
        Collections.addAll(squarePoints0, new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Square square0 = new Square(squarePoints0);
        square0.calculateArea();
        Assertions.assertEquals(square0.area, 0);
    }
    
    @org.junit.jupiter.api.Test
    void calculateAreaForTrianglePointN() {
        //для треугольника:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 3), new Point(4, 0));
        Triangle figure = new Triangle(trianglePoints);
        figure.calculateArea();
        Assertions.assertEquals(figure.area, 6);
    }

    @Test
    void calculateAreaForTrianglePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Triangle figure0 = new Triangle(trianglePoints);
        figure0.calculateArea();
        Assertions.assertEquals(figure0.area, 0);
    }



    //test perimetr


    @org.junit.jupiter.api.Test
    void calculatePerimetrForSquarePointN() {
        ArrayList<Point> squarePoints = new ArrayList<>();
        Collections.addAll(squarePoints, new Point(0, 0), new Point(0, 5), new Point(5, 5), new Point(5, 0));
        Square square = new Square(squarePoints);
        square.calculateArea();
        Assertions.assertEquals(square.perimetr, 20);
    }

    @Test
    void calculatePerimetrForSquarePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> squarePoints0 = new ArrayList<>();
        Collections.addAll(squarePoints0, new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Square square0 = new Square(squarePoints0);
        square0.calculateArea();
        Assertions.assertEquals(square0.perimetr, 0);
    }

    @org.junit.jupiter.api.Test
    void calculatePerimetrForTrianglePointN() {
        //для треугольника:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 3), new Point(4, 0));
        Triangle figure = new Triangle(trianglePoints);
        figure.calculateArea();
        Assertions.assertEquals(figure.perimetr, 12);
    }

    @Test
    void calculatePerimetrForTrianglePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Triangle figure0 = new Triangle(trianglePoints);
        figure0.calculateArea();
        Assertions.assertEquals(figure0.perimetr, 0);
    }




    //test center

    @org.junit.jupiter.api.Test
    void calculateCenterForSquarePointN() {
        ArrayList<Point> squarePoints = new ArrayList<>();
        Collections.addAll(squarePoints, new Point(0, 0), new Point(0, 5), new Point(5, 5), new Point(5, 0));
        Square square = new Square(squarePoints);
        square.calculateArea();
        Point center=new Point(2.5,2.5);
        Assertions.assertEquals(square.center,center );
    }

    @Test
    void calculateCenterForSquarePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> squarePoints0 = new ArrayList<>();
        Collections.addAll(squarePoints0, new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Square square0 = new Square(squarePoints0);
        square0.calculateArea();
        Point center=new Point(0,0);
        Assertions.assertEquals(square0.center,center );
    }

    @org.junit.jupiter.api.Test
    void calculateCenterForTrianglePointN() {
        //для треугольника:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 3), new Point(3, 0));
        Triangle figure = new Triangle(trianglePoints);
        figure.calculateArea();
        Point center=new Point(1,1);
        Assertions.assertEquals(figure.center,center );
    }

    @Test
    void calculateCenterForTrianglePointNull() {
        //если коогрдинаты 0:
        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(0, 0), new Point(0, 0), new Point(0, 0));
        Triangle figure0 = new Triangle(trianglePoints);
        figure0.calculateArea();
        Point center=new Point(0,0);
        Assertions.assertEquals(figure0.center,center );
    }

}