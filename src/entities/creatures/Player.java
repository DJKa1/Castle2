package entities.creatures;
import Inventory.*;
import entities.ID;
import entities.items.Bow;
import entities.items.SteelSword;
import main_pack.CreatureHandler;
import main_pack.KeyboardInput;

import java.awt.*;

public class Player extends Creature {
    protected Inventory inventory;
    protected HandSlot righthand;


    public Player(float x, float y) {
        super(x,y);
        this.hp=10;
        id= ID.Player;
        width=25;
        height=25;
        friendly=true;
        inventory=new Inventory();
        righthand=new HandSlot(0);
        createHitbox();

    }


    private void collision(){
        for (int i = 0; i < CreatureHandler.creatures.size(); i++) {
            Creature tempObject = CreatureHandler.creatures.get(i);
            if (hitbox.intersects(tempObject.getHitbox())){
                if (!tempObject.getFriendly()){
                    hp-=tempObject.caculateDmg();
                }

            }


        }

    }

    @Override
    public void tick() {
        updateHitbox();

        if (KeyboardInput.up){
            y--;

        }
        if (KeyboardInput.down){
            y++;

        }
        if (KeyboardInput.right){
            x++;

        }
        if (KeyboardInput.left){
            x--;

        }
        inventory.addItem(new SteelSword(50,50,10));
        inventory.addItem(new Bow(50,50,10));
        inventory.tick();
        righthand.tick();
        collision();



    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,width,height);
        righthand.render(g,0,0);
        if (KeyboardInput.e){
            inventory.render(g);
        }
    }

}
