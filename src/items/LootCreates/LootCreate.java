package items.LootCreates;


import entities.creatures.Creature;
import entities.creatures.Player;
import items.Item;
import items.LootTable;
import items.LootTableList;


public abstract class LootCreate extends Item {
    protected int maxRarity,minRarity;
    protected LootTable lootTable;

    public LootCreate( ){
        super();
        lootTable= LootTableList.standartLootTable;
        isUseableInInventory = true;
    }
    @Override
    public void tick() {
    }
    @Override
    public void use(Creature user) {
        ((Player) user).getInventory().addItems(lootTable.dropItem(maxRarity,minRarity));
        ((Player) user).getInventory().removeItem( this);
    }
}
