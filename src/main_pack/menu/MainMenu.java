package main_pack.menu;

import main_pack.Launcher;
import main_pack.UI_Element;

public class MainMenu extends Menus{
    public MainMenu() {
        ui_elements = new UI_Element[4];
        ui_elements[0] = new UI_Element("CONTINUE", (int) (Launcher.WIDTH/2-1.5d*128), 128*3, 26,0,3,1);
        ui_elements[1] = new UI_Element("OPTIONS", Launcher.WIDTH/2-3*128, (int) (128*4.5d), 32,0,6,1);
        ui_elements[2] = new UI_Element("EXIT", (int) (Launcher.WIDTH/2-1.5d*128), (int) (128*6), 29,0,3,1);
        ui_elements[3] = new UI_Element("JUSTTEXT", Launcher.WIDTH/2-3*128, 128, 38,0,6,1);
    }
}
