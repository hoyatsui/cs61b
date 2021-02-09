package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 55;
    private static final Random RANDOM = new Random(123123);

    /*
     Create a big hexagonal region consisting of several random small hexagonal regions.
     */
    public static void makeHexWorld(TETile[][] world, int lowerLeftY,
                                     int item, int width, int size) {
        int middleLowerLeftX = WIDTH / 2 - item;
        int xInstance = 2 * item - 1;
        int yInstance = item;
        for (int j = 0; j < 5; j++) {
            if (j < 3) {
                int mllx = middleLowerLeftX + j * xInstance;
                int lly =  lowerLeftY + j * yInstance;
                int ssize = size + j * yInstance;
                for (int i = 0; i < 3 + j; i++) {
                    addRandomHexagon(world,
                            mllx - i * xInstance,
                            lly + i * yInstance,
                            item, width,
                            ssize + i * yInstance);
                }
            } else {
                int mllx = middleLowerLeftX + 2 * xInstance;
                int lly = lowerLeftY + j * 2 * yInstance - 2 * yInstance;
                int ssize = size + j * 2 * yInstance - 2 * yInstance;
                for (int i = 0; i < 7 - j; i++) {
                    addRandomHexagon(world,
                            mllx - i * xInstance,
                            lly + i * yInstance,
                            item, width,
                            ssize + i * yInstance);
                }
            }
        }
    }


    /*
     Create a random hexagonal tile region.
     */
    public static void addRandomHexagon(TETile[][] world, int lowerLeftX, int lowerLeftY,
                                         int item, int width, int size) {
        TETile tile;
        int numOfTile = RANDOM.nextInt(8);
        switch (numOfTile) {
            case 0 :
                tile = Tileset.FLOWER;
                break;
            case 1:
                tile = Tileset.WALL;
                break;
            case 2:
                tile = Tileset.FLOOR;
                break;
            case 3:
                tile = Tileset.GRASS;
                break;
            case 4:
                tile = Tileset.MOUNTAIN;
                break;
            case 5:
                tile = Tileset.SAND;
                break;
            case 6:
                tile = Tileset.TREE;
                break;
            case 7:
                tile = Tileset.WATER;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + numOfTile);
        }
        addHexagon(world, lowerLeftX, lowerLeftY, item, width, tile, size);
    }

    /*
        Create a hexagonal region of given type tiles.
    */
    public static void addHexagon(TETile[][] world, int lowerLeftX, int lowerLeftY, int item,
                                  int width, TETile t, int size) {
        int space = (width - item) / 2;
        int upperLeftY = 2 * size - 1 - lowerLeftY;
        fillLines(world, lowerLeftX, lowerLeftY, upperLeftY, space, item, t);
        if (item == width) {
            int i = 1;
            return;
        }
        addHexagon(world, lowerLeftX, lowerLeftY + 1, item + 2, width, t, size);
    }

    /*
        Helper method for addHexagon.
     */
    // Fill lower lines with tiles.
    private static void fillLines(TETile[][] world, int lowerLeftX, int lowerLeftY,
                                  int upperLeftY, int space, int item, TETile t) {
        for (int i = 0; i < item; i++) {
            world[lowerLeftX + space + i][lowerLeftY] = t;
            world[lowerLeftX + space + i][upperLeftY] = t;
        }
    }
    // get width of lines.
    private static int getWidth(int s) {
        return  3 * s - 2;
    }
    // Fill with nothing
    public static void fillNothing(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }


    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        fillNothing(world);

        int size = 5;
        int width = getWidth(size);
//        addHexagon(world, 0, 0, size, width, Tileset.FLOWER);
//        addRandomHexagon(world, 0, 0, size, width);
        makeHexWorld(world, 0, size, width, size);
        // Draw the world to the screen
        ter.renderFrame(world);
    }
}

