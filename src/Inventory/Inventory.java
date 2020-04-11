package Inventory;
import entities.creatures.Player;
import items.Item;
import items.Shotgun;
import items.testWeapon;
import main_pack.MouseInput;

import java.awt.*;
import java.util.LinkedList;

public class Inventory {
    private Hotbar hotbar;
    private Player owner;
    public final int xpos=10,ypos=60,slotwidth=200,slotheight=70;
    public LinkedList<Item> inventoryItems;
    public int activeSlot=1;
    private int first=0,last =first+9;

    public Inventory(Player owner){
        this.owner=owner;
        inventoryItems=new LinkedList<>();
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
        first= MouseInput.mouseWheelPos;
        last=first+9;

        for (int i = 0; i < inventoryItems.size(); i++) {
            if(first<=i&&i<=last) {
                Item tempItem = inventoryItems.get(i);
               tempItem.tick();
            }
        }
    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    public void render(Graphics g) {

            //--------------------------
            for (int i=0;i<last-first;i++){
                g.setColor(Color.BLACK);
                g.drawRect(xpos, ypos + i * slotheight, slotwidth, slotheight);
            }

       hotbar.render(g);
    }

    //ItemManagement------------------------------------------
    public void addItembyID(String id) {
        Item item=null;
        switch (id){
            default:return;
            case "testWeapon": item=new testWeapon(this);break;
            case "shotgun": item=new Shotgun(this);break;

        }
        addItem(item);
    }
    public void addItem(Item item){
        inventoryItems.add(item);
    }
    public Item getItem(int i){
        if(i<inventoryItems.size()){
            return inventoryItems.get(i);
        }else {
            return null;
        }
    }
    public int getItemCount(){
        return inventoryItems.size();
    }

    public void clearInventory(){
        inventoryItems.clear();

    }
    public void switchPositions(int i1,int i2){
        Item I1 =inventoryItems.get(i1);
        Item I2=inventoryItems.get(i2);
        inventoryItems.set(i1,I1);
        inventoryItems.set(i2,I2);
    }




}
