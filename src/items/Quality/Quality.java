package items.Quality;


import ID_Lists.QualityID;

import java.awt.*;
import java.util.Random;

public abstract class Quality {

    protected float minD, maxD,minA,maxA;
    protected float dmg;
    protected float armorBoost;


    protected Color color;
    protected QualityID id;
    private Random r=new Random();


    public Quality(){
        id=QualityID.valueOf(this.getClass().getSimpleName());
    }


    public void rollDmg(){
        dmg= (r.nextFloat()*(maxD-minD))+minD;
    }

    public void rollArmor(){
        armorBoost= (r.nextFloat()*(maxA-minA))+minA;
    }

    public float getDmg() {
        return dmg;
    }

    public float getArmorBoost() {
        return armorBoost;
    }
    public QualityID getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public void setDmg(float dmg) {
        this.dmg = dmg;
    }

    public void setArmorBoost(float armorBoost) {
        this.armorBoost = armorBoost;
    }
}
