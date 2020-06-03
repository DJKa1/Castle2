package items.LootCreates;

import graphics.Texture;
import items.LootTableList;

public class MagicLootCreate extends  LootCreate {


    public MagicLootCreate(){
        super();

        image= Texture.Inventory[7][5];
        lootTable= LootTableList.magicBaseLootTable;
        attributes.add("Press E");
        attributes.add("to open");

    }
}
