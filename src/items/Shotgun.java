package items;

import Inventory.Inventory;
import entities.Knockback;
import entities.Vector2D;
import entities.projectile.Shotgunbolt;
import graphics.Texture;

import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class Shotgun extends ShootingWeapons {
    public Shotgun(Inventory inventory) {
        super(inventory);
        image = Texture.sprite[25];
        user = inventory.getOwner();
        cooldown = 60;
        magazineSize = 6;

    }

    @Override
    public void use() {
        if (delay == 0 && remainingMunition > 0) {
            fire();
            delay++;
        } else if (delay == 0) {
            reload();
        }

    }

    private void fire() {
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, mouseX, mouseY, user.getProjectileHandler(), user.getEffectshandler()));
        Vector2D s = new Vector2D(mouseX, mouseY);
        double angle = s.getAngle() - Math.toRadians(9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler()));
        s = new Vector2D(mouseX, mouseY);
        angle = s.getAngle() - Math.toRadians(-9);
        s.x = Math.cos(angle);
        s.y = Math.sin(angle);
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler(), user.getEffectshandler()));
        remainingMunition--;


    }


}
