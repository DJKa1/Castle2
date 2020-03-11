package Inventory;

import entities.items.Item;

import java.awt.*;

public class Inventory {

    private InvSlot[] slots;
    private double invWeight;
    private float x=100,y=100;
    protected int slotwidth=10,slotheight=10;


    public Inventory(){
        slots=new InvSlot[16];
        for (int i=0;i <slots.length;i++){
            slots[i]=new InvSlot(i);

        }


    }

    public void tick(){
        invWeight=0;
        for (int i=0;i <slots.length;i++){
            slots[i].tick();
            invWeight+=slots[i].getWeight();

        }
    }

    public void render(Graphics g){
        g.setColor(Color.cyan);
        for (int i=0;i <slots.length;i++){
            slots[i].render(g,(int)x,(int)y);
        }

    }

    public void addItem(Item item){
        for (int j=0;j <slots.length;j++) {
            if (slots[j].getCurrentItem() != null) {
                if (item.getName() == slots[j].getCurrentItem().getName()) {
                    slots[j].addItemtoStack(item);
                    return;
                }
            }
        }
        for (int i=0;i <slots.length;i++){
            if (slots[i].getCurrentItem()==null){
                slots[i].setCurrentItem(item);
                return;
            }

        }
        //InvFull



    }
}
