package entities;

public class Knockback {

    public Vector2D move;
    private int duration;
    public Knockback(Vector2D v,int duration){
        this.duration=duration;
        this.move=v;
    }
    public void tick(){
        duration--;
    }
    public int getDuration() {
        return duration;
    }
}
