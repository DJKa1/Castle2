package Tiles;


import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet tileSheet, ts;


    private BufferedImage sprite_Sheet = null;
    private BufferedImage tile_sheet = null;

    public static BufferedImage[] sprite = new BufferedImage[1024];
    public static BufferedImage[][] tiles = new BufferedImage[24][10];


    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            tile_sheet = loader.LoadImage("./rsc/Assets/dungeon_sheet.png");
            sprite_Sheet = loader.LoadImage("./rsc/Assets/SpriteSheet.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ts = new SpriteSheet(sprite_Sheet);
        tileSheet = new SpriteSheet(tile_sheet);

        getTextures();
        getTiles();

    }
    private void getTextures() {
        int i = 0;
        for(int y = 0;y<8;y++) {
            for(int x = 0;x<8;x++) {
                sprite[i] = ts.grabImage(x, y,16,16);
                i++;
            }
        }
    }
    private void getTiles() {
        for (int yy = 0;yy<10;yy++) {
            for (int xx = 0;xx<24;xx++) {
                tiles[xx][yy] = tileSheet.grabImage(xx,yy,16,16);
            }
        }
    }
}
