package IA.petroli;

public class PlataformaDistribuidora extends Plataforma implements Cloneable
{

	public PlataformaDistribuidora(int x)  { super(x, 0); }
	
	public boolean isPlataformaDistribuidora() { return true; }

	public void print()   
	{ 
		System.out.print("(" + getX() + "," + getY() + ") - Plataforma Distribuidora." );	
	}
	
	public Object clone(){
        Object obj=null;
     
            obj=super.clone();
        
        return obj;
    }
	

}
