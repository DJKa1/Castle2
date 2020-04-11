package entities.creatures;
import Buffs.Poison;
import States.GameState;
import ID_Lists.ID;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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

        //test--------------------------------
        //--------------------------------------
    }
    @Override
    public void tick() {
        removeifdead();
        updateTargetingArea();
        updateHitbox(0,0);
        tickActiveBuffs();

        if(currentTarget==null){
            currentTarget=searchTarget();
        }else {
            movement();

        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(GameState.texture.sprite[43],getPixelPosition(x),getPixelPosition(y),Game.UNIT_SCALE,Game.UNIT_SCALE,null);
        renderHealthbar(g);
    }

    private void movement(){

            move.x=currentTarget.getX()-x;
            move.y=currentTarget.getY()-y;
            move.normalize();

            speedX = (float) (move.x * movementRate);
            speedY = (float) (move.y * movementRate);

            normalizeHitbox();
            normalizeMovementhitbox();
            collision();

            y += speedY;
            x += speedX;
    }

    private void collision() {
        //XOffset-----------------------------------------
        if (speedX != 0) {
            updateHitbox(speedX, 0);
            updateMovementhitbox(speedX,0);

            Creature k = checkCollision_ifOneOf(blockedby);
            if(k!=null){
                float i = getFreeSpaceindirectionX(k.getHitbox());
                if (i != -1) {
                    speedX = i;
                }
            }

            Rectangle2D.Double temp=collisionWithTiles(getTilesinDirection(speedX,0,movementhitbox),movementhitbox);
            if (temp!=null) {
                normalizeMovementhitbox();
                float i = getFreeSpaceindirectionX(movementhitbox, temp);
                if (i != -1) {
                    speedX = i;
                }
            }
        }

        //YOffest---------------------------------------------
        if (speedY != 0) {
            updateHitbox(0, speedY);
            updateMovementhitbox(0,speedY);

            Creature k = checkCollision_ifOneOf( blockedby);
            if(k!=null){
                float i = getFreeSpaceindirectionY(k.getHitbox());
                if (i != -1) {
                    speedY = i;
                }
            }
            Rectangle2D.Double temp=collisionWithTiles(getTilesinDirection(0,speedY,movementhitbox),movementhitbox);
            if(temp!=null){
                normalizeMovementhitbox();
                float i = getFreeSpaceindirectionY(movementhitbox,temp);
                if (i != -1) {
                    speedY = i;
                }
            }
        }

    }



}
