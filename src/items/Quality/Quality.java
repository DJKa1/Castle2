package items.Quality;


import ID_Lists.QualityID;

import java.awt.*;
import java.util.Random;

public abstract class Quality {

    protected float minD;
    protected float maxD;
    protected float dmg;
    protected Color color;
    protected QualityID id;
    private Random r=new Random();


    public Quality(){
        id=QualityID.valueOf(this.getClass().getSimpleName());
    }

    public Quality(Float dmg){
        id=QualityID.valueOf(this.getClass().getSimpleName());
        this.dmg=dmg;

    }

    public void rollDmg(){
        dmg= (r.nextFloat()*(maxD-minD))+minD;
    }

    public float getDmg() {
        return dmg;
    }

    public QualityID getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
