/*    Nom de la clase: ES
      Grup:9.1
      Autor: Marc Kirchner Villas
      Data:31/10/2004
      Descripció: Classe que facilitza la entrada/sortida de dades
	  Versió: 1.0
 */
package IA.Connectat;

import java.io.*;
public class ES {
    
    //Atributs
    private static BufferedReader entrada;
    private static PrintWriter sortida;

    public ES (){
       entrada = new BufferedReader(new InputStreamReader(System.in));
       sortida = new PrintWriter(System.out,true);    
    }
    
    public void escriu(int n) {
        sortida.println(n);
    }
    
    public void escriu(char c){
        sortida.println(c);
    }
    
    public void escriu(char[] s){
        sortida.println(s);
    }
       
    public void escriu(boolean b){
        sortida.println(b);
    }
    
    public void escriu(String s){
        sortida.println(s);
    }
    
    public void escriu(float f){
        sortida.println(f);
    }
    
    public void escriu(long l){
        sortida.println(l);
    }
    
    public void escriu(double d){
        sortida.println(d);
    }
    
    public int llegeix() throws Exception{
      String a=entrada.readLine();
      return Integer.valueOf(a).intValue();
    }
    
	public String llegeixString() throws Exception{
	
		return entrada.readLine();
	}
										 

}

