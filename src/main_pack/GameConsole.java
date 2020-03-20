package main_pack;


import java.awt.*;

public class GameConsole {
    protected Game game;
    protected KeyboardInput keyboardInput;

    public GameConsole(Game game){
        this.game=game;
        keyboardInput=game.getKeyboardInput();


    }


    public void  tick(){

    }
    public void render(Graphics g){

    }
}
