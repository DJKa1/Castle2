package entities.creatures.shop;

import entities.creatures.Creature;
import graphics.Animation;
import graphics.Texture;
import items.Item;
import items.LootCreates.MagicLootCreate;
import items.LootCreates.MoldyBox;
import items.LootCreates.OutstandingLootCreate;
import items.Munition.ShotgunAmmo;
import items.Munition.SniperAmmo;
import items.Potions.HealPotion;
import items.Quality.Primitiv;
import items.Weapons.*;
import main_pack.Game;
import main_pack.KeyboardInput;
import main_pack.MouseInput;

import java.awt.*;
import java.util.ArrayList;

public class Shop extends Creature {
    private Animation merchant;
    private Animation upperbanner, lowerbanner, cupperbanner, clowerbanner;
    private int bannerindex = 0, cbannerindex = 30;
    private boolean nearby = false;
    private ShopButton left, right;
    private ArrayList<Offer> offers = new ArrayList<>();
    private int index;

    public Shop(float x, float y, Game game) {
        super(x, y, game);
        merchant = new Animation(20, Texture.Inventory[23][0], Texture.Inventory[24][0], Texture.Inventory[25][0]);
        upperbanner = new Animation(5, Texture.Inventory[13][1], Texture.Inventory[14][1], Texture.Inventory[15][1], Texture.Inventory[16][1], Texture.Inventory[17][1]);
        lowerbanner = new Animation(5, Texture.Inventory[13][2], Texture.Inventory[14][2], Texture.Inventory[15][2], Texture.Inventory[16][2], Texture.Inventory[17][2]);
        cupperbanner = new Animation(5, Texture.Inventory[17][1], Texture.Inventory[16][1], Texture.Inventory[15][1], Texture.Inventory[14][1], Texture.Inventory[13][1]);
        clowerbanner = new Animation(5, Texture.Inventory[17][2], Texture.Inventory[16][2], Texture.Inventory[15][2], Texture.Inventory[14][2], Texture.Inventory[13][2]);
        left = new ShopButton(getPixelPosition(x+0.5), getPixelPosition(y + 2.75), 1);
        right = new ShopButton(getPixelPosition(x + 3.5), getPixelPosition(y + 2.75), 2);


        offers.add(new Offer(new HealPotion(1),20));
        offers.add(new Offer(new PotionThrower(new Primitiv()),5,150));
        offers.add(new Offer(new ShotgunAmmo(),10,8));
        offers.add(new Offer(new SniperAmmo(),10,10));
        offers.add(new Offer(new MoldyBox(),200));
        offers.add(new Offer(new OutstandingLootCreate(),500));
        offers.add(new Offer(new MagicLootCreate(),100));
        offers.add(new Offer(new Shotgun(new Primitiv()),150));
        offers.add(new Offer(new FabricatedSniper(new Primitiv()),150));
        offers.add(new Offer(new IceStorm(new Primitiv()),150));
        offers.add(new Offer(new testWeapon(new Primitiv()),150));

    }

    private void buyItem() {
        if (game.getPlayer().getInventory().money>=offers.get(index).prize) {
            game.getPlayer().getInventory().money-=offers.get(index).prize;
            for (int i = 0;i<offers.get(index).amount;i++) {
                game.getPlayer().getInventory().addItembyID(offers.get(index).item.getId().toString());
            }
            Sound.Sound.playSound("Buy");
        }
    }

    public void tick() {
        double dx = Math.sqrt(Math.pow(game.getPlayer().getCenter().getX() - (x + 2.5), 2) + Math.pow(game.getPlayer().getCenter().getY() - (y + 4), 2));
        if (dx < 1) {
            cbannerindex = 0;
            cupperbanner.setCount(0);
            clowerbanner.setCount(0);
            nearby = true;
            left.mouseIn(MouseInput.mxWorld, MouseInput.myWorld);
            right.mouseIn(MouseInput.mxWorld, MouseInput.myWorld);
            MouseInput.interact = true;

            if (bannerindex < 30) {
                upperbanner.runAnimation();
                lowerbanner.runAnimation();
                bannerindex++;
            }
            if (left.pressed) {
                if (index > 0) {
                    index--;
                }
            } else if (right.pressed) {
                if (index < offers.size() - 1) {
                    index++;
                }
            }
            if (KeyboardInput.impX) {
                buyItem();
            }

        } else {
            nearby = false;
            MouseInput.interact = false;
            bannerindex = 0;
            upperbanner.setCount(0);
            lowerbanner.setCount(0);
            if (cbannerindex < 30) {
                cupperbanner.runAnimation();
                clowerbanner.runAnimation();
                cbannerindex++;
            }
        }
        merchant.runAnimation();
    }


    @Override
    public void render(Graphics g) {
        merchant.drawAnimation(g, getPixelPosition(x + 2), getPixelPosition(y + 2) + 16, 128);
        for (int yy = 0; yy < 4; yy++) {
            for (int xx = 0; xx < 5; xx++) {
                g.drawImage(Texture.Inventory[xx + 18][yy], getPixelPosition(x + xx), getPixelPosition(y + yy), 128, 128, null);
            }
        }


        for (int i = 0; i < 3; i++) {
            if (index - 1 + i >= 0 && index - 1 + i < offers.size()) {
                switch (i) {
                    case 0:
                        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                        g.drawImage(offers.get(index - 1 + i).item.getImage(), getPixelPosition(x + 1 + i) + 64, getPixelPosition(y + 2) + 96, 64, 64, null);
                        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                        break;
                    case 1:
                        g.drawImage(offers.get(index - 1 + i).item.getImage(), getPixelPosition(x + 1 + i) + 16, getPixelPosition(y + 2) + 96, 96, 96, null);
                        break;
                    case 2:
                        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                        g.drawImage(offers.get(index - 1 + i).item.getImage(), getPixelPosition(x + 1 + i), getPixelPosition(y + 2) + 96, 64, 64, null);
                        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
                        break;
                }

            }
        }

        g.drawImage(Texture.Inventory[13][1], getPixelPosition(x + 3.5), getPixelPosition(y + 2), 128, 128, null);
        if (nearby) {
            upperbanner.drawAnimation(g, getPixelPosition(x + 3.5), getPixelPosition(y + 2), 128);
            lowerbanner.drawAnimation(g, getPixelPosition(x + 3.5), getPixelPosition(y + 3), 128);
            left.render(g);
            right.render(g);
            if (bannerindex >= 30) {
                for (int i = 0; i < offers.get(index).item.getAttributes().size(); i++) {
                    String str = offers.get(index).item.getAttributes().get(i);
                    int NameWidth = g.getFontMetrics().stringWidth(str);
                    g.setColor(Color.WHITE);
                    g.drawString(str, getPixelPosition(x + 4) - NameWidth / 2, getPixelPosition(y + 2.2) + 30 * i);
                }
            }
        } else {
            cupperbanner.drawAnimation(g, getPixelPosition(x + 3.5), getPixelPosition(y + 2), 128);
            clowerbanner.drawAnimation(g, getPixelPosition(x + 3.5), getPixelPosition(y + 3), 128);
        }
    }
}
