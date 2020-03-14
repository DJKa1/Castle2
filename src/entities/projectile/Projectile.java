package entities.projectile;

import entities.Entity;

public abstract class Projectile extends Entity {
    protected float aimX,aimY;
    protected float projeticespeed;


    public Projectile(float x, float y) {
        super(x, y);
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


    public void updateRealSpeed(){
        float diffY=aimY-y;
        float diffX=aimX-x;
        float hypo=(float)Math.sqrt(x*x+y*y);
        speedX=1/(diffY/diffX+1);
        speedY=1/(diffY/diffX+1)*diffY/diffX;

    }


    @Override
    public void tick(){
        x+=speedX;
        y+=speedY;

    }

}
