package entities.projectile;
import Handler.ProjectileHandler;
import ID_Lists.ID;
import main_pack.Game;
import java.awt.geom.Rectangle2D;

public class Shotgunbolt extends Projectile {
    public Shotgunbolt(float x, float y, float aimX, float aimY, ProjectileHandler projectileHandler) {
        super(x, y, projectileHandler);
        this.aimY = aimY;
        this.aimX = aimX;
        isHit = new ID[]{ID.GreenSlime};
        width = (float) 6 / 16;
        height = (float) 6 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.2;
        baseDgm = 1;
        img = Game.texture.sprite[20];
        move.set(aimX, aimY);
        move.normalize();
    }
}








