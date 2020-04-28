package Inventory;

import items.Item;

import java.awt.*;

public class Hotbar {
    private final int lenght= 9,px=10,py=10,slotWidth=64,slotHeight=64;
    private Color borderColor=Color.BLUE;
    private Inventory inventory;

    public Hotbar(Inventory inventory){
        this.inventory=inventory;
    }
    public void render(Graphics2D g){
        for(int i =0;i<lenght;i++){
            if(i<inventory.slots.length) {
                if (inventory.getItem(i)!=null) {
                    //g.rotate(Math.toRadians(-45),i*slotWidth/2d+px, slotHeight/2d+py);
                    g.drawImage(inventory.getItem(i).getImage(),i*slotWidth+px, py,64,64,null);
                    //g.rotate(Math.toRadians(45),i*slotWidth/2d+px, slotHeight/2d+py);

                    if (inventory.getItem(i).getAmount()>1) {
                        g.setColor(Color.RED);
                        //g.scale(2,2);
                        g.drawString(Integer.toString(inventory.getItem(i).getAmount()),slotWidth*i+px,py+20);
                        //g.scale(1/2d,1/2d);
                    }

                }
            }
            g.setColor(borderColor);
            g.drawRect(px+i*(slotWidth-1),py,slotWidth,slotHeight);
            if(i==inventory.getActiveSlot()){
                g.setColor(Color.BLACK);
                g.drawRect(px+i*(slotWidth-1),py,slotWidth,slotHeight);
            }
        }
    }
}
