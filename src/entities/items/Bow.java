package entities.items;

import java.awt.*;

public class Bow extends Item {
    public Bow(float x, float y, double weight) {
        super(x, y, weight);
        this.name="Bow";
    }

    @Override
    public void renderInv(Graphics g, int xpos, int ypos) {
        g.setColor(Color.green);
        g.drawString("B",xpos,ypos);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
