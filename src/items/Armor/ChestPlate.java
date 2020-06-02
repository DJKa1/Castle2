package items.Armor;

import entities.creatures.Creature;
import graphics.Texture;
import items.Quality.Quality;

public class ChestPlate extends  Armor {


    public ChestPlate(Quality quality) {
        super(quality);
        armorValue=4;
        image= Texture.Inventory[1][6];
        attributes.add("Armor : "+ armorValue +" + "+ quality.getArmorBoost());
    }

    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }
}
