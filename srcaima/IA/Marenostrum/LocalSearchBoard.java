/***********************************************************************
 ***                                                                 ***
 ***   Práctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearchBoard.java                                         ***
 ***                                                                 ***
 ***********************************************************************/
 
package IA.Marenostrum;

import java.util.Vector;
import java.util.Random;

public class LocalSearchBoard
{
	/* Variables */
	private static final int HORAS = 168;
	
	private static Vector procesos;	
	private Vector procesosSiAsignados;
	private Vector procesosNoAsignados;
	
	private int[] horas;
	private int[] procesosHoraInicio;
	private int horasLibres;
	
	private Random myRandom;
	
	private int[] procs;
	
	
/***********************************************************************
 ***                                                                 ***
 ***   class Proceso                                                 ***
 ***                                                                 ***
 ***********************************************************************/

private class Proceso
{
	int horaMin;
	int horaMax;
	int TE;	
	
	public Proceso(int pHoraInicio, int pPre, int pPost, int pTE)
	{
		horaMin = pHoraInicio - pPre;
		horaMax = pHoraInicio + pPost;
		TE = pTE;
		
		if (horaMin < 0) horaMin = 0;
		if (!(horaMax < HORAS)) horaMax = HORAS - 1;
	}
	
	public int getTE() { return TE; }				
	public int getHoraMin() { return horaMin; }
	public int getHoraMax() { return horaMax; }
	public int getRango()   { return (horaMax - horaMin); }
}

/***********************************************************************
 ***                                                                 ***
 ***   LocalSearchBoard                                              ***
 ***                                                                 ***
 ***********************************************************************/

public LocalSearchBoard()
{	
	horas = new int[HORAS];
	procs = new int[5];
	
	procesos = new Vector();	
	procesosSiAsignados = new Vector();
	procesosNoAsignados = new Vector();
	
	myRandom = new Random();
	
}

public LocalSearchBoard(int[] pHoras, Vector pSiAsignados, Vector pNoAsignados, int[] pHoraInicio, int pHorasLibres, int[] pProcs)
{	
	horas = new int[HORAS];	
	procesosHoraInicio = new int[procesos.size()];		
	procs = new int[5];
	
	myRandom = new Random();
	
	procesosSiAsignados = (Vector)pSiAsignados.clone();	
	procesosNoAsignados = (Vector)pNoAsignados.clone();
	
	for (int i = 0; i < HORAS; i++) horas[i] = pHoras[i];
	for (int i = 0; i < procesos.size(); i++) procesosHoraInicio[i] = pHoraInicio[i];	
	for (int i = 0; i < 5; i++) procs[i] = pProcs[i];
	
	horasLibres = pHorasLibres;
}

/***********************************************************************
 ***                                                                 ***
 ***   getHoras                                                      ***
 ***                                                                 ***
 ***********************************************************************/

public int[] getHoras()
{
	return horas;
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcs                                                      ***
 ***                                                                 ***
 ***********************************************************************/

public int[] getProcs()
{
	return procs;
}

/***********************************************************************
 ***                                                                 ***
 ***   getHorasInicio                                                ***
 ***                                                                 ***
 ***********************************************************************/

public int[] getHorasInicio()
{
	return procesosHoraInicio;
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcesosSiAsignados                                        ***
 ***                                                                 ***
 ***********************************************************************/

public Vector getProcesosSiAsignados()
{
	return procesosSiAsignados;
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcesosNoAsignados                                        ***
 ***                                                                 ***
 ***********************************************************************/

public Vector getProcesosNoAsignados()
{
	return procesosNoAsignados;
}

/***********************************************************************
 ***                                                                 ***
 ***   getIdProcesoSiAsignado                                        ***
 ***                                                                 ***
 ***********************************************************************/

public int getIdProcesoSiAsignado(int i)
{
	return Integer.parseInt(procesosSiAsignados.get(i).toString());
}

/***********************************************************************
 ***                                                                 ***
 ***   getIdProcesoNoAsignado                                        ***
 ***                                                                 ***
 ***********************************************************************/

public int getIdProcesoNoAsignado(int i)
{
	return Integer.parseInt(procesosNoAsignados.get(i).toString());
}

/***********************************************************************
 ***                                                                 ***
 ***   getTEProceso                                                  ***
 ***                                                                 ***
 ***********************************************************************/

public int getTEProceso(int pIdProc)
{
	return ((Proceso)procesos.get(pIdProc)).getTE();
}

/***********************************************************************
 ***                                                                 ***
 ***   getHoraMinProceso                                             ***
 ***                                                                 ***
 ***********************************************************************/

public int getHoraMinProceso(int pIdProc)
{
	return ((Proceso)procesos.get(pIdProc)).getHoraMin();
}

/***********************************************************************
 ***                                                                 ***
 ***   getHoraMaxProceso                                             ***
 ***                                                                 ***
 ***********************************************************************/

public int getHoraMaxProceso(int pIdProc)
{
	return ((Proceso)procesos.get(pIdProc)).getHoraMax();
}

/***********************************************************************
 ***                                                                 ***
 ***   getHoraInicioProceso                                          ***
 ***                                                                 ***
 ***********************************************************************/

public int getHoraInicioProceso(int pIdProc)
{
	return procesosHoraInicio[pIdProc];
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcesosSiAsignadosCount                                   ***
 ***                                                                 ***
 ***********************************************************************/

public int getProcesosSiAsignadosCount()
{
	return procesosSiAsignados.size();
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcesosNoAsignadosCount                                     ***
 ***                                                                 ***
 ***********************************************************************/

public int getProcesosNoAsignadosCount()
{
	return procesosNoAsignados.size();
}

/***********************************************************************
 ***                                                                 ***
 ***   getHorasLibres                                                ***
 ***                                                                 ***
 ***********************************************************************/

public int getHorasLibres()
{
	return horasLibres;
}

/***********************************************************************
 ***                                                                 ***
 ***   getProcsCount                                                 ***
 ***                                                                 ***
 ***********************************************************************/

public int getProcsCount(int i)
{
	return procs[i - 1]; // 1 <= i <= 5
}

/***********************************************************************
 ***                                                                 ***
 ***   getIntervalosCount                                            ***
 ***                                                                 ***
 ***********************************************************************/

public int getIntervalosCount()
{
	int intervalos = 0;
		 
	for (int i = 0; i < HORAS; i++)
	{
		if (horas[i] == -1) 
		{
			intervalos++;
			while ((i < HORAS) && (horas[i] == -1)) { i++; }
		}
	}	 
	return intervalos;
}

/***********************************************************************
 ***                                                                 ***
 ***   getRangoCount                                                 ***
 ***                                                                 ***
 ***********************************************************************/

public int getRangoCount()
{
	int rango = 0;
	for (int i = 0; i < procesos.size(); i++)	rango = rango + ((Proceso)procesos.get(i)).getRango();
	
	return (1680 - rango);
}

/***********************************************************************
 ***                                                                 ***
 ***   generarProcesos                                               ***
 ***                                                                 ***
 ***********************************************************************/
 
public String generarProcesos(int pNProcesos)
{
	String log = "";
	int horaInicio;
	int horaPre;
	int horaPost;
	int TE;
			
	procesos.clear();	
	
	for (int i = 0; i < pNProcesos; i++) 
	{
		horaInicio = myRandom.nextInt(HORAS);
		horaPre = myRandom.nextInt(6);
		horaPost = myRandom.nextInt(6);
		TE = (myRandom.nextInt(5)) + 1;
		
		Proceso p = new Proceso(horaInicio, horaPre, horaPost, TE);
		procesos.add(p);		
		log = log + i + "   Inicio: " + horaInicio + "   [ -" + horaPre + ", " + horaPost + " ]     TE: " + TE + "\n";		
	}
	
	return log;
}

/***********************************************************************
 ***                                                                 ***
 ***   generarEIVacio                                                ***
 ***                                                                 ***
 ***********************************************************************/
 
public void generarEIVacio()
{	
	/* Inicializamos procsX */
	procs[0] = 0;
	procs[1] = 0;
	procs[2] = 0;
	procs[3] = 0;
	procs[4] = 0;
	
	/* Limpiamos horas asignadas de procesos */
	procesosHoraInicio = new int[procesos.size()];	
	for (int i = 0; i < procesos.size(); i++) procesosHoraInicio[i] = -1;
	
	/* Limpiamos horas */
	for (int i = 0; i < HORAS; i++) horas[i] = -1;	
	horasLibres = HORAS;
	
	/* Inicializamos Si/No Asignados */
	procesosSiAsignados.clear();
	procesosNoAsignados.clear();
	for (int i = 0; i < procesos.size(); i++)
	{
		procs[getTEProceso(i) - 1] = procs[getTEProceso(i) - 1] + 1;
		procesosNoAsignados.add(new Integer(i));			
	}
}

/***********************************************************************
 ***                                                                 ***
 ***   generarEIAleatorio                                            ***
 ***                                                                 ***
 ***********************************************************************/
 
public void generarEIAleatorio()
{	
	Proceso p;
	int horaInicio;		
	int TE;
	
	/* Generamos el estado vacio */
	generarEIVacio();
				
	/* Intentamos asignar cada proceso en UNA de sus horas inicio posibles */
	for (int i = 0; i < procesos.size(); i++)
	{
		p = (Proceso)procesos.get(i);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(i, horaInicio);		
	}		
}

/***********************************************************************
 ***                                                                 ***
 ***   generarEIRango                                                ***
 ***                                                                 ***
 ***********************************************************************/

public void generarEIRango()
{
	int i;
	int TE, horaInicio;
	Proceso p;
	
	Vector rango0 = new Vector();
	Vector rango1 = new Vector();
	Vector rango2 = new Vector();
	Vector rango3 = new Vector();
	Vector rango4 = new Vector();
	Vector rango5 = new Vector();
	Vector rango6 = new Vector();
	Vector rango7 = new Vector();
	Vector rango8 = new Vector();
	Vector rango9 = new Vector();
	Vector rango10 = new Vector();
	
	/* Generamos el estado vacio */
	generarEIVacio();
	
	for (i = 0; i < procesos.size(); i++)
	{
		switch (((Proceso)procesos.get(i)).getRango())
		{
			case 0: rango0.add(new Integer(i)); break;
			case 1: rango1.add(new Integer(i)); break;
			case 2: rango2.add(new Integer(i)); break;
			case 3: rango3.add(new Integer(i)); break;
			case 4: rango4.add(new Integer(i)); break;
			case 5: rango5.add(new Integer(i)); break;
			case 6: rango6.add(new Integer(i)); break;
			case 7: rango7.add(new Integer(i)); break;
			case 8: rango8.add(new Integer(i)); break;
			case 9: rango9.add(new Integer(i)); break;
			case 10: rango10.add(new Integer(i)); break;
		}			
	}
	
	for (i = 0; i < rango10.size(); i++)
	{
		int id = Integer.parseInt(rango10.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango9.size(); i++)
	{
		int id = Integer.parseInt(rango9.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango8.size(); i++)
	{
		int id = Integer.parseInt(rango8.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango7.size(); i++)
	{
		int id = Integer.parseInt(rango7.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango6.size(); i++)
	{
		int id = Integer.parseInt(rango6.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango5.size(); i++)
	{
		int id = Integer.parseInt(rango5.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango4.size(); i++)
	{
		int id = Integer.parseInt(rango4.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango3.size(); i++)
	{
		int id = Integer.parseInt(rango3.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango2.size(); i++)
	{
		int id = Integer.parseInt(rango2.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango1.size(); i++)
	{
		int id = Integer.parseInt(rango1.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < rango0.size(); i++)
	{
		int id = Integer.parseInt(rango0.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
}

/***********************************************************************
 ***                                                                 ***
 ***   generarEIDuracion                                             ***
 ***                                                                 ***
 ***********************************************************************/

public void generarEIDuracion()
{
	int i;
	int TE, horaInicio;
	Proceso p;
	
	Vector duracion1 = new Vector();	
	Vector duracion2 = new Vector();
	Vector duracion3 = new Vector();
	Vector duracion4 = new Vector();
	Vector duracion5 = new Vector();
	
	/* Generamos el estado vacio */
	generarEIVacio();
	
	for (i = 0; i < procesos.size(); i++)
	{
		switch (((Proceso)procesos.get(i)).getTE())
		{			
			case 1: duracion1.add(new Integer(i)); break;
			case 2: duracion2.add(new Integer(i)); break;
			case 3: duracion3.add(new Integer(i)); break;
			case 4: duracion4.add(new Integer(i)); break;
			case 5: duracion5.add(new Integer(i)); break;			
		}			
	}
	
	for (i = 0; i < duracion1.size(); i++)
	{
		int id = Integer.parseInt(duracion1.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < duracion2.size(); i++)
	{
		int id = Integer.parseInt(duracion2.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < duracion3.size(); i++)
	{
		int id = Integer.parseInt(duracion3.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < duracion4.size(); i++)
	{
		int id = Integer.parseInt(duracion4.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}
	for (i = 0; i < duracion5.size(); i++)
	{
		int id = Integer.parseInt(duracion5.get(i).toString());
		p = (Proceso)procesos.get(id);
		TE = p.getTE();
		horaInicio = p.getHoraMin() + myRandom.nextInt(p.getHoraMax() - p.getHoraMin() + 1);
			
		if (comprobarAsignacion(horaInicio, TE)) asignarProceso(id, horaInicio);
	}	
}

/***********************************************************************
 ***                                                                 ***
 ***   comprobarAsignacion                                           ***
 ***                                                                 ***
 ***********************************************************************/
 
public boolean comprobarAsignacion(int pHoraInicio, int pTE)
{
	/* Comprobamos que no salga de la semana */
	if (pHoraInicio + pTE > HORAS) return false;
	
	/* Comprobamos el intervalo a ocupar */
	for (int i = pHoraInicio; i < pHoraInicio + pTE; i++)
	{
		if (horas[i] != -1) return false;
	}
	
	return true;
}

/***********************************************************************
 ***                                                                 ***
 ***   comprobarMovimiento                                           ***
 ***                                                                 ***
 ***********************************************************************/
 	
public boolean comprobarMovimiento(int pIdProc, int pHoraInicio, int pTE)
{
	/* Comprobamos que no salga de la hora maxima semanal */
	if (pHoraInicio + pTE > HORAS) return false;
		
	/* Comprobamos el intervalo a ocupar */
	for (int i = pHoraInicio; i < pHoraInicio + pTE; i++)
	{
		if ((horas[i] != -1) && (horas[i] != pIdProc)) return false;
	}
	return true;
}

/***********************************************************************
 ***                                                                 ***
 ***   comprobarForzar                                               ***
 ***                                                                 ***
 ***********************************************************************/

public boolean comprobarForzar(int pHoraInicio, int pTE)
{
	/* Comprobamos que no salga de la hora maxima semanal */
	if (pHoraInicio + pTE > HORAS) return false;		
		
	return true;
}

/***********************************************************************
 ***                                                                 ***
 ***   asignarProceso                                                ***
 ***                                                                 ***
 ***********************************************************************/
 
public void asignarProceso(int pIdProc, int pHoraInicio)
{
	int TE = getTEProceso(pIdProc);
	
	for (int i = pHoraInicio; i < pHoraInicio + TE; i++) horas[i] = pIdProc;	
	
	procesosHoraInicio[pIdProc] = pHoraInicio;
	
	procesosSiAsignados.add(new Integer(pIdProc));
	procesosNoAsignados.remove(new Integer(pIdProc));
	
	horasLibres = horasLibres - TE;
	
	procs[TE - 1] = procs[TE - 1] - 1;
}

/***********************************************************************
 ***                                                                 ***
 ***   quitarProceso                                                 ***
 ***                                                                 ***
 ***********************************************************************/
 
public void quitarProceso(int pIdProc)
{
	int TE = getTEProceso(pIdProc);
	
	for (int i = procesosHoraInicio[pIdProc]; i < procesosHoraInicio[pIdProc] + TE; i++) horas[i] = -1;
	
	procesosHoraInicio[pIdProc] = -1;
	
	procesosSiAsignados.remove(new Integer(pIdProc));
	procesosNoAsignados.add(new Integer(pIdProc));
	
	horasLibres = horasLibres + TE;
	
	procs[TE - 1] = procs[TE - 1] + 1;
}

/***********************************************************************
 ***                                                                 ***
 ***   moverProceso                                                  ***
 ***                                                                 ***
 ***********************************************************************/
 
public void moverProceso(int pIdProc, int pHoraInicio)
{
	quitarProceso(pIdProc);
	asignarProceso(pIdProc, pHoraInicio);
}

/***********************************************************************
 ***                                                                 ***
 ***   forzarProceso                                                 ***
 ***                                                                 ***
 ***********************************************************************/

public void forzarProceso(int pIdProc, int pHoraInicio)
{  	
	int horaFinal, j;  	
  Vector solapados = new Vector();
  	  	
  horaFinal = pHoraInicio + getTEProceso(pIdProc);
  	  	
  for (int i = pHoraInicio; i < horaFinal; i++)
  {
  	if (horas[i] != -1)
  	{
  		if (!solapados.contains(new Integer(horas[i]))) solapados.add(new Integer(horas[i]));  			
  	}  		
  }
  	
  while (!solapados.isEmpty())
  { 
  	quitarProceso(Integer.parseInt(solapados.get(0).toString()));
  	solapados.remove(0);
  }  	
  	
  asignarProceso(pIdProc, pHoraInicio);  	
}

/***********************************************************************
 ***                                                                 ***
 ***   apretarProceso                                                ***
 ***                                                                 ***
 ***********************************************************************/

public boolean apretarProceso(int pIdProc, int pHoraInicio)
{
		Proceso p = (Proceso)procesos.get(pIdProc);
		
		/* Si el proceso ya se puede asignar ya estamos */
		if (comprobarAsignacion(pHoraInicio, p.getTE()))
		{
			asignarProceso(pIdProc, pHoraInicio); 
			return true; 
		}
		
		/* Sino miramos hacia donde apretar */
		if (horas[pHoraInicio] == -1) return apretarDerecha(pIdProc, pHoraInicio);
		else
		{
			int idAux = horas[pHoraInicio];
			int horaIniAux = procesosHoraInicio[idAux];
			int horaFinAux = horaIniAux + ((Proceso)procesos.get(idAux)).getTE() - 1;
						
			if (((horaIniAux + horaFinAux) / (float)2) < (float)pHoraInicio) return apretarIzquierda(pIdProc, pHoraInicio);
			else if (((horaIniAux + horaFinAux) / (float)2) > (float)pHoraInicio) return apretarDerecha(pIdProc, pHoraInicio);			
			else
			{
				if (myRandom.nextInt(10) < 5) return apretarIzquierda(pIdProc, pHoraInicio);
				else return apretarDerecha(pIdProc, pHoraInicio);
			}
		}		
}

/***********************************************************************
 ***                                                                 ***
 ***   apretarIzquierda                                              ***
 ***                                                                 ***
 ***********************************************************************/
 
private boolean apretarIzquierda(int pIdProc, int pHoraInicio)
{
	
	int pos1, pos2;
	
	/* Buscamos limites */	
	pos1 = pHoraInicio;
	while (horas[pos1] == -1) pos1--;
	pos1 = procesosHoraInicio[horas[pos1]];
		
	pos2 = pos1;
	while ((pos2 > 0) && (horas[pos2] != -1)) pos2--;
	if ((pos2 == 0) && (horas[pos2] != -1)) return false;
		
	/* Empujamos hasta que quepa o no se pueda mas */
	while (horas[pHoraInicio] != -1)
	{		
		//System.out.println("pos1: " + pos1 + "   pos2: " + pos2);
		if ((pos2 == 0) && (horas[pos2] != -1)) return false;
		for (int i = pos2 + 1; i <= pos1; i++)
		{
			int procIdAux = horas[i];
			int horaIniAux = procesosHoraInicio[procIdAux];
			
			if (horaIniAux - 1 < ((Proceso)procesos.get(procIdAux)).getHoraMin()) return false;
			//System.out.println("Mover " + procIdAux + " a " + (horaIniAux - 1));
			moverProceso(procIdAux, horaIniAux - 1);
			i = horaIniAux + ((Proceso)procesos.get(procIdAux)).getTE() - 1;
		}		
		pos1--;		
		while ((pos2 > 0) && (horas[pos2] != -1)) pos2--;	
	}
		
	if (!comprobarAsignacion(pHoraInicio, ((Proceso)procesos.get(pIdProc)).getTE())) return apretarDerecha(pIdProc, pHoraInicio);
	else asignarProceso(pIdProc, pHoraInicio);
	
	return true;
}

/***********************************************************************
 ***                                                                 ***
 ***   apretarDerecha                                                ***
 ***                                                                 ***
 ***********************************************************************/
 
private boolean apretarDerecha(int pIdProc, int pHoraInicio)
{
	
	int pos1, pos2;
	
	/* Buscamos limites */
	int idAux = horas[pHoraInicio];
	
	if (idAux != -1) pos1 = procesosHoraInicio[idAux];
	else
	{
		pos1 = pHoraInicio;	
		while ((pos1 < 168) && (horas[pos1] == -1)) pos1++; 	
	}
	
	if (pos1 == 168) return false;
	
	pos2 = pos1;
	while ((pos2 < 167) && (horas[pos2] != -1)) pos2++;
	if ((pos2 == 167) && (horas[pos2] != -1)) return false;
		
	/* Empujamos hasta que quepa o no se pueda mas */
	while (!comprobarAsignacion(pHoraInicio, ((Proceso)procesos.get(pIdProc)).getTE()))
	{				
		if ((pos2 == 167) && (horas[pos2] != -1)) return false;
		for (int i = pos2 - 1; i >= pos1; i--)
		{
			int procIdAux = horas[i];
			int horaIniAux = procesosHoraInicio[procIdAux];
			
			if (horaIniAux + 1 > ((Proceso)procesos.get(procIdAux)).getHoraMax()) return false;
			moverProceso(procIdAux, horaIniAux + 1);
			i = horaIniAux;
		}		
		pos1++;		
		while ((pos2 < 167) && (horas[pos2] != -1)) pos2++;	
	}
	
	asignarProceso(pIdProc, pHoraInicio);
	
	return true;
}

/***********************************************************************
 ***                                                                 ***
 ***   toString                                                      ***
 ***                                                                 ***
 ***********************************************************************/

public String toString()
{
	return "";
}

}


 