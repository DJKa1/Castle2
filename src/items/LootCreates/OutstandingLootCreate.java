package items.LootCreates;

import graphics.Texture;
import items.LootTableList;

public class OutstandingLootCreate extends LootCreate {

    public OutstandingLootCreate(){
        super();
        maxRarity=1;
        minRarity=100;

        lootTable= LootTableList.highEndLoottable;

        dropAmount=1;
        image= Texture.Inventory[5][5];
        attributes.add("Press E");
        attributes.add("to open");

    }


}
