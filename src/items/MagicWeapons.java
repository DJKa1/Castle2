package items;

import Inventory.Inventory;

public abstract class MagicWeapons extends Weapons {
    protected int manacost;

    public MagicWeapons(Inventory inventory) {
        super(inventory);
    }
    public abstract void fire();

    @Override
    public void use() {
        if (delay==0&&user.getManaCount()>manacost){
            fire();
            delay++;
            user.reduceMana(manacost);
        }
    }
}
