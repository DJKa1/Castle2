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
        BORDER =new Rectangle(0,0,tileWidth* Game.UNIT_SCALE, tileHeight*Game.UNIT_SCALE);
        tiles=new int[tileWidth][tileHeight];
        for (int i=0;i<32;i++){
            for (int j=0;j<32;j++) {
                tiles[i][j] = 0;
            }

            }
    }

    public Tile getTilebyID(int x, int y){
        return Tile.idList[tiles[x][y]];
    }

    public  void tick(){

    }

    public void render(Graphics g){
        for (int i=0;i<32;i++){
            for (int j=0;j<32;j++) {
                getTilebyID(i,j).render(g,i,j);
            }


        }
    }
}
