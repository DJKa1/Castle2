package items.Weapons;

import ID_Lists.ItemID;
import Inventory.Inventory;
import entities.creatures.Creature;
import items.Item;
import items.Quality.Quality;
import items.Weapons.Weapons;


public abstract class ShootingWeapons extends Weapons {

    protected int magazineSize,remainingMunition;
    protected ItemID ammo;

    public ShootingWeapons( Quality quality) {
        super(quality);
    }


    public void reload(Inventory inventory){
        Item item=inventory.getItembyId(ammo);
        if(item!=null){
            int i=inventory.getByIndex(item);
            if(item.getAmount()>magazineSize-remainingMunition){
                item.reduceAmount(magazineSize-remainingMunition);
                remainingMunition=magazineSize;
            }
            else {
                remainingMunition=item.getAmount();
                inventory.removeItem(i);
            }
        }


    }
}
