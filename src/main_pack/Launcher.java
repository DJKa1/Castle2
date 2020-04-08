package main_pack;


import java.awt.*;

public class Launcher {
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH =dim.width, HEIGHT =dim.height;
    public static String VERSION ="0.0.2";
    public static final boolean enablecheats=true;

    public static void main(String[] args) {

        Game game = new Game (WIDTH, HEIGHT);
        game.start();


    }
}
