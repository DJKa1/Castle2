package items;
import ID_Lists.ItemID;
import Inventory.Inventory;
import entities.creatures.Creature;

import java.awt.image.BufferedImage;

public abstract class Item {

    protected ItemID id;
    protected int stackSize;
    protected BufferedImage image;
    protected Inventory inventory;
    protected Creature user;

    public Item(Inventory inventory){
        this.id=ItemID.valueOf(this.getClass().getSimpleName());
        this.inventory=inventory;
        user=inventory.getOwner();
    }


    public ItemID getId() {
        return id;
    }

    public int getStackSize() {
        return stackSize;
    }

    public BufferedImage getImage(){
        return image;
    }
    public abstract void tick();

    public abstract void use();

}
