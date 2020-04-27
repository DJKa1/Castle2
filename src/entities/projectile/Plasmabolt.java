package entities.projectile;
import Handler.Effectshandler;
import entities.Knockback;
import Handler.ProjectileHandler;
import items.Weapons.Weapons;
import main_pack.Game;
import java.awt.geom.Rectangle2D;
public class Plasmabolt extends Projectile {

    public Plasmabolt(float x, float y, float aimX, float aimY, ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapons) {
        super(x, y,projectileHandler, effectshandler,weapons);
        this.aimY = aimY;
        this.aimX = aimX;
        width= (float) 6/16;
        height= (float) 6/16;
        hitbox=new Rectangle2D.Double(x,y,width,height);
        projeticespeed = (float) 0.25;
        img=Game.texture.sprite[18];
        move.set(aimX,aimY);
        move.normalize();
        knockback=new Knockback(move.getMultiplied(6),10);
    }
}
