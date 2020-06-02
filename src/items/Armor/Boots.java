package items.Armor;

import entities.creatures.Creature;
import graphics.Texture;
import items.Quality.Quality;

public class Boots extends Armor {
    public Boots(Quality quality) {
        super(quality);
        armorValue=3;
        image= Texture.Inventory[2][6];

        attributes.add("Armor : "+ armorValue +" + "+ quality.getArmorBoost());
    }

    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }
}
