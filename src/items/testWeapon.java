package items;
import Inventory.Inventory;
import graphics.Texture;
import entities.creatures.Creature;
import entities.projectile.Plasmabolt;
import main_pack.MouseInput;

public class testWeapon extends Weapons {
    protected Creature user;

    public testWeapon(Inventory inventory) {
        super(inventory);
        image= Texture.sprite[1];
        user=inventory.getOwner();
    }

    @Override
    public void use() {
        user.getProjectileHandler().addObject(new Plasmabolt(user.getX(),user.getY(), MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler()));
    }

}
