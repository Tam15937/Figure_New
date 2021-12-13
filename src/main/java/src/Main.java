package src;

import creatorFigure.CircleCreator;
import creatorFigure.FigureCreator;
import creatorFigure.SquareCreator;
import creatorFigure.TriangleCreator;
import figures.Circle;
import figures.Figure;
import figures.Square;
import figures.Triangle;
import graphics.Window;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main extends JFrame {
    private static final File file = new File("figures.txt");


    private static final String mainMenu = "Выберете действие:\n" +
            "\n1. Вывести все фигуры\n" +
            "2. Добавить фигуру\n" +
            "3. Изменить фигуру\n" +
            "4. Удалить фигуру\n" +
            "\n0. Выход\n";
    private static final String actionSubMenu = "Что требуется сделать с фигурой?\n" +
            "\n1. Сдвинуть\n" +
            "2. Масштабировать \n" +
            "3. Повернуть\n";

    public static void main(String[] args) {

        ArrayList<Figure> figures;
        if (file.exists()) {
            figures = initializeFromFile(file);
            if (figures == null) {
                figures = initializeStart();
            }
        } else {
            figures = initializeStart();
        }

        //для отрисовки графического окна
        figures = new ArrayList<Figure>();
        Window window = new Window(figures);


        window.setVisible(true);
//        menu(figures);


    }

    public static void menu(ArrayList<Figure> figures) {

        for (; ; ) {

            System.out.println(mainMenu);

            Scanner in = new Scanner(System.in);
            int action = in.nextInt();

            switch (action) {
                case 0:
                    saveAllToFile(figures, file);
                    System.exit(0);
                    break;
                case 1:
                    printFigures(figures);
                    break;
                case 2:
                    selectFigures(figures);
                    break;
                case 3:
                    changeFigures(figures);
                    break;
                case 4:
                    removeFigures(figures);
                    break;
                default:
                    System.out.println("Введите правильное значение");
                    break;
            }
        }
    }


    public static void printFigures(ArrayList<Figure> figures) {
        int i = 1;
        for (Figure figure : figures) {
            System.out.println("\n" + i + ". " + figure);
            i++;
        }

    }

    public static void selectFigures(ArrayList<Figure> figures) {
        System.out.println("какую фигуру хотите создать");
        FigureCreator figureCreator;
        figureCreator = selectFigure();
        Figure figure = figureCreator.createFigure();
        figures.add(figure);
    }

    public static void changeFigures(ArrayList<Figure> figures) {
        Scanner in = new Scanner(System.in);
        printFigures(figures);
        System.out.println("\nВведите номер изменяемой фигуры");
        int k = in.nextInt();
        printFigure(figures, k - 1);
        System.out.println(actionSubMenu);
        int n = in.nextInt();
        switch (n) {
            case 1:
                System.out.println("\nКуда хотите переместить фигуру?");
                int x = in.nextInt();
                int y = in.nextInt();
                Point step = new Point(x, y);
                figures.get(k - 1).move(step);
                printFigure(figures, k - 1);
                break;
            case 2:
                System.out.println("Задайте масштаб");
                double scale = in.nextDouble();
                figures.get(k - 1).scale(scale);
                printFigure(figures, k - 1);

                break;
            case 3:
                System.out.println("Задайте угол поворота");
                double angle = in.nextDouble();
                figures.get(k - 1).rotate(angle);
                printFigure(figures, k - 1);
                break;
            default:
                break;
        }
    }

    public static void removeFigures(ArrayList<Figure> figures) {
        Scanner in = new Scanner(System.in);
        printFigures(figures);
        System.out.println("Напишите номер удаляемой фигуры");
        int i = in.nextInt();
        figures.remove(i - 1);
        printFigures(figures);
    }

    public static void printFigure(ArrayList<Figure> figures, int k) {
        System.out.println(figures.get(k) + "\n");
    }

    public static FigureCreator selectFigure() {
        System.out.println("Выберите фигуру");
        System.out.println("1. Кружочек");
        System.out.println("2. Треугольничек");
        System.out.println("3. Прямоугольничек");
        FigureCreator figureCreator;
        Scanner in = new Scanner(System.in);
        int action = in.nextInt();
        switch (action) {
            case 1:
                figureCreator = new CircleCreator();
                break;
            case 2:
                figureCreator = new TriangleCreator();
                break;
            case 3:
                figureCreator = new SquareCreator();
                break;
            default:
                System.out.println("Не то значение, введите то что можно");
                return selectFigure();
        }

        return figureCreator;

    }

    private static void saveAllToFile(ArrayList<Figure> figures, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, false));
            oos.writeObject(figures);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Figure> initializeFromFile(File file) {
        ArrayList<Figure> figures = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            figures = (ArrayList<Figure>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return figures;
    }

    private static ArrayList<Figure> initializeStart() {

        ArrayList<Point> trianglePoints = new ArrayList<>();
        Collections.addAll(trianglePoints, new Point(3, 2), new Point(3, 8), new Point(10, 8));
        Triangle triangle = new Triangle(trianglePoints);

        ArrayList<Point> squarePoints = new ArrayList<>();
        Collections.addAll(squarePoints, new Point(3, 2), new Point(3, 8), new Point(10, 8), new Point(10, 2));
        Square square = new Square(squarePoints);

        ArrayList<Point> circlePoints = new ArrayList<>();
        Collections.addAll(circlePoints, new Point(2, 2), new Point(2, 4));
        Circle circle = new Circle(circlePoints);

        ArrayList<Figure> figures = new ArrayList<>();
        Collections.addAll(figures, triangle, square, circle);
        return figures;
    }
}
