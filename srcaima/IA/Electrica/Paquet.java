package IA.Electrica;

public class Paquet {

	  private int q;
		private int f;
		private int p;
		private int b;
		private String companyia;
		private int numCompanyia;
		private int numPaquet;
		   private int qgastada;
		
	    /** Creates a new instance of Paquet */
	    public Paquet() {
	    }
	    
	    
// no hi ha qgastada perque un paqeut per consumidor nomes
		public Paquet(int q,int f,int p,int b,String companyia,int nComp,int numP){
			this.q=q;
			this.f=f;
			this.p=p;
			this.b=b;
			   qgastada=0;
			this.companyia=companyia;
			this.numCompanyia=nComp;
			numPaquet=numP;
		}
		public Paquet (Paquet paux){
			//paquet valid si es null no va be
			//la mateixa qgastada
			this.q=paux.consultaQini();
			this.f=paux.consultaF();
			this.p=paux.consultaP();
			this.b=paux.consultaB();
			   this.qgastada=paux.consultaQgastada();
			this.companyia=paux.consultaCompanyia();
			this.numCompanyia=paux.consultaNumCompanyia();
			numPaquet=paux.consultaPq();
		}
	        
		public int consultaQini (){
			return q;
		}
		public int consultaQgastada(){
        	//gastada
            return qgastada;
        }
		public int consultaF (){
			return f;
		}
		public int consultaP (){
			return p;
		}
		public int consultaB (){
			return b;
		}
		public String consultaCompanyia (){
			return companyia;
		}
		public int consultaNumCompanyia(){
			return this.numCompanyia;
		}
		public int consultaPq (){
			return numPaquet;
		}
		
		   public void modificaQ(int i){
	            //modifica la q gastada
	            qgastada=i;
	         
	    }
}
