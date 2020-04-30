package States;

import Inventory.Inventory;
import main_pack.Game;

import java.awt.*;

public class InventoryState extends State {
    private Inventory inventory;
    public InventoryState(Game game) {
        super(game);
        init();
    }

    @Override
    public void init() {
        inventory=game.getPlayer().getInventory();
    }

    @Override
    public void tick() {
        Game.gameState.tick();
    }

    @Override
    public void render(Graphics g) {
        Game.gameState.render(g);
        inventory.render(g);
    }
}
