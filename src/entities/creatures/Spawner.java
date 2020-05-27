package entities.creatures;

import graphics.Texture;
import main_pack.Game;

import java.awt.*;

public class Spawner extends Creature {
    int delay = 0;
    float spawnRange=2;

    public Spawner(float x, float y, Game game) {
        super(x, y, game);
    }

    @Override
    public void tick() {
        if(delay>60) {
            game.getCreatureHandler().addObject(new GreenSlime(x+getRandomSpred(),y+getRandomSpred(),game));
            delay = 0;
        }
        delay++;
    }

    @Override
    public void render(Graphics g) {
        for (int yy = 0; yy< 3;yy++) {
            for (int xx = 0; xx< 2;xx++) {
                g.drawImage(Texture.tiles[xx+10][yy],getPixelPosition(x)+xx*128,getPixelPosition(y)+yy*128,128,128,null);
            }
        }
    }

    private float getRandomSpred(){
        return (float) (Math.random()*spawnRange*2-spawnRange);
    }
}
