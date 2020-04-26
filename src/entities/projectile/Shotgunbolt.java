package entities.projectile;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import ID_Lists.ID;
import entities.Knockback;
import main_pack.Game;
import java.awt.geom.Rectangle2D;

public class Shotgunbolt extends Projectile {

    public Shotgunbolt(float x, float y, float aimX, float aimY, ProjectileHandler projectileHandler, Effectshandler effectshandler) {
        super(x, y, projectileHandler, effectshandler);
        this.aimY = aimY;
        this.aimX = aimX;
        width = (float) 6 / 16;
        height = (float) 6 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.2;
        baseDgm = 5;
        img = Game.texture.sprite[20];
        move.set(aimX, aimY);
        move.normalize();
        knockback = new Knockback(move.getMultiplied(1),10);
    }
}








