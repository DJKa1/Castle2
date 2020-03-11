package States;

import java.awt.*;

public class MenuState extends State{


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);

        g.drawRect(200,200,200,200);

    }
}
