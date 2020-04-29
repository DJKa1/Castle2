package items.Weapons;
import Sound.Sound;
import entities.creatures.Creature;
import graphics.Texture;
import entities.projectile.Plasmabolt;
import items.Quality.Quality;


public class testWeapon extends ShootingWeapons {
    public testWeapon(Quality quality) {
        super(quality);
        image= Texture.sprite[26];
        cooldown=2 ;
        baseDamage=4;
    }
    @Override
    public void use(Creature user) {
        super.use(user);
        if (delay==0) {
            Sound.playSound("Shotgun");
            user.getProjectileHandler().addObject(new Plasmabolt(user.getX()+0.5f,user.getY()+0.5f, user.getAimX(), user.getAimY(),user.getProjectileHandler(),user.getEffectshandler(),this));
        }
        if(delay<10) {
            delay++;
        }else {
            delay = 0;
        }

    }

    @Override
    protected void fire(Creature k) {

    }

    @Override
    protected void playReloadSound() {

    }

}
