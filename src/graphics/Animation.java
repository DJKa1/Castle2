package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage... args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for(int i = 0;i< args.length;i++) {
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void runAnimation() {
        index++;
        if(index > speed) {
            index = 0;
            nextFrame();
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BufferedImage[] getImages() {
        return images;
    }

    public void setImages(BufferedImage[] images) {
        this.images = images;
    }

    public BufferedImage getCurrentImg() {
        return currentImg;
    }

    public void setCurrentImg(BufferedImage currentImg) {
        this.currentImg = currentImg;
    }

    private void nextFrame(){
        for(int i = 0;i<frames;i++) {
            if(count == i) {
                currentImg = images[i];
            }
        }
        count++;

        if(count > frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int scale) {
        g.drawImage(currentImg, x, y,scale,scale, null);
    }

}
