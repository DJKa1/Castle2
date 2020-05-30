package main_pack;

import java.awt.*;

public class Launcher {
    public static final boolean fullscreen = false;
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static int WIDTH =2048, HEIGHT =1024;
    public static double GAMESCALE = 1d;
    //-------------------
    public static String VERSION ="0.0.5";
    public static final boolean enablecheats=true;

    public static Game game = new Game (WIDTH, HEIGHT);

    public static void main(String[] args) {
        if(fullscreen) {
            WIDTH = dim.width;
            HEIGHT = dim.height;
        }
        game.start();
    }
}
