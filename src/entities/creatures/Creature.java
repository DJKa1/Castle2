package entities.creatures;

import Buffs.Buff;
import Buffs.Poison;
import Buffs.iced;
import Handler.Effectshandler;
import entities.Entity;
import ID_Lists.ID;
import entities.Knockback;
import entities.projectile.Projectile;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;
import main_pack.KeyboardInput;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.RGBImageFilter;
import java.util.LinkedList;

public abstract class Creature extends Entity {
    protected float hp;
    protected float maxHp;
    protected float movementRate;
    protected Rectangle2D.Double movementhitbox;
    protected float baseDmg;
    protected CreatureHandler creatureHandler;
    protected ProjectileHandler projectileHandler;
    protected Effectshandler effectshandler;
    protected LinkedList<Buff> activeBuffs;
    protected RGBImageFilter colorMask;
    protected int manaCount;
    protected Knockback currentKnockback;

    //Tageting-------------------
    protected Ellipse2D targetingArea;
    protected Creature currentTarget;
    protected float targetingRange;

    //InteractionLists------------------
    protected ID[] targetable;
    protected ID[] blockedby;
    protected ID[] hitable;

    //Constructor-----------------------
    public Creature(float x, float y, CreatureHandler creatureHandler, ProjectileHandler projectileHandler, Effectshandler effectshandler) {
        super(x, y);
        id = ID.valueOf(this.getClass().getSimpleName());
        this.creatureHandler = creatureHandler;
        this.projectileHandler = projectileHandler;
        this.effectshandler = effectshandler;
        targetable = new ID[]{};
        blockedby = new ID[]{};
        hitable = new ID[]{};
        movementhitbox = new Rectangle2D.Double();
        targetingArea = new Ellipse2D.Double();
        activeBuffs = new LinkedList<>();
    }

    //Getter && Update------------------
    public ProjectileHandler getProjectileHandler() {
        return projectileHandler;
    }

    public Effectshandler getEffectshandler() {
        return effectshandler;
    }

    public float getHp() {
        return hp;
    }

    public float getMaxHp() {
        return maxHp;
    }

    public int getManaCount() {
        return manaCount;
    }

    public void setCurrentKnockback(Knockback currentKnockback) {
        this.currentKnockback = currentKnockback;
    }

    public Knockback getCurrentKnockback() {
        return currentKnockback;
    }

    public void reduceMana(int v) {
        manaCount -= v;
    }

    public float getMovementRate() {
        return movementRate;
    }

    public void setMovementRate(float movementRate) {
        this.movementRate = movementRate;
    }

    public void updateMovementhitbox(float xOffset, float yOffset) {
        movementhitbox.setRect(x + xOffset, y + height + yOffset, width, height / 4);
    }

    public void normalizeMovementhitbox() {
        movementhitbox.setRect(x, y + height, width, height / 4);
    }

    public void updateTargetingArea() {
        targetingArea.setFrameFromCenter(x + width / 2, y + height / 2, x + width / 2 - targetingRange, y + height / 2 - targetingRange);
    }

    //Render---------------------------------
    public void renderHealthbar(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(getPixelPosition(x) - 5, getPixelPosition(y) - 5, (int) (width * Game.UNIT_SCALE) + 10, 30);
        g.setColor(Color.WHITE);
        g.fillRect(getPixelPosition(x), getPixelPosition(y), (int) (width * Game.UNIT_SCALE), 20);
        g.setColor(Color.RED);
        g.fillRect(getPixelPosition(x), getPixelPosition(y), (int) (hp / maxHp * width * Game.UNIT_SCALE), 20);
    }

    public void renderHitbox(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(getPixelPosition(x), getPixelPosition(y), getPixelPosition(width), getPixelPosition(height));
        g.setColor(Color.lightGray);
        g.drawRect(getPixelPosition(x), getPixelPosition(y + width), getPixelPosition(width), getPixelPosition(height / 4));
        g.setColor(Color.red);
        g.drawOval(getPixelPosition(targetingArea.getX()), getPixelPosition(targetingArea.getY()), getPixelPosition(targetingArea.getWidth()), getPixelPosition(targetingArea.getWidth()));
    }

    //TargetingFunktions------------------------
    public Creature searchTarget() {
        for (Creature c : CreatureHandler.creatures) {
            for (ID id : targetable) {
                if (c.getId() == id) {
                    if (isInRange(c)) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public boolean isInRange(Creature k) {
        if (targetingArea.intersects(k.getHitbox())) {
            return true;
        }
        return false;
    }


    //HitFunktions----------------------
    public float caculateDmg() {
        return baseDmg;
    }

    public void hitbyProjectile(Projectile p) {
        hp -= p.caculateDmg();
    }

    public void hitbyEffect(double v) {
        hp -= v;
    }

    public void removeifdead() {
        if (hp <= 0) {
            creatureHandler.removeObject(this);
        }
    }

    public void getMovementFromKnockBack() {
        move.x = currentKnockback.move.x;
        move.y = currentKnockback.move.y;
        currentKnockback.tick();
        if (currentKnockback.getDuration() <= 0) {
            currentKnockback = null;
        }
    }


    //EffectManagment---------------------
    public void addBuff(Buff buff) {
        this.activeBuffs.add(buff);
    }

    public void removeBuff(Buff buff) {
        this.activeBuffs.remove(buff);
    }

    public void addBuffbyID(String id, int duration, int lvl) {
        switch (id) {
            default:
                return;
            case "Poison":
                addBuff(new Poison(this, duration, lvl));
                break;
            case "iced":
                addBuff(new iced(this, duration, lvl));
        }
    }

    public void tickActiveBuffs() {
        for (int i = 0; i < activeBuffs.size(); i++) {
            activeBuffs.get(i).tick();
        }
    }

    //Movement && Collision-------------------------
    protected void movement() {
        updateMovement();
        speedX = (float) (move.x * movementRate);
        speedY = (float) (move.y * movementRate);

        normalizeHitbox();
        normalizeMovementhitbox();

        collision();

        y += speedY;
        x += speedX;
    }

    protected void updateMovement() {
        if (currentKnockback == null) {
            move.x = 0;
            move.y = 0;
        } else {
            getMovementFromKnockBack();
        }
        move.normalize();
    }

    protected void collision() {
        //XOffset-----------------------------------------
        if (speedX != 0) {
            updateHitbox(speedX, 0);
            updateMovementhitbox(speedX, 0);

            Creature k = checkCollision_ifOneOf(blockedby);
            if (k != null) {
                float i = getFreeSpaceindirectionX(k.getHitbox());
                if (i != -1) {
                    speedX = i;
                }
            }

            Rectangle2D.Double temp = collisionWithTiles(getTilesinDirection(speedX, 0, movementhitbox), movementhitbox);
            if (temp != null) {
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
            updateMovementhitbox(0, speedY);

            Creature k = checkCollision_ifOneOf(blockedby);
            if (k != null) {
                float i = getFreeSpaceindirectionY(k.getHitbox());
                if (i != -1) {
                    speedY = i;
                }
            }
            Rectangle2D.Double temp = collisionWithTiles(getTilesinDirection(0, speedY, movementhitbox), movementhitbox);
            if (temp != null) {
                normalizeMovementhitbox();
                float i = getFreeSpaceindirectionY(movementhitbox, temp);
                if (i != -1) {
                    speedY = i;
                }
            }
        }
    }
}
