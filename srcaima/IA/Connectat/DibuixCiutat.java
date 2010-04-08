package IA.Connectat;


import java.awt.*;
import java.util.Vector;

public class DibuixCiutat extends Dibuix {
  
    ConnectatBoard ciutat; // Instància activa de la ciutat
            
  /* Constructores de la superclasse
  */
  public DibuixCiutat(){
    super();
  }
  public DibuixCiutat(int n, int m){
    super(n*10,m*10);
    try {
          setColorQuadricula(new Color(200,200,200));
          /*int p1=dibuixarPunt(10,10);
          int p2=dibuixarPunt(120,70);
          int p3=dibuixarPunt(30,170);
          dibuixarFletxa(p1,p2);*/
          setDibuixarQuadricula(true);
          setTamanyQuadriculaX(10);
          setTamanyQuadriculaY(10);
          } catch (Exception e){
           System.err.println("Excepcio: "+e.toString());
        }
  }
  
  /* Constructora de la classe, donant el tamany del dibuix, i les dimensions
     de la quadricula (M i N). S'inicialitza amb els vectors de centrals i
     repetidors, i amb les connexions.
  */
  public DibuixCiutat(ConnectatBoard newciutat){
      super(/*ciutat.getN()*10,ciutat.getM()*10*/);
      ciutat = newciutat;
      init();
  }
  
  public DibuixCiutat(int width, int height, int n, int m, Vector centrals, 
                       Vector repetidors, boolean[][] connexions){
    // Potser caldria calcular el width i height necessaris pq no estigui deformat,
    // en principi hauria de ser una quadricula de 10
    super(/*n*10,m*10*/);
	//System.out.println("mides: "+m+" x "+n);
    // calcular el limit de la quadricula, per tal que només es mostrin MxN
    
    for (int i = 0; i<centrals.size(); i++) {
	    ConnectatBoard.node central=(ConnectatBoard.node)centrals.elementAt(i);
	    dibuixarCentral(central.getX(),central.getY());
	    //System.out.println("central: "+central.getX()+" "+central.getY());
    }
    for (int i = 0; i<repetidors.size(); i++) {
	    ConnectatBoard.node repetidor=(ConnectatBoard.node)repetidors.elementAt(i);
	    dibuixarRepetidor(repetidor.getX(),repetidor.getY());
	    //System.out.println("repetidor: "+repetidor.getX()+" "+repetidor.getY());
    }
    try {
          setColorQuadricula(new Color(200,200,200));
          /*int p1=dibuixarPunt(10,10);
          int p2=dibuixarPunt(120,70);
          int p3=dibuixarPunt(30,170);
          dibuixarFletxa(p1,p2);*/
          setDibuixarQuadricula(true);
          setTamanyQuadriculaX(10);
          setTamanyQuadriculaY(10);
          setRadi(4);
          enquadrar(-10,-10,n*10,m*10);
     } catch (Exception e){
           System.err.println("Excepcio: "+e.toString());
        }
  }
  
  public void novaCiutat(ConnectatBoard newciutat){
      //setSize (ciutat.getN()*10,ciutat.getM()*10);
      ciutat = newciutat;
      init();
  }
  
  
  
  public void dibuixarCentral(int x, int y){
    try{
        setColorPunts(Color.black);
        int id = dibuixarPunt(x*10,y*10);
        setEtiquetaPunt(id, ""+id);
      } catch (Exception e){
       System.out.println("Excepcio: "+e.toString());
    }
  }
  public void dibuixarRepetidor(int x, int y){
    try{
	setColorPunts(new Color(150,150,150));
        int id = dibuixarPunt(x*10,y*10);
        setEtiquetaPunt(id, ""+id);
      } catch (Exception e){
       System.out.println("Excepcio: "+e.toString());
    }
  }
  public void dibuixarConnexio(int a, int b){
    try{
      dibuixarFletxa(a,b);
      } catch (Exception e){
       System.out.println("Excepcio: "+e.toString());
    }
  }

  // (Re)Carrega els nodes de la ciutat
    private void init() {
        esborrarTot();
        setColorFletxes(Color.orange);
        Vector centrals=ciutat.getCentrals();
        Vector repetidors=ciutat.getRepetidors();
        
        for (int i = 0; i<centrals.size(); i++) {
	    ConnectatBoard.node central=(ConnectatBoard.node)centrals.elementAt(i);
	    dibuixarCentral(central.getX(),central.getY());
	    //System.out.println("central: "+central.getX()+" "+central.getY());
        }
        for (int i = 0; i<repetidors.size(); i++) {
                ConnectatBoard.node repetidor=(ConnectatBoard.node)repetidors.elementAt(i);
                dibuixarRepetidor(repetidor.getX(),repetidor.getY());
                //System.out.println("repetidor: "+repetidor.getX()+" "+repetidor.getY());
        }

        try {
          setColorQuadricula(new Color(200,200,200));
          /*int p1=dibuixarPunt(10,10);
          int p2=dibuixarPunt(120,70);
          int p3=dibuixarPunt(30,170);
          dibuixarFletxa(p1,p2);*/
          setDibuixarQuadricula(true);
          setTamanyQuadriculaX(10);
          setTamanyQuadriculaY(10);
          setRadi(4);
          enquadrar (-10,-10,ciutat.getN()*10,ciutat.getM()*10);
          } catch (Exception e){
           System.out.println("Excepcio: "+e.toString());
        }
    }
    
    /**
     * Actualitza (Redibuixa) les connexions de la ciutat actual
     */
    public void actualitzar() throws Exception{
        esborrarFletxes();
        boolean[][] connexions = ciutat.getConnexions();
        for (int i=0;i<connexions.length;i++){
            for (int j=0;j<connexions[i].length;j++){
                if (connexions[i][j]) dibuixarConnexio(i,j);
            }
        }
        
    }

}