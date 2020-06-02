package Inventory;

import graphics.Texture;
import items.Item;
import main_pack.MouseInput;

import java.awt.*;

public class Slot {

    public Rectangle bounds;
    public Item item;
    protected String identifyer = "null";
    protected Class itemType;


    public Slot(Rectangle bounds) {
        this.bounds = bounds;
        itemType=Item.class;
    }

    public void render(Graphics2D g) {
        switch (itemType.getSimpleName())  {
            case "Item" : g.drawImage(Texture.Inventory[0][0],bounds.x,bounds.y, bounds.width,bounds.height,null);break;
            case "Armor": g.drawImage(Texture.Inventory[6][0],bounds.x,bounds.y, bounds.width,bounds.height,null);break;
            default:g.drawImage(Texture.Inventory[0][0],bounds.x,bounds.y, bounds.width,bounds.height,null);break;
        }

        if (item!=null) {
            if(item.isRotate()) {
                g.rotate(Math.toRadians(-45), bounds.x + bounds.width / 2d, bounds.y + bounds.height / 2);
                g.drawImage(item.getImage(), bounds.x, bounds.y, bounds.width, bounds.height, null);
                g.rotate(Math.toRadians(45), bounds.x + bounds.width / 2d, bounds.y + bounds.height / 2);
            }else {
                g.drawImage(item.getImage(), bounds.x, bounds.y, bounds.width, bounds.height, null);
            }

            if (item.getAmount()>1) {
                g.setColor(Color.RED);
                g.scale(2,2);
                g.drawString(Integer.toString(item.getAmount()),bounds.x/2,bounds.y/2+10);
                g.scale(1/2d,1/2d);
            }

        }

        /*if(identifyer.equals("last")) {
            g.setColor(Color.RED);
            g.fillRect(bounds.x,bounds.y,4,4);
        }*/
    }

    public void tick() {

    }

    public boolean isPuttable(Item item){
        if(itemType.isAssignableFrom(item.getClass())){

            return true;
        }
        return false;
    }

    public void setIdentifyer(String identifyer) {
        this.identifyer = identifyer;
    }

    public String getIdentifyer() {
        return identifyer;
    }

    public boolean inBounds(float mx, float my) {
        return bounds.contains(mx,my);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void deleteItem() {
        item=null;
    }

    public Class getItemType() {
        return itemType;
    }

    public void setItemType(Class itemType) {
        this.itemType = itemType;
    }
}
