package items;
import ID_Lists.ItemID;
import items.Quality.*;

import java.util.ArrayList;

public class LootTableList {
    public static LootTable standartLootTable;

    public LootTableList(){
        initLootTables();
    }



    private void initLootTables(){
        standartLootTable=new LootTable();
        standartLootTable.setBaseDrop(new ArrayList<Item>());
        standartLootTable.addItem(ItemID.Shotgun,new Primitiv(),100);
        standartLootTable.addItem(ItemID.Shotgun,new Ramshackle(),90);
        standartLootTable.addItem(ItemID.Shotgun,new Fine(),60);
        standartLootTable.addItem(ItemID.Shotgun,new Extraordinary(),20);
        standartLootTable.addItem(ItemID.Shotgun,new Outstanding(),10);

        standartLootTable.addItem(ItemID.FabricatedSniper,new Primitiv(),100);
        standartLootTable.addItem(ItemID.FabricatedSniper,new Ramshackle(),90);
        standartLootTable.addItem(ItemID.FabricatedSniper,new Fine(),60);
        standartLootTable.addItem(ItemID.FabricatedSniper,new Extraordinary(),20);
        standartLootTable.addItem(ItemID.FabricatedSniper,new Outstanding(),10);

        standartLootTable.addItem(ItemID.Boots,new Primitiv(),100);
        standartLootTable.addItem(ItemID.Boots,new Ramshackle(),90);
        standartLootTable.addItem(ItemID.Boots,new Fine(),60);
        standartLootTable.addItem(ItemID.Boots,new Extraordinary(),20);
        standartLootTable.addItem(ItemID.Boots,new Outstanding(),10);


        standartLootTable.addItem(ItemID.ChestPlate,new Primitiv(),100);
        standartLootTable.addItem(ItemID.ChestPlate,new Ramshackle(),90);
        standartLootTable.addItem(ItemID.ChestPlate,new Fine(),60);
        standartLootTable.addItem(ItemID.ChestPlate,new Extraordinary(),20);
        standartLootTable.addItem(ItemID.ChestPlate,new Outstanding(),10);


        standartLootTable.addItem(ItemID.Helmet,new Primitiv(),100);
        standartLootTable.addItem(ItemID.Helmet,new Ramshackle(),90);
        standartLootTable.addItem(ItemID.Helmet,new Fine(),60);
        standartLootTable.addItem(ItemID.Helmet,new Extraordinary(),20);
        standartLootTable.addItem(ItemID.Helmet,new Outstanding(),10);

        standartLootTable.addItem(ItemID.SniperAmmo,12,60);
        standartLootTable.addItem(ItemID.SniperAmmo,30,20);
        standartLootTable.addItem(ItemID.SniperAmmo,100,10);

        standartLootTable.addItem(ItemID.ShotgunAmmo,12,60);
        standartLootTable.addItem(ItemID.ShotgunAmmo,30,20);
        standartLootTable.addItem(ItemID.ShotgunAmmo,100,10);

    }
}
