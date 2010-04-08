/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IA.Gasolineras;

import java.util.*;
/**
 *
 * @author jesus
 */
public  class estado {

    consulta c;
    private int sumaTodosKilometros;
    private int[] gasolineras; //Tiene esl identificador de la Gasolinera
    private int[] viajes; //Numero de viajes de cada camion
    private int[] kilometros; //Numero de kilometros de cada camion
    estado(){}  
    estado(estado otro)
    {
        sumaTodosKilometros=otro.sumaTodosKilometros;
        c=otro.c;
        gasolineras = otro.gasolineras.clone();
        viajes = otro.viajes.clone();
        kilometros = otro.kilometros.clone();
    }
    estado( int numeroGasolineras, int numeroDepositos,consulta con ) {
        // rellenamos la variable de instancia con los datos
        // que se pasan al constructor
        c=con;
        viajes=new int[numeroDepositos];
        kilometros=new int[numeroDepositos];
        gasolineras = new int[numeroGasolineras];
        for (int x=0;x<numeroGasolineras;x++)
        {gasolineras[x]=-1;}
    }

    public void setTotalKm(int numero){sumaTodosKilometros=numero;}
    public int getTotalKm(){return sumaTodosKilometros;}

    public void setGasolinera(int idGasolinera,int idDeposito){gasolineras[idGasolinera]=idDeposito;}
    public int getGasolinera(int idGasolinera){return gasolineras[idGasolinera];}
    public void setNumViajes(int idCamion,int numViajes){viajes[idCamion]=numViajes;}
    public int getNumViajes(int idCamion){return viajes[idCamion];}
    public void setNumKilometros(int idCamion,int numKilometros){kilometros[idCamion]=numKilometros;}
    public int getNumKilometros(int idCamion){return kilometros[idCamion];}

    public void restarViaje(int idCamion){viajes[idCamion]=viajes[idCamion]-1;}
    public void sumarViaje(int idCamion){viajes[idCamion]=viajes[idCamion]+1;}
    public boolean hipoteticoViaje(int idCamion){if ((viajes[idCamion]-1)<0){return false;}else{return true;}}
    
    
    public void restarKilometros(int idCamion,int numero){kilometros[idCamion]=kilometros[idCamion]-(numero*2);}
    public void sumarKilometros(int idCamion,int numero){kilometros[idCamion]=kilometros[idCamion]+(numero*2);}
    public boolean hipoteticoKilometros(int idCamion,int numero){if ((kilometros[idCamion]-(numero*2))<0){return false;}else{return true;}}
    public boolean hipoteticoKilometros(int idCamion,int numeroPoner,int numeroQuitar)
    {if ((kilometros[idCamion]-(numeroQuitar*2)+(numeroPoner*2))<0){return false;}else{return true;}}
    
    public int countGasolineras(){return gasolineras.length;}
    public int countCamiones(){return viajes.length;}
    public void setConsulta(consulta tabla){c=tabla;}
    
    public boolean puedeBorrar( int gasolineraNueva)
    {
        
        int otroCamion=getGasolinera(gasolineraNueva);
        if (otroCamion==-1)
        {return false;}else{
        return true;}
    }
    public boolean puedeAfegir(int camionNuevo, int gasolineraNueva)
    {
            if ((hipoteticoViaje(camionNuevo)) && (hipoteticoKilometros(camionNuevo, c.getDistancia(camionNuevo, gasolineraNueva))))
            {return true;
            }else
            {return false;}
    }
    public boolean puedeCambiar(int gasolinera1,int gasolinera2)
    {
        int camion1=getGasolinera(gasolinera1);
        int camion2=getGasolinera(gasolinera2);
        if ((camion1!=-1) && (camion2!=-1) && (camion1!=camion2))
        {
            if (hipoteticoKilometros(camion1,c.getDistancia(camion1, gasolinera1),c.getDistancia(camion1, gasolinera2))) 
            {
                if (hipoteticoKilometros(camion2,c.getDistancia(camion2, gasolinera2),c.getDistancia(camion2, gasolinera1))) 
                {return true;
                }else
                {return false;}
            }else
            {return false;}
        }else
        {
            return false;
        }
    }
    public void Cambiar(int gasolinera1, int gasolinera2)
    {
        int camion1=getGasolinera(gasolinera1);
        int camion2=getGasolinera(gasolinera2);

        sumarKilometros(camion1,c.getDistancia(camion1, gasolinera1));
        restarKilometros(camion1,c.getDistancia(camion1, gasolinera2));    
        setGasolinera(gasolinera2,camion1);
        
        sumarKilometros(camion2,c.getDistancia(camion2, gasolinera2));
        restarKilometros(camion2,c.getDistancia(camion2, gasolinera1));    
        setGasolinera(gasolinera1,camion2);

    }
    public void Afegir(int camionNuevo, int gasolineraNueva)
    {
        int otroCamion=getGasolinera(gasolineraNueva);
        if (otroCamion==-1)
        {
            restarViaje(camionNuevo);
            restarKilometros(camionNuevo,c.getDistancia(camionNuevo, gasolineraNueva));
            setGasolinera(gasolineraNueva,camionNuevo);
        }else
        {
            sumarViaje(otroCamion);
            sumarKilometros(otroCamion,c.getDistancia(otroCamion, gasolineraNueva));    
            
            restarViaje(camionNuevo);
            restarKilometros(camionNuevo,c.getDistancia(camionNuevo, gasolineraNueva));
            setGasolinera(gasolineraNueva,camionNuevo);
        }
    }

    public void Borrar(int gasolineraNueva)
    {
            int otroCamion=getGasolinera(gasolineraNueva);
            sumarViaje(otroCamion);
            sumarKilometros(otroCamion,c.getDistancia(otroCamion, gasolineraNueva));    
            setGasolinera(gasolineraNueva,-1);
    }
     
}
