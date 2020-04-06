package items;


import Inventory.Inventory;
import Tiles.Texture;

public class testWeapon extends Weapons {

    public testWeapon(Inventory inventory) {
        super(inventory);
        image= Texture.sprite[1];
    }

    @Override
    public void use() {



    }

}
