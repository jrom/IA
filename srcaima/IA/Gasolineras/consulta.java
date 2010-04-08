/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IA.Gasolineras;

/**
 *
 * @author jesus
 */

public class consulta {
private int camiones;
private int gasolineras;
private int[][] distancia; //Gasolinera - Depositos
private int[] prioridad;    //Prioridad de las Gasolineras
consulta(){}
consulta( int numeroCamiones, int numeroGasolineras ) {
        // rellenamos la variable de instancia con los datos
        // que se pasan al constructor
        camiones=numeroCamiones;
        gasolineras=numeroGasolineras;
        prioridad  = new int[numeroGasolineras];    
        distancia  = new int[numeroCamiones][numeroGasolineras];    
        
        }

    public void setDistancia(int idCamion,int idGasolinera,int distanciaKm){distancia[idCamion][idGasolinera]=distanciaKm;}
    public int getDistancia(int idCamion,int idGasolinera){return distancia[idCamion][idGasolinera];}
    public void setPrioridad(int idGasolinera,int valor){prioridad[idGasolinera]=valor;}
    public int getPrioridad(int idGasolinera){return prioridad[idGasolinera];}
    public int countCamiones(){return camiones;}
    public int countGasolineras(){return gasolineras;}
}
