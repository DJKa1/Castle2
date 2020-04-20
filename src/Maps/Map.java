package Maps;

import Tiles.Tile;
import main_pack.Game;
import main_pack.KeyboardInput;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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

    public Tile getTilebyCords(int x, int y,int layer) {
        return Tiles[x][y][layer];
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int layer = 0; layer < 3; layer++) {
            for (int y = 0; y < MapHeight; y++) {
                for (int x = 0; x < MapWidth; x++) {
                    if (Tiles[x][y][layer] != null) {
                        Rectangle2D temp = Tiles[x][y][layer].getHitbox();
                        g.drawImage(Tiles[x][y][layer].getImg(), x * Game.UNIT_SCALE, y * Game.UNIT_SCALE, Game.UNIT_SCALE, Game.UNIT_SCALE, null);
                    }
                }
            }
        }
        if(KeyboardInput.f3G){
        renderHitbox(g);

        }
    }

    public void renderHitbox(Graphics g){
        for (int layer = 0; layer<3;layer++) {
            for (int y = 0; y < MapHeight; y++) {
                for (int x = 0; x < MapWidth; x++) {
                    if (Tiles[x][y][layer] != null) {
                        Rectangle2D temp = Tiles[x][y][layer].getHitbox();
                        if (temp != null) {
                            g.setColor(Color.pink);
                            g.drawRect((int) (temp.getX() * Game.UNIT_SCALE), (int) (temp.getY() * Game.UNIT_SCALE), (int) (temp.getWidth() * Game.UNIT_SCALE), (int) (temp.getHeight() * Game.UNIT_SCALE));
                        }
                    }
                }
            }
        }
    }
}
