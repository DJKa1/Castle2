package items.Munition;

import Inventory.Inventory;
import entities.creatures.Creature;
import graphics.Texture;
import items.Item;

public class SniperAmmo extends Item {


    public SniperAmmo( ){
        super();
        image= Texture.Inventory[1][9];
        stackSize=100;

    }

    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }

}
