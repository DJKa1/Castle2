package Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BufferedImageLoader {
    private BufferedImage img;

    public BufferedImage LoadImage(String FileName){

        BufferedImage img = null;

        try{
            img = ImageIO.read(new File(FileName));
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
