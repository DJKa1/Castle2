package items.Munition;

import Inventory.Inventory;
import graphics.Texture;
import items.Item;

public class SniperAmmo extends Item {


    public SniperAmmo( ){
        super();
        image= Texture.sprite[18];
        stackSize=100;

    }

    @Override
    public void tick() {

    }

    @Override
    public void use() {

    }
}
