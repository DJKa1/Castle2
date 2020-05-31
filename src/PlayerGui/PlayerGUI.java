package PlayerGui;


import Buffs.Buff;
import entities.creatures.Player;
import graphics.Texture;
import main_pack.Launcher;

import java.awt.*;

import static main_pack.Launcher.game;

public class PlayerGUI {
    private Player player;
    //private Color backColor = new Color(46,34,47);
    private Color backColor = new Color(21, 10, 31);

    public PlayerGUI(Player player) {
        this.player = player;
    }


    public void render(Graphics g) {
        renderHealthbar(g);
        renderManaCount(g);
        renderBuffs(g);
        renderMoney(g);
        renderWave(g);
    }

    private void renderMoney(Graphics g) {
        String msg = (int) game.getPlayer().getInventory().money + "$";
        int w = (msg.length()) * 32;
        g.setColor(backColor);
        g.fillRect(Launcher.WIDTH - w - 32, 112, Launcher.WIDTH, 32 + 16);
        drawString(g, Launcher.WIDTH - w - 8, 120, msg, 0);
    }

    private void renderHealthbar(Graphics g) {
        //Border 8px
        String msg = (int)game.getPlayer().getHp() + "/" + (int)game.getPlayer().getMaxHp();
        int w = msg.length() * 32;
        g.setColor(backColor);
        g.fillRect(Launcher.WIDTH - w - 32, 0, Launcher.WIDTH, 56);
        drawString(g, Launcher.WIDTH - w - 8, 8, msg, 2);
        w+=16;
        g.setColor(backColor);
        g.fillRect((int) (Launcher.WIDTH - player.getMaxHp() - 32-w), 0, (int) (player.getMaxHp() + 16), 56);
        g.setColor(Color.WHITE);
        g.fillRect((int) (Launcher.WIDTH - player.getMaxHp() - 24-w), 8, (int) (player.getMaxHp()), 40);
        g.setColor(new Color(104, 24, 36));
        g.fillRect((int) (Launcher.WIDTH - player.getHp()) - 24-w, 8, (int) (player.getHp()), 40);
        g.setColor(new Color(180, 35, 19));
        g.fillRect((int) (Launcher.WIDTH - player.getHp()) - 24-w, 8, (int) (player.getHp()), 32);


    }


    private void renderManaCount(Graphics g) {
        String msg = String.valueOf(game.getPlayer().getManaCount());
        int w = msg.length() * 32;
        g.setColor(backColor);
        g.fillRect(Launcher.WIDTH - w - 32, 56, Launcher.WIDTH, 56);
        drawString(g, Launcher.WIDTH - w - 8, 64, msg, 14);
        w+=8;

        g.setColor(backColor);
        g.fillRect((int) (Launcher.WIDTH - player.getManaCount() - 32-w), 56, (int) (player.getManaCount() + 16), 56);
        g.setColor(new Color(34, 45, 129));
        g.fillRect((int) (Launcher.WIDTH - player.getManaCount() - 24-w), 64, (int) (player.getManaCount()), 40);
        g.setColor(new Color(70, 91, 231));
        g.fillRect((int) (Launcher.WIDTH - player.getManaCount() - 24-w), 64, (int) (player.getManaCount()), 32);


    }


    private void renderWave(Graphics g ){
        String msg = String.valueOf(game.getGameState().getWave());
        int w = msg.length() * 32;
        g.setColor(backColor);
        drawString(g, Launcher.WIDTH - w - 8, 256, msg, 14);
    }


    private void renderBuffs(Graphics g) {
        int i = 0;
        for (Buff b : player.getActiveBuffs()) {
            if (b.getImage() != null) {
                g.drawImage(b.getImage(), Launcher.WIDTH - 96 - (48 * i), Launcher.HEIGHT - 96, 48, 48, null);
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
                g.drawImage(Texture.goldenUIElements[index][shade], x + i * 32 - 16, y, 32, 32, null);
            } else {
                System.out.println("No supported char");
            }
        }
    }
}
