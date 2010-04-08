package IA.Electrica;

public class Companyia {

	// max 10 paquets posar al generador aleatori
	private Paquet[] vp;

	private int ip;

	private int numComp;

	private String nomComp;

	public Companyia() {
		vp = new Paquet[10];
		ip = 0;
		numComp = -1;
		nomComp = "";
	}

	public Companyia(int ip2, Paquet[] vp2, int numComp, String nomComp) {
		ip = ip2;
		this.vp=new Paquet[ip];
		for (int i = 0; i < ip; ++i)
			vp[i] = vp2[i];
		this.numComp = numComp;
		this.nomComp = nomComp;
	}
	public Companyia(Companyia c2){
		this.ip=c2.consultaQuantsPaquets();
		this.nomComp=c2.consultaNomComp();
		this.numComp=c2.consultaNumComp();
		for (int i = 0; i < ip; ++i)
			vp[i] = c2.obtePaquetIndexat(i);
		//mantinc els paquets enlloc de crearne de nous , nomes nova la demanda
		
	}

	// modificadores
	public void modificaVectorPaquets(Paquet[] vp2, int ip2) {
		ip = ip2;
		for (int i = 0; i < ip; ++i)
			vp[i] = vp2[i];
	}

	public void modificaNumComp(int n) {
		numComp = n;

	}

	public void modificaNomComp(String s) {
		nomComp = s;

	}

	// consultores
	public int consultaQuantsPaquets() {
		return ip;
	}

	public int consultaNumComp() {
		return numComp;
	}

	public String consultaNomComp() {
		return nomComp;
	}

	public Paquet obtePaquet(int numPaquet) {
		// retorna el paquet o null si no hi es
		Paquet p = null;
		for (int j = 0; j < ip; j++) {
			if (vp[j].consultaPq() == numPaquet) {
				p = vp[j];
				j = ip;
			}
		}
		return p;
	}
	public Paquet obtePaquetIndexat (int i){
		Paquet p;
		if (i<this.ip && i>=0) p = vp[i];
		else p=null;
		return p;
		
	}

}
