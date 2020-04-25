package Maps;

import Pathfinding.AStar;
import Pathfinding.Node;
import Tiles.Tile;
import main_pack.Game;
import main_pack.KeyboardInput;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Map {

    public static Rectangle BORDER;

    protected int MapWidth = 16, MapHeight = 16;
    protected Tile[][][] Tiles;
    private AStar pathfinder;
    private int[][]solidTiles;

    public Map(String LevelName) {
        Tiles = JSON.loadMapJson(LevelName);
        loadMap();
        createPathfinding(Tiles);
    }

    public void loadMap() {
        BORDER = new Rectangle(0, 0, MapWidth, MapHeight);
    }

    public List<Node> getPath(Point2D start, Point2D end ){
        pathfinder.setInitialNode(new Node((int)Math.floor(start.getX()),(int)Math.floor(start.getY())));
        pathfinder.setFinalNode(new Node((int)Math.floor(end.getX()),(int)Math.floor(end.getY())));
        return pathfinder.findPath();
    }



    private int[][]loadSolidTilesArray(Tile[][][] t){
        ArrayList<int[]>arrayList=new ArrayList<>();
        for (int i=0; i<MapWidth;i++) {
            for (int j = 0; j < MapHeight; j++) {
                Tile temp =t[i][j][1];
                if (temp!= null){
                    if(temp.isSolid()){
                        //System.out.println("Tile X"+i+" and Y"+j +"isSolid");
                        arrayList.add(new int[]{i,j});
                    }
                }
            }
        }
        int [][] blockArray= new int [arrayList.size()][2];
        for (int i=0;i<arrayList.size();i++){
            blockArray[i][0]=arrayList.get(i)[0];
            blockArray[i][1]=arrayList.get(i)[1];
        }
        return blockArray;
    }
    private void createPathfinding(Tile[][][] t){
        pathfinder=new AStar(MapHeight,MapWidth);
        solidTiles=loadSolidTilesArray(t);
        pathfinder.setBlocks(solidTiles);
    }

    public Tile getTilebyCords(int x, int y,int layer) {
        return Tiles[x][y][layer];
    }

    public Point2D getCenterofTiles(int x, int y){
        return new Point.Float(x+0.5f,y+0.5f) ;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        for (int layer = 0; layer < 3; layer++) {
            for (int y = 0; y < MapHeight; y++) {
                for (int x = 0; x < MapWidth; x++) {
                    if (Tiles[x][y][layer] != null) {
                        Rectangle2D temp = Tiles[x][y][layer].getHitbox();
                        g.drawImage(Tiles[x][y][layer].getImg(), x * Game.UNIT_SCALE, y * Game.UNIT_SCALE, Game.UNIT_SCALE, Game.UNIT_SCALE, null);
                    }
                }
            }
        }
        if(KeyboardInput.f3G){
             renderHitbox(g);
            markSolidTiles(g);
        }
    }

    private void renderHitbox(Graphics g){
        for (int layer = 0; layer<3;layer++) {
            for (int y = 0; y < MapHeight; y++) {
                for (int x = 0; x < MapWidth; x++) {
                    if (Tiles[x][y][layer] != null) {
                        Rectangle2D temp = Tiles[x][y][layer].getHitbox();
                        if (temp != null) {
                            g.setColor(Color.pink);
                            g.drawRect((int) (temp.getX() * Game.UNIT_SCALE), (int) (temp.getY() * Game.UNIT_SCALE), (int) (temp.getWidth() * Game.UNIT_SCALE), (int) (temp.getHeight() * Game.UNIT_SCALE));
                        }
                    }
                }
            }
        }
    }

    private void markSolidTiles(Graphics g){
        g.setColor(Color.YELLOW);
        for (int i=0; i<solidTiles.length;i++)
        g.fillRect(solidTiles[i][0]*Game.UNIT_SCALE,solidTiles[i][1]*Game.UNIT_SCALE,10,10);
    }

    private void renderPath(Graphics g ,List<Node> path){
        Graphics2D g2d=(Graphics2D) g;
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(10));
        for (int i=1; i< path.size();i++){

            g2d.drawLine(path.get(i-1).getX()*Game.UNIT_SCALE,path.get(i-1).getY()*Game.UNIT_SCALE,path.get(i).getX()*Game.UNIT_SCALE,path.get(i).getY()*Game.UNIT_SCALE);
        }
        g2d.setStroke(new BasicStroke());
    }



}
