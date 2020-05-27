package entities.creatures;

import graphics.Animation;
import graphics.Texture;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Slotmachine extends Creature {
    private Animation animation;
    private boolean rooling = false;
    public Slotmachine(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(3,Texture.SlotMachine[0],Texture.SlotMachine[1],Texture.SlotMachine[2],Texture.SlotMachine[3],Texture.SlotMachine[4],Texture.SlotMachine[5],Texture.SlotMachine[6],Texture.SlotMachine[7],Texture.SlotMachine[8],Texture.SlotMachine[9],Texture.SlotMachine[10],Texture.SlotMachine[11]);
        hitbox = new Rectangle2D.Double(x,y,3,3);
    }
    public void tick() {
        animation.runAnimation();
    }

    private void roll() {}

    @Override
    public void render(Graphics g) {
        animation.drawAnimation(g,getPixelPosition(x),getPixelPosition(y),128*3);
    }
}
