package items.Potions;

import entities.creatures.Creature;
import entities.creatures.Player;
import graphics.Texture;
import items.Item;

public abstract class Potion extends Item {
    protected int lvl;


    public Potion(int lvl){
        super();
        this.lvl=lvl;
        stackSize=30;
        attributes.add("Lvl"+ lvl);
    }

    @Override
    public void use(Creature user) {
        ((Player) user).getInventory().removeItem(this);
    }

    @Override
    public void tick() {

    }



}
