package States;

import main_pack.Game;
import main_pack.Launcher;

import java.awt.*;

public class MenuState extends State {
    public int menuindex = 0;

    public MenuState(Game game) {
        super(game);
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(46, 34, 47));
        g.fillRect(0, 0, Launcher.WIDTH, Launcher.HEIGHT);
        game.menu[menuindex].renderMenu(g);
    }
}
