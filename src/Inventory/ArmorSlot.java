package Inventory;

import items.Armor.Armor;

import java.awt.*;

public class ArmorSlot extends Slot {

    public ArmorSlot(Rectangle bounds) {
        super(bounds);
        itemType= Armor.class;
    }
}
