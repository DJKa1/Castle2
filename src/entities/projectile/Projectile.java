package entities.projectile;
import entities.Entity;
import ID_Lists.ID;
import entities.Vector2D;
import Handler.ProjectileHandler;
import java.awt.*;

public abstract class Projectile extends Entity {
    protected float aimX, aimY;
    protected float projeticespeed;
    protected ProjectileHandler projectileHandler;
    protected ID[] isHit;
    protected float baseDgm;

    public float getBaseDgm() {
        return baseDgm;
    }

    public float caculateDmg() {
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
        this.projectileHandler = projectileHandler;
        move = new Vector2D(0, 0);
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

    public boolean isSolid(float ox, float oy) {
        if (collisionWithTiles(getTilesinDirection(ox, oy, hitbox), hitbox) != null) {
            return true;
        }
        return false;
    }

    public void renderHitbox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x), getPixelPosition(y), getPixelPosition(width), getPixelPosition(height));
    }

    public boolean collisionWithTile() {
        normalizeHitbox();
        if (speedX > speedY) {
            if (isSolid(speedX, 0)) {
                return true;
            }
        } else {
            if (isSolid(0, speedY)) {
                return true;
            }
        }
        return false;
    }







}
