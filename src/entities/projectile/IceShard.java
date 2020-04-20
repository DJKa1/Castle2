package entities.projectile;
import Buffs.iced;
import Handler.ProjectileHandler;
import ID_Lists.ID;
import entities.Vector2D;
import entities.creatures.Creature;
import graphics.Texture;
import main_pack.Game;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class IceShard extends  Projectile {
    private double angle;
        public IceShard(float x, float y, Vector2D v,double angle, ProjectileHandler projectileHandler) {
        super(x, y, projectileHandler);
        isHit = new ID[]{ID.GreenSlime};
        this.angle=Math.toDegrees(angle);
        width = (float) 2/16;
        height = (float) 2/16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.3;
        baseDgm = 2;
        move.set(v);
        move.normalize();

    }

    @Override
    public void tick() {
        speedX = (float) move.x * projeticespeed;
        speedY = (float) move.y * projeticespeed;
        x += speedX;
        y += speedY;
        collision();
    }


    @Override
    public void render(Graphics g) {
       g.drawImage(Texture.rotate(Game.texture.sprite[21],angle),(getPixelPosition(x)),(getPixelPosition(y)), Game.UNIT_SCALE, Game.UNIT_SCALE, null);

    }

    private void collision() {
        if(collisionWithTile()){
            projectileHandler.removeObject(this);
        }
        Creature[] creatures = checkCollision_forAll(isHit);
        for (Creature k : creatures) {
            if (k != null) {
                k.hitbyProjectile(this);
                k.addBuff(new iced(k,180,3));
                projectileHandler.removeObject(this);
            }

        }
    }


}
