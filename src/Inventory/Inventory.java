package Inventory;

import ID_Lists.ItemID;
import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import items.Item;
import items.Munition.SniperAmmo;
import items.Quality.Extraordinary;
import items.Quality.Primitiv;
import items.Weapons.IceStorm;
import items.Weapons.Shotgun;
import items.Weapons.Weapons;
import items.Weapons.testWeapon;
import main_pack.Game;
import main_pack.Launcher;
import main_pack.MouseInput;

import java.awt.*;

public class Inventory {
    private Hotbar hotbar;
    private Creature owner;
    public final int xpos = 10, ypos = 60, slotwidth = 200, slotheight = 70;
    public Slot[] slots;
    //private Item holdItem = null;
    public int activeSlot = 1;
    private int first = 0, last = first + 9;

    private final int width = 16, height = 12;

    private Animation animation;

    private int prevIndex = 0;
    private Item prevItem = null;

    private double scale = 4;
    private double transX = Launcher.WIDTH / 2 / scale - (width + 1) / 2 * 16;
    private double transY = Launcher.HEIGHT / 2 / scale - (height + 1) / 2 * 16;

    public Inventory(Creature owner) {
        this.owner = owner;
        slots = new Slot[12 * 8 - 15+4];
        init_slots();
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

    public Creature getOwner() {
        return owner;
    }


    public void tick() {
        animation.runAnimation();

        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item != null) {
                Item tempItem = slots[i].item;
                tempItem.tick();
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.scale(scale, scale);
        g2d.translate(transX, transY);
        drawBorader(g2d, width, height);
        g2d.translate(-transX, -transY);
        g2d.scale(1 / scale, 1 / scale);

        for (int i = 0; i < slots.length; i++) {
            slots[i].render(g2d);
        }
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item != null) {
                if(slots[i].item.getQuality()!=null){
                    g.setColor( slots[i].item.getQuality().getColor());
                }
                else {
                    g.setColor(Color.white);
                }
                if (slots[i].inBounds(MouseInput.mouseX, MouseInput.mouseY)) {
                    if (slots[i].item.getAttributes().size() > 0) {
                        g.drawImage(Texture.Inventory[6][1], slots[i].bounds.x + slots[i].bounds.width, slots[i].bounds.y, 64, 64, null);
                        g.drawImage(Texture.Inventory[7][1], slots[i].bounds.x + slots[i].bounds.width * 2, slots[i].bounds.y, 64, 64, null);
                        g.drawImage(Texture.Inventory[6][2], slots[i].bounds.x + slots[i].bounds.width, slots[i].bounds.y + slots[i].bounds.height, 64, 64, null);
                        g.drawImage(Texture.Inventory[7][2], slots[i].bounds.x + slots[i].bounds.width * 2, slots[i].bounds.y + slots[i].bounds.height, 64, 64, null);
                        if (slots[i].item!=null) {
                            for (int e = 0;e<slots[i].item.getAttributes().size();e++) {
                                g.drawString(slots[i].item.getAttributes().get(e), slots[i].bounds.x + slots[i].bounds.width + 8, slots[i].bounds.y+24+30*e);
                            }
                        }
                    }
                }

            }
        }
        if (MouseInput.holdItem != null) {
            g.drawImage(MouseInput.holdItem.getImage(), (int) (MouseInput.mouseX) - 32, (int) (MouseInput.mouseY) - 32, Game.UNIT_SCALE / 2, Game.UNIT_SCALE / 2, null);
        }

    }

    public Hotbar getHotbar() {
        return hotbar;
    }

    public Item grabItem(int mouseX, int mouseY) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].inBounds(mouseX, mouseY)) {
                Item tempItem = slots[i].item;
                slots[i].item = null;
                slots[i].setIdentifyer("last");
                return tempItem;
            }
        }
        return null;
    }

    public void putItem(Item item, int mouseX, int mouseY) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].inBounds(mouseX, mouseY)) {
                if (slots[i].item == null) {
                    slots[i].item = item;
                    clearIdentifyer();
                } else {
                    for (int e = 0; e < slots.length; e++) {
                        if (slots[e].getIdentifyer().equals("last")) {
                            Item tempItem = item;
                            slots[e].item = slots[i].item;
                            slots[i].item = tempItem;
                            clearIdentifyer();
                        }
                    }
                }
            }
        }

        for (int e = 0; e < slots.length; e++) {
            if (slots[e].getIdentifyer().equals("last")) {
                slots[e].item = item;
                break;
            }
        }
        clearIdentifyer();
    }

    private void clearIdentifyer() {
        for (int a = 0; a < slots.length; a++) {
            if (slots[a].getIdentifyer().equals("last")) {
                slots[a].setIdentifyer("null");
            }
        }
    }

    public void deleteItem(float mx, float my) {
        for (int i = 0;i<slots.length;i++) {
            if(slots[i].inBounds(mx,my)) {
                slots[i].deleteItem();
                return;
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


        for (int i = 0; i < 9; i++) {
            animation.drawAnimation(g, (i + 2) * 16, 2 * 16);
        }
    }

    private void di(Graphics g, int x, int y, int tx, int ty) {
        g.drawImage(Texture.Inventory[tx][ty], x * 16, y * 16, null);
    }

    //ItemManagement------------------------------------------
    public void switch_Items(Slot slot0, Slot slot1) {
        Item item0 = slot0.item;
        slot0.item = slot1.item;
        slot1.item = item0;
    }

    public void addItembyID(String id) {
        Item item = null;
        switch (id) {
            default:
                return;
            case "testWeapon":
                item = new testWeapon(new Extraordinary());
                break;
            case "shotgun":
                item = new Shotgun(new Primitiv());
                break;
            case "IceStorm":
                item = new IceStorm(new Primitiv());
                break;

            case "SniperAmmo":
                item = new SniperAmmo();
                break;
        }
        addItem(item);
    }

    public void setItem(Item item, int index) {
        slots[index].item = item;
    }

    public void addItem(Item item) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item != null) {
                if (slots[i].item.getId() == item.getId() && slots[i].item.getAmount() < slots[i].item.getStackSize()) {
                    slots[i].item.addAmount();
                    break;
                }
            } else {
                slots[i].item = item;
                break;
            }
        }
    }

    public Item getItem(int i) {
        if (i < slots.length) {
            return slots[i].item;
        } else {
            return null;
        }
    }

    public Item getItembyId(ItemID id) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item != null) {
                if (slots[i].item.getId() == id) {
                    return slots[i].item;
                }
            }
        }
        return null;
    }

    public void removeItem(int i) {
        slots[i].item = null;
    }

    public int getByIndex(Item item) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item == item) {
                return i;
            }
        }
        return -1;
    }


    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i].item != null) {
                count++;
            }
        }
        return count;
    }

    public void clearInventory() {
        for (int i = 0; i < slots.length; i++) {
            slots[i].item = null;
        }
    }

    public void init_slots() {
        int index = 0;
        for (int i = 0; i < 9; i++) {
            slots[index] = new Slot(new Rectangle((int) (i * Game.UNIT_SCALE / 2 + transX * scale + 64 * 2), (int) (transY * scale + 64 * 2), Game.UNIT_SCALE / 2, Game.UNIT_SCALE / 2));
            index++;
        }
        for (int yy = 2; yy < 8; yy++) {
            for (int xx = 0; xx < 12; xx++) {
                slots[index] = new Slot(new Rectangle((int) (xx * Game.UNIT_SCALE / 2 + transX * scale + 64 * 2), (int) (yy * Game.UNIT_SCALE / 2 + transY * scale + 64 * 2), Game.UNIT_SCALE / 2, Game.UNIT_SCALE / 2));
                index++;
            }
        }
        for (int yy = 0; yy < 2; yy++) {
            for (int xx = 10; xx < 12; xx++) {
                slots[index] = new Slot(new Rectangle((int) (xx * Game.UNIT_SCALE / 2 + transX * scale + 64 * 2), (int) (yy * Game.UNIT_SCALE / 2 + transY * scale + 64 * 2), Game.UNIT_SCALE / 2, Game.UNIT_SCALE / 2));
                slots[index].setIdentifyer("armor");
                index++;
            }
        }
    }
}
