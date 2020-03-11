package States;

import entities.creatures.Player;


import java.awt.*;

public class GameState extends State{
    private Player p1;

    public GameState(){
        p1 =new Player(100,100);


    }


    @Override
    public void tick() {
        p1.tick();


    }

    @Override
    public void render(Graphics g) {
        p1.render(g);


    }
}
