package items.Weapons;

import ID_Lists.ID;
import ID_Lists.ItemID;
import entities.Knockback;
import entities.Vector2D;
import entities.creatures.Creature;
import entities.creatures.Player;
import entities.projectile.Shotgunbolt;
import graphics.Texture;
import items.Quality.Quality;
import Sound.Sound;

public class Shotgun extends ShootingWeapons {
    public Shotgun(Quality quality) {
        super(quality);
        image = Texture.sprite[25];
        cooldown = 30;
        magazineSize = 6;
        ammo= ItemID.ShotgunAmmo;
        baseDamage=5;


    }




    @Override
    protected void fire(Creature user) {
        Sound.playSound("Shotgun");
        user.getProjectileHandler().addObject(new Shotgunbolt((float) user.getCenter().getX(), (float) user.getCenter().getY(), user.getAimX(), user.getAimY(), user.getProjectileHandler(), user.getEffectshandler(),this));
        Vector2D s = new Vector2D(user.getAimX(), user.getAimY());
        double angle = s.getAngle() - Math.toRadians(9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt((float) user.getCenter().getX(), (float) user.getCenter().getY(), (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler(),this));
        s = new Vector2D(user.getAimX(), user.getAimY());
        angle = s.getAngle() - Math.toRadians(-9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt((float) user.getCenter().getX(), (float) user.getCenter().getY(), (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler(),this));
        remainingMunition--;
        user.setCurrentKnockback(new Knockback(s.getMultiplied(-2),20));

    }

    @Override
    protected void playReloadSound() {
        Sound.playSound("Shotgun_Reload");
    }


}
