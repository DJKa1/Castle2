package items;

import Inventory.Inventory;

public abstract class Weapons extends Item{
    protected double baseDamage;
    protected int delay=0;
    protected int cooldown;
    public Weapons(Inventory inventory) {
        super(inventory);
        cooldown=0;
    }

    @Override
    public void tick() {
        if (delay < cooldown && delay != 0) {
            delay++;
        } else {
            delay = 0;
        }
    }

    public int getDelay() {
        return delay;
    }

    public int getCooldown() {
        return cooldown;
    }
}
