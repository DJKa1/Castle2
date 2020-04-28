package items;

import ID_Lists.ItemID;
import items.Munition.ShotgunAmmo;
import items.Munition.SniperAmmo;
import items.Quality.*;
import items.Weapons.Shotgun;


import java.util.ArrayList;

public class LootTable {
    private ArrayList[] table;
    private Item[] baseDrop;

    public LootTable(){
        table= new ArrayList[]{new ArrayList<ItemID>(),new ArrayList<Quality>(),new ArrayList<Integer>(), new ArrayList<Integer>()};
        baseDrop=null;
    }

    public Item[] getBaseDrop() {
        return baseDrop;
    }

    public void setBaseDrop(Item[] baseDrop) {
        this.baseDrop = baseDrop;
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
    public Item[] dropItem(int maxRarity, int minRarity){
        if(minRarity>100){
            minRarity=100;
        }
        if(minRarity<maxRarity){
            maxRarity=minRarity;
        }
        maxRarity += Math.random()*(minRarity-maxRarity);

        ArrayList<Integer>vallid=getChoices(minRarity,maxRarity);
        if(vallid.isEmpty()){
           return baseDrop;
        }

        int index= (int)(Math.random()*(vallid.size()));
        Item[] drops=new Item[(int)table[3].get(vallid.get(index))];
        for (int i=0 ;i<(int)table[3].get(vallid.get(index));i++){
             drops[i]=createItembyID((ItemID)table[0].get(vallid.get(index)),(Quality)table[1].get(vallid.get(index)));
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
        }
    }
}




