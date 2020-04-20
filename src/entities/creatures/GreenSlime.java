package entities.creatures;
import States.GameState;
import ID_Lists.ID;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;
import java.awt.*;


public class GreenSlime extends Creature {
    public GreenSlime(float x, float y, CreatureHandler creatureHandler, ProjectileHandler projectileHandler) {
        super(x, y,creatureHandler,projectileHandler);
        width= 1;
        height=1;
        baseDmg=1;
        hp=10;
        maxHp = hp;
        targetingRange=3;
        targetable=new ID[]{ID.Player};
        movementRate= (float) 0.05;
        normalizeHitbox();
        normalizeMovementhitbox();
    }
    @Override
    public void tick() {
        removeifdead();
        updateTargetingArea();
        updateHitbox(0,0);
        tickActiveBuffs();

        if(currentTarget==null) {
            currentTarget = searchTarget();
        }
        movement();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameState.texture.sprite[43],getPixelPosition(x),getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);
    }

    @Override
    protected void updateMovement(){
        if(currentKnockback==null){
            if(currentTarget!=null){
                move.x=currentTarget.getX()-x;
                move.y=currentTarget.getY()-y;
            }else {
                move.x=0;
                move.y=0;
            }
            move.normalize();
        }
        else {
            getMovementFromKnockBack();
        }

    }
}
