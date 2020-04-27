package items.Weapons;

import ID_Lists.ItemID;
import Inventory.Inventory;
import items.Item;
import items.Weapons.Weapons;


public abstract class ShootingWeapons extends Weapons {

    protected int magazineSize,remainingMunition;
    protected ItemID ammo;

    public ShootingWeapons( ) {
        super();
    }


    public void reload(){
        /*
        Item item=user.inventory.getItembyId(ammo);
        if(item!=null){
            int i=inventory.getIndex(item);
            if(item.getAmount()>magazineSize-remainingMunition){
                item.reduceAmount(magazineSize-remainingMunition);
                remainingMunition=magazineSize;
            }
            else {
                remainingMunition=item.getAmount();
                inventory.removeItem(i);
            }
        }

         */
    }
}
