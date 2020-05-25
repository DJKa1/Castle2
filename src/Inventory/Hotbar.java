package Inventory;

import graphics.Texture;

import java.awt.*;

public class Hotbar {
    private final int lenght = 9, ofs = 2, slotWidth = 68, slotHeight = 68;
    private Color borderColor = Color.BLUE;
    private Inventory inventory;

    public Hotbar(Inventory inventory) {
        this.inventory = inventory;
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < lenght; i++) {

            if (i == inventory.getActiveSlot()) {
                g.drawImage(Texture.Inventory[6][0], i * slotWidth, 0, slotWidth, slotHeight, null);
            }else {
                g.drawImage(Texture.Inventory[0][0], i * slotWidth, 0, slotWidth, slotHeight, null);
            }
            if (i < inventory.slots.length) {
                if (inventory.getItem(i)!=null) {
                    g.drawImage(inventory.getItem(i).getImage(), i * slotWidth + ofs, ofs, slotWidth - ofs * 2, slotHeight - ofs * 2, null);
                }
            }
        }
    }
}
