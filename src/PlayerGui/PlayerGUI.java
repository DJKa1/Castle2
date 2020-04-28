package PlayerGui;


import entities.creatures.Player;
import main_pack.Launcher;

import java.awt.*;

public class PlayerGUI {
    private Player player;

    public PlayerGUI(Player player){
        this.player = player;
    }


    public void render(Graphics g){
        renderHealthbar(g);
    }

    private void renderHealthbar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)(Launcher.WIDTH-player.getMaxHp()-8),0, (int)(player.getMaxHp()+8),48);
        g.setColor(Color.WHITE);
        g.fillRect((int)(Launcher.WIDTH-player.getMaxHp()),0, (int)(player.getMaxHp()),40);
        g.setColor(Color.RED);
        g.fillRect((int)(Launcher.WIDTH-player.getHp()),0, (int)(player.getHp()),40);
    }
}
