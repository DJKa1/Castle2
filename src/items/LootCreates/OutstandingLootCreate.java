package items.LootCreates;

import graphics.Texture;

public class OutstandingLootCreate extends LootCreate {

    public OutstandingLootCreate(){
        super();
        maxRarity=1;
        minRarity=30;
        image= Texture.sprite[43];
        attributes.add("Press E");
        attributes.add("to open");

    }


}
