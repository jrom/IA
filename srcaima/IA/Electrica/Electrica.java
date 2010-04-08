package IA.Electrica;

import java.util.Random;

import aima.search.framework.Successor;

public class Electrica {

	private Demanda[] d;

	private Paquet[] p;

	private Companyia[] co;

	private int id, ip, ico, valorHeuristic1, valorHeuristic2, valorHeuristic3,
			valorHeuristic4,valorHeuristic5, numPassos;

	/** Creates a new instance of Electrica */
	public Electrica() {
	}

	public Electrica(int nc, int ncompanyies) {
		d = new Demanda[nc];
		co = new Companyia[ncompanyies];
		p = new Paquet[ncompanyies * 10];
		id = 0;
		ico = 0;
		ip = 0;
		valorHeuristic1 = -1;
		valorHeuristic2 = -1;
		valorHeuristic3 = -1;
		valorHeuristic4 = -1;
		valorHeuristic5 = 0;
		numPassos = 0;
		
		// generacio aleatoria fer per cada pos del vector

		// num aleatori de paquets per companyia
		Random myRandom = new Random();
		for (int j = 0; j < ncompanyies; j++) {
			// una companyia

			// opcio 2 10 paquets + q cada paquet random + controlar min i si
			// max q=0 +
			// np=numpaquets q>200

			int contq = 0;
			int np = 0;
			Paquet[] vpaux = new Paquet[10];
			int q, f, pr, b;
			for (int i = 0; i < 10; i++) {

				// un paquet
				// cada paquet les mateixes limitacions que un consumidor?
				// idea 2 cada paq entre 200 i 5000 pero mirar que suma no
				// sobrepasi i min

				f = myRandom.nextInt(3) + 1;
				pr = myRandom.nextInt(3);
				b = myRandom.nextInt(20);
				switch (f) {
				case (1):
					pr = pr + 1;
					b = b + 30;
					break;
				case (2):
					pr = pr + 3;
					b = b + 20;
					break;
				case (3):
					pr = pr + 6;
					b = b + 5;
					break;
				default:
				}
				q = myRandom.nextInt(26) * 200;
				contq += q;
				// System.out.println(np);
				if (contq > 5000) {

					contq = contq - q;
					q = 5000 - contq;
					i = 10;
					if (q > 0) {
						contq += q;
						Paquet pp = new Paquet(q, f, pr, b, "", j, ip);
						
						p[ip] = pp;

						vpaux[np] = pp;
						np++;
						/*
						 * System.out.println("paquet " + ip + " Q=" +
						 * p[ip].consultaQini() + " F=" + p[ip].consultaF() + "
						 * P=" + p[ip].consultaP() + " B=" + p[ip].consultaB());
						 */
						ip++;
					}
				} else if (contq <= 5000 && i == 9 && contq < 1000) {
					q = 1000 - contq;
					contq += q;

					Paquet pp = new Paquet(q, f, pr, b, "", j, ip);
					p[ip] = pp;
					
					vpaux[np] = pp;
					np++;
					/*
					 * System.out.println("paquet " + ip + " Q=" +
					 * p[ip].consultaQini() + " F=" + p[ip].consultaF() + " P=" +
					 * p[ip].consultaP() + " B=" + p[ip].consultaB());
					 */
					ip++;
				} else if (contq <= 5000 && q > 0) {

					Paquet pp = new Paquet(q, f, pr, b, "", j, ip);
					p[ip] = pp;
					
					vpaux[np] = pp;
					np++;
					/*
					 * System.out.println("paquet " + ip + " Q=" +
					 * p[ip].consultaQini() + " F=" + p[ip].consultaF() + " P=" +
					 * p[ip].consultaP() + " B=" + p[ip].consultaB());
					 */
					ip++;
				}

			}
			co[j] = new Companyia(np, vpaux, j, "");
			ico++;

		}
		int q, f, pr;
		for (int i = 0; i < nc; i++) {

			// un consumidor
			q = myRandom.nextInt(300) + 200;
			f = myRandom.nextInt(3) + 1;
			pr = myRandom.nextInt(10) + 1;
			Consumidor c = new Consumidor(q, f, pr, "", i);
			id++;
			d[i] = new Demanda(c, null);// assigna un paquet null
			
			valorHeuristic2 += 100000;
			valorHeuristic3 += 100000;
			valorHeuristic4 += 100000;
			// sol ini ja asigna

			/*
			 * System.out.println("consumidor " + i + ": q=" + d[i].consultaQ() + "
			 * f=" + d[i].consultaF() + " p=" + d[i].consultaP() + " nump=" +
			 * d[i].consultaNumPaquet() + " q" + d[i].consultaQPaquet() + " f" +
			 * d[i].consultaFPaquet() + " P" + d[i].consultaPPaquet() + " b" +
			 * d[i].consultaBPaquet());
			 */
		}
		

	}

