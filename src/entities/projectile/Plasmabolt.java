package entities.projectile;
import ID_Lists.ID;
import entities.Knockback;
import Handler.ProjectileHandler;
import main_pack.Game;
import java.awt.geom.Rectangle2D;

public class Plasmabolt extends Projectile {

    public Plasmabolt(float x, float y, float aimX, float aimY,ProjectileHandler projectileHandler) {
        super(x, y,projectileHandler);
        this.aimY = aimY;
        this.aimX = aimX;
        isHit= new ID[]{ID.GreenSlime};
        width= (float) 6/16;
        height= (float) 6/16;
        hitbox=new Rectangle2D.Double(x,y,width,height);
        projeticespeed = (float) 0.25;
        baseDgm=1;
        img=Game.texture.sprite[18];
        move.set(aimX,aimY);
        move.normalize();
        knockback=new Knockback(move,30);
    }
}
