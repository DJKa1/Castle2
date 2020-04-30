package entities.projectile;
import Buffs.Buff;
import Effects.DmgIndicator;
import Handler.CreatureHandler;
import Handler.Effectshandler;
import ID_Lists.ProjectileID;
import entities.Entity;
import ID_Lists.ID;
import entities.Knockback;
import entities.Vector2D;
import Handler.ProjectileHandler;
import entities.creatures.Creature;
import items.Weapons.Weapons;
import main_pack.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Entity {
    protected float aimX, aimY;
    protected float projeticespeed;
    protected ProjectileHandler projectileHandler;
    protected Effectshandler effectshandler;
    protected ID[] isHit;
    protected Knockback knockback;
    protected BufferedImage img;
    protected int lifeTime=0;
    protected ProjectileID id;
    protected Buff buff = null;
    protected Weapons weapon;

    //Konstructor-------------------------------------------------
    public Projectile(float x, float y, ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapon) {
        super(x, y);
        this.id=ProjectileID.valueOf(this.getClass().getSimpleName());
        this.projectileHandler = projectileHandler;
        this.effectshandler = effectshandler;
        this.weapon=weapon;
        move = new Vector2D(0, 0);
    }

    //Getter && Setter--------------------------------------------------------
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
        return collisionWithTiles(getTilesinDirection(ox, oy, hitbox), hitbox) != null;
    }

    public boolean collisionWithTile() {
        normalizeHitbox();
        if (speedX > speedY) {
            return isSolid(speedX, 0);
        } else {
            return isSolid(0, speedY);
        }
    }

    protected void collision(){
        if(collisionWithTile()){
            projectileHandler.removeObject(this);
        }
        for (Creature k : checkCollision_forAll()) {
            if(k!=null&&k.isHitby(id)&&k!=weapon.getUser()){
                hitTarget(k);
                projectileHandler.removeObject(this);
            }
        }
    }

    protected void hitTarget(Creature k){
        k.hit(weapon.getDmg(),null);
        k.setCurrentKnockback(knockback);
    }

    public Buff giveBuff() {
        return buff;
    }
}
