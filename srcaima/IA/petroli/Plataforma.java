package IA.petroli;

public abstract class Plataforma implements Cloneable
{
	private int x, y;
	
	

	public Plataforma(int x, int y)
	{
		if (x < 0) x = 0;
		if (y < 0) y = 0;
		
		setX(x);
		setY(y);
	}
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	
	public void setY(int y) { this.y = y; }
	public void setX(int x) { this.x = x; }
	
	
	public boolean isPlataformaPetrolifera() { return false; }
	public boolean isPlataformaDistribuidora() { return false; }
	
	public abstract void print();
	
	public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
	
}
