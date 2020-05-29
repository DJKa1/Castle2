package PlayerGui;


import Buffs.Buff;
import entities.creatures.Player;
import graphics.Texture;
import main_pack.Game;
import main_pack.Launcher;

import java.awt.*;

import static main_pack.Launcher.game;

public class PlayerGUI {
    private Player player;

    public PlayerGUI(Player player){
        this.player = player;
    }


    public void render(Graphics g){
        renderHealthbar(g);
        renderManaCount(g);
        renderBuffs(g);
        renderMoney(g);
    }
    private void renderMoney(Graphics g) {
        String msg = (int)game.getPlayer().getInventory().money +"$";
        int w = (msg.length()+1)*64;
        g.drawImage(Texture.sprite[32],Launcher.WIDTH-w, 100, 64, 64, null);
        drawString(g, Launcher.WIDTH-w, 100, msg, 0);
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
    private void drawString(Graphics g, int x, int y, String string, int shade) {
        int index = 0;
        string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            int temp = c;
            int temp_integer = 32; //for upper case
            if (temp <= 90 & temp >= 32) {
                index = temp - temp_integer;
                g.drawImage(Texture.goldenUIElements[index][shade], x + i * 64, y, 64, 64, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }
}
