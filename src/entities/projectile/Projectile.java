package entities.projectile;
import entities.Entity;
import entities.ID;
import entities.Vector2D;
import Handler.ProjectileHandler;

public abstract class Projectile extends Entity {
    protected float aimX,aimY;
    protected float projeticespeed;
    protected ProjectileHandler projectileHandler;
    protected ID[]isHit;
    protected float baseDgm;

    public float getBaseDgm() {
        return baseDgm;
    }
    public float caculateDmg(){
        return baseDgm;
    }

    public float getProjeticespeed() {
        return projeticespeed;
    }
    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }
    public ID[] getIsHit() {
        return isHit;
    }
    public Projectile(float x, float y, ProjectileHandler projectileHandler) {
        super(x, y);
        this.projectileHandler=projectileHandler;
        move=new Vector2D(0,0);
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

    public void removeifSolid(float ox, float oy) {
        if (collisionWithTiles(getTilesinDirection(ox, oy,hitbox),hitbox) != null) {
            projectileHandler.removeObject(this);
        }
    }






}
