package IA.petroli;

public class PlataformaPetrolifera extends Plataforma implements Cloneable
{
	int petroli;
	
	public PlataformaPetrolifera(int x, int y, int petroli)
	{
		super(x,y);
	    if (petroli < 1) {
            petroli = 1;
        }
	    else if (petroli > 5) {
            petroli = 5;
        }
	    setPetroli(petroli);
	}
	 
	public int getPetroli() { return this.petroli; }
	public void setPetroli(int petroli) { this.petroli = petroli; }
	
	public boolean isPlataformaPetrolifera() { return true; }

	public void print() 
	{
		System.out.print("(" + getX() + "," + getY() + "). Plataforma Petrolifera (" + getPetroli() + " m3/s)." );	
	}
	
	public Object clone(){
        Object obj=null; 
        obj=super.clone();
        return obj;
    }

}
