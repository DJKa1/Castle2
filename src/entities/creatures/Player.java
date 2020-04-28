package entities.creatures;

import ID_Lists.ProjectileID;
import Inventory.Inventory;
import States.GameState;
import entities.Vector2D;
import graphics.Animation;


import items.Item;
import items.Quality.Outstanding;
import items.Weapons.Shotgun;
import main_pack.*;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class Player extends Creature {
    protected Inventory inventory;
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerWalkUp;
    private Animation playerWalkDown;
    private Animation idle;
    private Animation[] animation;
    private int animationIndex = 0;

    public Player(float x, float y, Game game) {
        super(x, y, game);
        this.hp = 600;
        maxHp = hp;
        manaCount = 1000;
        width = (float) 0.8;
        height = (float) 0.8;
        movementRate = (float) 0.1;
        inventory = new Inventory(this);

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
        inventory.addItem(new Shotgun(new Outstanding()));
        inventory.addItembyID("IceStorm");
        inventory.addItembyID("AK47");
        for(int i =0 ; i<70;i++){
            inventory.addItembyID("SniperAmmo");
            inventory.addItem(new Shotgun(new Outstanding()));
        }
        //---------------------------------------
    }

    public Inventory getInventory() {
        return inventory;
    }


    @Override
    public void tick() {
        hitCooldown--;
        tickActiveBuffs();
        movement();
        inventory.tick();
        Item item = inventory.getItem(inventory.getActiveSlot());
        if (KeyboardInput.Keyboard) {
            if (MouseInput.leftPressed) {
                if (item != null) {
                    item.use(this);
                }
            }
        } else if (ControllerInput.R1isPressed()) {
            if (item != null) {
                item.use(this);
            }
        }

        aimX=mouseX;
        aimY=mouseY;
    }


    @Override
    public void render(Graphics g) {
        animation[animationIndex].drawAnimation(g, getPixelPosition(x), getPixelPosition(y), (Game.UNIT_SCALE));
        renderHealthbar(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldtrans = g2d.getTransform();
        AffineTransform trans = g2d.getTransform();
        Vector2D dir = new Vector2D(MouseInput.mouseX, MouseInput.mouseY);
        trans.rotate(dir.getAngle(), getPixelPosition(x) + 64, getPixelPosition(y) + 64 + 8);
        g2d.setTransform(trans);
        if (inventory.getItem(inventory.getActiveSlot()) != null) {
            g2d.drawImage(inventory.getItem(inventory.getActiveSlot()).getImage(), getPixelPosition(x) + 64, getPixelPosition(y), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
        }
        g2d.setTransform(oldtrans);


    }

    @Override
    public void renderHealthbar(Graphics g) {
        g.setColor(Color.red);
        g.drawString(String.valueOf(hp),Launcher.WIDTH-100,100);
    }

    @Override
    public void updateMovement() {
        if (currentKnockback == null) {
            move.x = KeyboardInput.AxiesLX;
            move.y = KeyboardInput.AxiesLY;
            move.normalize();
        } else {
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
            //move.normalize();
        } else if (move.x == 0 && move.y == 0) {
            idle.runAnimation();
            animationIndex = 0;
        }
    }
}


