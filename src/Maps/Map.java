package Maps;

import Tiles.Texture;
import Tiles.Tile;
import main_pack.Game;

import java.awt.*;


public class Map {

    public static Rectangle BORDER;

    protected int MapWidth = 16, MapHeight = 16;
    protected Tile[][][] Tiles;

    public Map(String LevelName) {
        Tiles = JSON.loadMapJson(LevelName);
        loadMap();
    }

    public void loadMap() {

        BORDER = new Rectangle(0, 0, MapWidth, MapHeight);



    }

    public Tile getTilebyCords(int x, int y) {
        return Tiles[x][y][1];
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int layer = 0;layer<2;layer++) {
            for (int y = 0; y < MapHeight; y++) {
                for (int x = 0; x < MapWidth; x++) {

                        //g.drawImage(Texture.sprite[16],x*Game.UNIT_SCALE,y*Game.UNIT_SCALE,Game.UNIT_SCALE,Game.UNIT_SCALE,null);
                        if (Tiles[x][y][layer]!=null) {
                            g.setColor(Color.pink);
                            g.drawRect(x*Game.UNIT_SCALE,y*Game.UNIT_SCALE,Game.UNIT_SCALE,Game.UNIT_SCALE);

                            try{
                                g.drawImage(Tiles[x][y][layer].getImg(),x*Game.UNIT_SCALE,y*Game.UNIT_SCALE,Game.UNIT_SCALE,Game.UNIT_SCALE,null);

                            }catch (Exception e){
                            }

                        }


                }
            }
        }
    }
}