	/*
	 * //caldra actualitzar el NumConsumidorsPaquet[] public Electrica(Demanda[]
	 * nd, Paquet[] np) { // id,ip com el length dle vector id = nd.length; ip =
	 * np.length;
	 * 
	 * d = new Demanda[id];
	 * 
	 * p = new Paquet[ip]; for (int i = 0; i < id; i++) d[i] = nd[i]; for (int j =
	 * 0; j < ip; j++) p[j] = np[j]; // camp qeu depen del tipus dfe heuristica
	 * valorHeuristic = this.heuristica1(); }
	 */
	public Electrica(Electrica e) {
		// constructor de copia
		id = e.obteNdemandes();
		ip = e.obteNpaquets();
		ico = e.obteNcomp();
		valorHeuristic1 = e.obteValHeuristic(1);
		valorHeuristic2 = e.obteValHeuristic(2);
		valorHeuristic3 = e.obteValHeuristic(3);
		valorHeuristic4 = e.obteValHeuristic(4);
		valorHeuristic5 = e.obteValHeuristic(5);
		numPassos = e.NombreDePassos();
		d = new Demanda[id];
		p = new Paquet[ip];
		co = new Companyia[ico];
		for (int j = 0; j < ip; j++)
			// primer crear vector de paquets mab paquets NOUS i mateix index
			// que el que copiem
			p[j] = new Paquet(e.obteUnPaquet(j));
		for (int i = 0; i < id; i++) {
			// despres crear demanda legint el index del paquets que copiem pero
			// agafant els del vector nou
			// no tots tenen paquet pertatnt si -1 asignar null
			int numpaquet = e.obteUnaDemanda(i).consultaNumPaquet();
			if (numpaquet != -1)
				d[i] = new Demanda(e.obteUnaDemanda(i).consultaC(),
						p[numpaquet]);
			else
				d[i] = new Demanda(e.obteUnaDemanda(i).consultaC(), null);
		}
		for (int j = 0; j < ip; j++)
			// p[j] = e.obteUnPaquet(j);
			// potser no cal crear nou pquet perque els paquest no es modifiquen
			p[j] = new Paquet(e.obteUnPaquet(j));
		// for (int k = 0; k < ico; k++) {
		// co[k] = new Companyia (e.obteUnaComp(k));
		// si els paquest son els mateixos les companyies tambe!!!

		// }
		co = e.obteCompanyies();
		// num passos
		this.pas();

	}

	public int obteNdemandes() {
		return id;
	}

	public int obteNpaquets() {
		return ip;
	}

	public int obteNcomp() {
		return ico;
	}

	public Demanda obteUnaDemanda(int i) {
		return d[i];
	}

	public Paquet[] obtePaquets() {
		return p;
	}

	public Paquet obteUnPaquet(int i) {
		return p[i];
	}

	public Companyia obteUnaComp(int i) {
		return this.co[i];
	}

	public Companyia[] obteCompanyies() {
		return this.co;
	}

