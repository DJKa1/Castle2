package entities.projectile;
import Handler.ProjectileHandler;
import ID_Lists.ID;
import entities.Vector2D;
import entities.creatures.Creature;
import java.awt.geom.Rectangle2D;

public class IceBall extends Projectile {
    private int splitAmount=8;

    public IceBall(float x, float y,float aimX ,float aimY,ProjectileHandler projectileHandler) {
        super(x, y, projectileHandler);
        this.aimY = aimY;
        this.aimX = aimX;
        isHit = new ID[]{ID.GreenSlime};
        width = (float) 6/16;
        height = (float) 6/16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.2;
        baseDgm = 1;
        img=null;
        lifeTime=20;
        move.set(aimX, aimY);
        move.normalize();

    }

    @Override
    public void tick() {
        speedX= (float) move.x*projeticespeed;
        speedY = (float) move.y*projeticespeed;
        x+=speedX;
        y+=speedY;
        collision();
        lifeTime--;
        if(lifeTime<0) {
            projectileHandler.removeObject(this);
            split(splitAmount);
        }

    }

    @Override
    protected void collision() {
        if(collisionWithTile()){
            projectileHandler.removeObject(this);
            split(splitAmount);
        }
        Creature[] creatures = checkCollision_forAll(isHit);
        for (Creature k : creatures) {
            if (k != null) {
                k.hitbyProjectile(this);
                projectileHandler.removeObject(this);
            }
        }
    }

    private void split(int amount){
        float x=(float)getCenter().getX(),y=(float)getCenter().getY();
        double a;
        for (int i=0; i<amount;i++){
            a=2*Math.PI/amount*i;
            projectileHandler.addObject(new IceShard(x,y,new Vector2D( Math.sin(a),Math.cos(a)),a,projectileHandler));
        }

    }
}
