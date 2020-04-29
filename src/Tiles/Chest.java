package Tiles;

import Inventory.Slot;
import States.InventoryState;
import States.State;
import main_pack.Game;
import main_pack.Launcher;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Chest extends Tile{
    private Slot[] slots = new Slot[6];
    public Chest(int x, int y, BufferedImage img, boolean isSolid) {
        super(x, y, img, isSolid);
    }
    public void interact() {
        System.out.println("OpenChest");
        State.setState(Game.invenstoryState);
    }

    private void initSlots() {
        for(int i = 0;i<slots.length;i++) {
            slots[i] = new Slot(new Rectangle(i*64,0,64,64));
        }
    }
}
