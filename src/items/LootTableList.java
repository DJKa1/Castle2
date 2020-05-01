package items;

import ID_Lists.ItemID;
import items.Munition.SniperAmmo;
import items.Quality.*;

public class LootTableList {
    public static LootTable standartLootTable;

    public LootTableList(){
        initLootTables();
    }



    private void initLootTables(){
        standartLootTable=new LootTable();
        standartLootTable.setBaseDrop(new Item[]{new SniperAmmo(),new SniperAmmo()});
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

        standartLootTable.addItem(ItemID.SniperAmmo,12,60);
        standartLootTable.addItem(ItemID.SniperAmmo,30,20);
        standartLootTable.addItem(ItemID.SniperAmmo,100,10);

        standartLootTable.addItem(ItemID.ShotgunAmmo,12,60);
        standartLootTable.addItem(ItemID.ShotgunAmmo,30,20);
        standartLootTable.addItem(ItemID.ShotgunAmmo,100,10);

    }
}
