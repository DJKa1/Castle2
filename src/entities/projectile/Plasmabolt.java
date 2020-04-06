package entities.projectile;
import entities.ID;
import entities.creatures.Creature;
import main_pack.ProjectileHandler;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Plasmabolt extends Projectile {

    public Plasmabolt(float x, float y, float aimX, float aimY,ProjectileHandler projectileHandler) {
        super(x, y,projectileHandler);
        this.aimY = aimY;
        this.aimX = aimX;
        isHit= new ID[]{ID.Greenslime};
        width= (float) 0.1;
        height= (float) 0.1;
        hitbox=new Rectangle2D.Double(x,y,width,height);
        projeticespeed = (float) 0.1;
        baseDgm=10;
        move.set(aimX,aimY);
        move.normalize();
    }
    @Override
    public void tick() {
        speedX= (float) move.x*projeticespeed;
        speedY = (float) move.y*projeticespeed;
        x+=speedX;
        y+=speedY;
        collision();
    }
    @Override
    public void render(Graphics g) {
    }

    @Override
    public void drawHitbox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(getPixelPosition(x),getPixelPosition(y),getPixelPosition(width),getPixelPosition(height));
    }

    public void collision() {
        //NoOffset---------------------------------------------------
        normalizeHitbox();
        if(speedX>speedY){
            removeifSolid(speedX, 0);
        }else {
            removeifSolid(0, speedY);
        }
        Creature[] creatures=checkCollision_forAll(isHit);
            for (Creature k :creatures) {
                if(k!=null){
                k.hitbyProjectile(this);
                projectileHandler.removeObject(this);
                }

            }
    }
}
