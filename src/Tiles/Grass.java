package Tiles;

import java.awt.*;

public class Grass extends Tile{
    public Grass(int id) {
        super(id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g,int x,int y) {
        g.setColor(Color.green);
        g.fillRect((int)x*width,(int)y*height,width,height);
    }
}
