/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IA.Gasolineras;

import java.util.*;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;



/**
 *
 * @author jesus
 */
public class ControladorEstado {
    public estado e;
    public estado eFinal;
    ControladorEstado(){}
    public void crearEstadoInicial(consulta c,int tipoCreacion){
   
        
        int camiones=c.countCamiones();
        int gasolineras=c.countGasolineras();

        switch (tipoCreacion) {
        case 1:
            Random r;
            r=new Random();
            r.setSeed(new Date().getTime());

            for (int x = 0; x < camiones; x++ ) {
                boolean iterar=true;
                while (iterar)
                {
                    int i =r.nextInt(gasolineras);
                    if ((e.getGasolinera(i)==-1) && (e.hipoteticoViaje(x)) && (e.hipoteticoKilometros(x, c.getDistancia(x, i))))
                    {
                        e.setGasolinera(i, x); //meter el codigo de camion
                        e.restarViaje(x);
                        e.restarKilometros(x, c.getDistancia(x, i));
                    }
                    else
                    {
                        iterar=false;
                        if (e.getNumViajes(x)>0){
                            for (i=0;i<gasolineras;i++)
                            {
                                if ((e.getGasolinera(i)==-1) && (e.hipoteticoKilometros(x, c.getDistancia(x, i))))
                                {
                                    e.setGasolinera(i, x); //meter el codigo de camion
                                    e.restarViaje(x);
                                    e.restarKilometros(x, c.getDistancia(x, i));
                                    iterar=true;
                                    break;
                                }
                            }
                        }
                    }
                    if (e.getNumViajes(x)==0){iterar=false;}
                }
            }
            break;
        case 2:
        //Greedy

            int[][] lista = new int[gasolineras][2];
            
            for (int x = 0; x < camiones; x++ ) {
                for (int y = 0; y < gasolineras; y++ ) {
                    lista[y][0]=y;
                    lista[y][1]=c.getDistancia(x, y);
                }
                //algoritmo de ordenacion
                for(int i=0;i<gasolineras-1;i++){
                    for(int j=i+1;j<gasolineras;j++){
                        if (lista[j][1]<lista[i][1])
                        {
                            int cambio=lista[i][1];
                            lista[i][1]=lista[j][1];
                            lista[j][1]=cambio;

                            cambio=lista[i][0];
                            lista[i][0]=lista[j][0];
                            lista[j][0]=cambio;
                        }
                    }
                }
                
                int sig=0;
                boolean iterar=true;
                while (iterar)
                {
                    if ((e.getGasolinera(lista[sig][0])==-1) && (e.hipoteticoViaje(x)) && (e.hipoteticoKilometros(x, c.getDistancia(x, lista[sig][0]))))
                    {
                        e.setGasolinera(lista[sig][0], x); //meter el codigo de camion
                        e.restarViaje(x);
                        e.restarKilometros(x, c.getDistancia(x, lista[sig][0]));
                    }
                    sig++;
                    if ((e.getNumViajes(x)==0)||sig>=gasolineras){iterar=false;}
                }
            }
            break;
        case 3:
            int limite=gasolineras*camiones;
            int[][] listaC = new int[limite][3];
            
            for (int x = 0; x < camiones; x++ ) {
                for (int y = 0; y < gasolineras; y++ ) {
                    
                    listaC[x*gasolineras+y][0]=c.getDistancia(x, y);
                    listaC[x*gasolineras+y][1]=x; //camion
                    listaC[x*gasolineras+y][2]=y; //deposito
                }
            }
            
            //algoritmo de ordenacion
            for(int i=0;i<limite-1;i++){
                for(int j=i+1;j<limite;j++){
                    if (listaC[j][0]<listaC[i][0])
                    {
                        int cambio=listaC[i][0];
                        listaC[i][0]=listaC[j][0];
                        listaC[j][0]=cambio;

                        cambio=listaC[i][1];
                        listaC[i][1]=listaC[j][1];
                        listaC[j][1]=cambio;

                        cambio=listaC[i][2];
                        listaC[i][2]=listaC[j][2];
                        listaC[j][2]=cambio;
                    }
                }
            }

            for(int i=0;i<limite-1;i++){
                int x=listaC[i][1]; //camion
                int y=listaC[i][2]; //gasolinera
                int distancia=listaC[i][0];//distancia
                
                if ((e.getGasolinera(y)==-1) && (e.hipoteticoViaje(x)) && (e.hipoteticoKilometros(x, distancia)))
                {
                    e.setGasolinera(y, x); //meter el codigo de camion
                    e.restarViaje(x);
                    e.restarKilometros(x, distancia);
                }
            }

                    
            
            break;
        default:
        }
    }
    
        public String HillClimbingSearch(estado EstadoInicial,int Bondad) {
        try {
            String salida="";
            Problem problema;
            if (Bondad==1){
                problema = new Problem(EstadoInicial,new Operador(),new Final(),new HeuristicFunction_1());
            }else{
                problema = new Problem(EstadoInicial,new Operador(),new Final(),new HeuristicFunction_2());
            }
            HillClimbingSearch search =  new HillClimbingSearch();
            SearchAgent agent = new SearchAgent(problema,search);
            
            
            salida=salida +VerAction(agent.getActions());
            salida=salida +VerInstrumentation(agent.getInstrumentation());
            eFinal=(estado)search.getLastSearchState();
            
            return salida;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
        
        public String SimulatedAnnealing(estado EstadoInicial,int iteracion, int grados,int K, float B,int Bondad) {
        try {
            String salida="";
            Problem problema;
            if (Bondad==1){
                problema = new Problem(EstadoInicial,new Operador(),new Final(),new HeuristicFunction_1());
            }else{
                problema = new Problem(EstadoInicial,new Operador(),new Final(),new HeuristicFunction_2());
            }
            
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(iteracion,grados,K,B);
            SearchAgent agent = new SearchAgent(problema,search);
            
            
            salida=salida +VerAction(agent.getActions());
            salida=salida +VerInstrumentation(agent.getInstrumentation());
            eFinal=(estado)search.getLastSearchState();
            
            return salida;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

                
    private String VerInstrumentation(Properties properties) {
        String salida="";
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            salida=salida + key + " : " + property + "\n";
        }
        return salida;
    }
    private String VerAction(List actions) {
        String salida="";
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            salida=salida +action + "\n";
        }
        return salida;
    }
    
    
    
}
