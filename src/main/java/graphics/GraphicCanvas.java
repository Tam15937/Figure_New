package graphics;

import figures.Circle;
import figures.Figure;
import src.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicCanvas extends JPanel {
    private ArrayList<Figure> figures;

    public Double multiplierX=1.0;
    public Double multiplierY=1.0;

    public GraphicCanvas(ArrayList<Figure> figures) {
        super();
        this.figures = figures;
    }

    @Override
    public void paintComponent(Graphics g) {

        int graphSizeX = g.getClipBounds().width;
        int graphSizeY = g.getClipBounds().height;
        g.drawRect(0, 1, graphSizeX - 1, graphSizeY - 2);
        g.drawLine(graphSizeX / 2, 0, graphSizeX / 2, graphSizeY);
        g.drawLine(0, graphSizeY / 2, graphSizeX, graphSizeY / 2);

        if (figures.size() > 0) {
            double minX = figures.get(0).getPoints().get(0).getX();
            double minY = figures.get(0).getPoints().get(0).getY();
            double maxX = figures.get(0).getPoints().get(0).getX();
            double maxY = figures.get(0).getPoints().get(0).getY();
            for (Figure figure : figures) {
                if (figure instanceof Circle) {

                    if (figure.getCenter().getX() - ((Circle) figure).radius < minX) minX = figure.getCenter().getX() - ((Circle) figure).getRadius();

                    if (figure.getCenter().getX() + ((Circle) figure).radius > maxX) maxX = figure.getCenter().getX() + ((Circle) figure).getRadius();

                    if (figure.getCenter().getY() - ((Circle) figure).radius < minY) minY = figure.getCenter().getY() - ((Circle) figure).getRadius();

                    if (figure.getCenter().getY() + ((Circle) figure).radius > maxY) maxY = figure.getCenter().getY() + ((Circle) figure).getRadius();
                } else {
                    for (Point point : figure.getPoints()) {
                        if (point.getX() < minX) minX = point.getX();
                        if (point.getX() > maxX) maxX = point.getX();
                        if (point.getY() < minY) minY = point.getY();
                        if (point.getY() > maxY) maxY = point.getY();
                    }
                }
            }
            multiplierX = (this.getWidth() * 0.4 / Math.max(Math.abs(maxX), Math.abs(minX)));
            multiplierY = (this.getHeight() * 0.4 / Math.max(Math.abs(maxY), Math.abs(minY)));


            for (Figure figure : figures) {
                if (figure instanceof Circle) {
                    double multiplier = Math.max(multiplierX, multiplierY);
                    Circle circle = (Circle) figure;
                    Point center = circle.getCenter();
                    int radius = (int) circle.radius;
                    int x = (int) (center.getX() * multiplierX - radius * multiplierX  + graphSizeX / 2);
                    int y = (int) (-center.getY() * multiplierY- radius * multiplierX + graphSizeY / 2);
                    int width = (int) (2 * radius * multiplier);
                    int height = (int )(2 * radius * multiplier);
                    g.drawOval(x, y, width, height);
                } else {
                    for (int i = 0; i < figure.getPoints().size() - 1; i++) {
                        Point first = figure.getPoints().get(i);
                        Point second = figure.getPoints().get(i + 1);
                        int x1 = (int) (first.getX() * multiplierX + graphSizeX / 2);
                        int y1 = (int) (-first.getY() * multiplierY + graphSizeY / 2);
                        int x2 = (int) (second.getX() * multiplierX + graphSizeX / 2);
                        int y2 = (int) (-second.getY() * multiplierY + graphSizeY / 2);
                        g.drawLine(x1, y1, x2, y2);

                    }
                    Point first = figure.getPoints().get(figure.getPoints().size() - 1);
                    Point second = figure.getPoints().get(0);
                    int x1 = (int) (first.getX() * multiplierX + graphSizeX / 2);
                    int y1 = (int) (-first.getY() * multiplierY + graphSizeY / 2);
                    int x2 = (int) (second.getX() * multiplierX + graphSizeX / 2);
                    int y2 = (int) (-second.getY() * multiplierY + graphSizeY / 2);
                    g.drawLine(x1, y1, x2, y2);
                }
            }
        }

    }
    //TODO сделать масштабирование фигур , а так же повернуть график на 90 градусов против часовой стрелки

    public void repaintGraphics(ArrayList<Figure> figures) {
        this.figures = figures;
        this.repaint();
    }
}
