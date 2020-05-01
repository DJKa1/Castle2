package items.Armor;

import entities.creatures.Creature;
import items.Quality.Quality;

public class ChestPlate extends  Armor {


    public ChestPlate(Quality quality) {
        super(quality);
        armorValue=4;

        attributes.add("Armor : "+ armorValue +" + "+ quality.getArmorBoost());
    }

    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }
}
