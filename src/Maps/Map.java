package Maps;

import Tiles.Tile;

import java.awt.*;

public class Map {

    protected int[][] tiles;
    protected String source;

    public Map(String source){
        this.source=source;
        loadMap();
    }

    public void loadMap(){
        tiles=new int[32][32];
        for (int i=0;i<32;i++){
            for (int j=0;j<32;j++) {
                if (i%2==00){
                    tiles[i][j] = 0;
                }else {
                    tiles[i][j] = 1;

                }
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
