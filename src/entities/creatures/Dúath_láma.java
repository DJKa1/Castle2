package entities.creatures;

import ID_Lists.ID;
import Inventory.Inventory;
import States.GameState;

import items.Quality.Primitiv;

import items.Weapons.Shotgun;
import items.Weapons.Weapons;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Dúath_láma extends Creature{
    private Weapons weapon;

    public Dúath_láma(float x, float y, Game game) {
        super(x, y, game);
        width=1;
        height=1.6f;
        targetingRange=3;
        targetable=new ID[]{ID.Player};
        hp=100;
        maxHp = hp;
        movementRate= (float) 0.05;
        armorValue=1;
        followingMultiplier=2;
        weapon=new Shotgun(new Primitiv());
        normalizeHitbox();
        normalizeMovementhitbox();

    }

    @Override
    protected void attack() {
        weapon.use(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameState.texture.sprite[30],getPixelPosition(getCenter().getX()-1),getPixelPosition(getCenter().getY()-1),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        g.drawImage(GameState.texture.sprite[31],getPixelPosition(getCenter().getX()),getPixelPosition(getCenter().getY()-1),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        g.drawImage(GameState.texture.sprite[38],getPixelPosition(getCenter().getX()-1),getPixelPosition(getCenter().getY()),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        g.drawImage(GameState.texture.sprite[39],getPixelPosition(getCenter().getX()),getPixelPosition(getCenter().getY()),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform oldtrans = g2d.getTransform();
        AffineTransform trans = g2d.getTransform();

        trans.rotate(45, getPixelPosition(x) + 64, getPixelPosition(y) + 64 + 8);
        g2d.setTransform(trans);
        if (weapon!=null) {
            g2d.drawImage(weapon.getImage(), getPixelPosition(x) + 64, getPixelPosition(y), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
        }
        g2d.setTransform(oldtrans);
    }
}
