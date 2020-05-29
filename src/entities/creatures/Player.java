package entities.creatures;

import Buffs.Poison;
import Inventory.Inventory;
import Tiles.Chest;
import Tiles.DoubleDoor;
import Tiles.Tile;
import entities.Vector2D;
import graphics.Animation;


import graphics.Texture;
import items.Armor.Boots;
import items.Item;
import items.LootCreates.OutstandingLootCreate;
import items.Potions.HealPotion;
import items.Quality.Outstanding;
import items.Quality.Primitiv;
import items.Weapons.FabricatedSniper;
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
        manaCount = 200;
        width = (float) 0.8;
        height = (float) 0.8;
        movementRate = (float) 0.1;
        inventory = new Inventory(this);

        playerWalkLeft = new Animation(3, Texture.sprite[8], Texture.sprite[9], Texture.sprite[10], Texture.sprite[11], Texture.sprite[12]);
        playerWalkRight = new Animation(3, Texture.sprite[0], Texture.sprite[1], Texture.sprite[2], Texture.sprite[3], Texture.sprite[4]);
        playerWalkUp = new Animation(3, Texture.sprite[0], Texture.sprite[1], Texture.sprite[2], Texture.sprite[3], Texture.sprite[4]);
        playerWalkDown = new Animation(3, Texture.sprite[0], Texture.sprite[1], Texture.sprite[2], Texture.sprite[3], Texture.sprite[4]);
        idle = new Animation(10, Texture.sprite[0], Texture.sprite[5]);

        animation = new Animation[5];
        animation[0] = idle;
        animation[1] = playerWalkRight;
        animation[2] = playerWalkLeft;
        animation[3] = playerWalkUp;
        animation[4] = playerWalkDown;

        //Test------------------------------------
        /*
        inventory.addItembyID("testWeapon");
        inventory.addItem(new Shotgun(new Outstanding()));
        inventory.addItembyID("IceStorm");
        inventory.addItembyID("AK47");
        FabricatedSniper fabi = new FabricatedSniper(new Outstanding());
        fabi.getQuality().setDmg(4.5f);
        fabi.addAttribute("OFFSET :"+ (int)(fabi.getQuality().getDmg()*100) + "%");
        inventory.addItem(fabi);
        inventory.addItem(new Boots(new Primitiv()));
        for (int i = 0; i < 20; i++) {
            inventory.addItembyID("SniperAmmo");
            inventory.addItembyID("ShotgunAmmo");
            inventory.addItem(new HealPotion(1));
            inventory.addItem(new OutstandingLootCreate());


        }
        addBuff(new Poison(this,300,10));
        addBuff(new Poison(this,300,10));
        */
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
        armorValue=inventory.getArmorValue();
        Item item = inventory.getItem(inventory.getActiveSlot());
        if (KeyboardInput.Keyboard) {
            if (MouseInput.leftPressed&&!MouseInput.interact) {
                if (item != null) {
                    item.use(this);
                }
            }
        } else if (ControllerInput.R1isPressed()) {
            if (item != null) {
                item.use(this);
            }
        }

        if (KeyboardInput.f) {
            getSurroundInterActs();
        }

        aimX = mouseX;
        aimY = mouseY;


    }

    private void getSurroundInterActs() {
        for (int layer = 0; layer < 2; layer++) {
            for (int yy = -1; yy < 2; yy++) {
                for (int xx = -1; xx < 2; xx++) {
                    if ((int) Math.floor(x + xx) >= 0 && (int) Math.floor(y + yy) >= 0) {
                        if (map.getTilebyCords((int) Math.floor(x + xx), (int) Math.floor(y + yy), layer) != null) {
                            Tile tempTile = map.getTilebyCords((int) Math.floor(x + xx), (int) Math.floor(y + yy), layer);
                            if (tempTile.getClass() == Chest.class) {
                                ((Chest) tempTile).interact();
                            } else if (tempTile.getClass() == DoubleDoor.class) {
                                ((DoubleDoor) tempTile).open();
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public void render(Graphics g) {
        animation[animationIndex].drawAnimation(g, getPixelPosition(x), getPixelPosition(y), (Game.UNIT_SCALE));

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
        g.drawString(String.valueOf(hp), Launcher.WIDTH - 100, 100);
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


