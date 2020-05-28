package entities.creatures.shop;

import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import main_pack.Game;

import java.awt.*;

public class Shop extends Creature {
    private Animation animation;
    public Shop(float x, float y, Game game) {
        super(x, y, game);
        animation = new Animation(10,Texture.Inventory[23][0],Texture.Inventory[24][0],Texture.Inventory[25][0]);
    }
    public void tick() {
        animation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        animation.drawAnimation(g,getPixelPosition(x+2),getPixelPosition(y+2)+16,128);
        for (int yy = 0;yy<4;yy++) {
            for (int xx = 0;xx<5;xx++) {
                g.drawImage(Texture.Inventory[xx+18][yy],getPixelPosition(x+xx),getPixelPosition(y+yy),128,128,null);
            }
        }

    }
}
