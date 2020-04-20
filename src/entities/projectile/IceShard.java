package entities.projectile;
import Handler.ProjectileHandler;
import ID_Lists.ID;
import entities.Vector2D;
import main_pack.Game;
import java.awt.geom.Rectangle2D;

public class IceShard extends  Projectile {
    private double angle;
        public IceShard(float x, float y, Vector2D v,double angle, ProjectileHandler projectileHandler) {
        super(x, y, projectileHandler);
        isHit = new ID[]{ID.GreenSlime};
        this.angle=Math.toDegrees(angle);
        width = (float) 2/16;
        height = (float) 2/16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.3;
        baseDgm = 2;
        img=Game.texture.sprite[21];
        move.set(v);
        move.normalize();
    }
}
