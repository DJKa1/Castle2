package entities.creatures;
import entities.Entity;
import entities.projectile.Projectile;
import main_pack.CreatureHandler;


public abstract class Creature extends Entity {
    protected float hp;
    protected float movementRate;
    protected float baseDmg;
    protected CreatureHandler creatureHandler;

    public Creature(float x,float y,CreatureHandler creatureHandler){
        super(x,y);
        this.creatureHandler=creatureHandler;
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

}
