package main_pack.menu;

import Sound.Sound;
import graphics.Texture;
import main_pack.*;

import java.awt.*;


public class OptionsMenu extends Menus{


    public OptionsMenu() {
        ui_elements = new UI_Element[5];
        ui_elements[0] = new UI_Element("BACK", (int) (Launcher.WIDTH/2-2d*128), (int) (128*6), 50,0,4,1);
        ui_elements[1] = new UI_Element("JUSTTEXT", Launcher.WIDTH/2-3*128, 128, 38,0,6,1);
        ui_elements[2] = new UI_Element("JUSTTEXT", (int) (Launcher.WIDTH/2-2.5d*128), 128*3, 54,0,5,1);
        ui_elements[3] = new UI_Element("V1",(int) (Launcher.WIDTH/2-2.5d*128),128*4,23,4,1,1);
        ui_elements[4] = new UI_Element("V2",(int) (Launcher.WIDTH/2+1.5d*128),128*4,24,4,1,1);
    }
    public void renderMenu(Graphics g) {
        for (UI_Element ui_element : ui_elements) {
            ui_element.render(g);
        }
        for (int i = 0; i< 6;i++) {
            g.drawImage(Texture.Inventory[24][4],Launcher.WIDTH/2-3*128+i*128,128*2-40,128,128,null);
        }
        UI_Element.drawString(g,(int) (Launcher.WIDTH/2-1d*128),128*4, String.valueOf(Sound.Volume),128,0);
        g.drawImage(Texture.Inventory[23][4],Launcher.WIDTH/2-4*128+48,128*2-40,128,128,null);
        g.drawImage(Texture.Inventory[25][4],Launcher.WIDTH/2+2*128+32,128*2-40,128,128,null);
    }
}
