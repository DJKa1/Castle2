package Maps;

import Tiles.Tile;
import main_pack.Game;

import java.awt.*;

public class Map {

    public static Rectangle BORDER;
    protected int[][] tiles;
    protected String source;
    protected int tileWidth=32,tileHeight=32;

    public Map(String source){
        this.source=source;
        loadMap();
    }

    public void loadMap(){
        BORDER =new Rectangle(0,0,tileWidth, tileHeight);
        tiles=new int[tileWidth][tileHeight];
        for (int i=0;i<32;i++){
            for (int j=0;j<32;j++) {
                tiles[i][j] = 0;
            }
        }
        tiles[5][5]=1;
    }

    public Tile getTilebyCords(int x, int y){
        return Tile.idList[tiles[x][y]];
    }

    public  void tick(){

    }

    public void render(Graphics g){
        for (int i=0;i<32;i++){
            for (int j=0;j<32;j++) {
                getTilebyCords(i,j).render(g,i,j);
            }


        }
    }
}
