package Inventory;
import entities.creatures.Player;
import items.Item;
import items.testWeapon;
import main_pack.KeyboardInput;

import java.awt.*;
import java.util.LinkedList;

public class Inventory {
    private Hotbar hotbar;
    private Player owner;
    public final int xpos=10,ypos=10,slotwidth=50,slotheight=50;
    public LinkedList<Item> iventoryItems;
    public int activeSlot=1;
    private int first=-1,last =first+9;





    public Inventory(Player owner){
        this.owner=owner;
        iventoryItems=new LinkedList<>();
        hotbar=new Hotbar(this);
    }

    public int getActiveSlot() {
        return activeSlot;
    }

    public void setActiveSlot(int n) {
        this.activeSlot = n;
    }

    public Player getOwner() {
        return owner;
    }


    public void tick(){
    }

    public void render(Graphics g) {
        if(KeyboardInput.e_s) {
            for (int i = 0; i < iventoryItems.size(); i++) {
                if(first<i&&i<last) {
                    Item tempItem = iventoryItems.get(i);
                    g.drawImage(tempItem.getImage(), xpos, ypos + ((i - first) * slotheight), null);
                    g.setColor(Color.BLACK);
                    g.drawRect(xpos, ypos + ((i - first) * slotheight), slotwidth, slotheight);
                }
            }
        }
        hotbar.render(g);
    }






    //ItemManagement------------------------------------------
    public void addItembyID(String id) {
        Item item=null;
        switch (id){
            case "testWeapon": item=new testWeapon(this);
        }
        addItem(item);
    }
    public void addItem(Item item){
        iventoryItems.add(item);
    }
    public Item getItem(int i){
        if(i<iventoryItems.size()){
            return iventoryItems.get(i);
        }else {
            return null;
        }
    }

}
