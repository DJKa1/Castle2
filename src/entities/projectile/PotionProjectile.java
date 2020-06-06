package entities.projectile;


import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.creatures.Creature;
import entities.groundEffect.GroundEffect;
import entities.groundEffect.PoisonGroundEffect;
import graphics.Texture;
import items.Weapons.Weapons;

import java.awt.geom.Rectangle2D;

import static main_pack.Launcher.game;

public class PotionProjectile extends Projectile {
    private GroundEffect groundEffect;
    public PotionProjectile(float x, float y,float aimX, float aimY, ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapon) {
        super(x, y, projectileHandler, effectshandler, weapon);
        lifeTime=30;
        projeticespeed = 0.25f;
        img = Texture.Inventory[5][7];
        move.set(aimX, aimY);
        move.normalize();
        width = 1;
        height = 1;
        hitbox = new Rectangle2D.Double(x, y, width, height);
    }

    public void tick() {
        speedX = (float) move.x * projeticespeed;
        speedY = (float) move.y * projeticespeed;
        x += speedX;
        y += speedY;
        collision();
        lifeTime--;
        if(lifeTime==0) {
            projectileHandler.removeObject(this);
            game.getCreatureHandler().addObject(new PoisonGroundEffect(x,y,game));
        }
    }
    @Override
    public void collision(){
        if(collisionWithTile()){
            projectileHandler.removeObject(this);
            game.getCreatureHandler().addObject(new PoisonGroundEffect(x,y,game));
        }
        for (Creature k : checkCollision_forAll()) {
            if(k!=null&&k.isHitby(id)&&k!=weapon.getUser()){
                hitTarget(k);
                projectileHandler.removeObject(this);
                game.getCreatureHandler().addObject(new PoisonGroundEffect(x,y,game));
                System.out.println("AD");
            }
        }
    }

    public GroundEffect getGroundEffect() {
        return groundEffect;
    }

    public void setGroundEffect(GroundEffect groundEffect) {
        this.groundEffect = groundEffect;
    }
}
