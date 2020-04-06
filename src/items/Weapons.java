package items;

import Inventory.Inventory;

public abstract class Weapons extends Item{
    protected double baseDamage;



    public Weapons(Inventory inventory) {
        super(inventory);
    }
}
