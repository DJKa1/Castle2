package entities.creatures;
import Handler.CreatureHandler;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import ID_Lists.ProjectileID;
import Inventory.Inventory;
import States.GameState;
import entities.Vector2D;
import entities.projectile.Projectile;
import graphics.Animation;
import items.Item;
import main_pack.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Creature {
    protected Inventory inventory;
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerWalkUp;
    private Animation playerWalkDown;
    private Animation idle;
    private Animation[] animation;
    private int animationIndex = 0;

    public Player(float x, float y, ProjectileHandler projectileHandler, CreatureHandler creatureHandler, Effectshandler effectshandler) {
        super(x, y,creatureHandler,projectileHandler, effectshandler);
        this.hp = 10;
        maxHp=hp;
        manaCount=1000;
        width = (float)0.8;
        height = (float)0.8;
        movementRate = (float) 0.1;
        inventory = new Inventory(this);
        nothitby=new ProjectileID[]{ProjectileID.IceBall,ProjectileID.IceShard,ProjectileID.Plasmabolt,ProjectileID.Shotgunbolt};

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
        inventory.addItembyID("testWeapon");
        inventory.addItembyID("shotgun");
        inventory.addItembyID("IceStorm");
        //---------------------------------------
    }
    public Inventory getInventory(){
        return inventory;
    }


    @Override
    public void tick() {
        tickActiveBuffs();
        movement();
        inventory.tick();

        Item item= inventory.getItem(inventory.getActiveSlot());
        if(KeyboardInput.Keyboard) {
            if (MouseInput.leftPressed) {
                if(item!=null){
                    item.use();
                }
            }
        }else if(ControllerInput.R1isPressed()) {
            if(item!=null){
                item.use();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        animation[animationIndex].drawAnimation(g, getPixelPosition(x), getPixelPosition(y), (Game.UNIT_SCALE));
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldtrans = g2d.getTransform();
        AffineTransform trans = g2d.getTransform();
        Vector2D dir = new Vector2D(MouseInput.mouseX,MouseInput.mouseY);
        trans.rotate(dir.getAngle(), getPixelPosition(x)+64,getPixelPosition(y)+ 64+8);
        g2d.setTransform(trans);

        if(inventory.getItem(inventory.getActiveSlot())!=null){
            g2d.drawImage(inventory.getItem(inventory.getActiveSlot()).getImage(),getPixelPosition(x)+64,getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        }
        g2d.setTransform(oldtrans);
    }

    @Override
    public void updateMovement(){
        if(currentKnockback==null) {
            move.x = KeyboardInput.AxiesLX;
            move.y = KeyboardInput.AxiesLY;
            move.normalize();
        }else {
            getMovementFromKnockBack();
        }


    }

    @Override
    public void movement() {
        updateMovement();
        playAnimation(move.x, move.y);
        speedX = (float) (move.x * movementRate);
        speedY = (float) (move.y * movementRate);
        normalizeHitbox();
        normalizeMovementhitbox();
        collision();

        y += speedY;
        x += speedX;

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


