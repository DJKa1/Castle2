package items;
import java.awt.image.BufferedImage;

public abstract class Item {

    protected ItemID id;
    protected int stackSize;
    protected BufferedImage image;

    public Item(){
        this.id=ItemID.valueOf(this.getClass().getSimpleName());

    }


    public ItemID getId() {
        return id;
    }

    public int getStackSize() {
        return stackSize;
    }

    public BufferedImage getImage(){
        return image;
    }
}
