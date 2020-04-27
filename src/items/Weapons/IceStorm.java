package items.Weapons;

import Inventory.Inventory;
import entities.creatures.Creature;
import entities.projectile.IceBall;
import graphics.Texture;
import items.Quality.Quality;

import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class IceStorm extends MagicWeapons {

    public IceStorm(Quality quality) {
        super(quality);
        image= Texture.sprite[28];
        manacost=10;
        cooldown=60;
    }


    @Override
    public void fire(Creature user) {
        user.getProjectileHandler().addObject(new IceBall(user.getX()+0.5f,user.getY()+0.5f,mouseX,mouseY,user.getProjectileHandler(),user.getEffectshandler(),this));
    }
}
