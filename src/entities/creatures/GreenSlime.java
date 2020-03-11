package entities.creatures;

import entities.ID;

import java.awt.*;

public class GreenSlime extends Creature {



    public GreenSlime(float x, float y) {
        super(x, y);
        id= ID.Greenslime;
        width=10;
        height=10;
        baseDmg=1;
        hp=5;
        createHitbox();
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect((int)x,+(int)y,width,height);

    }
}
