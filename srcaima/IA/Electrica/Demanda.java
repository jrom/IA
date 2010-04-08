package IA.Electrica;

public class Demanda {

	   private Consumidor c;
		private Paquet pq;
		
		public Demanda(){
			c=new Consumidor();
			pq= new Paquet();
		}
	        public Demanda(Consumidor c, Paquet p){
	            this.c=c;
	            this.pq=p;
	        }
	        public Demanda(Demanda d2,int i){
	        	//en realitat consumidors i paquets no canvien pertant nomes nova la assignacio de la demanda aquesta funcio no fer servir!!
	        	this.c = new Consumidor(d2.consultaC(),i);
	        	//this.pq= new Paquet(d2.consultaPq());
	        	//creo nou paquet? o l'agafo nomes??
	        	this.pq=d2.consultaPq();
	        }
		
		//feta amb acoblament de la ostia! fer els dos amb i sense acoblament
		public Paquet consultaPq (){
			return pq;
		}
	
		public void modificaPq (Paquet pq){
			this.pq=pq;
		}
		public Consumidor consultaC (){
			return c;
		}
		public void modificaC (Consumidor c2){
			c=c2;
		}
		
		//ara com hauria de ser
		public int consultaQ (){
			return c.consultaQ();
		}
		public int consultaF (){
			return c.consultaF();
		}
		public int consultaP (){
			return c.consultaP();
		}
		
		public String consultaConsumidor (){
			return c.consultaConsumidor();
		}
		public int consultaNumConsumidor(){
			return c.consultaNumConsumidor();
		}
		

		
		

		

		public int consultaQPaquet (){
			if (pq!=null) return pq.consultaQini();
			else return -1;
		}
		public int consultaQGastada (){
			if (pq!=null) return pq.consultaQgastada();
			else return -1;
		}
		public int consultaFPaquet (){
			
			if (pq!=null) return pq.consultaF();
			else return -1;
		}
		public int consultaPPaquet (){
			if (pq!=null) return pq.consultaP();
			else return -1;
		}
		public int consultaNumPaquet(){
			if (pq!=null) return pq.consultaPq();
			else return -1;
		}
		public int consultaNumP (){
			if (pq!=null)return pq.consultaPq();
			else return -1;
		}
		public int consultaBPaquet(){
			if (pq!=null) return pq.consultaB();
			else return -1;
		}
		public int consultaNumCompanyia(){
			//retorna -1 si no te paquet asignat
			if (pq!=null) return pq.consultaNumCompanyia();
			else return -1;
		}
		public String consultaNomCompanyia(){
			//retorna -1 si no te paquet asignat
			if (pq!=null) return pq.consultaCompanyia();
			else return null;
		}
		
		public void incrementaQgastada(){
	        //modifica la q gastada , antiga q gastada mes la q del paquet actual
	        pq.modificaQ(pq.consultaQgastada()+c.consultaQ());
	    }
	     public void decrementaQgastada(){
	        //modifica la q gastada , antiga q gastada mes la q del paquet actual
	        pq.modificaQ(pq.consultaQgastada()-c.consultaQ());
	    }

	}
