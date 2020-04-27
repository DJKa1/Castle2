package items.Weapons;

import Inventory.Inventory;
import entities.creatures.Creature;
import graphics.Texture;
import items.Quality.Quality;


public class EchoStaff extends MagicWeapons {
    public EchoStaff(Quality quality) {
        super(quality);
        image= Texture.sprite[29];
    }

    @Override
    public void fire(Creature user) {

    }


}
