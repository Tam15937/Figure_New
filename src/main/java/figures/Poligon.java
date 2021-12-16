package figures;

import figureInterface.IMovable;
import figureInterface.IRotateble;
import figureInterface.IScaleble;
import src.Point;

import java.util.ArrayList;

public abstract class Poligon extends Figure implements IMovable, IRotateble, IScaleble {

    public Poligon(ArrayList<Point> points) {
        super(points);
        this.calculateCenter();
        this.calculateArea();
        this.calculatePerimetr();
    }

    public void calculateArea() {
        area = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            area += Math.abs(points.get(i).getX() * points.get(i + 1).getY() - points.get(i).getY() * points.get(i + 1).getX());
        }
    }

    public double pow(double a) {
        return a * a;
    }

    public void calculatePerimetr() {
        perimetr = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            perimetr += Math.sqrt(pow(points.get(i + 1).getX() - points.get(i).getX()) + pow(points.get(i + 1).getY() - points.get(i).getY()));
        }
        perimetr += Math.sqrt(pow(points.get(points.size() - 1).getX() - points.get(0).getX()) + pow(points.get(points.size() - 1).getY() - points.get(0).getY()));
    }



    @Override
    public void rotate(double angle) {
        angle = angle * Math.PI / 180;
        for (int i = 0; i < points.size(); i++) {
            double x = (points.get(i).getX() - center.getX()) * Math.cos(angle) - (points.get(i).getY() - center.getY()) * Math.sin(angle);
            double y = (points.get(i).getX() - center.getX()) * Math.sin(angle) + (points.get(i).getY() - center.getY()) * Math.cos(angle);
            points.set(i, new Point(x, y));
        }
    }

    @Override
    public void scale(double scale) {
        for (int i = 0; i < points.size(); i++) {
            double x = (points.get(i).getX() - center.getX()) * scale + center.getX();
            double y = (points.get(i).getY() - center.getY()) * scale + center.getY();
            points.set(i, new Point(x, y));
        }
        calculateArea();
        calculatePerimetr();
    }

    @Override
    public boolean containPoint(int x, int y, double multiplierX, double multiplierY) {
        boolean flag = false;
        for (int i = 0; i < this.getPoints().size(); i++) {
            int j = i == this.getPoints().size() - 1 ? 0 : i + 1;
            double x1 = this.getPoints().get(i).getX() * multiplierX;
            double x2 = this.getPoints().get(j).getX() * multiplierX;
            double y1 = this.getPoints().get(i).getY() * multiplierY;
            double y2 = this.getPoints().get(j).getY() * multiplierY;

            if(x2-x1!=0) {
                double a = (y2 - y1) / (x2 - x1);
                double b = y1 - a * x1;
                if ((Math.abs(y -  (a * x + b)) <= 2)) flag = true;
            }
            else{
                if ((Math.abs(x - x1) <= 2)&&(y>=Math.min(y1, y2) && y<=Math.max(y1, y2))) flag = true;
            }
        }
        return flag;
    }
}
