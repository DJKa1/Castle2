package States;

import main_pack.Game;
import main_pack.Launcher;

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
        g.setColor(new Color(21, 10, 31));
        g.fillRect(0,0, Launcher.WIDTH,Launcher.HEIGHT);
    }
}
