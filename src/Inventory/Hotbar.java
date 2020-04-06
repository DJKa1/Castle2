package Inventory;
import items.Item;
import java.awt.*;

public class Hotbar {
    private final int lenght= 9,px=10,py=10,slotWidth=50,slotHeight=50;
    private Color borderColor=Color.BLUE;
    private Inventory inventory;

    public Hotbar(Inventory inventory){
        this.inventory=inventory;

    }


    public void render(Graphics g){
        for(int i =0;i<lenght;i++){
            if(i<inventory.iventoryItems.size()) {
                Item tempItem = inventory.getItem(i);
                if (tempItem != null) {
                    g.drawImage(tempItem.getImage(),px+i*(slotWidth-1), py,null);

                }
            }
            g.setColor(borderColor);
            g.drawRect(px+i*(slotWidth-1),py,slotWidth,slotHeight);
            if(i==inventory.getActiveSlot()-1){
                g.setColor(Color.BLACK);
                g.drawRect(px+i*(slotWidth-1),py,slotWidth,slotHeight);


            }

        }


    }
}