	public int obteValHeuristic(int i) {
		if (i == 1)
			return valorHeuristic1;
		else if (i == 2)
			return valorHeuristic2;
		else if (i == 3)
			return valorHeuristic3;
		else if (i == 4)
			return valorHeuristic4;
		else if (i == 5)
			return valorHeuristic5;
		else
			return -1;
	}

	public void afegirDemanda(Demanda dm) {
		d[id] = dm;
		id++;
	}

	public void afegirPaquet(Paquet pq) {
		p[ip] = pq;
		ip++;
	}

	public void modificaValHeuristic(int vh, int i) {
		if (vh == 1)
			valorHeuristic1 = i;
		else if (vh == 2)
			valorHeuristic2 = i;
		else if (vh == 3)
			valorHeuristic3 = i;
		else if (vh == 4)
			valorHeuristic4 = i;
		else if (vh == 5)
			valorHeuristic5 = i;
	}

	// s'hna de modificar per cada heuristica!!' acoblament pensar altre manera
	// funcio que guarda quina heuristica es vol...
	public void decrementaValHeuristic(int nump, int numd) {
		int h1 = this.obteValHeuristic(1);
		int h2 = this.obteValHeuristic(2);
		int h5=this.obteValHeuristic(5);
		
		int x = h1 + p[nump].consultaF() * d[numd].consultaQ();
		int y = h2 - p[nump].consultaP() * d[numd].consultaQ() + 100000;
		int z = x + y + p[nump].consultaB() * d[numd].consultaQ()+ 100000;

		this.modificaValHeuristic(1, x);
		this.modificaValHeuristic(2, y);
		this.modificaValHeuristic(3, x + y);
		this.modificaValHeuristic(4, z);
		//this.modificaValHeuristic(5, h5+d[numd].consultaQ());
	}

	public void incrementaValHeuristic(int nump, int numd) {
		int h1 = this.obteValHeuristic(1);
		int h2 = this.obteValHeuristic(2);
		int h5=this.obteValHeuristic(5);
		int x = h1 - p[nump].consultaF() * d[numd].consultaQ();
		int y = h2 + p[nump].consultaP() * d[numd].consultaQ()- 100000;
		int z = x + y - p[nump].consultaB() * d[numd].consultaQ()- 100000;

		this.modificaValHeuristic(1, x);
		this.modificaValHeuristic(2, y);
		this.modificaValHeuristic(3, x + y);
		this.modificaValHeuristic(4, z);
		//this.modificaValHeuristic(5, h5-d[numd].consultaQ());
	}

	// funcio heuristica
	public int heuristica1() {
		// heuristica de energia ecologica f max
		int f = 0;
		int q = 0;
		int sum = 0;
		for (int i = 0; i < this.id; i++) {
			f = d[i].consultaFPaquet();
			q = d[i].consultaQ();
			sum = sum + f * q;
		}
		return sum;
	}

	// funcions per fer successors

	public Electrica obteEstat() {
		// retorna el estat
		return this;
	}

	public void assignaPaquet(int nump, int numConsumidor) {
		d[numConsumidor].modificaPq(p[nump]);
		d[numConsumidor].incrementaQgastada();
		this.incrementaValHeuristic(nump, numConsumidor);
	}

	public void desassignarPaquet(int numConsumidor) {

		if (d[numConsumidor].consultaPq() != null) {
			d[numConsumidor].decrementaQgastada();
			this.decrementaValHeuristic(d[numConsumidor].consultaNumP(),
					numConsumidor);
		}
		d[numConsumidor].modificaPq(null);

	}

	public boolean restriccions(int nump, int numConsumidor) {
		// afegir tambe que el paqeut no estigui ja asigant , evitem repetir
		return ((d[numConsumidor].consultaNumPaquet() != nump)
				&& (d[numConsumidor].consultaF() <= p[nump].consultaF())
				&& (d[numConsumidor].consultaP() >= p[nump].consultaP()) && (d[numConsumidor]
				.consultaQ() <= (p[nump].consultaQini() - p[nump]
				.consultaQgastada())));
	}

