package items.Weapons;

import ID_Lists.ItemID;
import Sound.Sound;
import entities.Knockback;
import entities.Vector2D;
import entities.creatures.Creature;
import entities.projectile.Shotgunbolt;
import entities.projectile.SniperBullet;
import graphics.Texture;
import items.Quality.Quality;

public class FabricatedSniper  extends  ShootingWeapons{


    public FabricatedSniper(Quality quality) {
        super(quality);
        image= Texture.sprite[35];
        cooldown=60;
        magazineSize=10;
        ammo= ItemID.SniperAmmo;
        baseDamage=12;
    }

    @Override
    protected void fire(Creature user) {
        Sound.playSound("Sniper");
        Vector2D s=new Vector2D(user.getAimX(),user.getAimY());
        s.normalize();
        user.getProjectileHandler().addObject(new SniperBullet((float) user.getCenter().getX(), (float) user.getCenter().getY(),(float) s.x,(float)s.y, user.getProjectileHandler(), user.getEffectshandler(),this));
        remainingMunition--;
        user.setCurrentKnockback(new Knockback(s.getMultiplied(-0.05),20));
    }



    protected void playReloadSound() {
        Sound.playSound("Shotgun_Reload");
    }
}
