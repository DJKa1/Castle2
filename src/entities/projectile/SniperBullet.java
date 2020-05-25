package entities.projectile;

import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.Knockback;
import graphics.Texture;
import items.Weapons.Weapons;

import java.awt.geom.Rectangle2D;

public class SniperBullet extends Projectile {
    public SniperBullet(float x, float y, float aimX, float aimY,ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapon) {
        super(x, y, projectileHandler, effectshandler, weapon);

        this.aimY = aimY;
        this.aimX = aimX;
        width= (float) 2/16;
        height= (float) 2/16;
        hitbox=new Rectangle2D.Double(x,y,width,height);
        projeticespeed = (float) 0.5;
        move.set(aimX,aimY);
        move.normalize();
        knockback=new Knockback(move.getMultiplied(6),10);
        img = Texture.sprite[29];
    }
}
