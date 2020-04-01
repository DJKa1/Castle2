package Inventory;

import items.Item;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

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

    public void addItembyID(String id) {
        for (int j=0;j <slots.length;j++) {
            if (slots[j].getCurrentItem() != null&&slots[j].getCurrentItem().getId().toString()==id) {
                slots[j].addItemtoStack();
            }
        }
        for (int i=0;i <slots.length;i++){
            if (slots[i].getCurrentItem()==null){

                Item o= null;
                try {
                    o = (Item)Class.forName(id).getConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                slots[i].setCurrentItem(o);
                    return;


            }

        }

    }

    public void addItem(Item item){
        for (int j=0;j <slots.length;j++) {
            if (slots[j].getCurrentItem() != null) {
                if (item.getId() == slots[j].getCurrentItem().getId()) {
                    slots[j].addItemtoStack();
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
    }
}
