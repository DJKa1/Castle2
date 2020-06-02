package items.Armor;

import entities.creatures.Creature;
import graphics.Texture;
import items.Quality.Quality;

public class Helmet extends Armor {
    public Helmet(Quality quality) {
        super(quality);
        armorValue=2;
        image= Texture.Inventory[0][6];
        attributes.add("Armor : "+ armorValue +" + "+ quality.getArmorBoost());
    }

    @Override
    public void tick() {

    }

    @Override
    public void use(Creature user) {

    }
}
