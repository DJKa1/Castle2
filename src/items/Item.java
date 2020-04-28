package items;
import ID_Lists.ItemID;
import Inventory.Inventory;
import entities.creatures.Creature;
import items.Quality.Quality;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Item {

    protected ItemID id;
    protected int stackSize,amount;
    protected ArrayList<String> attributes;
    protected BufferedImage image;
    protected Quality quality;
    
    public Item(){
        this.id=ItemID.valueOf(this.getClass().getSimpleName());
        attributes = new ArrayList<>();
        stackSize=1;
        amount=1;
        attributes.add(String.valueOf(id));
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

    public Quality getQuality(){
        return quality;
    }

    public abstract void tick();

    public abstract void use(Creature user);

    public ArrayList<String> getAttributes() {
        return attributes;
    }
    public void addAttribute(String attribute) {
        attributes.add(attribute);
    }
    public void removeAttribute(String attribute) {
        attributes.remove(attribute);
    }
}
