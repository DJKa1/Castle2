package items.Quality;

import java.awt.*;

public class Outstanding extends Quality {


    public Outstanding(){
        super();
        color= Color.RED;
        minD=2.2f;
        maxD=3.0f;
        dmg=rollDmg();
    }
}
