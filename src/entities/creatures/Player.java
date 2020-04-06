package entities.creatures;
import Inventory.HandSlot;
import Inventory.Inventory;
import States.GameState;
import entities.ID;
import entities.Vector2D;
import entities.projectile.Plasmabolt;
import graphics.Animation;
import main_pack.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
public class Player extends Creature {
    protected Inventory inventory;
    protected HandSlot righthand;
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerWalkUp;
    private Animation playerWalkDown;
    private Animation idle;
    private Animation[] animation;
    private int animationIndex = 0;
    private ProjectileHandler projectileHandler;
    private ID[] blockedby = {ID.Greenslime};
    private Rectangle2D.Double movementhitbox;


    //--------------------------------------------------------
    int cooldown = 0;
    //-------------------------------------------------------

    public Player(float x, float y, ProjectileHandler projectileHandler, CreatureHandler creatureHandler) {
        super(x, y,creatureHandler);
        this.hp = 10;
        this.projectileHandler = projectileHandler;
        move = new Vector2D(0, 0);
        id = ID.Player;
        width = (float)0.8;
        height = (float)0.8;
        movementRate = (float) 0.1;
        inventory = new Inventory();
        righthand = new HandSlot(0);
        hitbox=new Rectangle2D.Double(x,y,width,height);


        movementhitbox=new Rectangle2D.Double(x,y+height,width,height/4);

        playerWalkLeft = new Animation(3, GameState.texture.sprite[8], GameState.texture.sprite[9], GameState.texture.sprite[10], GameState.texture.sprite[11], GameState.texture.sprite[12]);
        playerWalkRight = new Animation(3, GameState.texture.sprite[0], GameState.texture.sprite[1], GameState.texture.sprite[2], GameState.texture.sprite[3], GameState.texture.sprite[4]);
        playerWalkUp = new Animation(3, GameState.texture.sprite[0], GameState.texture.sprite[1], GameState.texture.sprite[2], GameState.texture.sprite[3], GameState.texture.sprite[4]);
        playerWalkDown = new Animation(3, GameState.texture.sprite[0], GameState.texture.sprite[1], GameState.texture.sprite[2], GameState.texture.sprite[3], GameState.texture.sprite[4]);
        idle = new Animation(10, GameState.texture.sprite[0], GameState.texture.sprite[5]);

        animation = new Animation[5];
        animation[0] = idle;
        animation[1] = playerWalkRight;
        animation[2] = playerWalkLeft;
        animation[3] = playerWalkUp;
        animation[4] = playerWalkDown;

        //Test------------------------------------
         inventory.addItembyID("Item.testWeapon");
        //---------------------------------------
    }


    public void firePlasma(float aimX, float aimY) {
        projectileHandler.addObject(new Plasmabolt(x, y, aimX, aimY, projectileHandler));
    }

    public void updateMovementhitbox(float xOffset, float yOffset){
        movementhitbox.setRect(x+xOffset,y+height+yOffset,width,height/4);
    }
    public void normalizeMovementhitbox(){
        movementhitbox.setRect(x,y+height,width,height/4);
    }


    @Override
    public void tick() {
        movement();
        inventory.tick();
        righthand.tick();
        //------------------------------------------------------
        if (MouseInput.leftPressed && cooldown == 0) {
            firePlasma(MouseInput.mouseX, MouseInput.mouseY);
            cooldown = 10;
        } else if (cooldown > 0) {
            cooldown--;
        }
        //------------------------------------------------------
    }

    @Override
    public void render(Graphics g) {
        animation[animationIndex].drawAnimation(g, getPixelPosition(x), getPixelPosition(y), (Game.UNIT_SCALE));

        if (KeyboardInput.e) {
            inventory.render(g);
        }

    }

    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x), getPixelPosition(y), getPixelPosition(width), getPixelPosition(height));

        g.setColor(Color.lightGray);
        g.drawRect(getPixelPosition(x),getPixelPosition(y+width),getPixelPosition(width),getPixelPosition(height/4));
    }

    private void movement() {

        move.x = boolToInt(KeyboardInput.right) - boolToInt(KeyboardInput.left);
        move.y = boolToInt(KeyboardInput.down) - boolToInt(KeyboardInput.up);

        playAnimation(move.x, move.y);

        speedX = (float) (move.x * movementRate);
        speedY = (float) (move.y * movementRate);

        normalizeHitbox();
        normalizeMovementhitbox();

        collision();

        y += speedY;
        x += speedX;




    }

    private void collision() {
        //XOffset-----------------------------------------
        if (speedX != 0) {
            updateHitbox(speedX, 0);
            updateMovementhitbox(speedX,0);

            Creature k = checkCollision_ifOneOf(blockedby);
            if(k!=null){
                float i = getFreeSpaceindirectionX(k.getHitbox());
                if (i != -1) {
                    speedX = i;
                }
            }

            Rectangle2D.Double temp=collisionWithTiles(getTilesinDirection(speedX,0,movementhitbox),movementhitbox);
            if (temp!=null) {
                normalizeMovementhitbox();
                float i = getFreeSpaceindirectionX(movementhitbox, temp);
                if (i != -1) {
                    speedX = i;
                }
            }
        }


        //YOffest---------------------------------------------
        if (speedY != 0) {
            updateHitbox(0, speedY);
            updateMovementhitbox(0,speedY);

            Creature k = checkCollision_ifOneOf( blockedby);
            if(k!=null){
                float i = getFreeSpaceindirectionY(k.getHitbox());
                if (i != -1) {
                    speedY = i;
                }
            }
            Rectangle2D.Double temp=collisionWithTiles(getTilesinDirection(0,speedY,movementhitbox),movementhitbox);
            if(temp!=null){
                normalizeMovementhitbox();
                float i = getFreeSpaceindirectionY(movementhitbox,temp);
                if (i != -1) {
                    speedY = i;
                }
            }
        }

    }
    public void playAnimation(double velX, double velY) {
        if (velY < 0) {
            playerWalkUp.runAnimation();
            animationIndex = 3;
        } else if (velY > 0) {
            playerWalkDown.runAnimation();
            animationIndex = 4;
        }
        if (velX > 0) {
            playerWalkRight.runAnimation();
            animationIndex = 1;
        } else if (velX < 0) {
            playerWalkLeft.runAnimation();
            animationIndex = 2;
        }
        if (!(move.x == 0 || move.y == 0)) {
            move.normalize();
        } else if (move.x == 0 && move.y == 0) {
            idle.runAnimation();
            animationIndex = 0;
        }
    }


}


