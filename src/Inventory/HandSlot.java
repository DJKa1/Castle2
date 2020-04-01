package Inventory;

import main_pack.Launcher;

import java.awt.*;

public class HandSlot extends InvSlot{


    public HandSlot(int slotNumber) {
        super(slotNumber);
    }
    @Override
    public void tick(){

    }
    @Override
    public void render(Graphics g, int x, int y){
        g.setColor(Color.CYAN);
        g.drawRect(Launcher.WIDTH -150, Launcher.HEIGHT -100,80,40);

    }
}
