package items;

import Inventory.Inventory;
import entities.creatures.Creature;
import entities.projectile.Plasmabolt;
import entities.projectile.Shotgunbolt;
import graphics.Texture;
import main_pack.MouseInput;

public class Shotgun extends Weapons {
    protected Creature user;

    private int delay = 0;


    public Shotgun(Inventory inventory) {
        super(inventory);
        image= Texture.sprite[25];
        user=inventory.getOwner();

    }

    @Override
    public void tick() {
        if(delay<100&&delay!=0) {
            delay++;
        }else {
            delay = 0;
        }
    }

    @Override
    public void use() {
        if (delay==0) {
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX()+0.5f,user.getY()+0.5f, MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler()));
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX()+0.5f,user.getY()+0.5f, MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler()));
            user.getProjectileHandler().addObject(new Shotgunbolt(user.getX()+0.5f,user.getY()+0.5f, MouseInput.mouseX,MouseInput.mouseY,user.getProjectileHandler()));
            delay++;
        }
    }

}
