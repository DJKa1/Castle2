package entities.creatures;

import Inventory.*;
import States.GameState;
import entities.ID;
import entities.Vector2D;
import entities.projectile.Plasmabolt;
import graphics.Animation;
import main_pack.Game;
import main_pack.KeyboardInput;
import main_pack.ProjectileHandler;
import java.awt.*;
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
    private int plasmacooldown;
    private Vector2D move;
    private ID[] blockedby={ID.Greenslime};


    //--------------------------------------------------------

    //-------------------------------------------------------


    public Player(float x, float y, ProjectileHandler projectileHandler) {
        super(x,y);
        this.hp = 10;
        this.projectileHandler = projectileHandler;
        move = new Vector2D(0, 0);
        id = ID.Player;
        width = 0.8;
        height = 0.8;
        movementRate=0.1;
        inventory = new Inventory();
        righthand = new HandSlot(0);
        createHitbox();

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
    }


    public void firePlasma(float aimX, float aimY) {
        projectileHandler.addObject(new Plasmabolt(x, y, aimX, aimY));


    }

    @Override
    public void tick() {
        movement();

        //System.out.print(String.format("X: %s;Y: %s", speedX, speedY) + "\r");
        //System.out.print(String.format("X: %s;Y: %s", x, y) + "\r");

        inventory.tick();
        righthand.tick();






    }

    @Override
    public void render(Graphics g) {
        animation[animationIndex].drawAnimation(g, getPixelPosition(x), getPixelPosition(y),(Game.UNIT_SCALE));

        if (KeyboardInput.e) {
            inventory.render(g);
        }

    }

    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x),getPixelPosition(y),getPixelPosition(width),getPixelPosition(height));

    }

    private void movement() {
        move.x = boolToInt(KeyboardInput.right) - boolToInt(KeyboardInput.left);
        move.y = boolToInt(KeyboardInput.down) - boolToInt(KeyboardInput.up);

        playAnimation(move.x,move.y);

        speedX = (float) (move.x*movementRate);
        speedY = (float) (move.y*movementRate);

        collision();






}


    private void collision(){

        if(speedX!=0) {
            normalizeHitbox();
            updateHitbox(speedX, 0);
            //------------------------------------
            if(!isInMap(hitbox)){speedX=0;}

            Creature k = checkCollision_ifOneOf(hitbox, blockedby);
            float i=geFreeSpaceindirectionX(k);
            if(i!=-1){
                speedX=i;
            }
            if(collisionWithNextTile(hitbox,speedX,0)){
                if (speedX<0){
                    speedX= -(float) (x-Math.floor(x));
                }
                else{
                    speedX= (float) ((Math.ceil(x+width))-(x+width));

                }
            }

        }

        if (speedY!=0) {
            normalizeHitbox();
            updateHitbox(0, speedY);
            //--------------------------------------
            if(!isInMap(hitbox)){speedY=0;}




            Creature k = checkCollision_ifOneOf(hitbox, blockedby);
            float i=geFreeSpaceindirectionY(k);
            if(i!=-1){
                speedY=i;
            }

            if(collisionWithNextTile(hitbox,0,speedY)){
                if (speedY<0){
                    speedY= -(float) (y-Math.floor(y));
                }
                else{
                    speedY= (float) ((Math.ceil(y+height))-(y+height));

                }
            }
        }

        y+=speedY;
        x+=speedX;
    }

    private float geFreeSpaceindirectionX(Creature k) {

        if (k != null) {
            if (speedX < 0) {
                if ((x - k.getX() + k.getWidth()) >= 0) {
                    return (float) -(x - (k.getX() + k.getWidth()));
                } else
                    return 0;
            } else if (speedX > 0) {
                if (k.getX() - (x + width) >= 0) {
                    return (float) (k.getX() - (x + width)-0.0001);
                } else
                    return 0;

            }


        }return -1;


    }

        private float geFreeSpaceindirectionY(Creature k){
            if (k!=null){
                if (speedY<0){
                    if((y-k.getY()+k.getHeight())>=0){
                        return (float)-(y-(k.getY()+k.getHeight()));
                    }else
                        return 0;
                }else if(speedY>0){
                    if(k.getY()-(y+height)>=0){
                        return (float)(k.getY()-(y+height));
                    }else
                        return 0;

                }
            }

        return -1;



    }

    public int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
    public void playAnimation(double velX, double velY){
        if (velY<0) {
            playerWalkUp.runAnimation();
            animationIndex = 3;
        } else if (velY>0) {
            playerWalkDown.runAnimation();
            animationIndex = 4;
        }
        if (velX>0) {
            playerWalkRight.runAnimation();
            animationIndex = 1;
        } else if (velX<0) {
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


