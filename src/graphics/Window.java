package graphics;

import main_pack.Game;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame jfi;
    private Canvas cvs;
    private int width,height;
    private String title;
    private  Dimension dimension;


    public Window(String title , int width, int height) {
        this.title=title;
        this.width=width;
        this.height=height;


        create_window();

    }
    public void create_window(){
        jfi=new JFrame(title);
        jfi.setSize(width,height);
        jfi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfi.setResizable(false);
        jfi.setVisible(true);
        jfi.setLocationRelativeTo(null);
        cvs=new Canvas();
        dimension=new Dimension(width,height);
        cvs.setPreferredSize(dimension);
        cvs.setMaximumSize(dimension);
        cvs.setMinimumSize(dimension);
        cvs.setFocusable(false);
        jfi.add(cvs);





    }

    public Canvas getCanvas(){
        return cvs;
    }

    public JFrame getJFrame(){
        return jfi;
    }


}
