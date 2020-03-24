package Tiles;

import main_pack.Game;

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
        g.drawImage(Texture.sprite[17],x*width,y*height, Game.UNIT_SCALE,Game.UNIT_SCALE,null);

    }
}