	public String toString() {
		// retorna estat explicat en un string
		String s = "";
		int nump;
		for (int i = 0; i < this.id; i++) {
			nump = d[i].consultaNumPaquet();
			s = s.concat("c" + i + "p" + nump + "|");
		}
		s = s.concat("\n");
		return s;
	}

	// funcio per la solucio inicial

	public void solucioInicial3() {
		// voraç crea vector paquets ordenats de pitjor f a millor
		// vector de cons ordenats de mes q a menys
		// asigna per ordre
		// voraç la solucio mes allunyada de optim local
		this.solucioInicial1();
		// voraç crea vector paquets ordenats de pitjor f a millor
		Paquet[] vp = new Paquet[this.obteNpaquets()];
		for (int j = 0; j < this.obteNpaquets(); j++)
			vp[j] = this.obteUnPaquet(j);
		quicksortP(vp);
		/*
		 * System.out.println("ord paquets"); for (int k=0;k<ip;k++){
		 * System.out.println(" nump=" + vp[k].consultaPq() + " q"
		 * +vp[k].consultaQini() + " f" + vp[k].consultaF() + " P" +
		 * vp[k].consultaP() + " b" + vp[k].consultaB() ); }
		 */
		// vector de cons ordenats de mes q a menys
		Consumidor[] vc = new Consumidor[this.obteNdemandes()];
		for (int j = 0; j < this.obteNdemandes(); j++)
			vc[j] = this.obteUnaDemanda(j).consultaC();
		quicksortC(vc);
		/*
		 * System.out.println("ord consumidors"); for (int k=0;k<id;k++){
		 * System.out.println(" numc=" + vc[k].consultaNumConsumidor() + " q"
		 * +vc[k].consultaQ() + " f" + vc[k].consultaF() + " P" +
		 * vc[k].consultaP()); }
		 */
		// asigna per ordre
		for (int ii = 0; ii < this.id; ii++) {
			for (int jj = 0; jj < this.ip; jj++) {
				int i = vc[ii].consultaNumConsumidor();
				int j = vp[jj].consultaPq();
				if (this.restriccions(j, i)) {

					this.assignaPaquet(j, i);
					jj = this.ip;
				}
			}

		}
	}

	public void solucioInicial3p() {
		// voraç crea vector paquets ordenats de pitjor f a millor
		// vector de cons ordenats de mes q a menys
		// asigna per ordre
		// voraç la solucio mes allunyada de optim local
		this.solucioInicial1();
		// voraç crea vector paquets ordenats de pitjor f a millor
		Paquet[] vp = new Paquet[this.obteNpaquets()];
		for (int j = 0; j < this.obteNpaquets(); j++)
			vp[j] = this.obteUnPaquet(j);
		quicksortPp(vp);
		/*
		 * System.out.println("ord paquets"); for (int k=0;k<ip;k++){
		 * System.out.println(" nump=" + vp[k].consultaPq() + " q"
		 * +vp[k].consultaQini() + " f" + vp[k].consultaF() + " P" +
		 * vp[k].consultaP() + " b" + vp[k].consultaB() ); }
		 */
		// vector de cons ordenats de mes q a menys
		Consumidor[] vc = new Consumidor[this.obteNdemandes()];
		for (int j = 0; j < this.obteNdemandes(); j++)
			vc[j] = this.obteUnaDemanda(j).consultaC();
		quicksortC(vc);
		/*
		 * System.out.println("ord consumidors"); for (int k=0;k<id;k++){
		 * System.out.println(" numc=" + vc[k].consultaNumConsumidor() + " q"
		 * +vc[k].consultaQ() + " f" + vc[k].consultaF() + " P" +
		 * vc[k].consultaP()); }
		 */
		// asigna per ordre
		for (int ii = 0; ii < this.id; ii++) {
			for (int jj = 0; jj < this.ip; jj++) {
				int i = vc[ii].consultaNumConsumidor();
				int j = vp[jj].consultaPq();
				if (this.restriccions(j, i)) {

					this.assignaPaquet(j, i);
					jj = this.ip;
				}
			}

		}
	}

