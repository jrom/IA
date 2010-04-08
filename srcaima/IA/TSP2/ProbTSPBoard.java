/*!
 * REpresentacion del estado del problema del viajante de comercio
 */
package IA.TSP2;

import java.util.Random;

public class ProbTSPBoard {

    /// String que describe el operador
    public static String INTERCAMBIO = "Intercambio";
    /// Nuemro de ciudades
    private int ncities;
    /// Orden entre las ciudades
    private int[] path;
    /// Distancias entre las ciudades
    private double[][] dist;
    /// Posiciones de las ciudades
    private int[][] pcities;

    /*!\brief Genera una instancia del problema del TSP
     *
     * Crea una nueva instancia del problema del viajante de comercion con nc ciudades
     * 
     * @param [in] nc Numero de ciudades
     */
    public ProbTSPBoard(int nc) {
        Random myRandom = new Random();
        double d;

        pcities = new int[nc][2];
        path = new int[nc];
        dist = new double[nc][nc];
        ncities = nc;

        for (int i = 0; i < nc; i++) {
            path[i] = i;
        }

        for (int i = 0; i < nc; i++) {
            pcities[i][0]=myRandom.nextInt(20);
            pcities[i][1]=myRandom.nextInt(20);
        }
        
        for (int i = 0; i < nc; i++) {
            for (int j = i; j < nc; j++) {
                if (i == j) {
                    dist[i][j] = 0.0;
                } else {
                    d = Math.abs(pcities[i][0]-pcities[j][0])+Math.abs(pcities[i][1]-pcities[j][1]);
                    dist[i][j] = d;
                    dist[j][i] = d;
                }
            }
        }

    }

    public ProbTSPBoard(int nc, int seed) {
        Random myRandom = new Random();
        double d;

        myRandom.setSeed(seed);
        path = new int[nc];
        dist = new double[nc][nc];
        pcities = new int[nc][2];
        ncities = nc;

        for (int i = 0; i < nc; i++) {
            path[i] = i;
        }

        for (int i = 0; i < nc; i++) {
            pcities[i][0]=myRandom.nextInt(20);
            pcities[i][1]=myRandom.nextInt(20);
            
        }
        
        for (int i = 0; i < nc; i++) {
            for (int j = i; j < nc; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    d = (double) Math.abs(pcities[i][0]-pcities[j][0])+Math.abs(pcities[i][1]-pcities[j][1]);
                    dist[i][j] = d;
                    dist[j][i] = d;
                }
            }
        }

    }

    /*!\brief Genera una instancia del TSP con un camino inicial y una matriz de distancias
     *
     * Genera una instancia del problema del viajante de comercio recibiendo el numero de ciudades
     * el camino inicial y la matriz de distancias
     *
     * @param[in] nc Numero de ciudades
     * @param[in] p Camino inicial
     * @param[in] pos matriz de posiciones de las ciudades
     *
     */
    public ProbTSPBoard(int nc, int[] p, int[][] pos) {
        double d;

        path = new int[nc];
        dist = new double[nc][nc];
        pcities = new int[nc][2];
        ncities = nc;
        
        for (int i = 0; i < nc; i++) {
            path[i] = p[i];
        }
        
        pcities=pos;
        
//        for (int i = 0; i < nc; i++) {
//            pcities[i][0]=pos[i][0];
//            pcities[i][1]=pos[i][1];
//        }
        
        for (int i = 0; i < nc; i++) {
            for (int j = i; j < nc; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    d = (double) Math.abs(pcities[i][0]-pcities[j][0])+Math.abs(pcities[i][1]-pcities[j][1]);
                    dist[i][j] = d;
                    dist[j][i] = d;
                }
            }
        }
    }

    /*!\brief Retorna el numero de ciudades de la instancia
     *
     */
    public int getNCities() {
        return (ncities);
    }

    /*!\brief Retorna el camino entre las ciudades
     *
     */
    public int[] getPath() {
        int [] npath= new int[path.length];
        for (int i=0;i<path.length;i++){
            npath[i]=path[i];
        }
        return (npath);
    }

    /*!\brief Retorna la matriz de distancias
     *
     */
    public double[][] getDists() {
        return (dist);
    }

    /**
     * retorna las posiciones de las ciudades
     * @return
     */
    public int[][] getCityPos() {
        return (pcities);
    }

    /*!\brief Retorna la distancia entre la ciudad i y la siguiente ciudad en el camino
     *
     */
    public double distCities(int i) {
        if (i < ncities - 1) {
            return (dist[path[i]][path[i + 1]]);
        } else {
            return (dist[path[i]][path[0]]);
        }
    }

    /*!\Brief Intercambia dos ciudades en el recorrido
     *
     * \pre los valores han de ser validos
     */
    public void swapCities(int i, int j) {
        int tmp;

        tmp = path[i];
        path[i] = path[j];
        path[j] = tmp;
    }

    /*!\brief Retorna un string indicando la diferencia entre los recorridos
     *
     */
    public String getDiff(ProbTSPBoard t) {
        int[] b;
        String s = "Intercambio ciudad ";
        boolean primera = true;

        b = t.getPath();

        for (int i = 0; i < ncities; i++) {
            if (b[i] != path[i]) {
                if (primera) {
                    primera = false;
                    s = s + path[i] + " con ";
                } else {
                    s = s + path[i];
                }
            }
        }

        return (s);
    }

    /*!\brief Retorna el coste del recorrido
     *
     */
    public double pathCost() {
        double sum = 0.0;
        for (int i = 0; i < ncities; i++) {
            sum = sum + distCities(i);
        }
        return sum;
    }

    /*!\brief Retorna el recorrido como un string
     *
     */
    @Override
    public String toString() {
        String retVal = "|";
        for (int i = 0; i < ncities; i++) {
            retVal = retVal + path[i] + "|";
        }
        return retVal;
    }
}
