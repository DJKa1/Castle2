package Inventory;

import entities.items.Item;

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
        if (currentItem!=null) {
            weight = currentItem.getWeight();


        }

    }

    public void render(Graphics g, int x, int y){

        g.drawRect(x+slotNumber*10,y,10,10);
        if (currentItem!=null) {
            currentItem.renderInv(g,x+slotNumber*10,y);
            if (itemcount!=1){
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(itemcount),x+slotNumber*10+10,y);
            }
        }
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
    public void addItemtoStack(Item item) {
        if (item.getName() == currentItem.getName()) {
            itemcount++;
        }
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