	public void solucioInicial4() {

		// voraç crea vector paquets ordenats de pitjor f a millor
		// vector de cons ordenats de mes q a menys
		// asigna per ordre pero al reves
		// voraç la solucio mes bona? de optim local
		this.solucioInicial1();
		// voraç crea vector paquets ordenats de pitjor f a millor
		Paquet[] vp = new Paquet[this.obteNpaquets()];
		for (int j = 0; j < this.obteNpaquets(); j++)
			vp[j] = this.obteUnPaquet(j);
		quicksortP(vp);

		// vector de cons ordenats de mes q a menys
		Consumidor[] vc = new Consumidor[this.obteNdemandes()];
		for (int j = 0; j < this.obteNdemandes(); j++)
			vc[j] = this.obteUnaDemanda(j).consultaC();
		quicksortC(vc);

		// asigna per ordre peor vp al reves
		for (int ii = 0; ii < this.id; ii++) {
			for (int jj = this.ip - 1; jj >= 0; jj--) {
				int i = vc[ii].consultaNumConsumidor();
				int j = vp[jj].consultaPq();
				if (this.restriccions(j, i)) {

					this.assignaPaquet(j, i);
					jj = -1;
				}
			}

		}
	}

	public void solucioInicial4p() {

		// voraç crea vector paquets ordenats de pitjor f a millor
		// vector de cons ordenats de mes q a menys
		// asigna per ordre pero al reves
		// voraç la solucio mes bona? de optim local
		this.solucioInicial1();
		// voraç crea vector paquets ordenats de pitjor f a millor
		Paquet[] vp = new Paquet[this.obteNpaquets()];
		for (int j = 0; j < this.obteNpaquets(); j++)
			vp[j] = this.obteUnPaquet(j);
		quicksortPp(vp);

		// vector de cons ordenats de mes q a menys
		Consumidor[] vc = new Consumidor[this.obteNdemandes()];
		for (int j = 0; j < this.obteNdemandes(); j++)
			vc[j] = this.obteUnaDemanda(j).consultaC();
		quicksortC(vc);

		// asigna per ordre peor vp al reves
		for (int ii = 0; ii < this.id; ii++) {
			for (int jj = this.ip - 1; jj >= 0; jj--) {
				int i = vc[ii].consultaNumConsumidor();
				int j = vp[jj].consultaPq();
				if (this.restriccions(j, i)) {

					this.assignaPaquet(j, i);
					jj = -1;
				}
			}

		}
	}

	public void quicksortC(Object[] v) {
		quicksortC2(v, 0, v.length - 1);
	}

	public void quicksortC2(Object[] v, int e, int d) {
		if (e < d) {
			int q = partitionCons(v, e, d);
			quicksortC2(v, e, q);
			quicksortC2(v, q + 1, d);
		}
	}

	public void quicksortP(Object[] v) {
		quicksortP2(v, 0, v.length - 1);
	}

	public void quicksortPp(Object[] v) {
		quicksortP2p(v, 0, v.length - 1);
	}

	public void quicksortP2(Object[] v, int e, int d) {
		if (e < d) {
			int q = partitionP(v, e, d);
			quicksortP2(v, e, q);
			quicksortP2(v, q + 1, d);
		}
	}

	public void quicksortP2p(Object[] v, int e, int d) {
		if (e < d) {
			int q = partitionPp(v, e, d);
			quicksortP2p(v, e, q);
			quicksortP2p(v, q + 1, d);
		}
	}

	public int partitionCons(Object[] v, int e, int d) {
		// ordena de major a menor q
		Consumidor x = (Consumidor) v[e];
		int i = e - 1;
		int j = d + 1;

		int n = v.length;
		for (;;) {
			j--;
			while (x.consultaQ() > ((Consumidor) v[j]).consultaQ()) {
				j--;
			}

			i++;
			while (((Consumidor) v[i]).consultaQ() > (x.consultaQ())) {
				i++;
			}
			if (i >= j)
				return j;

			Object aux = v[i];
			v[i] = v[j];
			v[j] = aux;
		}
	}

