package items;

import ID_Lists.ItemID;
import items.Armor.Boots;
import items.Armor.ChestPlate;
import items.Armor.Helmet;
import items.Munition.ShotgunAmmo;
import items.Munition.SniperAmmo;
import items.Potions.HealPotion;
import items.Quality.*;
import items.Weapons.FabricatedSniper;
import items.Weapons.Shotgun;


import java.io.Serializable;
import java.util.ArrayList;

public class LootTable {
    private ArrayList[] table;
    private ArrayList<Item> baseDrops;

    public LootTable(){
        table= new ArrayList[]{new ArrayList<ItemID>(),new ArrayList<Quality>(),new ArrayList<Integer>(), new ArrayList<Integer>()};
        baseDrops=new ArrayList<>();
    }

    public ArrayList<Item> getBaseDrop() {
        return baseDrops;
    }

    public void setBaseDrop(ArrayList<Item> baseDrops) {
        this.baseDrops = baseDrops;
    }

    private ArrayList<Integer> getChoices(int minRarity, int maxRarity){
        ArrayList<Integer> vallid=new ArrayList<>();
        for (int i=0 ;i<table[2].size();i++){
            if((int)(table[2].get(i))<=minRarity&(int)(table[2].get(i))>maxRarity){
                vallid.add(i);
            }
        }
        return vallid;

    }
    public ArrayList dropItem(int maxRarity, int minRarity,int amount){
        ArrayList<Item> drops = new ArrayList();
        if(minRarity>100){
            minRarity=100;
        }
        if(minRarity<maxRarity){
            maxRarity=minRarity;
        }
        for (int j =0;j<amount;j++) {
            int tempmaxRarity = (int) (maxRarity + Math.random() * (minRarity - maxRarity));
            ArrayList<Integer> vallid = getChoices(minRarity, tempmaxRarity);
            int index = (int) (Math.random() * (vallid.size()));
            if(!vallid.isEmpty()) {
                for (int i = 0; i < (int) table[3].get(vallid.get(index)); i++) {
                    drops.add(createItembyID((ItemID) table[0].get(vallid.get(index)), (Quality) table[1].get(vallid.get(index))));
                }
            }
        }
        if(drops.isEmpty()){
            return baseDrops;
        }
        return drops;
    }

    public ArrayList dropItem(int maxRarity, int minRarity){
        ArrayList<Item> drops = new ArrayList();
        if(minRarity>100){
            minRarity=100;
        }
        if(minRarity<maxRarity){
            maxRarity=minRarity;
        }
            int tempmaxRarity = (int) (maxRarity + Math.random() * (minRarity - maxRarity));
            ArrayList<Integer> vallid = getChoices(minRarity, tempmaxRarity);
            int index = (int) (Math.random() * (vallid.size()));
        if(!vallid.isEmpty()) {
            for (int i = 0; i < (int) table[3].get(vallid.get(index)); i++) {
                drops.add(createItembyID((ItemID) table[0].get(vallid.get(index)), (Quality) table[1].get(vallid.get(index))));
            }
        }
        if(drops.isEmpty()){
            return baseDrops;
        }
        return drops;
    }

    public void addItem(ItemID id ,Quality quality,int rarity){
        table[0].add(id);
        table[1].add(quality);
        table[2].add(rarity);
        table[3].add(1);
    }

    public void addItem(ItemID id ,int amount,int rarity) {
        table[0].add(id);
        table[1].add(null);
        table[2].add(rarity);
        table[3].add(amount);
    }
    public void addItem(ItemID id ,Quality quality,int amount,int rarity){
        table[0].add(id);
        table[1].add(quality);
        table[2].add(rarity);
        table[3].add(amount);
    }
    public Item createItembyID(ItemID id ,Quality quality) {
        switch (id){
            default:return null;
            case Shotgun:return new Shotgun(quality);
            case SniperAmmo:return new SniperAmmo();
            case ShotgunAmmo:return new ShotgunAmmo();
            case FabricatedSniper:return new FabricatedSniper(quality);
            case Boots:return new Boots(quality);
            case ChestPlate:return new ChestPlate(quality);
            case Helmet:return new Helmet(quality);
        }
    }
}




