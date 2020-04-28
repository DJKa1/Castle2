package entities.creatures;

import Buffs.Buff;
import Buffs.Poison;
import Buffs.iced;
import Effects.DmgIndicator;
import Effects.HitAnimation;
import Handler.Effectshandler;
import ID_Lists.ProjectileID;
import Maps.Map;
import Pathfinding.Node;
import entities.Entity;
import ID_Lists.ID;
import entities.Knockback;
import Handler.CreatureHandler;
import main_pack.Game;
import Handler.ProjectileHandler;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


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
    protected int manaCount;
    protected Knockback currentKnockback;
    protected int hitCooldown;
    protected double armorValue;
    protected Game game;
    protected Map map;
    protected List<Node> nextMoves;
    protected float aimX,aimY;

    //Tageting-------------------
    protected Ellipse2D targetingArea;
    protected Creature currentTarget;
    protected float targetingRange,followingMultiplier;

    //InteractionLists------------------
    protected ID[] targetable;
    protected ID[] blockedby;
    protected ProjectileID[] nothitby;

    //Constructor-----------------------
    public Creature(float x,float y,Game game){
        super(x,y);
        id=ID.valueOf(this.getClass().getSimpleName());
        this.game=game;
        this.creatureHandler=game.getCreatureHandler();
        this.projectileHandler=game.getProjectileHandler();
        this.effectshandler = game.getEffectshandler();
        this.map=game.getMap();
        targetable=new ID []{};
        blockedby=new ID[]{};
        nothitby=new ProjectileID[]{};
        movementhitbox=new Rectangle2D.Double();
        targetingArea=new Ellipse2D.Double();
        activeBuffs=new LinkedList<>();
        nextMoves=new ArrayList<>();

        //baseValues------------------------
        followingMultiplier=1;

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

    public void reduceMana(int v){
        manaCount-=v;
    }

    public float getAimX() {
        return aimX;
    }

    public void setAimX(float aimX) {
        this.aimX = aimX;
    }

    public float getAimY() {
        return aimY;
    }

    public void setAimY(float aimY) {
        this.aimY = aimY;
    }

    public float getMovementRate() {return movementRate; }

    public void setMovementRate(float movementRate) {
        this.movementRate = movementRate;
    }

    public void updateMovementhitbox(float xOffset, float yOffset){
        movementhitbox.setRect(x+xOffset+width/8,y+height+yOffset,width-width/8,height/4);
    }
    public Point2D.Float getCenterFromMovementHitbox(){
        return new Point2D.Float((float)movementhitbox.getCenterX(),(float)movementhitbox.getCenterY());
    }
    public void normalizeMovementhitbox(){
        movementhitbox.setRect(x+width/8,y+height,width-width/8,height/4);
    }

    public void updateTargetingArea(){
        targetingArea.setFrameFromCenter(x+width/2,y+height/2,x+width/2-targetingRange,y+height/2-targetingRange);
    }

    public void setCurrentTarget(Creature k){
            currentTarget = k;
    }

    public void sysoutMoves(){
        for (int i= 0 ;i<nextMoves.size();i++){
            System.out.println(nextMoves.get(i));
        }
    }

    //Render && Tick---------------------------------
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

    public void renderPath(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(10));
        for (int i=1; i< nextMoves.size();i++){

            g2d.drawLine(nextMoves.get(i-1).getX()*Game.UNIT_SCALE,nextMoves.get(i-1).getY()*Game.UNIT_SCALE,nextMoves.get(i).getX()*Game.UNIT_SCALE,nextMoves.get(i).getY()*Game.UNIT_SCALE);
        }
        g2d.setStroke(new BasicStroke());
    }

    @Override
    public void tick(){
        hitCooldown--;
        tickActiveBuffs();
        movement();
        removeifdead();
        updateTargetingArea();

        if(currentTarget==null) {
            setCurrentTarget(searchTarget());
        }else {
            updateTarget();

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

    protected void updateMovement(){
        if(currentKnockback==null){
            if(!nextMoves.isEmpty()) {
                move.x = (nextMoves.get(0).getX() + 0.5f) - getCenterFromMovementHitbox().getX();
                move.y = (nextMoves.get(0).getY() + 0.5f) - getCenterFromMovementHitbox().getY();
                checkifTargetReached();
            }else {
                getMoves();
                move.x=0;
                move.y=0;
            }
            move.normalize();
        }else {
            getMovementFromKnockBack();
        }

    }
    protected void getMoves(){
        if(currentTarget!=null){
            nextMoves=map.getPath(getCenterFromMovementHitbox(),currentTarget.getCenterFromMovementHitbox());
        }
    }

    public void getMovementFromKnockBack(){
        move.x=currentKnockback.move.x;
        move.y=currentKnockback.move.y;
        currentKnockback.tick();
        if(currentKnockback.getDuration()<=0){
            currentKnockback=null;
        }
    }

    protected void checkifTargetReached(){
        if (Math.floor(getCenterFromMovementHitbox().getX())==nextMoves.get(0).getX()&&Math.floor(getCenterFromMovementHitbox().getY())==nextMoves.get(0).getY()){
            nextMoves.remove(0);
        }

    }

    //TargetingFunktions------------------------

    protected void updateTarget(){
        aimX=currentTarget.x-x;
        aimY=currentTarget.y-y;

        if(isInRange(currentTarget)){
            attack();

        }
        if(!checkifNear(currentTarget)){
            currentTarget=null;
        }

    }

    protected Creature searchTarget(){
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

    protected void attack(){

    }
    public boolean isInRange (Creature k){
        if (targetingArea.intersects(k.getHitbox())){
            return true;
        }
        return false;
    }
    protected boolean checkifNear(Creature k){
        if(k.getCenterFromMovementHitbox().distance(getCenterFromMovementHitbox())<(targetingRange*followingMultiplier)){
            return true;
        }
        return false;
    }




    protected void collision(){
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


    //HitFunktions----------------------------------------
    public void hit(float value, Buff buff) {
        if (hitCooldown <= 0) {
            if (buff != null) {
                addBuff(buff);
            }
            hp -= getDmgAfterArmor(value);
            effectshandler.addObject(new DmgIndicator(x,y,getDmgAfterArmor(value),effectshandler));
            //hitCooldown= (int) (0.1*Game.TICKRATE);
            hitCooldown = 1;
            effectshandler.addObject(new HitAnimation(x,y,hitCooldown,effectshandler));
        }
    }

    private float getDmgAfterArmor(float f){
        final double ARMORMULTIPLIER=0.95;
        return (float) (f*( Math.pow(ARMORMULTIPLIER,armorValue)));
    }

    public boolean isHitby(ProjectileID id){
        for (ProjectileID p :nothitby){
            if (p==id){
                return false;
            }
        }
        return true;
    }
    public void removeifdead(){
        if(hp<=0){
            creatureHandler.removeObject(this);
        }
    }

    //EffectManagment---------------------
    public void addBuff(Buff buff) {
        this.activeBuffs.add(buff);
    }
    public void removeBuff(Buff buff) {
        this.activeBuffs.remove(buff);
    }

    public void addBuffbyID(String id, int duration ,int lvl){
        switch (id){
            default:return;
            case "Poison":addBuff(new Poison(this,duration,lvl));break;
            case "iced":addBuff(new iced(this,duration,lvl));
        }
    }
    public void tickActiveBuffs(){
        for (int i = 0; i < activeBuffs.size(); i++) {
            activeBuffs.get(i).tick();
        }
    }

}
