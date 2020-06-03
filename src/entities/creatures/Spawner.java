package entities.creatures;

import graphics.Texture;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;

public class Spawner extends Creature {
    int spawnRate,counter =0,retryAmount,retrycounter=0;
    float spawnRange;
    Creature creature;

    public Spawner(float x, float y,int lvl,  Creature creature ,Game game) {
        super(x, y, game);
        this.lvl=lvl;
        this.creature=creature;
        this.retryAmount=5;
        this.spawnRange=2;
        this.spawnRate=10;
    }

    public Spawner(float x, float y,int lvl , Creature creature,int retryAmount,int spawnRate ,float spawnRange, Game game) {
        super(x, y, game);
        this.lvl=lvl;
        this.creature=creature;
        this.retryAmount=retryAmount;
        this.spawnRate=spawnRate;
        this.spawnRange=spawnRange;
    }

    public void increaselvl(int v){
        lvl+=v;
    }

    private void action()  {
        float w= creature.getHeight();
        float h = creature.getWidth();
        if(retrycounter>=retryAmount){
            retrycounter=0;
            return;
        }

        float xs=getRandomSpred(),ys=getRandomSpred();
        if(checkCollision_ifOneOf(new Rectangle2D.Double(x+xs,y+ys,w,h))==null&&checkCollision_ifOneOf(hitbox)==null){
            try {
                game.getCreatureHandler().addObject(creature.getClass().getConstructor(Float.TYPE,Float.TYPE,Integer.TYPE , Game.class).newInstance(x+xs,y+ys,lvl,game));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            counter = 0;
            retrycounter=0;
            return;
        }else {
            retrycounter++;
            action();
        }

    }

    @Override
    public void tick() {
        if(counter>spawnRate) {
            action();
        }
        counter++;
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
