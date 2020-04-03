package main_pack;

import java.awt.*;

public class Launcher {
    public static int WIDTH =1200, HEIGHT =1200;
    public static String VERSION ="0.0.2";
    public static final boolean enablecheats=true;

    public static void main(String[] args) {
        Game game = new Game (WIDTH, HEIGHT);
        game.start();


    }
}
