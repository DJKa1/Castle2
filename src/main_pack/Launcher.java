package main_pack;

public class Launcher {
    public static int width=1024,height=1024;

    public static void main(String[] args) {
        Game game = new Game (width,height);
        game.start();

    }
}
