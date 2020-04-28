package Tiles;

import java.awt.image.BufferedImage;

public class DoorTile extends Tile {

    public DoorTile(int x, int y, BufferedImage img, boolean isSolid) {
        super(x, y, img, isSolid);
        interactable=true;
    }


    public void interact(){

    }
}
