package entities.creatures;
import entities.Entity;
import entities.ID;
import entities.projectile.Projectile;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public abstract class Creature extends Entity {
    protected float hp;
    protected float maxHp;
    protected float movementRate;
    protected float baseDmg;
    protected CreatureHandler creatureHandler;
    protected ProjectileHandler projectileHandler;
    protected Ellipse2D targetingArea;
    protected float targetingRange;
    protected ID[] targetable;

    public Creature(float x,float y,CreatureHandler creatureHandler,ProjectileHandler projectileHandler){
        super(x,y);
        this.creatureHandler=creatureHandler;
        this.projectileHandler=projectileHandler;
    }

    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }


    public float caculateDmg(){
        return baseDmg;
    }


    public void removeifdead(){
        if(hp<=0){
            creatureHandler.removeObject(this);
        }
    }
    public void hitbyProjectile(Projectile p){
        hp-=p.caculateDmg();
    }

    public void renderHealthbar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPixelPosition(x)-5,getPixelPosition(y)-5, (int) (width* Game.UNIT_SCALE)+10,30);
        g.setColor(Color.WHITE);
        g.fillRect(getPixelPosition(x),getPixelPosition(y), (int) (width*Game.UNIT_SCALE),20);
        g.setColor(Color.RED);
        g.fillRect(getPixelPosition(x),getPixelPosition(y), (int) (hp/maxHp*width*Game.UNIT_SCALE),20);
    }

}
