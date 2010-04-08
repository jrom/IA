package IA.Electrica;

public class Consumidor {
	  
	private int q;
	private int f;
	private int p;
	private String consumidor;
	//el numero de consumidro es el num de paquet pero si fem ordenar paquest no
	private int numC;

	public Consumidor(){
	
	}
	public Consumidor(int q,int f,int p,String consumidor,int i){
		this.q=q;
		this.f=f;
		this.p=p;
		this.consumidor=consumidor;
		this.numC=i;
	}
	public Consumidor(Consumidor caux,int i){
		this.q=caux.consultaQ();
		this.f=caux.consultaF();
		this.p=caux.consultaP();
		this.consumidor=caux.consultaConsumidor();
		this.numC=i;
	}
	public int consultaQ (){
		return q;
	}
	public int consultaF (){
		return f;
	}
	public int consultaP (){
		return p;
	}
	public String consultaConsumidor (){
		return consumidor;
	}
	public int consultaNumConsumidor (){
		return this.numC;
	}

}


