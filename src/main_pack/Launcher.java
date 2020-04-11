package main_pack;


import java.awt.*;

public class Launcher {
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH =1024, HEIGHT =1024;
    //-------------------
    public static String VERSION ="0.0.5";
    public static final boolean enablecheats=true;

    public static void main(String[] args) {

        Game game = new Game (WIDTH, HEIGHT);
        game.start();


    }
}
