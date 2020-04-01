package Inventory;


import items.Item;

import java.awt.*;

public class InvSlot {

    protected Item currentItem;
    protected int itemcount;
    protected double weight;
    protected int slotNumber;




    public InvSlot(int slotNumber){
        this.slotNumber=slotNumber;
        this.currentItem=null;
        this.itemcount=0;

    }

    public void tick(){


    }

    public void render(Graphics g, int x, int y){
        g.drawImage(currentItem.getImage(),x,y,null);
    }




    public double getWeight(){
        return weight;
    }

    public Item getCurrentItem(){
        return currentItem;
    }

    public int getItemcount() {
        return itemcount;
    }


    public void addItemtoStack() {
        itemcount++;
    }
    public void setCurrentItem(Item item){
        if (currentItem==null){
            currentItem=item;
            itemcount=1;
        }
        return;


    }

    public void removeItem(){
        if (itemcount>=1){
            itemcount--;
            return;
        }else if (itemcount==1){
            itemcount=0;
            currentItem=null;
        }
    }


}
