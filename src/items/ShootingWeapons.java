package items;

import Inventory.Inventory;


public abstract class ShootingWeapons extends Weapons {

    protected int magazineSize,remainingMunition;


    public ShootingWeapons(Inventory inventory) {
        super(inventory);
    }


    public void reload(){
        //reduceMunition magazineSize -remainingMunition
        remainingMunition=magazineSize;
    }
}
