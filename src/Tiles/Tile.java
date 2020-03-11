package Tiles;

import java.awt.*;

public abstract class Tile {
    protected int width=64,height=64;
    protected int id;
    protected boolean solid;
    public static Tile[] idList =new Tile[64];
    public static Tile grass=new Grass(0);
    public static Tile rock=new Rock(1);



    public Tile(int id){
        this.id =id;
        idList[id]=this;
        solid=false;


    }

    public abstract void tick();

    public abstract void render(Graphics g,int x,int y);


}