	public int partitionP(Object[] v, int e, int d) {
		// ordena de menor a major f
		Paquet x = (Paquet) v[e];
		int i = e - 1;
		int j = d + 1;

		int n = v.length;
		for (;;) {
			j--;
			while (x.consultaF() < ((Paquet) v[j]).consultaF()) {
				j--;
			}

			i++;
			while (((Paquet) v[i]).consultaF() < (x.consultaF())) {
				i++;
			}
			if (i >= j)
				return j;

			Object aux = v[i];
			v[i] = v[j];
			v[j] = aux;
		}
	}

	public int partitionPp(Object[] v, int e, int d) {
		// ordena de menor a major
		Paquet x = (Paquet) v[e];
		int i = e - 1;
		int j = d + 1;

		int n = v.length;
		for (;;) {
			j--;
			while (x.consultaP() < ((Paquet) v[j]).consultaP()) {
				j--;
			}

			i++;
			while (((Paquet) v[i]).consultaP() < (x.consultaP())) {
				i++;
			}
			if (i >= j)
				return j;

			Object aux = v[i];
			v[i] = v[j];
			v[j] = aux;
		}
	}

	public void solucioInicial5() {

		// reiniciem
		this.solucioInicial1();
		// asigna un paquet a cada consumidor tal com ho troba max(np*nc)
		// respectant resticcions
		for (int i = 0; i < this.id; i++) {
			for (int j = 0; j < this.ip; j++) {

				if (this.restriccions(j, i)) {

					this.assignaPaquet(j, i);
					j = this.ip;
				}
			}
			/*
			 * System.out.println("consumidor " + i + ": q=" + d[i].consultaQ() + "
			 * f=" + d[i].consultaF() + " p=" + d[i].consultaP() + " nump=" +
			 * d[i].consultaNumPaquet() + " q" + d[i].consultaQPaquet() + " f" +
			 * d[i].consultaFPaquet() + " P" + d[i].consultaPPaquet() + " b" +
			 * d[i].consultaBPaquet() + " numcomp=" +
			 * d[i].consultaNumCompanyia());
			 */
		}
	}

	public void solucioInicial2() {

		/*
		 * for (int j = 0; j < this.ip; ++j) { System.out.println("paquet " + j + "
		 * Q=" + p[j].consultaQini() + " F=" + p[j].consultaF() + " P=" +
		 * p[j].consultaP() + " B=" + p[j].consultaB()+"
		 * numcomp="+p[j].consultaNumCompanyia()); }
		 */

		this.solucioInicial1();
		// asigna un paquet a cada consumidor aleatoriament i respectant
		// restriccions
		// respectant resticcions
		Random myRandom = new Random();
		int nc = this.id;
		int np = this.ip;
		for (int i = 0; i < nc; i++) {

			int j = myRandom.nextInt(np);
			int contador = 0;
			while (!this.restriccions(j, i) && contador < 3 * np) {
				j = myRandom.nextInt(np);
				contador++;

			}

			if (this.restriccions(j, i)) {
				this.assignaPaquet(j, i);
			}
		}
	}

	public void solucioInicial1() {
		// solucio buida
		for (int i = 0; i < this.id; i++)
			d[i].modificaPq(null);
		for (int i = 0; i < this.ip; i++)
			p[i].modificaQ(0);// q gastada a 0

		valorHeuristic1 = 0;
		valorHeuristic2 = 0;
		valorHeuristic3 = 0;
		valorHeuristic4 = 0;

	}

	// sol ini assigni els pitjors paquets
	// sol ini ordre

	//

	// goal test
	public boolean isGoalState() {
		return (false);
	}

