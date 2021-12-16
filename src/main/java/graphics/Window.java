package graphics;

import creatorFigure.CircleCreator;
import creatorFigure.FigureCreator;
import creatorFigure.SquareCreator;
import creatorFigure.TriangleCreator;
import figures.Figure;
import src.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Window extends JFrame implements ActionListener {

//	JOptionPane.showMessageDialog(new Frame("hgbdnt"), "This is a Message Box.");

    private HashMap<String, HashMap<String, JComponent>> components;
    private HashMap<String, JComponent> addButtonComponents;
    private HashMap<String, JComponent> changeButtonComponents;
    private HashMap<String, JComponent> deleteButtonComponents;

    Figure activeFigure = null;
    boolean chooseFigureAvailable = false;


    private ArrayList<Figure> figures;
    private GraphicCanvas graph;
    public final static String removeButtonName = "removePointButton";
    public final static String mainButtons = "mainButtons";
    public final static String newPointButton = "newPointButton";
    public final static String addButton = "addButton";
    public final static String currentRowId = "currentRowId";
    public final static String doneButton = "doneButton";
    public final static String warningLabel = "warningLabel";

    private Container contentPane;
    private SpringLayout layout;

    Dimension screen = this.getToolkit().getScreenSize();


    public Window(ArrayList<Figure> figures) {
        super("Программа");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(screen.width * 3 / 4, screen.height * 3 / 4);

//        this.setSize(1800, 1000);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
//        this.setUndecorated(true);

        components = new HashMap<>();
        this.figures = figures;
        graph = new GraphicCanvas(figures);

        contentPane = this.getContentPane();
        layout = new SpringLayout();
        addButtonComponents = new HashMap<>();
        deleteButtonComponents = new HashMap<>();
        panel();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                Component c = (Component) evt.getSource();
                c.revalidate();
                c.repaint();
            }
        });

    }

    public void panel() {


        contentPane.setLayout(layout);

        int space = screen.width * 5 / 384;// 25

        graph.setBackground(Color.pink);
        layout.getConstraints(graph).setX(Spring.constant(space));
        layout.getConstraints(graph).setY(Spring.constant(space));
        layout.getConstraints(graph).setWidth(Spring.constant(space * space));
        layout.getConstraints(graph).setHeight(Spring.constant(space * space));
        contentPane.add(graph);

        graph.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int x = (int) ((event.getX() - graph.getWidth() / 2) / graph.multiplierX);
                int y = (int) (-(event.getY() - graph.getHeight() / 2) / graph.multiplierY);
                JOptionPane.showMessageDialog(new Frame("Оповещение"), "x = " + x + " y =" + y);
                activeFigure = defineFigureByCursor(x, y, graph.multiplierX, graph.multiplierY, figures);
                if (chooseFigureAvailable && activeFigure != null) {
                    JOptionPane.showMessageDialog(new Frame("Оповещение"), "Фигура успешно выбрана!");
                    graph.repaintGraphics(new ArrayList<>() {{
                        add(1, activeFigure);
                    }});
                    JLabel areaParameter = new JLabel("Площадь фигуры = " + activeFigure.getArea());
                    JLabel perimetrParametr = new JLabel("Периметр фигуры = " + activeFigure.getPerimetr());
                    JLabel centerParametr = new JLabel("Центр в точке = [ " + activeFigure.getCenter().getX() + ", " + activeFigure.getCenter().getY() + " ]");

                    areaParameter.setBackground(Color.WHITE);
                    layout.getConstraints(areaParameter).setHeight(Spring.constant(space));
                    layout.putConstraint(SpringLayout.NORTH, areaParameter, space, SpringLayout.SOUTH, graph);
                    layout.putConstraint(SpringLayout.WEST, areaParameter, space, SpringLayout.WEST, graph);
                    contentPane.add(areaParameter);
                    revalidate();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        JButton addButton = new JButton("Создать фигуру");
        addButton.addActionListener(this);

        JButton changeButton = new JButton("Изменить фигуру");
        changeButton.addActionListener(this);

        JButton deleteButton = new JButton("Удалить фигуру");
        deleteButton.addActionListener(this);

        JButton exitButton = new JButton("Назад");
        exitButton.addActionListener(this);


        addButton.setBackground(Color.yellow);
        layout.getConstraints(addButton).setHeight(Spring.constant(space));
        layout.getConstraints(addButton).setWidth(Spring.constant(space * 7));
        layout.putConstraint(SpringLayout.WEST, addButton, space, SpringLayout.EAST, graph);
        contentPane.add(addButton);

        changeButton.setBackground(Color.cyan);
        layout.getConstraints(changeButton).setHeight(Spring.constant(space));
        layout.getConstraints(changeButton).setWidth(Spring.constant(space * 7));
        layout.putConstraint(SpringLayout.WEST, changeButton, space, SpringLayout.EAST, addButton);
        contentPane.add(changeButton);

        deleteButton.setBackground(Color.green);
        layout.getConstraints(deleteButton).setHeight(Spring.constant(space));
        layout.getConstraints(deleteButton).setWidth(Spring.constant(space * 7));
        layout.putConstraint(SpringLayout.WEST, deleteButton, space, SpringLayout.EAST, changeButton);
        contentPane.add(deleteButton);

        HashMap<String, JComponent> mainButtons = new HashMap<>();
        mainButtons.put("addButton", addButton);
        mainButtons.put("changeButton", changeButton);
        mainButtons.put("deleteButton", deleteButton);
        mainButtons.put("exitButton", exitButton);
        components.put("mainButtons", mainButtons);

        this.

                revalidate();
        this.

                repaint();

    }

    public Figure defineFigureByCursor(int x, int y, double multiplierX, double multiplierY, ArrayList<Figure> figures) {
        for (Figure figure : figures) {
            if (figure.containPoint(x, y, multiplierX, multiplierY)) return figure;
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (components.get("mainButtons").containsValue(event.getSource())) {

            if (event.getSource().equals(components.get("mainButtons").get("addButton"))) {

                JButton newPointButton = new JButton("+");
                newPointButton.addActionListener(this);

                JButton doneButton = new JButton("Готово");
                doneButton.addActionListener(this);


                JLabel currentRowId = new JLabel("1");
                String id = currentRowId.getText();

                JLabel Xlabel = new JLabel(id + ". X= ");
                JLabel Ylabel = new JLabel("Y= ");
                JTextField Xtext = new JTextField();
                JTextField Ytext = new JTextField();

                int space = screen.width * 5 / 384;// 25


                Xlabel.setBackground(Color.YELLOW);
                layout.getConstraints(Xlabel).setHeight(Spring.constant(space));
                layout.getConstraints(Xlabel).setWidth(Spring.constant(space * 3 / 2));
                layout.putConstraint(SpringLayout.NORTH, Xlabel, space, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                layout.putConstraint(SpringLayout.WEST, Xlabel, space, SpringLayout.EAST, graph);
                contentPane.add(Xlabel);

                Xtext.setBackground(Color.YELLOW);
                layout.getConstraints(Xtext).setHeight(Spring.constant(space));
                layout.getConstraints(Xtext).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, Xtext, 0, SpringLayout.EAST, Xlabel);
                layout.putConstraint(SpringLayout.NORTH, Xtext, space, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(Xtext);

                Ylabel.setBackground(Color.YELLOW);
                layout.getConstraints(Ylabel).setHeight(Spring.constant(space));
                layout.getConstraints(Ylabel).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.NORTH, Ylabel, space, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                layout.putConstraint(SpringLayout.WEST, Ylabel, space / 2, SpringLayout.EAST, Xtext);
                contentPane.add(Ylabel);

                Ytext.setBackground(Color.YELLOW);
                layout.getConstraints(Ytext).setHeight(Spring.constant(space));
                layout.getConstraints(Ytext).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, Ytext, 0, SpringLayout.EAST, Ylabel);
                layout.putConstraint(SpringLayout.NORTH, Ytext, space, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(Ytext);

                newPointButton.setBackground(Color.YELLOW);
                layout.getConstraints(newPointButton).setHeight(Spring.constant(space));
                layout.getConstraints(newPointButton).setWidth(Spring.constant(space * 17 / 10));
                layout.putConstraint(SpringLayout.WEST, newPointButton, space / 2, SpringLayout.EAST, Ytext);
                layout.putConstraint(SpringLayout.NORTH, newPointButton, space, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(newPointButton);

                doneButton.setBackground(Color.YELLOW);
                layout.getConstraints(doneButton).setHeight(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, doneButton, 0, SpringLayout.WEST, Xtext);
                layout.putConstraint(SpringLayout.EAST, doneButton, 0, SpringLayout.EAST, Ytext);
                layout.putConstraint(SpringLayout.NORTH, doneButton, space * 3, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(doneButton);


                addButtonComponents.put("newPointButton", newPointButton);
                addButtonComponents.put("doneButton", doneButton);
                addButtonComponents.put("Xlabel" + id, Xlabel);
                addButtonComponents.put("Ylabel" + id, Ylabel);
                addButtonComponents.put("Xtext" + id, Xtext);
                addButtonComponents.put("Ytext" + id, Ytext);
                addButtonComponents.put("currentRowId", currentRowId);

                components.put("addButtonComponents", addButtonComponents);


                this.revalidate();
            }
            else if (event.getSource().equals(components.get("mainButtons").get("changeButton"))) {

            }
            else if (event.getSource().equals(components.get("mainButtons").get("deleteButton"))) {

                int space = screen.width * 5 / 384;// 25


                chooseFigureAvailable = true;

                JButton delete = new JButton("Удалить");
                delete.addActionListener(this);
                delete.setBackground(Color.GREEN);
                layout.getConstraints(delete).setHeight(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, delete, space, SpringLayout.WEST, components.get("mainButtons").get("deleteButton"));
                layout.putConstraint(SpringLayout.EAST, delete, -space, SpringLayout.EAST, components.get("mainButtons").get("deleteButton"));
                layout.putConstraint(SpringLayout.NORTH, delete, space, SpringLayout.SOUTH, components.get("mainButtons").get("deleteButton"));
                contentPane.add(delete);

                deleteButtonComponents.put("delete", delete);
                components.put("deleteButtonComponents", deleteButtonComponents);
                this.revalidate();
            }
        }
        else if (addButtonComponents.containsValue(event.getSource())) {
            if (event.getSource().equals(addButtonComponents.get("newPointButton"))) {

                int space = screen.width * 5 / 384;// 25


                String id = ((JLabel) (addButtonComponents.get("currentRowId"))).getText();
                int y = addButtonComponents.get("Xlabel" + id).getY() + space;

                id = String.valueOf(Integer.valueOf(id) + 1);
                ((JLabel) (addButtonComponents.get("currentRowId"))).setText(id);


                JLabel Xlabel = new JLabel(id + ". X= ");
                JLabel Ylabel = new JLabel("Y= ");
                JTextField Xtext = new JTextField();
                JTextField Ytext = new JTextField();

                JButton removePointButton = new JButton("-");
                removePointButton.addActionListener(this);


                Xlabel.setBackground(Color.YELLOW);
                layout.getConstraints(Xlabel).setHeight(Spring.constant(space));
                layout.getConstraints(Xlabel).setWidth(Spring.constant(space * 3 / 2));
                layout.putConstraint(SpringLayout.NORTH, Xlabel, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                layout.putConstraint(SpringLayout.WEST, Xlabel, space, SpringLayout.EAST, graph);
                contentPane.add(Xlabel);

                Xtext.setBackground(Color.YELLOW);
                layout.getConstraints(Xtext).setHeight(Spring.constant(space));
                layout.getConstraints(Xtext).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, Xtext, 0, SpringLayout.EAST, Xlabel);
                layout.putConstraint(SpringLayout.NORTH, Xtext, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(Xtext);

                Ylabel.setBackground(Color.YELLOW);
                layout.getConstraints(Ylabel).setHeight(Spring.constant(space));
                layout.getConstraints(Ylabel).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.NORTH, Ylabel, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                layout.putConstraint(SpringLayout.WEST, Ylabel, space / 2, SpringLayout.EAST, Xtext);
                contentPane.add(Ylabel);

                Ytext.setBackground(Color.YELLOW);
                layout.getConstraints(Ytext).setHeight(Spring.constant(space));
                layout.getConstraints(Ytext).setWidth(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, Ytext, 0, SpringLayout.EAST, Ylabel);
                layout.putConstraint(SpringLayout.NORTH, Ytext, y, SpringLayout.SOUTH, components.get("mainButtons").get("addButton"));
                contentPane.add(Ytext);


                removePointButton.setBackground(Color.YELLOW);
                layout.getConstraints(removePointButton).setHeight(Spring.constant(space));
                layout.putConstraint(SpringLayout.WEST, removePointButton, 0, SpringLayout.WEST, addButtonComponents.get("newPointButton"));
                layout.putConstraint(SpringLayout.EAST, removePointButton, 0, SpringLayout.EAST, addButtonComponents.get("newPointButton"));
                layout.putConstraint(SpringLayout.SOUTH, removePointButton, y, SpringLayout.NORTH, components.get("mainButtons").get("addButton"));
                contentPane.add(removePointButton);

                layout.getConstraints(addButtonComponents.get("newPointButton")).setY(Spring.constant(addButtonComponents.get("newPointButton").getY() + space * 2));
                layout.getConstraints(addButtonComponents.get("doneButton")).setY(Spring.constant(addButtonComponents.get("doneButton").getY() + space * 2));

                addButtonComponents.put(removeButtonName + id, removePointButton);
                addButtonComponents.put("Xlabel" + id, Xlabel);
                addButtonComponents.put("Ylabel" + id, Ylabel);
                addButtonComponents.put("Xtext" + id, Xtext);
                addButtonComponents.put("Ytext" + id, Ytext);
                components.put("addButtonComponents", addButtonComponents);


                this.revalidate();

            } else if (event.getSource().equals(addButtonComponents.get("doneButton"))) {
                boolean flag = true;
                for (int i = 1; i <= Integer.valueOf(((JLabel) (addButtonComponents.get("currentRowId"))).getText()); i++) {
                    try {
                        if (addButtonComponents.containsKey("Xtext" + i)) {
                            if (addButtonComponents.get("Xtext" + i).isEnabled()) {
                                double x = Double.parseDouble(((JTextField) (addButtonComponents.get("Xtext" + i))).getText());
                                addButtonComponents.get("Xtext" + i).setBackground(Color.YELLOW);
                            }
                        }

                    } catch (NumberFormatException numb) {
                        flag = false;
                        addButtonComponents.get("Xtext" + i).setBackground(Color.pink);
                        this.revalidate();
                    }
                }
                for (int i = 1; i <= Integer.valueOf(((JLabel) (addButtonComponents.get("currentRowId"))).getText()); i++) {

                    try {
                        if (addButtonComponents.containsKey("Ytext" + i)) {
                            if (addButtonComponents.get("Ytext" + i).isEnabled()) {
                                double y = Double.parseDouble(((JTextField) (addButtonComponents.get("Ytext" + i))).getText());
                                addButtonComponents.get("Ytext" + i).setBackground(Color.YELLOW);
                            }
                        }

                    } catch (NumberFormatException numb) {
                        flag = false;

                        addButtonComponents.get("Ytext" + i).setBackground(Color.pink);
                        this.revalidate();
                    }
                }

                if (flag) {
                    ArrayList<src.Point> points = new ArrayList<>();
                    for (int i = 1; i <= Integer.parseInt(((JLabel) (addButtonComponents.get("currentRowId"))).getText()); i++) {
                        if (addButtonComponents.containsKey("Xtext" + i)) {

                            double x = Double.parseDouble(((JTextField) (addButtonComponents.get("Xtext" + i))).getText());
                            double y = Double.parseDouble(((JTextField) (addButtonComponents.get("Ytext" + i))).getText());


                            points.add(new Point(x, y));
                        }
                    }
                    FigureCreator figureCreator = null;
                    switch (points.size()) {
                        case 2:
                            figureCreator = new CircleCreator();
                            break;

                        case 3:
                            figureCreator = new TriangleCreator();
                            break;

                        case 4:
                            figureCreator = new SquareCreator();
                            break;

                        default:
                            for (JComponent component : addButtonComponents.values()) {
                                contentPane.remove(component);
                            }
                            addButtonComponents.clear();
                            this.revalidate();
                            this.repaint();
                            JOptionPane.showMessageDialog(new Frame("ОПАСНО!"), "Некорректное кол-во точек.");
                            break;
                    }
                    if (figureCreator != null) {
                        Figure figure = figureCreator.createFigure(points);
                        figures.add(figure);
                        for (JComponent component : addButtonComponents.values()) {
                            component.setEnabled(true);
                            contentPane.remove(component);
                        }

                        addButtonComponents.clear();
                        graph.repaintGraphics(figures);
                        //	JOptionPane.showMessageDialog(new Frame("Оповещение"), "Фигура готова!");
                    }
                } else {
                    JOptionPane.showMessageDialog(new Frame("Оповещение"), "Введите координаты фигуры правильно!\n координаты X и Y, должны быть целыми числами!");
                }
                this.revalidate();
                this.repaint();
            } else if (event.getActionCommand().equals("-")) {

                for (Map.Entry<String, JComponent> component : addButtonComponents.entrySet()) {
                    if (component.getValue() instanceof JButton) {
                        if (component.getValue().equals(event.getSource())) {
                            String key = component.getKey();

                            int id = Integer.valueOf(key.substring(key.indexOf(removeButtonName) + removeButtonName.length())) - 1;
                            int idForRemove = Integer.valueOf(key.substring(key.indexOf(removeButtonName) + removeButtonName.length()));

                            addButtonComponents.get("removePointButton" + idForRemove).setEnabled(false);
                            addButtonComponents.get("removePointButton" + idForRemove).setBackground(Color.LIGHT_GRAY);
                            addButtonComponents.remove("removePointButton" + idForRemove);
                            JComponent obj = addButtonComponents.remove("removePointButton" + idForRemove);
                            addButtonComponents.put("remove1", obj);

                            addButtonComponents.get("Xlabel" + id).setEnabled(false);
                            addButtonComponents.get("Xlabel" + id).setBackground(Color.LIGHT_GRAY);
                            obj = addButtonComponents.remove("Xlabel" + id);
                            addButtonComponents.put("remove2", obj);

                            addButtonComponents.get("Ylabel" + id).setEnabled(false);
                            addButtonComponents.get("Ylabel" + id).setBackground(Color.LIGHT_GRAY);
                            obj = addButtonComponents.remove("Ylabel" + id);
                            addButtonComponents.put("remove3", obj);

                            addButtonComponents.get("Xtext" + id).setEnabled(false);
                            addButtonComponents.get("Xtext" + id).setBackground(Color.LIGHT_GRAY);
                            obj = addButtonComponents.remove("Xtext" + id);
                            addButtonComponents.put("remove4", obj);

                            addButtonComponents.get("Ytext" + id).setEnabled(false);
                            addButtonComponents.get("Ytext" + id).setBackground(Color.LIGHT_GRAY);
                            obj = addButtonComponents.remove("Ytext" + id);
                            addButtonComponents.put("remove5", obj);

                            if (addButtonComponents.containsKey("warningLabel" + id)) {
                                addButtonComponents.get("warningLabel" + id).setVisible(false);

                            }
                            this.revalidate();
                            this.repaint();

                        }
                    }
                }


            }

        }
        else if (deleteButtonComponents.containsValue(event.getSource())) {
            if (event.getSource().equals(deleteButtonComponents.get("delete"))) {
                JOptionPane.showMessageDialog(new Frame("Оповещение"), "бум");
                figures.remove(activeFigure);
                this.repaint();
            }
        }
    }
}

