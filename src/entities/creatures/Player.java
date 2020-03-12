package entities.creatures;
import Inventory.*;
import States.GameState;
import Tiles.Texture;
import entities.ID;
import entities.items.Bow;
import entities.items.SteelSword;
import main_pack.CreatureHandler;
import main_pack.KeyboardInput;

import java.awt.*;

public class Player extends Creature {
    protected Inventory inventory;
    protected HandSlot righthand;
    private final ID[] INCLUDE=null;


    public Player(float x, float y) {
        super(x,y);
        this.hp=10;
        id= ID.Player;
        width=16;
        height=16;
        inventory=new Inventory();
        righthand=new HandSlot(0);
        createHitbox();

    }






    @Override
    public void tick() {


        if (KeyboardInput.up){
            speedY=-1;

        }else if (KeyboardInput.down){
            speedY=+1;

        }else{
            speedY=0;
        }
        if (KeyboardInput.right){
            speedX=+1;

        }else if (KeyboardInput.left){
            speedX=-1;

        }else{
            speedX=0;
        }


        updateHitbox((int) speedX,0);
        if (!checkCollision_ifOneOf(hitbox,ID.Greenslime))
            x+=speedX;
        updateHitbox(0, (int) speedY);
        if (!checkCollision_ifOneOf(hitbox,ID.Greenslime))
            y+=speedY;









        inventory.addItem(new SteelSword(50,50,10));
        inventory.addItem(new Bow(50,50,10));
        inventory.tick();
        righthand.tick();




    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        //g.drawRect((int)x,(int)y,width,height);
        g.drawImage(GameState.texture.sprite[0],(int)x,(int)y,null);
        //righthand.render(g,0,0);
        if (KeyboardInput.e){
            inventory.render(g);
        }
    }

}


