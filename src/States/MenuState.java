package States;

import main_pack.Game;

import java.awt.*;

public class MenuState extends State{


    public MenuState(Game game) {
        super(game);
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);

        g.drawRect(200,200,200,200);
    }
}
