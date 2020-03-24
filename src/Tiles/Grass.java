package Tiles;

import main_pack.Game;

import java.awt.*;

public class Grass extends Tile{
    public Grass(int id) {
        super(id);

        solid=false;
    }



    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g,int x,int y) {
        g.drawImage(Texture.sprite[16],x*width,y*height, Game.UNIT_SCALE,Game.UNIT_SCALE,null);
    }
}
