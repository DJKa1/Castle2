package items;
import ID_Lists.ItemID;
import Inventory.Inventory;
import entities.creatures.Creature;

import java.awt.image.BufferedImage;

public abstract class Item {

    protected ItemID id;
    protected int stackSize,amount;
    protected BufferedImage image;
    protected Inventory inventory;
    protected Creature user;


    public Item(Inventory inventory){
        this.id=ItemID.valueOf(this.getClass().getSimpleName());
        this.inventory=inventory;
        user=inventory.getOwner();
        stackSize=1;
        amount=1;
    }






    public ItemID getId() {
        return id;
    }

    public int getStackSize() {
        return stackSize;
    }

    public int getAmount(){return  amount;}

    public void addAmount(){
        amount++;
    }

    public void addAmount(int i){
        amount=+i;
    }

    public void reduceAmount(int i){
        amount-=i;
    }

    public BufferedImage getImage(){
        return image;
    }

    public abstract void tick();

    public abstract void use();

}
