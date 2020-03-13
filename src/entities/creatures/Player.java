package entities.creatures;
import Inventory.*;
import States.GameState;
import Tiles.Texture;
import entities.ID;
import entities.items.Bow;
import entities.items.SteelSword;
import graphics.Animation;
import main_pack.CreatureHandler;
import main_pack.KeyboardInput;

import java.awt.*;

public class Player extends Creature {
    protected Inventory inventory;
    protected HandSlot righthand;
    private final ID[] INCLUDE=null;

    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerWalkUp;
    private Animation playerWalkDown;
    private Animation idle;


    public Player(float x, float y) {
        super(x,y);
        this.hp=10;
        id= ID.Player;
        width=16;
        height=16;
        inventory=new Inventory();
        righthand=new HandSlot(0);
        createHitbox();

        playerWalkLeft = new Animation(3,GameState.texture.sprite[8],GameState.texture.sprite[9],GameState.texture.sprite[10],GameState.texture.sprite[11],GameState.texture.sprite[12]);
        playerWalkRight = new Animation(3,GameState.texture.sprite[0],GameState.texture.sprite[1],GameState.texture.sprite[2],GameState.texture.sprite[3],GameState.texture.sprite[4]);
        playerWalkUp = new Animation(3,GameState.texture.sprite[0],GameState.texture.sprite[1],GameState.texture.sprite[2],GameState.texture.sprite[3],GameState.texture.sprite[4]);
        playerWalkDown = new Animation(3,GameState.texture.sprite[0],GameState.texture.sprite[1],GameState.texture.sprite[2],GameState.texture.sprite[3],GameState.texture.sprite[4]);
        idle = new Animation(10,GameState.texture.sprite[0],GameState.texture.sprite[5]);

    }






    @Override
    public void tick() {


        if (KeyboardInput.up){
            speedY=-1;
            //playerWalkUp.runAnimation();

        }else if (KeyboardInput.down){
            speedY=+1;
            //playerWalkDown.runAnimation();

        }else{
            speedY=0;
            //idle.runAnimation();
        }
        if (KeyboardInput.right){
            speedX=+1;
            //playerWalkRight.runAnimation();

        }else if (KeyboardInput.left){
            speedX=-1;
            //playerWalkLeft.runAnimation();

        }else{
            speedX=0;
            //idle.runAnimation();
        }
        idle.runAnimation();

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
        //g.drawImage(GameState.texture.sprite[0],(int)x,(int)y,null);
        idle.drawAnimation(g,(int)x,(int)y);
        //righthand.render(g,0,0);
        if (KeyboardInput.e){
            inventory.render(g);
        }
    }

}


