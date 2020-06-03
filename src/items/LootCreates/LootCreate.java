package items.LootCreates;


import entities.creatures.Creature;
import entities.creatures.Player;
import items.Item;
import items.LootTable;
import items.LootTableList;


public abstract class LootCreate extends Item {
    protected int maxRarity,minRarity;
    protected LootTable lootTable;
    protected int dropAmount;

    public LootCreate( ){
        super();
        maxRarity=1;
        minRarity=100;
        lootTable= LootTableList.standartLootTable;
        isUseableInInventory = true;
        dropAmount=1;
        rotate=false;
    }
    @Override
    public void tick() {
    }
    @Override
    public void use(Creature user) {
        ((Player) user).getInventory().addItems(lootTable.dropItem(maxRarity,minRarity,dropAmount));
        ((Player) user).getInventory().removeItem( this);
    }
}
