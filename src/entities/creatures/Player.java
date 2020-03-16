package entities.creatures;
import Inventory.*;
import States.GameState;
import Tiles.Texture;
import entities.ID;
import entities.Vector2D;
import entities.items.Bow;
import entities.items.SteelSword;
import entities.projectile.Plasmabolt;
import graphics.Animation;
import main_pack.CreatureHandler;
import main_pack.KeyboardInput;
import main_pack.MouseInput;
import main_pack.ProjectileHandler;

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
    private Animation[] animation;
    private int animationIndex = 0;
    private ProjectileHandler projectileHandler;
    private int plasmacooldown;
    private Vector2D move;


    public Player(float x, float y, ProjectileHandler projectileHandler) {
        super(x,y);
        this.hp=10;
        this.projectileHandler=projectileHandler;
        move = new Vector2D(0,0);
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

        animation = new Animation[5];
        animation[0] = idle;
        animation[1] = playerWalkRight;
        animation[2] = playerWalkLeft;
        animation[3] = playerWalkUp;
        animation[4] = playerWalkDown;
    }


    public void firePlasma(float aimX,float aimY){
        projectileHandler.addObject(new Plasmabolt(x,y,aimX,aimY));


    }

    @Override
    public void tick() {


        if (KeyboardInput.up){
            move.y = -1;
            playerWalkUp.runAnimation();
            animationIndex = 3;

        }else if (KeyboardInput.down){
            move.y = 1;
            playerWalkDown.runAnimation();
            animationIndex = 4;

        }else{
            move.y=0;
        }
        if (KeyboardInput.right){
            move.x = 1;
            playerWalkRight.runAnimation();
            animationIndex = 1;

        }else if (KeyboardInput.left){
            move.x = -1;
            playerWalkLeft.runAnimation();
            animationIndex = 2;

        }else{
            move.x = 0;

        }
        if(!(move.x==0||move.y==0)) {
            move.normalize();
        }else if(move.x==0&&move.y==0) {
            idle.runAnimation();
            animationIndex = 0;
        }

        speedX = (float) move.x;
        speedY = (float) move.y;
        System.out.print(String.format("X: %s;Y: %s",speedX,speedY)+"\r");

        int speIntX = (int)speedX;
        int speIntY = (int)speedY;
        if(speedX!=0){
            if(speedX>0){
                speIntX = 1;
            }else {
                speIntX = -1;
            }
        }
        if(speedY!=0){
            if(speedY>0){
                speIntY = 1;
            }else {
                speIntY = -1;
            }
        }

        updateHitbox(speIntX,0);
        if (!checkCollision_ifOneOf(hitbox,ID.Greenslime))
            x+=speedX;
        updateHitbox(0, speIntY);
        if (!checkCollision_ifOneOf(hitbox,ID.Greenslime))
            y+=speedY;

        inventory.addItem(new SteelSword(50,50,10));
        inventory.addItem(new Bow(50,50,10));
        inventory.tick();
        righthand.tick();

        if (MouseInput.leftPressed&&plasmacooldown==0){
            firePlasma(MouseInput.mouseX,MouseInput.mouseY);
            plasmacooldown=10;
        }else if (plasmacooldown>0){
            plasmacooldown--;
        }

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        animation[animationIndex].drawAnimation(g,(int)x,(int)y);
        //righthand.render(g,0,0);
        if (KeyboardInput.e){
            inventory.render(g);
        }
    }

}


