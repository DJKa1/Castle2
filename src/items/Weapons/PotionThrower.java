package items.Weapons;

import ID_Lists.ID;
import Inventory.Inventory;
import entities.creatures.Creature;
import entities.creatures.Player;
import entities.projectile.PotionProjectile;
import graphics.Texture;
import items.Quality.Quality;


public class PotionThrower extends ShootingWeapons{
    public PotionThrower(Quality quality) {
        super(quality);
        cooldown = 60;
        image = Texture.Inventory[5][7];
        remainingMunition = 1;
    }
    @Override
    public void reload(Inventory inventory) {

    }

    @Override
    protected void playReloadSound() {

    }

    @Override
    protected void fire(Creature user) {
        user.getProjectileHandler().addObject(new PotionProjectile(user.getX()+0.5f,user.getY()+0.5f,user.getAimX(), user.getAimY(),user.getProjectileHandler(),user.getEffectshandler(),this));
        if(user.getId()== ID.Player){
            Player p= (Player) user;
            p.getInventory().removeItem(this);
        }
    }
}
