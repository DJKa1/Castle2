package Inventory;

import entities.creatures.Player;
import graphics.Animation;
import graphics.Texture;
import items.*;
import main_pack.Launcher;
import main_pack.MouseInput;

import java.awt.*;
import java.util.LinkedList;

public class Inventory {
    private Hotbar hotbar;
    private Player owner;
    public final int xpos = 10, ypos = 60, slotwidth = 200, slotheight = 70;
    public LinkedList<Item> inventoryItems;
    public int activeSlot = 1;
    private int first = 0, last = first + 9;

    private Animation animation;

    private double scale = 4;

    public Inventory(Player owner) {
        this.owner = owner;
        inventoryItems = new LinkedList<>();
        hotbar = new Hotbar(this);
        animation = new Animation(3, Texture.Inventory[0][0], Texture.Inventory[1][0], Texture.Inventory[2][0], Texture.Inventory[3][0], Texture.Inventory[4][0], Texture.Inventory[5][0], Texture.Inventory[6][0], Texture.Inventory[7][0], Texture.Inventory[8][0], Texture.Inventory[9][0], Texture.Inventory[10][0], Texture.Inventory[11][0], Texture.Inventory[12][0], Texture.Inventory[13][0], Texture.Inventory[14][0], Texture.Inventory[15][0], Texture.Inventory[16][0], Texture.Inventory[17][0]);
    }

    public int getActiveSlot() {
        return activeSlot;
    }

    public void setActiveSlot(int n) {
        this.activeSlot = n;
    }

    public Player getOwner() {
        return owner;
    }


    public void tick() {
        animation.runAnimation();

        first = MouseInput.mouseWheelPos;
        last = first + 9;
        for (int i = 0; i < inventoryItems.size(); i++) {
            if (first <= i && i <= last) {
                Item tempItem = inventoryItems.get(i);
                tempItem.tick();
            }
        }
    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scale, scale);
        drawBorader(g2d, 16, 12);
        g2d.scale(1 / scale, 1 / scale);
        //hotbar.render(g);
    }

    private void drawBorader(Graphics2D g, int width, int height) {
        width -= 1;
        height -= 1;

        double transX = Launcher.WIDTH / 2 / scale - (width + 1) / 2 * 16;
        double transY = Launcher.HEIGHT / 2 / scale - (height + 1) / 2 * 16;

        g.translate(transX, transY);

        for (int w = 1; w < width; w++) {
            for (int h = 1; h < height; h++) {
                di(g, w, h, 4, 3);
            }
        }
        for (int w = 0; w < width; w++) {
            for (int i = 0; i < 2; i++) {
                di(g, w, i * height, 4 + i, 1);
            }
        }
        for (int h = 0; h < height; h++) {
            for (int i = 0; i < 2; i++) {
                di(g, i * width, h, 4 + i, 2);
            }
        }

        di(g, 0, 0, 0, 1);
        di(g, 1, 0, 1, 1);
        di(g, 0, 1, 0, 2);
        di(g, 1, 1, 1, 2);

        di(g, width - 1, 0, 2, 1);
        di(g, width, 0, 3, 1);
        di(g, width - 1, 1, 2, 2);
        di(g, width, 1, 3, 2);

        di(g, 0, height - 1, 0, 3);
        di(g, 1, height - 1, 1, 3);
        di(g, 0, height, 0, 4);
        di(g, 1, height, 1, 4);

        di(g, width - 1, height - 1, 2, 3);
        di(g, width, height - 1, 3, 3);
        di(g, width - 1, height, 2, 4);
        di(g, width, height, 3, 4);

        for (int yy = 2; yy < height-1; yy++) {
            for (int xx = 2; xx < width-1; xx++) {
                animation.drawAnimation(g, xx * 16, yy * 16);
            }
        }


        g.translate(-transX, -transY);

    }

    private void di(Graphics g, int x, int y, int tx, int ty) {
        g.drawImage(Texture.Inventory[tx][ty], x * 16, y * 16, null);
    }

    //ItemManagement------------------------------------------
    public void addItembyID(String id) {
        Item item = null;
        switch (id) {
            default:
                return;
            case "testWeapon":
                item = new testWeapon(this);
                break;
            case "shotgun":
                item = new Shotgun(this);
                break;
            case "IceStorm":
                item = new IceStorm(this);
                break;
            case "AK47":
                item = new AK47(this);
                break;
        }
        addItem(item);
    }

    public void addItem(Item item) {
        inventoryItems.add(item);
    }

    public Item getItem(int i) {
        if (i < inventoryItems.size()) {
            return inventoryItems.get(i);
        } else {
            return null;
        }
    }

    public int getItemCount() {
        return inventoryItems.size();
    }

    public void clearInventory() {
        inventoryItems.clear();

    }

    public void switchPositions(int i1, int i2) {
        Item I1 = inventoryItems.get(i1);
        Item I2 = inventoryItems.get(i2);
        inventoryItems.set(i1, I1);
        inventoryItems.set(i2, I2);
    }


}
