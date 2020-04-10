package graphics;

import main_pack.Game;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame jfi;
    private Canvas cvs;
    private int width, height;
    private String title;
    private Dimension dimension;
    private Game game;


    public Window(String title, int width, int height, Game game) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.game = game;

        create_window();

    }

    public void create_window() {
        jfi = new JFrame(title);
        jfi.setSize(width, height);
        jfi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfi.setResizable(false);
        jfi.setVisible(true);
        jfi.setLocationRelativeTo(null);
        cvs = new Canvas();
        dimension = new Dimension(width, height);
        cvs.setPreferredSize(dimension);
        cvs.setMaximumSize(dimension);
        cvs.setMinimumSize(dimension);
        cvs.setFocusable(false);
        jfi.add(cvs);
        jfi.addMouseWheelListener(game.getMouseInput());
        /*
        jfi = new JFrame(title);
        jfi.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jfi.setSize(800,800);
        jfi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfi.setUndecorated(true);
        jfi.setResizable(false);
        jfi.setVisible(true);
        jfi.setLocationRelativeTo(null);
        cvs = new Canvas();
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        cvs.setPreferredSize(dimension);
        cvs.setMaximumSize(dimension);
        cvs.setMinimumSize(dimension);
        cvs.setFocusable(false);
        jfi.add(cvs);
        jfi.addMouseWheelListener(game.getMouseInput());
         */
    }

    public void fullscreen() {
    }

    public Canvas getCanvas() {
        return cvs;
    }

    public JFrame getJFrame() {
        return jfi;
    }


}
