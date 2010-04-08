package IA.PathFinderDemo;

import java.util.Random;

public class ProbPathFinderBoard {

    public static String NORTH = "N";
    public static String SOUTH = "S";
    public static String EAST = "E";
    public static String WEST = "W";
    private int nc;
    private int x,  y;
    private int prob;
    private int[][] plano;
    private Random r = new Random();
    private static Laberinto lab;

    private int lrandom(int l) {
        return (Math.abs(r.nextInt()) % l);
    }

    /** Creates a new instance of ProbIA1Board
     * @param n
     * @param p
     * @param l 
     */
    public ProbPathFinderBoard(int n, int p, Laberinto l) {

        lab = l;

        prob = p;
        nc = n;
        x = 0;
        y = 0;

        plano = new int[nc][nc];
        for (int i = 0; i < nc; i++) {
            for (int j = 0; j < nc; j++) {
                if (lrandom(prob) == 0) {
                    plano[i][j] = -1;
                }
            }
        }
        plano[0][0] = -2;
        plano[nc - 1][nc - 1] = -3;

        plano[1][0] = 0;
        plano[nc - 1][nc - 2] = 0;
        lab.setPlano(n, getPlano());
    }

    public ProbPathFinderBoard(int n, int pr, int px, int py, int[][] p, Laberinto l) {
        lab = l;

        nc = n;
        prob = pr;
        plano = new int[nc][nc];
        for (int i = 0; i < nc; i++) {
            for (int j = 0; j < nc; j++) {
                plano[i][j] = p[i][j];
            }
        }
        x = px;
        y = py;
       // lab.setPlano(n, getPlano());
    }

    public ProbPathFinderBoard(int n, String s, Laberinto l) {
        lab = l;
        if (s.compareTo("A") == 0) {
            int mid;
            nc = n;
            prob = 0;
            x = 0;
            y = 0;
            plano = new int[nc][nc];
            plano[0][0] = -2;
            plano[nc - 1][nc - 1] = -3;
            mid = (nc / 2);
            for (int i = 0; i < nc - 3; i++) {
                plano[i][mid - 1] = -1;
            }
            for (int i = nc - 3; i < nc; i++) {
                plano[i][mid + 1] = -1;
            }
        }
        if (s.compareTo("B") == 0) {
            int mid;
            nc = n;
            prob = 0;
            x = 0;
            y = 0;
            plano = new int[nc][nc];
            plano[0][0] = -2;
            plano[nc - 1][nc - 1] = -3;
            mid = (nc / 2);
            for (int i = 0; i < mid + 1; i++) {
                plano[mid - 1][i] = -1;
            }
            for (int i = mid - 1; i < nc; i++) {
                plano[mid + 1][i] = -1;
            }
        }
        if (s.compareTo("C") == 0) {
            int mid;
            nc = n;
            prob = 0;
            x = 0;
            y = 0;
            plano = new int[nc][nc];
            plano[0][0] = -2;
            plano[nc - 1][nc - 1] = -3;
            mid = (nc / 2);
            for (int i = 0; i < nc; i++) {
                if (i != mid) {
                    plano[mid][i] = -1;
                }
            }

        }
        if (s.compareTo("D") == 0) {
            nc = n;
            prob = 0;
            x = 0;
            y = 0;
            plano = new int[nc][nc];
            plano[0][0] = -2;
            plano[nc - 1][nc - 1] = -3;
            for (int i = 2; i < nc - 1; i++) {
                plano[nc - i - 1][i - 2] = -1;
            }
            for (int i = 1; i < nc - 2; i++) {
                plano[nc - i - 1][i + 2] = -1;
            }
        }
        lab.setPlano(n, getPlano());

    }

    public int[][] getPlano() {
        return (plano);
    }

    public int getX() {
        return (x);
    }

    public int getY() {
        return (y);
    }

    public int size() {
        return (nc);
    }

    public int dificultad() {
        return (prob);
    }

    public Laberinto getLaberinto() {
        return (lab);
    }

    boolean canMoveWest() {
        return ((x > 0) && (plano[x - 1][y] != -1));
    }

    void moveWest() {
        if (canMoveWest()) {
            x = x - 1;
            lab.testPlano(x, y);
        }
    }

    boolean canMoveEast() {
        return ((x < nc - 1) && (plano[x + 1][y] != -1));
    }

    void moveEast() {
        if (canMoveEast()) {
            x = x + 1;
             lab.testPlano(x, y);
       }
    }

    boolean canMoveSouth() {
        return ((y < nc - 1) && (plano[x][y + 1] != -1));
    }

    void moveSouth() {
        if (canMoveSouth()) {
            y = y + 1;
            lab.testPlano(x, y);
        }
    }

    boolean canMoveNorth() {
        return ((y > 0) && (plano[x][y - 1] != -1));
    }

    void moveNorth() {
        if (canMoveNorth()) {
            y = y - 1;
            lab.testPlano(x, y);
        }
    }

    public int euclidean() {
        double dist;
        dist = Math.sqrt(Math.pow(nc - x, 2) + Math.pow(nc - y, 2));
        return ((int) dist);
    }

    public int cityBlock() {
        return ((nc - x) + (nc - y));
    }

    public int blocking() {
        int bl = 0;
        if ((x < nc - 1) && (y < nc - 1)) {
            if (plano[x + 1][y] + plano[x][y + 1] == -2) {
                bl = 2;
            }
        }
        return (bl);
    }

    public void modifyPlano(int[][] p) {
        for (int i = 0; i < nc; i++) {
            for (int j = 0; j < nc; j++) {
                plano[i][j] = p[i][j];
            }
        }
    }

    public boolean isGoalState() {
        return ((x == nc - 1) && (y == nc - 1));
    }
    
}
