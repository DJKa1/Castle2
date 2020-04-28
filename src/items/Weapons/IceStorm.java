package items.Weapons;

import Inventory.Inventory;
import entities.creatures.Creature;
import entities.projectile.IceBall;
import graphics.Texture;
import items.Quality.Quality;


public class IceStorm extends MagicWeapons {

    public IceStorm(Quality quality) {
        super(quality);
        image= Texture.sprite[28];
        manacost=10;
        cooldown=60;
    }

    public void use(){
        super.use(user);
        if (delay==0&&user.getManaCount()>manacost){
            user.getProjectileHandler().addObject(new IceBall(user.getX()+0.5f,user.getY()+0.5f,user.getAimX(), user.getAimY(),user.getProjectileHandler(),user.getEffectshandler(),this));
            delay++;
            user.reduceMana(manacost);
        }
    }
}
