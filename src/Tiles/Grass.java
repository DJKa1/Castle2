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
        g.drawImage(Texture.sprite[16],x*width,y*height,null);
    }
}
