package items.Weapons;

import Inventory.Inventory;
import entities.projectile.Shotgunbolt;
import graphics.Texture;


import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class AK47 extends ShootingWeapons {


    public AK47(Inventory inventory) {
        super(inventory);
        image = Texture.sprite[25];
        user = inventory.getOwner();
        cooldown = 20;
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



    private void fire(){
        user.getProjectileHandler().addObject(new Shotgunbolt(user.getX(),user.getY(),mouseX,mouseY,user.getProjectileHandler(),user.getEffectshandler()));

    }

}
