package entities.projectile;

import Buffs.iced;
import Handler.Effectshandler;
import Handler.ProjectileHandler;
import entities.Vector2D;
import graphics.Texture;
import items.Weapons.Weapons;
import main_pack.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class IceShard extends Projectile {
    private double angle;

    public IceShard(float x, float y, Vector2D v, double angle, ProjectileHandler projectileHandler, Effectshandler effectshandler, Weapons weapons) {
        super(x, y, projectileHandler, effectshandler,weapons);
        this.angle = Math.toDegrees(angle);
        width = (float) 2 / 16;
        height = (float) 2 / 16;
        hitbox = new Rectangle2D.Double(x, y, width, height);
        projeticespeed = (float) 0.2;
        img = Texture.sprite[22];
        move.set(v);
        move.normalize();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Texture.rotate(img, angle), (getPixelPosition(x)), (getPixelPosition(y)), Game.UNIT_SCALE, Game.UNIT_SCALE, null);
    }



}
