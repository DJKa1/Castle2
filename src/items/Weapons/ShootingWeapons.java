package items.Weapons;

import ID_Lists.ID;
import ID_Lists.ItemID;
import Inventory.Inventory;
import Sound.Sound;
import entities.creatures.Creature;
import entities.creatures.Player;
import items.Item;
import items.Quality.Quality;
import items.Weapons.Weapons;


public abstract class ShootingWeapons extends Weapons {

    protected int magazineSize,remainingMunition;
    protected ItemID ammo;

    public ShootingWeapons( Quality quality) {
        super(quality);
    }


    public void use(Creature user) {
        super.use(user);
        if (delay == 0 && remainingMunition > 0) {
            fire(user);
            delay++;
        } else if (delay == 0) {
            if(user.getId()== ID.Player){
                Player p= (Player) user;
                reload(p.getInventory());
                playReloadSound();

            }else {
                remainingMunition=magazineSize;
            }
            delay++;
        }

    }

    protected abstract void fire(Creature k);

    protected abstract void playReloadSound();

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
