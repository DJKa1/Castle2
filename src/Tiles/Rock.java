package Tiles;

import java.awt.*;

public class Rock extends Tile {
    public Rock(int id) {
        super(id);
        solid=true;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g, int x, int y) {
        g.setColor(Color.darkGray);
        g.fillRect((int)x*width,(int)y*height,width,height);

    }
}
