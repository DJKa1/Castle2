package entities.projectile;

import java.awt.*;

public class Plasmabolt extends Projectile {
    protected float sy,sx,h;


    public Plasmabolt(float x, float y,float aimX,float aimY) {
        super(x, y);
        this.projeticespeed = 5;
        this.aimY = aimY;
        this.aimX = aimX;

       speedX=1;
        speedY = aimY / aimX;


        if (aimX < 0 && aimY < 0) {
            speedX*=-1;
            speedY*=-1;


        }else if (aimX<0){
                speedY*=-1;
                speedX*=-1;


        }
        h= projeticespeed / (Math.abs(speedX )+Math.abs(speedY));
        speedY *= h;
        speedX *= h;

        System.out.println(speedX+"   "+speedY);
        System.out.println(aimX+"   "+aimY);



    }



    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);

        g.drawLine((int) (x - speedX * 1), (int) (y - speedY * 1), (int) x, (int) y);

    }
}
