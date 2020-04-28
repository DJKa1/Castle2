package items.Weapons;

import ID_Lists.ID;
import ID_Lists.ItemID;
import Inventory.Inventory;
import entities.Knockback;
import entities.Vector2D;
import entities.creatures.Creature;
import entities.creatures.Player;
import entities.projectile.Shotgunbolt;
import graphics.Texture;
import items.Quality.Quality;
import items.Weapons.ShootingWeapons;

import javax.swing.*;

import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class Shotgun extends ShootingWeapons {
    public Shotgun(Quality quality) {
        super(quality);
        image = Texture.sprite[25];
        cooldown = 30;
        magazineSize = 6;
        ammo= ItemID.SniperAmmo;
        baseDamage=5;

    }

    @Override
    public void use(Creature user) {
        if (delay == 0 && remainingMunition > 0) {
            fire(user);
            delay++;
        } else if (delay == 0) {
            if(user.getId()== ID.Player){
                Player p= (Player) user;
                reload(p.getInventory());
            }else {
                remainingMunition=magazineSize;
            }
            delay++;
        }

    }

    private void fire(Creature user) {
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, mouseX, mouseY, user.getProjectileHandler(), user.getEffectshandler(),this));
        Vector2D s = new Vector2D(mouseX, mouseY);
        double angle = s.getAngle() - Math.toRadians(9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler(),this));
        s = new Vector2D(mouseX, mouseY);
        angle = s.getAngle() - Math.toRadians(-9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler(),this));
        remainingMunition--;
        user.setCurrentKnockback(new Knockback(s.getMultiplied(-2),20));

    }


}
