package entities.creatures.shop;

import graphics.Texture;
import main_pack.MouseInput;

import java.awt.*;

public class ShopButton {
    private int x,y;
    private int iState=0;
    private int indexType;
    private Rectangle hitbox;
    public boolean pressed = false;
    public boolean released = true;
    public ShopButton(int x, int y,int indexType) {
        this.x = x;
        this.y = y;
        this.indexType = indexType;
        hitbox = new Rectangle(x,y,128,128);
    }
    public void mouseIn(int mx, int my) {
        pressed = false;
        if(hitbox.contains(mx,my)) {
            iState = 1;
            if(MouseInput.leftPressed&&released) {
                pressed = true;
                released = false;
            }else if(!MouseInput.leftPressed) {
                released = true;
                pressed = false;
            }else {
                iState = 2;
            }
        }else {
            iState = 0;
            pressed = false;
        }
    }
    public void render(Graphics g) {
        g.drawImage(Texture.Inventory[iState+23][indexType],x,y,128,128,null);
    }
}
