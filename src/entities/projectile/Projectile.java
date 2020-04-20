package entities.projectile;
import entities.Entity;
import ID_Lists.ID;
import entities.Knockback;
import entities.Vector2D;
import Handler.ProjectileHandler;
import entities.creatures.Creature;
import main_pack.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Entity {
    protected float aimX, aimY;
    protected float projeticespeed;
    protected ProjectileHandler projectileHandler;
    protected ID[] isHit;
    protected float baseDgm;
    protected Knockback knockback;
    protected BufferedImage img;
    protected int lifeTime=0;

    //Konstructor-------------------------------------------------
    public Projectile(float x, float y, ProjectileHandler projectileHandler) {
        super(x, y);
        this.projectileHandler = projectileHandler;
        move = new Vector2D(0, 0);
    }

    //Getter && Setter--------------------------------------------------------
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

    //Tick && Render Funktions----------------------------------
    @Override
    public void tick(){
        speedX = (float) move.x * projeticespeed;
        speedY = (float) move.y * projeticespeed;
        x += speedX;
        y += speedY;
        collision();
        lifeTime--;
        if(lifeTime==0) {
            projectileHandler.removeObject(this);
        }
    }
    @Override
    public void render(Graphics g) {
        if(img!=null){
            g.drawImage(img, (int) (getPixelPosition(x)-5*Game.SCALE), (int) (getPixelPosition(y)-5*Game.SCALE), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
        }
    }

    public void renderHitbox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x), getPixelPosition(y), getPixelPosition(width), getPixelPosition(height));
    }

    //Collision---------------------------------------------
    public boolean isSolid(float ox, float oy) {
        if (collisionWithTiles(getTilesinDirection(ox, oy, hitbox), hitbox) != null) {
            return true;
        }
        return false;
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

    protected void collision(){
        if(collisionWithTile()){
            projectileHandler.removeObject(this);
        }
        Creature[] creatures=checkCollision_forAll(isHit);
        for (Creature k :creatures) {
            if(k!=null){
                k.hitbyProjectile(this);
                k.setCurrentKnockback(knockback);
                projectileHandler.removeObject(this);
            }

        }
    }







}
