package entities.creatures.shop;

import items.Item;

public class Offer {
    public Item item;
    public int prize;
    public int amount = 1;

    public Offer(Item item, int prize) {
        this.item = item;
        this.prize = prize;
    }
    public Offer(Item item,int amount, int prize) {
        this.item = item;
        this.prize = prize;
        this.amount = amount;
    }
}
