package entities.projectile;
import entities.ID;
import entities.creatures.Creature;
import Handler.ProjectileHandler;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Plasmabolt extends Projectile {

    public Plasmabolt(float x, float y, float aimX, float aimY,ProjectileHandler projectileHandler) {
        super(x, y,projectileHandler);
        this.aimY = aimY;
        this.aimX = aimX;
        isHit= new ID[]{ID.GreenSlime};
        width= (float) 0.1;
        height= (float) 0.1;
        hitbox=new Rectangle2D.Double(x,y,width,height);
        projeticespeed = (float) 0.1;
        baseDgm=1;
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
        g.drawImage(Game.texture.sprite[18], (int) (getPixelPosition(x)-5*Game.SCALE), (int) (getPixelPosition(y)-5*Game.SCALE), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
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
