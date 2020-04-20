package entities.projectile;

import Buffs.iced;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.Vector2D;
import graphics.Texture;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class IceShard extends Projectile {
    private double angle;

    public IceShard(float x, float y, Vector2D v, double angle, ProjectileHandler projectileHandler, Effectshandler effectshandler) {
        super(x, y, projectileHandler, effectshandler);
        this.angle = Math.toDegrees(angle);
        width = (float) 2 / 16;
        height = (float) 2 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.3;
        baseDgm = 2;
        img = Game.texture.sprite[21];
        move.set(v);
        move.normalize();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.rotate(img, angle), (getPixelPosition(x)), (getPixelPosition(y)), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
    }



}
