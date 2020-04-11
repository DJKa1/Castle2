package entities.creatures;
import entities.Entity;
import entities.ID;
import entities.projectile.Projectile;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Creature extends Entity {
    protected float hp;
    protected float maxHp;
    protected float movementRate;
    protected Rectangle2D.Double movementhitbox;
    protected float baseDmg;
    protected CreatureHandler creatureHandler;
    protected ProjectileHandler projectileHandler;

    //Tageting-------------------
    protected Ellipse2D targetingArea;
    protected Creature currentTarget;
    protected float targetingRange;

    //InteractionLists------------------
    protected ID[] targetable;
    protected ID[] blockedby;
    protected ID[] hitable;

    //Constructor-----------------------
    public Creature(float x,float y,CreatureHandler creatureHandler,ProjectileHandler projectileHandler){
        super(x,y);
        id=ID.valueOf(this.getClass().getSimpleName());
        this.creatureHandler=creatureHandler;
        this.projectileHandler=projectileHandler;
        targetable=new ID []{};
        blockedby=new ID[]{};
        hitable=new ID []{};
        movementhitbox=new Rectangle2D.Double();
        targetingArea=new Ellipse2D.Double();
    }

    //Getter && Update------------------
    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }
    public void updateMovementhitbox(float xOffset, float yOffset){
        movementhitbox.setRect(x+xOffset,y+height+yOffset,width,height/4);
    }
    public void normalizeMovementhitbox(){
        movementhitbox.setRect(x,y+height,width,height/4);
    }

    public void updateTargetingArea(){
        targetingArea.setFrameFromCenter(x+width/2,y+height/2,x+width/2-targetingRange,y+height/2-targetingRange);
    }

    //Render---------------------------------
    public void renderHealthbar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPixelPosition(x)-5,getPixelPosition(y)-5, (int) (width* Game.UNIT_SCALE)+10,30);
        g.setColor(Color.WHITE);
        g.fillRect(getPixelPosition(x),getPixelPosition(y), (int) (width*Game.UNIT_SCALE),20);
        g.setColor(Color.RED);
        g.fillRect(getPixelPosition(x),getPixelPosition(y), (int) (hp/maxHp*width*Game.UNIT_SCALE),20);
    }
    public void renderHitbox(Graphics g){
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x), getPixelPosition(y), getPixelPosition(width), getPixelPosition(height));
        g.setColor(Color.lightGray);
        g.drawRect(getPixelPosition(x),getPixelPosition(y+width),getPixelPosition(width),getPixelPosition(height/4));
        g.setColor(Color.red);
        g.drawOval(getPixelPosition(targetingArea.getX()),getPixelPosition(targetingArea.getY()),getPixelPosition(targetingArea.getWidth()),getPixelPosition(targetingArea.getWidth()));
    }

    //TargetingFunktions------------------------
    public Creature searchTarget(){
        for (Creature c:CreatureHandler.creatures){
            for (ID id :targetable){
                if (c.getId()==id){
                    if(isInRange(c)){
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public boolean isInRange (Creature k){
        if (targetingArea.intersects(k.getHitbox())){
            return true;
        }
        return false;
    }


    //HitFunktions----------------------
    public float caculateDmg(){
        return baseDmg;
    }
    public void hitbyProjectile(Projectile p){
        hp-=p.caculateDmg();
    }
    public void removeifdead(){
        if(hp<=0){
            creatureHandler.removeObject(this);
        }
    }
}
