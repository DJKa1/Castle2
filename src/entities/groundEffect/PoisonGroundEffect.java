package entities.groundEffect;

import main_pack.Game;

public class PoisonGroundEffect extends GroundEffect {

    public PoisonGroundEffect(float x, float y, Game game) {
        super(x, y, game);
        duration = 60;
        radius = 2;
        dmg = 10;
    }
}
