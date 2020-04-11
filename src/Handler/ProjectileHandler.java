package Handler;
import entities.projectile.Projectile;
import main_pack.KeyboardInput;

import java.awt.*;
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
    }
    public void render(Graphics g) {
        for (int i = 0; i < projectile.size(); i++) {
            Projectile tempObject = projectile.get(i);
            tempObject.render(g);

        }
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
