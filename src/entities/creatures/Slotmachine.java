package entities.creatures;

import graphics.Texture;
import main_pack.Game;

import java.awt.*;

public class Slotmachine extends Creature {
    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
    }
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.SlotMachine[0],getPixelPosition(x),getPixelPosition(y),128*3,128*3,null);
    }
}
