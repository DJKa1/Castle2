package items;

import Inventory.Inventory;
import entities.Vector2D;
import entities.creatures.Creature;
import entities.projectile.Plasmabolt;
import entities.projectile.Shotgunbolt;
import graphics.Texture;
import main_pack.MouseInput;

import static main_pack.MouseInput.mouseX;
import static main_pack.MouseInput.mouseY;

public class Shotgun extends Weapons {
    protected Creature user;

    private int delay = 0;


    public Shotgun(Inventory inventory) {
        super(inventory);
        image = Texture.sprite[25];
        user = inventory.getOwner();

    }

    @Override
    public void tick() {
        if (delay < 60 && delay != 0) {
            delay++;
        } else {
            delay = 0;
        }
    }

    @Override
    public void use() {
        if (delay == 0) {
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, mouseX, mouseY, user.getProjectileHandler()));
            Vector2D s = new Vector2D(mouseX, mouseY);
            double angle = s.getAngle() - Math.toRadians(9);
            s.x = Math.cos(angle);
            s.y = Math.sin(angle);
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler()));
            s = new Vector2D(mouseX, mouseY);
            angle = s.getAngle() - Math.toRadians(-9);
            s.x = Math.cos(angle);
            s.y = Math.sin(angle);
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX() + 0.5f, user.getY() + 0.5f, (float) s.x, (float) s.y, user.getProjectileHandler()));
            delay++;
        }
    }
    

}
