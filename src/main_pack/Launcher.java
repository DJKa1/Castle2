package main_pack;

import java.awt.*;

public class Launcher {
    public static int WIDTH =1224, HEIGHT =1224;
    public static String VERSION ="0.0.1";

    public static void main(String[] args) {
        Game game = new Game (WIDTH, HEIGHT);
        game.start();


    }
}
