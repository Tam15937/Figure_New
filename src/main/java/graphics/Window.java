package graphics;

import figures.Figure;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Window extends JFrame {

    private HashMap<String,ArrayList<JComponent>> components;

    public Window() {
        super("Программа");
        Dimension screen = this.getToolkit().getScreenSize();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(screen.width * 3 / 4, screen.height * 3 / 4);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);



        this.setVisible(true);

    }

    public void panel(ArrayList<Figure> figures) {





        Container contentPane = this.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);


        GraphicCanvas graph = new GraphicCanvas(figures);
        layout.getConstraints(graph).setX(Spring.constant(50));
        layout.getConstraints(graph).setY(Spring.constant(50));
        graph.setBackground(Color.pink );
        contentPane.add(graph);


        JButton addButton = new JButton("Создать фигуру");
        JButton changeButton = new JButton("Изменить фигуру");
        JButton deleteButton = new JButton("Удалить фигуру");
        JButton exitButton = new JButton("Назад");


        addButton.setBackground(Color.yellow);
        layout.getConstraints(addButton).setHeight(Spring.constant(25));
        layout.getConstraints(addButton).setWidth(Spring.constant(200));
        layout.putConstraint(SpringLayout.WEST, addButton, 50, SpringLayout.EAST, graph);


        changeButton.setBackground(Color.cyan);
        layout.getConstraints(changeButton).setHeight(Spring.constant(25));
        layout.getConstraints(changeButton).setWidth(Spring.constant(200));
        layout.putConstraint(SpringLayout.WEST, changeButton, 50, SpringLayout.EAST, addButton);


        deleteButton.setBackground(Color.green);
        layout.getConstraints(deleteButton).setHeight(Spring.constant(25));
        layout.getConstraints(deleteButton).setWidth(Spring.constant(200));
        layout.putConstraint(SpringLayout.WEST, deleteButton, 50, SpringLayout.EAST, changeButton);


        ArrayList<JComponent> mainButtons=new ArrayList<>();
        Collections.addAll(mainButtons,addButton,changeButton,deleteButton,exitButton);


        components.put("mainButtons",mainButtons);


    }
}
