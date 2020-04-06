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
    public LinkedList<Item> iventoryItems;
    public int activeSlot=1;




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
                Item tempItem = iventoryItems.get(i);
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
        return iventoryItems.get(i);
    }

}
