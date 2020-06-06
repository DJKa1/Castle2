package entities.creatures.slotmachine;

import ID_Lists.SymbolID;
import graphics.Texture;

import java.awt.image.BufferedImage;

import static main_pack.Launcher.game;

public class Symbol {
    private BufferedImage img;
    private SymbolID id;

    public Symbol() {
    }

    public Symbol(SymbolID id) {
        this.id = id;
    }

    private void changeSymbol(SymbolID id) {
        this.id = id;
        switch (id) {
            case Heart:
                img = Texture.sprite[56];
                break;
            case Bell:
                img = Texture.sprite[57];
                break;
            case Questionmark:
                img = Texture.sprite[58];
                break;
        }
    }


    public void roll() {
        int p = (int) (Math.random() * 100);
        if (!game.getGameConsole().hackerman) {
            if (p < 33) {
                changeSymbol(SymbolID.Bell);
            } else if (p < 66) {
                changeSymbol(SymbolID.Heart);
            } else if (p <= 100) {
                changeSymbol(SymbolID.Questionmark);
            }
        }else {
            if (p < 0) {
                changeSymbol(SymbolID.Bell);
            } else if (p < 100) {
                changeSymbol(SymbolID.Heart);
            } else if (p <= 100) {
                changeSymbol(SymbolID.Questionmark);
            }
        }
    }


    public void showRoll() {
        int p = (int) (Math.random() * 100);
        if (p < 33) {
            changeSymbol(SymbolID.Bell);
        } else if (p < 66) {
            changeSymbol(SymbolID.Heart);
        } else if (p <= 100) {
            changeSymbol(SymbolID.Questionmark);
        }
    }

    public void roll(SymbolID id) {
        changeSymbol(id);
    }

    public BufferedImage getImg() {
        return img;
    }

    public SymbolID getId() {
        return id;
    }


    public double getMultiplier() {
        switch (id) {
            case Heart:
                return 3;
            case Bell:
                return 2;
            case Questionmark:
                return 1;
        }
        return 0;
    }
}
