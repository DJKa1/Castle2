package items.Munition;

import entities.creatures.Creature;
import graphics.Texture;
import items.Item;



public class ShotgunAmmo extends Item {
    public ShotgunAmmo(){
        super();
        image= Texture.sprite[33];
        stackSize=100;
    }
    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }
}
