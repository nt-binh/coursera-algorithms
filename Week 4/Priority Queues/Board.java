import java.util.ArrayList;
import java.util.List;

public class Board {
    
    private final int[][] tiles;
    private int hamming;
    private int manhattan;
    private List<Board> neighbors;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        this.hamming = 0;
        this.manhattan = 0;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                int tile = tiles[i][j];
                this.tiles[i][j] = tile;

                if (tile == 0) continue;
                if (tile != (i * dimension() + j + 1)) {
                    hamming+=1;                                                     // Hamming distance
                }

                int tileOriginI = (tile - 1) / dimension();
                int tileOriginJ = (tile - 1) - tileOriginI * dimension();
                manhattan += Math.abs(i - tileOriginI) + Math.abs(j - tileOriginJ); // Manhattan distance
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(dimension());
        for (int i = 0; i < dimension(); i++) {
            sb.append("\n");
            for (int j = 0; j < dimension(); j++) {
                sb.append(" " + tiles[i][j]);
            }
        }
        return sb.toString();
    }

    public int dimension() {
        return tiles[0].length;
    }

    public int hamming() {
        return hamming;
    }

    public int manhattan() {
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0 || manhattan() == 0;
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;

        Board anotherBoard = (Board) y;

        if (dimension() != anotherBoard.dimension()) return false;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] != (anotherBoard.tiles[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        if (neighbors != null) return neighbors;
        neighbors = new ArrayList<>();

        int blankTileI = 0, blankTileJ = 0;

        outerLoop:
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] == 0) {
                    blankTileI = i;
                    blankTileJ = j;
                    break outerLoop;
                }
            }
        }
        int[] directions = new int[]{1, 0, -1, 0, 1};
        for (int i = 0; i < 4; i++) {
            int[][] copyTiles = copyTiles();
            int incJ = directions[i];
            int incI = directions[i+1];

            int tileI = blankTileI + incI;
            int tileJ = blankTileJ + incJ;

            if (!move(copyTiles, tileI, tileJ, incI, incJ)) continue;
            Board neighbor = new Board(copyTiles);
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    private int[][] copyTiles() {
        int[][] copyTiles = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                copyTiles[i][j] = tiles[i][j];
            }
        }
        return copyTiles;
    }

    public Board twin() {
        int[][] twinTiles = copyTiles();

        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            twinTiles[0][0] = tiles[0][1];
            twinTiles[0][1] = tiles[0][0];
        } else {
            twinTiles[1][0] = tiles[1][1];
            twinTiles[1][1] = tiles[1][0];
        }

        return new Board(twinTiles);
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < dimension() && j >= 0 && j < dimension();
    }

    private boolean move(int[][] copyTiles, int i, int j, int incI, int incJ) {
        if (!isValid(i, j)) return false;
        int revI = -incI;
        int revJ = -incJ;
        copyTiles[i + revI][j + revJ] = copyTiles[i][j];
        copyTiles[i][j] = 0;
        return true;
    }
}