package graphics;

import figures.Circle;
import figures.Figure;
import src.Point;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicCanvas extends JFrame {
    private ArrayList<Figure> figures;
    Dimension screen = getToolkit().getScreenSize();

    public GraphicCanvas(ArrayList<Figure> figures) {
        super();
        this.figures = figures;
        this.setSize(screen.width * 3 / 4, screen.height * 3 / 4);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void paint(Graphics g) {
        int windowSizeX = screen.width * 3 / 4;
        int windowSizeY = screen.height * 3 / 4;
        int scale = 2;
        int graphSizeX = windowSizeX / scale;
        int graphSizeY = windowSizeY / scale;
        Graphics area = g.create(windowSizeX - graphSizeX - graphSizeX / 4, graphSizeY / 4, graphSizeX, graphSizeY);
        area.drawRect(0, 1, graphSizeX - 1, graphSizeY - 2);
        area.drawLine(graphSizeX / 2, 0, graphSizeX / 2, graphSizeY);
        area.drawLine(0, graphSizeY / 2, graphSizeX, graphSizeY / 2);


        for (Figure figure : figures) {
            if (figure instanceof Circle) {
                Circle circle = (Circle) figure;
                src.Point center = circle.getCenter();
                int radius = (int) circle.radius;
                area.drawOval((int) center.getX() - radius + graphSizeX / 2, (int) center.getY() - radius + graphSizeY / 2, 2 * radius, 2 * radius);
            } else {
                for (int i = 0; i < figure.getPoints().size() - 1; i++) {
                    src.Point first = figure.getPoints().get(i);
                    src.Point second = figure.getPoints().get(i + 1);
                    area.drawLine((int) first.getX()+graphSizeX / 2, (int) first.getY()+ graphSizeY / 2, (int) second.getX()+ graphSizeX / 2, (int) second.getY()+ graphSizeY / 2);
                }
                src.Point first = figure.getPoints().get(figure.getPoints().size() - 1);
                Point second = figure.getPoints().get(0);
                area.drawLine((int) first.getX()+graphSizeX / 2, (int) first.getY()+ graphSizeY / 2, (int) second.getX()+graphSizeX / 2, (int) second.getY()+ graphSizeY / 2);
            }
        }
    }

    //находит максимальную координату по модулю среди всех фигур
    public void findMaxScale(ArrayList<Figure> figures) {
        int maxX = 0, maxY = 0;
        for (Figure figure : figures)
        {
            for (Point point : figure.getPoints())
            {
                if (Math.abs(point.getX()) > maxX)
                {
                    maxX = Math.abs((int) point.getX());
                }
                if (Math.abs(point.getY()) > maxY)
                {
                    maxY = Math.abs((int) point.getY());
                }
            }
        }
    }

    public void repaintGraphics(ArrayList<Figure> figures) {
        this.figures = figures;
        this.repaint();
    }

}
