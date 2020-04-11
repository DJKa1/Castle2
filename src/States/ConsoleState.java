package States;

import main_pack.Game;
import main_pack.GameConsole;

import java.awt.*;

public class ConsoleState extends  State{
    private GameConsole gameConsole;



    public ConsoleState(Game game){
        super(game);
        init();


    }

    @Override
    public void init() {
       gameConsole=game.getGameConsole();

    }

    @Override
    public void tick() {
        game.gameState.tick();

    }

    @Override
    public void render(Graphics g) {
        game.gameState.render(g);
        gameConsole.render(g);
        gameConsole.renderLog(g);
    }
}
