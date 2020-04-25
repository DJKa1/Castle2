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
    public Item[] inventoryItems;
    public int activeSlot = 1;
    private int first = 0, last = first + 9;

    private final int width = 16,height = 12;

    private Animation animation;

    private int prevIndex = 0;
    private Item prevItem = null;

    private double scale = 4;
    private double transX = Launcher.WIDTH / 2 / scale - (width + 1) / 2 * 16;
    private double transY = Launcher.HEIGHT / 2 / scale - (height + 1) / 2 * 16;

    public Inventory(Player owner) {
        this.owner = owner;
        inventoryItems = new Item[12*8];
        clearInventory();
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
        for (int i = 0; i < inventoryItems.length; i++) {
            if (first <= i && i <= last && inventoryItems[i]!=null) {
                Item tempItem = inventoryItems[i];
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
        g2d.translate(transX, transY);

        drawBorader(g2d, width, height);
        renderHotbar(g2d);

        g2d.translate(-transX, -transY);

        if (MouseInput.holdItem!=null) {
            g.drawImage(MouseInput.holdItem.getImage(),(int)(MouseInput.mouseX/scale)-8,(int) (MouseInput.mouseY/scale)-8,null);
        }
        g2d.scale(1 / scale, 1 / scale);



    }

    public Item grabItem(int mouseX, int mouseY) {
        Point point = new Point((int)((mouseX - transX*scale) / scale / 16)-2,(int)((mouseY - transY*scale) / scale / 16)-2);
        Rectangle bounds = new Rectangle(0,0,width-4,height-4);
        if(bounds.contains(point)) {
            //System.out.println(" X: "+point.x+" Y: "+point.y);
            int index = point.y*(width-4)+point.x;
            Item tempItem = inventoryItems[index];
            prevIndex = index;
            prevItem = tempItem;
            inventoryItems[index] = null;
            return tempItem;
        }

        return null;
    }

    public void putItem(int mouseX, int mouseY) {
        Point point = new Point((int)((mouseX - transX*scale) / scale / 16)-2,(int)((mouseY - transY*scale) / scale / 16)-2);
        Rectangle bounds = new Rectangle(0,0,width-4,height-4);
        if(bounds.contains(point)) {
            int index = point.y*(width-4)+point.x;
            switchPositionsByDrag(prevIndex,index);
        }else {
            addItem(MouseInput.holdItem);
        }
    }


    private void renderHotbar(Graphics2D g) {
        for (int y = 0;y<inventoryItems.length/12d;y++) {
            for (int i = 0; i < width-4; i++) {
                if (inventoryItems[y*(width-4)+i]!=null) {
                    g.rotate(Math.toRadians(-45),(i+2) * 16+8, y* 16+2*16+8);
                    g.drawImage(inventoryItems[y*(width-4)+i].getImage(), (i+2) * 16, y* 16+2*16, null);
                    g.rotate(Math.toRadians(45),(i+2) * 16+8, y* 16+2*16+8);
                }
            }
        }
    }

    private void drawBorader(Graphics2D g, int width, int height) {
        width -= 1;
        height -= 1;

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

        for (int yy = 4; yy < height - 1; yy++) {
            for (int xx = 2; xx < width - 1; xx++) {
                //animation.drawAnimation(g, xx * 16, yy * 16);
                di(g, xx, yy, 0, 0);
            }
        }

        for (int i = 0; i < 9; i++) {
            animation.drawAnimation(g, (i + 2) * 16, 2 * 16);
        }
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

    public void addItem(Item item, int index) {
        inventoryItems[index] = item;
    }

    public void addItem(Item item) {
        for (int i = 0; i < inventoryItems.length; i++) {
            if (inventoryItems[i] == null) {
                inventoryItems[i] = item;
                break;
            }
        }
    }

    public Item getItem(int i) {
        if (i < inventoryItems.length) {
            return inventoryItems[i];
        } else {
            return null;
        }
    }

    public int getItemCount() {
        return inventoryItems.length;
    }

    public void clearInventory() {
        for (int i = 0; i<inventoryItems.length;i++) {
            inventoryItems[i] = null;
        }
    }

    public void switchPositionsByDrag(int i1, int i2) {
        Item I1 = prevItem;
        Item I2 = inventoryItems[i2];
        inventoryItems[i1] = I2;
        inventoryItems[i2] = I1;
    }


}
