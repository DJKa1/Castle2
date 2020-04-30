package Tiles;


import graphics.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DoubleDoor extends Tile {
    public DoubleDoor(int x, int y, BufferedImage img, boolean isSolid) {
        super(x, y, img, isSolid);

    }

    public void open() {
        isSolid = false;
        hitbox = null;


    }
    public void init() {
        hitbox.width = 3;
        hitbox.height = 2;
        BufferedImage result = new BufferedImage((int)(hitbox.width*16), (int)(hitbox.height*16), BufferedImage.TYPE_INT_RGB);//work these out
        Graphics g = result.getGraphics();
        for(int yy = 0;yy<2;yy++){
            for (int xx = 0;xx<3;xx++) {
                g.drawImage(Texture.tiles[xx][yy+8], x+xx*16, y+yy*16, null);
            }
        }
        img = result;

        System.out.println(hitbox);

    }
}
