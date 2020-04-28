package graphics;


import Tiles.BufferedImageLoader;
import Tiles.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet tileSheet, ts, UIELementsSheet, Inventorysheet;
    BufferedImage tile_sheet, UI_Elements_Scource, inventorysource;

    public static BufferedImage[] sprite = new BufferedImage[1024];
    public static BufferedImage[][] tiles = new BufferedImage[24][10];
    public static BufferedImage[][] goldenUIElements = new BufferedImage[64][3];
    public static BufferedImage[][] greenUIElements = new BufferedImage[64][3];
    public static BufferedImage[][] Inventory = new BufferedImage[64][64];


    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        BufferedImage sprite_Sheet = null;
        tile_sheet = null;
        try {
            tile_sheet = loader.LoadImage("./rsc/Assets/dungeon_sheet2.png");
            sprite_Sheet = loader.LoadImage("./rsc/Assets/SpriteSheet.png");
            UI_Elements_Scource = loader.LoadImage("./rsc/Assets/UIElements.png");
            inventorysource = loader.LoadImage("./rsc/Assets/Inventory.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ts = new SpriteSheet(sprite_Sheet);
        tileSheet = new SpriteSheet(tile_sheet);
        UIELementsSheet = new SpriteSheet(UI_Elements_Scource);
        Inventorysheet = new SpriteSheet(inventorysource);

        getTextures();

    }

    private void getTextures() {
        int in = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                sprite[in] = ts.grabImage(x, y, 16, 16);

                in++;
            }
        }

        for (int l = 0; l < 3; l++) {
            for (int i = 0; i < 60; i++) {
                goldenUIElements[i][l] = UIELementsSheet.grabImage(i, l, 16, 16);
            }
        }

        for (int l = 0; l < 3; l++) {
            for (int i = 0; i < 60; i++) {
                greenUIElements[i][l] = UIELementsSheet.grabImage(i, l+3, 16, 16);
            }
        }
        for (int yy = 0; yy < 10; yy++) {
            for (int xx = 0; xx < 24; xx++) {
                tiles[xx][yy] = tileSheet.grabImage(xx, yy, 16, 16);
            }
        }
        for (int yy = 0; yy < 64; yy++) {
            for (int xx = 0; xx < 64; xx++) {
                Inventory[xx][yy] = Inventorysheet.grabImage(xx,yy,16,16);
            }
        }
    }


    public static BufferedImage rotate(BufferedImage bimg, double angle) {

        int w = bimg.getWidth();
        int h = bimg.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawImage(bimg, null, 0, 0);
        graphic.dispose();
        return rotated;
    }


}
