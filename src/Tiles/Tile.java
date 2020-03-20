package Tiles;

import main_pack.Game;

import java.awt.*;

public abstract class Tile {
    protected int width= Game.UNIT_SCALE,height=Game.UNIT_SCALE;
    protected int id;
    protected boolean solid;
    public static Tile[] idList =new Tile[64];
    public static Tile grass=new Grass(0);
    public static Tile rock=new Rock(1);
    public static Tile border=new Border(2);



    public Tile(int id){
        this.id =id;
        idList[id]=this;
        solid=false;


    }

    public abstract void tick();

    public abstract void render(Graphics g,int x,int y);


}
