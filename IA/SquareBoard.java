package IA;
import java.util.List;
import java.util.Arrays;

public class SquareBoard
{
  // Globals and misc
  // Global N value
  public static int N = 20;
	public static int K;
	public static int P;
	
  // Data

	Ruta[] rutes;
	Parada[] parades;

  public SquareBoard(int k, int p)
  {
		int i;
		this.K = k;
		this.P = p;
		rutes = new Ruta[K];
		parades = new Parada[P];
		crearParades();
		for (i = 0; i < K; i++)
			rutes[i] = new Ruta();
			
		solIni2();
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
        if (paradaOcupada(i,j)) out +=" " + idParada(i,j) + " ";
				else out += " - ";
      }
      out +="\n";
    }
    out += "########################################\n";

		out += "Rutes:\n";
		for (i = 0; i < K; i++)
		{
			out += "Ruta "+i+": ";
			for(j = 0; j < rutes[i].numparades; j++)
			{
				out += rutes[i].parades[j]+" ";
			}
			out += "\n";
		}

    return out;
  }


	class Parada
	{
		public int id;
		public int x;
		public int y;

		public Parada(int x_, int y_, int id_)
		{
			id = id_;
			x = x_;
			y = y_;
		}
	}

	class Ruta
	{
		int[] parades;
		int numparades;
	// Suma de les distancies entre totes les parades seguint l'ordre de la ruta
		int dist;

		public Ruta()
		{
			numparades = 0;
			dist = 0;
			parades = new int[P];
		}

		public void afegirParada(int id_)
		{
			// TODO Comprovar que la parada no estigui ja a la ruta
			parades[numparades] = id_;
			numparades++;
		}
	}
}

