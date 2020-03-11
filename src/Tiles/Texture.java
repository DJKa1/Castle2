package Tiles;


import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet bs, ts;


    private BufferedImage sprite_Sheet = null;

    public static BufferedImage[] sprite = new BufferedImage[1024];


    public Texture() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            sprite_Sheet = loader.LoadImage("C:\\Users\\Pete Louis Benz\\Documents\\Castle2\\rsc\\Assets\\SpriteSheet.png");

        } catch (Exception e) {
            e.printStackTrace();
        }
        ts = new SpriteSheet(sprite_Sheet);

        getTextures();

    }
    private void getTextures() {
        int i = 0;
        for(int y = 0;y<32;y++) {
            for(int x = 0;x<32;x++) {
                sprite[i] = ts.grabImage(x+1, y+1,32,32);
                i++;
            }
        }
    }
}
