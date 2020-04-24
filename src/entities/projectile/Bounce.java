package entities.projectile;

import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.creatures.Creature;
import main_pack.Game;
import org.lwjgl.Sys;

import java.awt.geom.Rectangle2D;

public class Bounce extends Projectile {
    public Bounce(float x, float y, float aimX, float aimY, ProjectileHandler projectileHandler, Effectshandler effectshandler) {
        super(x, y, projectileHandler, effectshandler);
        this.aimY = aimY;
        this.aimX = aimX;
        width = (float) 6 / 16;
        height = (float) 6 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.1;
        baseDgm = 1;
        img = Game.texture.sprite[21];
        lifeTime = 1000;
        move.set(aimX, aimY);
        move.normalize();
    }

    @Override
    public void tick() {
        collision();
        speedX = (float) move.x * projeticespeed;
        speedY = (float) move.y * projeticespeed;
        x += speedX;
        y += speedY;

        lifeTime--;
        if (lifeTime < 0) {
            projectileHandler.removeObject(this);
        }
    }

    @Override
    protected void collision() {
        normalizeHitbox();

        if (isSolid(speedX, 0)) {
            //move.x *= -1;
        }

        if (isSolid(0, speedY)) {
            move.y *= -1;

        }
        for (Creature k : checkCollision_forAll()) {
            if (k != null && k.isHitby(id)) {
                k.setCurrentKnockback(knockback);
                projectileHandler.removeObject(this);
            }
        }
    }
}