	// metodes per la interficie grafica
	public int[][] getPlano() {
		// cal acabar
		int matriu[][] = new int[id + 2][ip];
		// canvi rep [ip+1] i treure NumcomsumiforsPaquet
		int NumConsumidorsPaquet[] = new int[ip];
		for (int k = 0; k < ip; ++k)
			NumConsumidorsPaquet[k] = 0;
		int paquetsrecorreguts = 0;
		for (int i = 1; i < id + 2; i++) {
			for (int j = 0; j < ip; j++) {
				// canvi rep ip+1
				if (i == 1) {

					matriu[i - 1][j] = p[j].consultaNumCompanyia();
					matriu[i][j] = j;

				} else
					matriu[i][j] = -1;

			}

		}

		for (int i = 0; i < id; i++) {
			// anar a la pos del paquet i apilar al final(guardar ultim index)
			int numpaquet = d[i].consultaNumPaquet();
			if (numpaquet >= 0) {
				matriu[NumConsumidorsPaquet[numpaquet] + 2][numpaquet] = i;

				// canvi rep matriu[i+1][numpaquet+1]...
				NumConsumidorsPaquet[numpaquet] = NumConsumidorsPaquet[numpaquet] + 1;
			}
		}
		return matriu;
	}

public void EscriuEstat() {
		for (int j = 0; j < this.ip; ++j) { System.out.println("paquet " + j + "				  Q=" + p[j].consultaQini() + " F=" + p[j].consultaF() + " P=" +
				  p[j].consultaP() + " B=" + p[j].consultaB()+" numcomp="+p[j].consultaNumCompanyia()); }
		for (int i = 0; i < this.id; i++) {

			System.out.println("consumidor " + i + ": q=" + d[i].consultaQ()
					+ " f=" + d[i].consultaF() + " p=" + d[i].consultaP()
					+ " nump=" + d[i].consultaNumPaquet() + " q"
					+ d[i].consultaQPaquet() + " f" + d[i].consultaFPaquet()
					+ " P" + d[i].consultaPPaquet() + " b"
					+ d[i].consultaBPaquet() + " numcomp="
					+ d[i].consultaNumCompanyia());
		}

	}	public int NombreDePassos() {
		return numPassos;
	}

	public void pas() {
		numPassos++;
	}
	//com que no intervenen en els algorismes es poden fer poc eficients
	public double obteBenefici(){
		double b=0;
		double q=0;
		double p=0;
		double benefici=0;
		for (int i=0;i<id;i++){
			b=((double)d[i].consultaBPaquet())/100.0;
			p=((double)d[i].consultaPPaquet());
			if (p==-1.0) p=0.0;
			q=((double)d[i].consultaQ());
			benefici+=q*p*b;
		}
		return benefici;
	}
	public int obtePagament(){
		int q=0;
		int p=0;
		int pagament=0;
		for (int i=0;i<id;i++){
			
			p=d[i].consultaPPaquet();
			q=d[i].consultaQ();
			if (p==-1) p=0;
			pagament+=q*p;
		}
		return pagament;
	}
	public float obteFactorMig(){
		float f=0;
		float q=0;
		float fm=0;
		float qtot=0;
		for (int i=0;i<id;i++){
			
			f=d[i].consultaFPaquet();
			q=d[i].consultaQ();
			if (f==-1) f=0;
			fm+=q*f;
			qtot+=q;
		}
		return (fm/qtot);
	}
	public int obteQnoAssignada(){
		// de les companyies
		int qtot=0;
		int q=0;
		for (int i=0;i<ip;i++){
			
			qtot+=this.p[i].consultaQini();
			q+=this.p[i].consultaQgastada();
			
		}
		return (qtot-q);
	}
	public int obteQnoConsumida(){
		int q=0;
		for (int i=0;i<id;i++){
			if (this.obteUnaDemanda(i).consultaNumPaquet()==-1){
				q+=this.obteUnaDemanda(i).consultaQ();
			}
		}
			return q;
			
		
	}
}
