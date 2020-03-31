package entities.projectile;

import Maps.Map;
import entities.Entity;
import entities.Vector2D;
import main_pack.Game;
import main_pack.ProjectileHandler;

import java.awt.geom.Rectangle2D;

public abstract class Projectile extends Entity {
    protected float aimX,aimY;
    protected float projeticespeed;
    protected Rectangle2D.Double hitbox;
    protected Vector2D move;
    protected float width=(float) 0.5,height=(float)0.5;
    protected ProjectileHandler projectileHandler;

    public Projectile(float x, float y, ProjectileHandler projectileHandler) {
        super(x, y);
        this.projectileHandler=projectileHandler;
        move=new Vector2D(0,0);
    }



    public Rectangle2D.Double getHitbox() {
        return hitbox;
    }

    public float getAimY() {
        return aimY;
    }

    public void setAimY(float aimY) {
        this.aimY = aimY;
    }

    public float getAimX() {
        return aimX;
    }

    public void setAimX(float aimX) {
        this.aimX = aimX;
    }

    public void updateHitbox(double xOffset,double yOffset){
        hitbox.setRect(x+xOffset,y+yOffset,width,height);

    }
    public int getPixelPosstion(float v){
        return (int)(v* Game.UNIT_SCALE);
    }
    public abstract void collision();





}
