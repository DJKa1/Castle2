package items;
import ID_Lists.ItemID;
import items.Armor.Armor;
import items.Quality.*;

import java.util.ArrayList;

public class LootTableList {
    public static LootTable standartLootTable, highEndLoottable,magicBaseLootTable;

    public LootTableList(){
        initLootTables();
    }



    private void initLootTables(){
        standartLootTable=new LootTable();
        ArrayList basedrops=new ArrayList<Item>();

        //basedrops.add(ItemID.Shotgun,new Primitiv(),100);
        standartLootTable.setBaseDrop(basedrops);
        standartLootTable.addItem(ItemID.Shotgun,new Ramshackle(),90);


        standartLootTable.addItem(ItemID.FabricatedSniper,new Primitiv(),100);
        standartLootTable.addItem(ItemID.FabricatedSniper,new Ramshackle(),90);


        standartLootTable.addItem(ItemID.Boots,new Primitiv(),100);
        standartLootTable.addItem(ItemID.Boots,new Ramshackle(),90);



        standartLootTable.addItem(ItemID.ChestPlate,new Primitiv(),100);
        standartLootTable.addItem(ItemID.ChestPlate,new Ramshackle(),90);



        standartLootTable.addItem(ItemID.Helmet,new Primitiv(),100);
        standartLootTable.addItem(ItemID.Helmet,new Ramshackle(),90);

/*
        standartLootTable.addItem(ItemID.SniperAmmo,12,60);
        standartLootTable.addItem(ItemID.SniperAmmo,30,20);
        standartLootTable.addItem(ItemID.SniperAmmo,100,10);

        standartLootTable.addItem(ItemID.ShotgunAmmo,12,60);
        standartLootTable.addItem(ItemID.ShotgunAmmo,30,20);
        standartLootTable.addItem(ItemID.ShotgunAmmo,100,10);


 */

        highEndLoottable=new LootTable();

        highEndLoottable.addItem(ItemID.ChestPlate,new Fine(),100);
        highEndLoottable.addItem(ItemID.ChestPlate,new Extraordinary(),60);
        highEndLoottable.addItem(ItemID.ChestPlate,new Outstanding(),20);

        highEndLoottable.addItem(ItemID.Helmet,new Fine(),100);
        highEndLoottable.addItem(ItemID.Helmet,new Extraordinary(),60);
        highEndLoottable.addItem(ItemID.Helmet,new Outstanding(),20);

        highEndLoottable.addItem(ItemID.Boots,new Fine(),100);
        highEndLoottable.addItem(ItemID.Boots,new Extraordinary(),60);
        highEndLoottable.addItem(ItemID.Boots,new Outstanding(),20);


        highEndLoottable.addItem(ItemID.FabricatedSniper,new Fine(),100);
        highEndLoottable.addItem(ItemID.FabricatedSniper,new Extraordinary(),60);
        highEndLoottable.addItem(ItemID.FabricatedSniper,new Outstanding(),20);

        highEndLoottable.addItem(ItemID.Shotgun,new Fine(),100);
        highEndLoottable.addItem(ItemID.Shotgun,new Extraordinary(),60);
        highEndLoottable.addItem(ItemID.Shotgun,new Outstanding(),20);



        magicBaseLootTable=new LootTable();

        magicBaseLootTable.addItem(ItemID.IceStorm, new Primitiv(),100);
        magicBaseLootTable.addItem(ItemID.IceStorm, new Ramshackle(),100);
        magicBaseLootTable.addItem(ItemID.IceStorm, new Fine(),100);
        magicBaseLootTable.addItem(ItemID.IceStorm, new Extraordinary(),100);
        magicBaseLootTable.addItem(ItemID.IceStorm, new Outstanding(),100);





    }


}
