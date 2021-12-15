package graphics;

import figures.Circle;
import figures.Figure;
import src.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicCanvas extends JPanel {
    private ArrayList<Figure> figures;

    public GraphicCanvas(ArrayList<Figure> figures) {
        super();
        this.figures = figures;
        this.setSize(500,500);
    }


    public void paint(Graphics g) {
        
        int graphSizeX =g.getClipBounds().height;
        int graphSizeY = g.getClipBounds().width;
        g.drawRect(0, 1, graphSizeX - 1, graphSizeY - 2);
        g.drawLine(graphSizeX / 2, 0, graphSizeX / 2, graphSizeY);
        g.drawLine(0, graphSizeY / 2, graphSizeX, graphSizeY / 2);

        for (Figure figure : figures) {
            if (figure instanceof Circle) {
                Circle circle = (Circle) figure;
                src.Point center = circle.getCenter();
                int radius = (int) circle.radius;
                g.drawOval((int) center.getX() - radius + graphSizeX / 2, (int) center.getY() - radius + graphSizeY / 2, 2 * radius, 2 * radius);
            } else {
                for (int i = 0; i < figure.getPoints().size() - 1; i++) {
                    src.Point first = figure.getPoints().get(i);
                    src.Point second = figure.getPoints().get(i + 1);
                    g.drawLine((int) first.getX()+graphSizeX / 2, (int) first.getY()+ graphSizeY / 2, (int) second.getX()+ graphSizeX / 2, (int) second.getY()+ graphSizeY / 2);
                }
                src.Point first = figure.getPoints().get(figure.getPoints().size() - 1);
                Point second = figure.getPoints().get(0);
                g.drawLine((int) first.getX()+graphSizeX / 2, (int) first.getY()+ graphSizeY / 2, (int) second.getX()+graphSizeX / 2, (int) second.getY()+ graphSizeY / 2);
            }
        }
    }


    //TODO сделать масштабирование фигур , а так же повернуть график на 90 градусов против часовой стрелки

    public void repaintGraphics(ArrayList<Figure> figures) {
        this.figures = figures;
        this.repaint();
    }


}
