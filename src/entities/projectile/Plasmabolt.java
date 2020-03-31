package entities.projectile;
import main_pack.ProjectileHandler;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Plasmabolt extends Projectile {

    public Plasmabolt(float x, float y, float aimX, float aimY,ProjectileHandler projectileHandler) {
        super(x, y,projectileHandler);
        this.projeticespeed = (float) 0.1;
        this.aimY = aimY;
        this.aimX = aimX;
        move.set(aimX,aimY);
        hitbox=new Rectangle2D.Double(x,y,width,height);

        move.normalize();

        speedX= (float) move.x*projeticespeed;
        speedY = (float) move.y*projeticespeed;
    }
    @Override
    public void tick() {
        x+=speedX;
        y+=speedY;
        updateHitbox(0,0);
        collision();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.red);
        g.drawRect(getPixelPosstion(x),getPixelPosstion(y),getPixelPosstion(width),getPixelPosstion(height));


    }

    @Override
    public void collision() {
        if(isTileSolid((int)Math.floor(x), (int) Math.floor(y))){
            projectileHandler.removeObject(this);
        }






    }
}
