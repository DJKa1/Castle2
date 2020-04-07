package States;

import main_pack.Game;
import main_pack.Launcher;
import main_pack.Menu;

import java.awt.*;

public class MenuState extends State{
    private Menu menu;

    public MenuState(Game game) {
        super(game);
        init();
    }

    @Override
    public void init() {
        menu = game.getMenu();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(21, 10, 31));
        g.fillRect(0,0, Launcher.WIDTH,Launcher.HEIGHT);
        menu.renderMenu(g);
    }
}
