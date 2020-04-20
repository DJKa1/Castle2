package items;
import Inventory.Inventory;
import graphics.Texture;
import entities.projectile.Plasmabolt;
import main_pack.MouseInput;

public class testWeapon extends ShootingWeapons {
    public testWeapon(Inventory inventory) {
        super(inventory);
        image= Texture.sprite[26];
        cooldown=10;
    }
    @Override
    public void use() {
        if (delay==0) {
            user.getProjectileHandler().addObject(new Plasmabolt(user.getX()+0.5f,user.getY()+0.5f, MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler()));
        }
        if(delay<10) {
            delay++;
        }else {
            delay = 0;
        }
    }
}
