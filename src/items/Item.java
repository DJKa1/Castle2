package items;
import Inventory.Inventory;

import java.awt.image.BufferedImage;

public abstract class Item {

    protected ItemID id;
    protected int stackSize;
    protected BufferedImage image;
    protected Inventory inventory;

    public Item(Inventory inventory){
        this.id=ItemID.valueOf(this.getClass().getSimpleName());
        this.inventory=inventory;
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

    public abstract void use();

    public abstract void tick();
}
