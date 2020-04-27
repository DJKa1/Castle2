package items.Weapons;

import Inventory.Inventory;
import graphics.Texture;


public class EchoStaff extends MagicWeapons {
    public EchoStaff(Inventory inventory) {
        super(inventory);
        image= Texture.sprite[29];
    }

    @Override
    public void fire() {

    }
}
