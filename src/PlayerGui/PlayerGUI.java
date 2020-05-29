package PlayerGui;


import Buffs.Buff;
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
        renderManaCount(g);
        renderBuffs(g);
    }


    private void renderHealthbar(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int)(Launcher.WIDTH-player.getMaxHp()-8),0, (int)(player.getMaxHp()+8),48);
        g.setColor(Color.WHITE);
        g.fillRect((int)(Launcher.WIDTH-player.getMaxHp()),0, (int)(player.getMaxHp()),40);
        g.setColor(Color.RED);
        g.fillRect((int)(Launcher.WIDTH-player.getHp()),0, (int)(player.getHp()),40);
    }


    private void renderManaCount(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect((int)(Launcher.WIDTH-player.getManaCount()-8),50, (int)(player.getManaCount()+8),48);
        g.setColor(Color.BLUE);
        g.fillRect((int)(Launcher.WIDTH-player.getManaCount()),50, (int)(player.getManaCount()),40);
    }


    private void renderBuffs(Graphics g){
        int i=0;
        for ( Buff b : player.getActiveBuffs()){
            if (b.getImage()!=null){
                g.drawImage(b.getImage(),Launcher.WIDTH-96 -(48*i),Launcher.HEIGHT-96,48,48,null);
                i++;
            }
        }
    }
}
