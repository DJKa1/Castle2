package items.LootCreates;

import graphics.Texture;
import main_pack.MouseInput;

public class MoldyBox extends LootCreate {

    public MoldyBox(){
        super();
        maxRarity=60;
        minRarity=90;
        dropAmount=1;
        image= Texture.Inventory[1][5];
        attributes.add("Press E");
        attributes.add("to open");

    }
}
