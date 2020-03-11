package entities.items;

import java.awt.*;

public class SteelSword extends Item{
    protected int dmg;

    public SteelSword(float x, float y, double weight) {
        super(x, y, weight);
        this.name="SteelSword";
        this.dmg=7;

    }

    @Override
    public void renderInv(Graphics g, int xpos, int ypos) {
        g.setColor(Color.green);
        g.drawString("SW",xpos,ypos);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {


    }


}
