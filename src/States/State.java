package States;

import main_pack.Game;

import java.awt.*;

public abstract class State {

    private static State currentState=null;
    protected Game game;


    public State(Game game){
        this.game=game;

    }



    public static State getState(){
        return currentState;
    }
    public static void setState(State state){
        currentState=state;
    }

    public abstract void init();

    public abstract void tick();

    public abstract void render(Graphics g);


}
