package IA;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.search.framework.Successor;

public class SquareBoard
{
  // Globals and misc
  // Global N value
  public static int N = 20;
	public static int K;
	public static int P;

  // Data

	public Ruta[] rutes;
	public Parada[] parades;

  public SquareBoard(int k, int p)
  {
		int i;
		this.K = k;
		this.P = p;
		rutes = new Ruta[K];
		parades = new Parada[P];
		crearParades();
		for (i = 0; i < K; i++)
			rutes[i] = new Ruta(i);

		solIni2();
  }

  // Copy operator
  public SquareBoard(SquareBoard original)
  {
    int i;

    N = original.N;
    K = original.K;
    P = original.P;
    parades = new Parada[P];
    rutes = new Ruta[K];

    System.arraycopy(original.parades, 0, parades, 0, P);
    System.arraycopy(original.rutes, 0, rutes, 0, K);
  }

	private void crearParades()
	{
		int x,y, i;
		for (i=0; i<P; i++)
			parades[i] = new Parada(-1,-1,-1);

		for (i=0; i<P; i++)
		{
			x = aleatori(N);
			y = aleatori(N);
			while (paradaOcupada(x,y))
			{
				x = aleatori(N);
				y = aleatori(N);
			}
			parades[i] = new Parada(x,y, i);
		}
	}

	public void solIni1()
	{

	}

	//dolent
	public void solIni2()
	{
		int pi, ki;
		ki = 0;
		for (pi = 0; pi < P; pi++)
		{
			rutes[ki].afegirParada(pi);
			ki = (ki + 1) % K;
		}

	}

	private int aleatori(int max)
	{
		return new Double(Math.random() * max).intValue();
	}

	private boolean paradaOcupada(int x, int y)
	{
		if ((x == 0 && y == 0) || (x == 19 && y == 19)) return true;
		for (int i = 0; i < P; i++)
		{
			if (parades[i].x == x && parades[i].y == y) return true;
		}


		return false;
	}

	private int idParada(int x, int y)
	{
		if (x == 0 && y == 0) return -10; // 0,0
		else if (x == 19 && y == 19) return -20; // 19,19
		for (int i = 0; i < P; i++)
		{
			if (parades[i].x == x && parades[i].y == y) return parades[i].id;
		}
		return -1; // No hi ha parada
	}

  public String toString()
  {
    String out = "";
    int i,j;

    out += "########################################\n";

    for(i = 0; i < N; i++)
    {
      for(j = 0; j < N; j++)
      {
        if (paradaOcupada(i,j)) out += " " + idParada(i,j) + " ";
           else out += " - ";
      }
      out +="\n";
    }
    out += "########################################\n";

		out += "Rutes:\n";
		for (i = 0; i < K; i++)
		{
			out += "Ruta " + i + ": ";
			for(j = 0; j < rutes[i].numparades; j++)
			{
				out += rutes[i].paradesRuta[j] + " ";
			}
			out += "\n";
		}

    return out;
  }

	public boolean canviarRuta(int paradaOrigen, int rutaDesti)
	{
		if (rutes[parades[paradaOrigen].ruta].treureParada(paradaOrigen))
		{
			rutes[rutaDesti].afegirParada(paradaOrigen);
			return true;
		}
		else return false;
	}

	//Mou sempre cap a la dreta
	public boolean moureParada(int parada)
	{
		return rutes[parades[parada].ruta].moureParada(parada);
	}

	public List<Successor> getSuccessors()
	{
	  int i,j;
	  List<Successor> list = new ArrayList<Successor>();
	  SquareBoard newboard;

	  // Fem tots els moviments de parada possibles:
	  for(i = 0; i < P; i++) // Per cada parada
	  {
	    for(j = 0; j < rutes[parades[i].ruta].numparades; j++) // per cada posicio de la ruta a la que pertany
	    {
	      newboard = new SquareBoard(this);
        newboard.moureParada(i);
        // System.out.println(newboard);
        list.add(new Successor("P " +i+ " move in route " + parades[i].ruta + " ", newboard));
	    }
	  }

	  // Fem tots els intercanvis de parada possibles:
	  for(i = 0; i < P; i++) // per cada parada
	  {
	    for(j = 0; j < K; j++) // per cada ruta
	    {
	      if (j != parades[i].ruta) // Nomes la movem a rutes diferents...
	      {
	        newboard = new SquareBoard(this);
	        newboard.canviarRuta(i, j);
          // System.out.println(newboard);
          list.add(new Successor("P " +i+ " move to route " + j + " ", newboard));
	      }
	    }
	  }
    return list;
	}

	public double getHeuristic1()
	{
	  double h;

	  h = Math.random() * 100;
	  return h;
	}

	public double getHeuristic2()
	{
	  double h;

	  h = Math.random() * 100;
	  return h;
	}


	class Parada
	{
		public int id;
		public int x;
		public int y;
		public int ruta;

		public Parada(int x_, int y_, int id_)
		{
			id = id_;
			x = x_;
			y = y_;
			ruta = -1;
		}

		// Operador de copia
		public Parada(Parada original)
		{
		  id = original.id;
		  x = original.x;
		  y = original.y;
		  ruta = original.ruta;
		}

		// Retorna la distanfia fisica entre dues parades, independent de les rutes
		public int distParadaFisica(Parada desti)
		{
      return Math.abs(x - desti.x) + Math.abs(y - desti.y);
		}

		// Retorna la distancia entre dues parades, fent transbord si cal
		public int distParada(Parada desti)
		{
      if (ruta == desti.ruta)
      { // Estan a la mateixa ruta, calcular distancia directa
        // TODO
      }
		}
	}

	class Ruta
	{
		int id;
		int[] paradesRuta;
		int numparades;
	// Suma de les distancies entre totes les parades seguint l'ordre de la ruta
		int dist;

		public Ruta(int id_)
		{
			numparades = 0;
			dist = 0;
			paradesRuta = new int[P];
			this.id = id_;
		}

		// Operador de copia
		public Ruta(Ruta original)
		{
		  id = original.id;
		  numparades = original.numparades;
		  dist = original.dist;
      System.arraycopy(original.paradesRuta, 0, paradesRuta, 0, original.paradesRuta.length);
		}

		public void afegirParada(int id_)
		{
			// TODO Comprovar que la parada no estigui ja a la ruta
			paradesRuta[numparades] = id_;
			parades[id_].ruta = this.id;
			numparades++;
		}

		public boolean treureParada(int id_)
		{
			int i;
			if (numparades <= 1) return false;
			for (i = 0; i < numparades && paradesRuta[i] != id_; i++);
			for(; i < numparades-1; i++)
			{
				paradesRuta[i] = paradesRuta[i+1];
			}
			paradesRuta[numparades] = -1;
			numparades--;
			return true;
		}

		public boolean moureParada(int id_)
		{
			int i, aux;
			for (i = 0; i < numparades && paradesRuta[i] != id_; i++);
			aux = paradesRuta[i];
			paradesRuta[i] = paradesRuta[(i+1) % numparades];
			paradesRuta[(i+1) % numparades] = aux;
			return true;
		}
	}
}

