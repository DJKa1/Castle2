package PlayerGui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthBar {
     private BufferedImage border;
     private int x,y,scale;

    public HealthBar(int x, int y , int scale){

    }

    public void render(Graphics g){
        g.drawImage(border,x,y,border.getWidth()*scale,border.getHeight()*scale,null);

    }
}
