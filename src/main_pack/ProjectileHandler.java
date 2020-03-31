package main_pack;

import Maps.Map;
import entities.projectile.Projectile;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class ProjectileHandler {

    public static LinkedList<Projectile> projectile;

    public ProjectileHandler(){
        projectile= new LinkedList<>();


    }
    public void tick() {
        for (int i = 0; i < projectile.size(); i++) {
            Projectile tempObject = projectile.get(i);
            tempObject.tick();

        }
        removeUnneededProjectiles();
    }

    public void render(Graphics g) {
        for (int i = 0; i < projectile.size(); i++) {
            Projectile tempObject = projectile.get(i);
            tempObject.render(g);
        }

    }

    public void removeUnneededProjectiles(){
        for (int i = 0; i < projectile.size(); i++) {
            Projectile tempObject = projectile.get(i);
            if (!isInMap(tempObject.getHitbox())){
                removeObject(projectile.get(i));
            }
        }
    }


    public boolean isInMap(Rectangle2D.Double hitbox){
        if (Map.BORDER.contains(hitbox)){
            return true;
        }
        return false;
    }

    public void addObject(Projectile object) {
        this.projectile.add(object);
    }

    public void removeObject(Projectile object) {
        this.projectile.remove(object);
    }

    public int getProjectileCount(){
        return projectile.size();
    }
}
