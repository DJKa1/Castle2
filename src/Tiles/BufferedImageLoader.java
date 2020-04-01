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
            //System.out.println("try to read");
            img = ImageIO.read(new File(FileName));
            //System.out.println("File read");
        }catch(IOException e){
            e.printStackTrace();
        }
        return img;
    }
}
