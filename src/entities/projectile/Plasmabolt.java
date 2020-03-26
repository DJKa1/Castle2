package entities.projectile;

import main_pack.Game;

import java.awt.*;

public class Plasmabolt extends Projectile {
    protected float sy,sx,h;


    public Plasmabolt(float x, float y,float aimX,float aimY) {
        super(x, y);
        this.projeticespeed = 1;
        this.aimY = aimY;
        this.aimX = aimX;

        speedX=1;
        speedY =1;



    }



    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawLine((int)x,(int)y,(int)x-1,(int)y-1);

    }
}
