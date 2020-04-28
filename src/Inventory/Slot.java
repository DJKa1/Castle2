package Inventory;

import graphics.Texture;
import items.Item;
import main_pack.MouseInput;

import java.awt.*;

public class Slot {

    public Rectangle bounds;
    public Item item;
    private String identifyer = "null";


    public Slot(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void render(Graphics2D g) {
        g.drawImage(Texture.Inventory[0][0],bounds.x,bounds.y, bounds.width,bounds.height,null);
        if (item!=null) {
            g.rotate(Math.toRadians(-45),bounds.x+bounds.width/2d, bounds.y+bounds.height/2);
            g.drawImage(item.getImage(),bounds.x,bounds.y, bounds.width,bounds.height,null);
            g.rotate(Math.toRadians(45),bounds.x+bounds.width/2d, bounds.y+bounds.height/2);
        }

        /*if(identifyer.equals("last")) {
            g.setColor(Color.RED);
            g.fillRect(bounds.x,bounds.y,4,4);
        }*/
    }

    public void tick() {

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


}
