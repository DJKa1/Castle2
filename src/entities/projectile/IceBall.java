package entities.projectile;

import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.Vector2D;
import entities.creatures.Creature;
import graphics.Texture;
import items.Weapons.Weapons;

import java.awt.geom.Rectangle2D;

public class IceBall extends Projectile {
    private int splitAmount = 8;

    public IceBall(float x, float y, float aimX, float aimY, ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapons) {
        super(x, y, projectileHandler, effectshandler, weapons);
        this.aimY = aimY;
        this.aimX = aimX;
        width = (float) 6 / 16;
        height = (float) 6 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.1;
        img = Texture.sprite[21];
        lifeTime = 40;
        move.set(aimX, aimY);
        move.normalize();
    }

    @Override
    public void tick() {
        speedX = (float) move.x * projeticespeed;
        speedY = (float) move.y * projeticespeed;
        x += speedX;
        y += speedY;
        collision();
        lifeTime--;
        if (lifeTime < 0) {
            projectileHandler.removeObject(this);
            split(splitAmount);
        }
    }

    @Override
    protected void collision() {
        if (collisionWithTile()) {
            projectileHandler.removeObject(this);
            split(splitAmount);
        }
        for (Creature k : checkCollision_forAll()) {
            if (k != null && k.isHitby(id)) {
                hitTarget(k);
                projectileHandler.removeObject(this);
            }
        }

    }

    private void split(int amount) {
        float x = (float) getCenter().getX(), y = (float) getCenter().getY();
        double a;
        for (int i = 0; i < amount; i++) {
            a = 2 * Math.PI / amount * i;
            projectileHandler.addObject(new IceShard(x, y, new Vector2D(Math.sin(a), Math.cos(a)), a, projectileHandler, effectshandler, weapon));
        }

    }
}
