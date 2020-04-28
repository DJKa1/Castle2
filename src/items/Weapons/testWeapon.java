package items.Weapons;
import entities.creatures.Creature;
import graphics.Texture;
import entities.projectile.Plasmabolt;
import items.Quality.Quality;
import main_pack.MouseInput;

public class testWeapon extends ShootingWeapons {
    public testWeapon(Quality quality) {
        super(quality);
        image= Texture.sprite[26];
        cooldown=2 ;
        attributes.add(quality.getId().toString());
        attributes.add(Math.round(quality.getDmg()*100)+"%");
    }
    @Override
    public void use(Creature user) {
        if (delay==0) {
            user.getProjectileHandler().addObject(new Plasmabolt(user.getX()+0.5f,user.getY()+0.5f, MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler(),user.getEffectshandler(),this));
        }
        if(delay<10) {
            delay++;
        }else {
            delay = 0;
        }

    }

}
