package IA.Centrals;


public class Nodo {
		private int cordX;
		private int cordY;
		private boolean esCentral;
		
		public Nodo (int x, int y, boolean b) {
			this.cordX = x;
			this.cordY = y;
			this.esCentral = b;
			
		}
		
		public int getCordX() {
			return cordX;
		}
		
		public void setCordX(int cordX) {
			this.cordX = cordX;
		}
		
		public int getCordY() {
			return cordY;
		}
		
		public void setCordY(int cordY) {
			this.cordY = cordY;
		}
		public boolean getEsCentral() {
			return this.esCentral;
		}
	}