package entities.creatures;

import entities.ID;
import main_pack.Game;

import java.awt.*;

public class GreenSlime extends Creature {



    public GreenSlime(float x, float y) {
        super(x, y);
        id= ID.Greenslime;
        width= 1;
        height=1;
        baseDmg=1;
        hp=5;
        createHitbox();
    }

    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.green);
        g.drawRect(getPixelPosition(x),getPixelPosition(y),getPixelPosition(width),getPixelPosition(height));


    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        drawHitbox(g);

    }
}
