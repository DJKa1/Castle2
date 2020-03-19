package entities.creatures;

import main_pack.Launcher;

import static main_pack.Game.SCALE;
import static main_pack.Game.UNITDIMENSION;

public class Camera {
    private float x;
    private float y;


    public Camera(float x, float y) {
        this.x = x;
        this.y = y;

    }

    public void tick(Creature player) {
        x = -player.getX()+ Launcher.WIDTH/2 - (int)(UNITDIMENSION*SCALE)/2;
        y = -player.getY()+ Launcher.HEIGHT/2 - (int)(UNITDIMENSION*SCALE)/2;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
