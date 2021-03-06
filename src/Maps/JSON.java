package Maps;


import Tiles.Chest;
import Tiles.DoorTile;
import Tiles.DoubleDoor;
import Tiles.Tile;
import graphics.Texture;
import main_pack.Game;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;


public class JSON {
    //public static File conficFile = new File("C:\\Users\\Pete Louis Benz\\Documents\\LevelEditor\\config\\config.json");
    public static File saveMapFile = new File("rsc/Worlds/map.json");

    public static ArrayList<String> getLevelNames() {
        try {
            String speicher = Files.readString(Paths.get(saveMapFile.toURI()));
            JSONObject all = new JSONObject(speicher);
            Iterator<String> keys = all.keys();
            ArrayList<String> levelnames = new ArrayList<>();
            while (keys.hasNext()) {
                String key = keys.next();
                levelnames.add(key);
            }
            return levelnames;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exsists(String key) {
        try {
            String speicher = Files.readString(Paths.get(saveMapFile.toURI()));
            JSONObject all = new JSONObject(speicher);

            return all.has(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getGridSize(String level, boolean x) {
        try {
            String speicher = Files.readString(Paths.get(saveMapFile.toURI()));
            JSONObject all = new JSONObject(speicher);
            if (x) {
                return all.getJSONArray(level).getJSONObject(0).getInt("MapWidth");
            } else {
                return all.getJSONArray(level).getJSONObject(0).getInt("MapHeight");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static Tile[][][] loadMapJson(String levelname) {
        try {
            String jsonString = Files.readString(Paths.get(saveMapFile.toURI()));
            JSONObject all = new JSONObject(jsonString);
            JSONArray level = all.getJSONArray(levelname);
            //FirstObject stores Width and Height
            JSONObject dim = level.getJSONObject(0);
            int width = dim.getInt("MapWidth");
            int height = dim.getInt("MapHeight");


            Tile[][][] map = new Tile[width][height][3];


            JSONObject t = null;
            for (int i = 1; i < level.length(); i++) {
                t = level.getJSONObject(i);
                Tile tile;
                switch (t.getString("id")) {
                    case "door":
                        tile = new DoorTile(t.getInt("X"), t.getInt("Y"), Texture.tiles[t.getInt("imgX")][t.getInt("imgY")], false);
                        break;
                    case "chest":
                        tile = new Chest(t.getInt("X"), t.getInt("Y"), Texture.tiles[t.getInt("imgX")][t.getInt("imgY")], false);
                        break;
                    case "doubledoor":
                        tile = new DoubleDoor(t.getInt("X"), t.getInt("Y"), Texture.tiles[t.getInt("imgX")][t.getInt("imgY")], true);
                        tile.setHitbox(new Rectangle2D.Double(t.getDouble("X") + t.getDouble("ox") / 16d, t.getDouble("Y") + t.getDouble("oy") / 16d, t.getDouble("width") / 16d, t.getDouble("height") / 16d));
                        for (int yy = 0;yy<2;yy++) {
                            for (int xx = 0;xx<3;xx++) {
                                //if (xx!=0 && yy!=0) {
                                    Tile tempTile= new Tile(t.getInt("X")+xx,t.getInt("Y")+yy,Texture.tiles[t.getInt("imgX")+xx][t.getInt("imgY")+yy],true);
                                    tempTile.setHitbox(tile.getHitbox());
                                    map[t.getInt("X")+xx][t.getInt("Y")+yy][t.getInt("layer")] = tempTile;
                                //}
                            }
                        }
                        break;
                    default:
                        tile = new Tile(t.getInt("X"), t.getInt("Y"), Texture.tiles[t.getInt("imgX")][t.getInt("imgY")], false);
                        break;
                }

                if (t.has("ox")) {
                    tile.setSolid(true);
                    tile.setHitbox(new Rectangle2D.Double(t.getDouble("X") + t.getDouble("ox") / 16d, t.getDouble("Y") + t.getDouble("oy") / 16d, t.getDouble("width") / 16d, t.getDouble("height") / 16d));
                }

                map[t.getInt("X")][t.getInt("Y")][t.getInt("layer")] = tile;
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public static void SaveMapJSON(Tile[][][] map, String levelName) {
        try {
            String speicher = new String(Files.readAllBytes(Paths.get(saveMapFile.toURI())), "UTF-8");
            JSONObject all = new JSONObject(speicher);
            JSONArray levelArray = new JSONArray();
            if(all.has(levelName)) {
                levelArray = all.getJSONArray(levelName);
                System.out.println("OldLevel");
            }else {
                all.put(levelName,levelArray);
                System.out.println("NewLevel");
            }
                JSONObject Object = new JSONObject();
                Object.put("MapWidth", map.length);
                Object.put("MapHeight", map.length);
                levelArray.put(0, Object);
                for (int yy = 0; yy < map.length; yy++) {
                    for (int xx = 0; xx < map.length; xx++) {
                        for (int layer = 0; layer < 3; layer++) {
                            if (!(map[xx][yy][layer] == null)) {
                                Object = new JSONObject();
                                Object.put("name", "tile");
                                Object.put("layer", layer);
                                Object.put("X", xx);
                                Object.put("Y", yy);
                                Object.put("imgX", map[xx][yy][layer].getImgX());
                                Object.put("imgY", map[xx][yy][layer].getImgY());
                                levelArray.put(Object);
                            }
                        }
                    }
                }
            System.out.println(all.toString());
            FileWriter fileWriter = new FileWriter(saveMapFile);
            fileWriter.write(all.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

