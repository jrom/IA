package IA.Connectat;

//import IA.Connectat.*;
import javax.swing.*;
import java.awt.*;

public class TesterDibuix {
    static DibuixCiutat dibu;
    static int width,height;
    static int x,y,id,r,g,b,p1,p2;
    
    static ConnectatBoard ciutat;

    public TesterDibuix () {}	
 
    private static void crearGUI() throws Exception{
        //Crea la finestra
        JFrame finestra = new JFrame("Tester DibuixCiutat");
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //crea el panell en el que posarem el dibuix
        JPanel panell = new JPanel ();
        panell.setLayout (new GridLayout (1,0));
        panell.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Dibuix Graf"),BorderFactory.createEmptyBorder(5,5,5,5)));

        
        ciutat = new ConnectatBoard();
        ciutat.generarCiutat(20,20,50,18);
        
        
        //creem el dibuix i l'afegim al panell i a la finestra
        if (width!=0&&height!=0) {
            dibu = new DibuixCiutat(width,height,ciutat.getM(),ciutat.getN(),ciutat.getCentrals(),ciutat.getRepetidors(),ciutat.getConnexions());	
        } else {
            dibu = new DibuixCiutat();
        }
        panell.add (dibu);   
        finestra.setContentPane(panell);

        //Mostra la finestra
        finestra.pack();
        finestra.setVisible(true);
        
       // <editor-fold defaultstate="collapsed" desc=" Codi Prova ">
        /*
        try{
        dibu.dibuixarCentral(2,2);
        dibu.dibuixarRepetidor(5,5);
        dibu.dibuixarCentral(10,2);
        dibu.dibuixarRepetidor(5,15);
        dibu.dibuixarCentral(2,12);
        dibu.setColorPunts(new Color(200,200,200));
        dibu.dibuixarRepetidor(15,5);
        dibu.dibuixarCentral(7,8);
        dibu.dibuixarRepetidor(11,6);
        dibu.dibuixarCentral(17,18);
        dibu.dibuixarRepetidor(11,16);
        dibu.setColorPunt(3,Color.blue);
        dibu.dibuixarRepetidor(20,19);
        dibu.dibuixarConnexio(0,1);
        }catch (Exception e) { System.out.println("AAA"); }*/
        // </editor-fold>
    }	

public static void main(String[] args) throws Exception {
        try {
            width = 200;
            height = 200;
        } catch (Exception e) { }
        //Crea un thread que executa la interficie grafica
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    crearGUI();
                }catch(Exception e){
                    System.err.println(e.toString());
                }
            }
        });

       // <editor-fold defaultstate="collapsed" desc=" Codi Galceran Input ">
        /*
        try {
        
            op = es.llegeix();
            while (0<1) {
                switch (op) {
                    case 1:
                    subop = es.llegeix();
                    switch (subop) {
                        case 1:
                            es.escriu(dibu.getRadi());
                            break;
                        case 2:
                            es.escriu
                            (dibu.getColorPunt().toString());
                            break;
                        case 3: 
                            x = es.llegeix();
                            y = es.llegeix();
                            es.escriu
                            (dibu.getColorFletxa(x,y).toString());
                            break;
                        case 4:
                            es.escriu
                            (dibu.getColorSeleccionat().toString());
                            break;
                        case 5:
                            id = es.llegeix();
                            es.escriu(dibu.getPosicioX(id));
                            break;
                        case 6:
                            id = es.llegeix();
                            es.escriu(dibu.getPosicioY(id));
                            break;
                        case 7:
                            es.escriu(dibu.getSeleccionat());
                            break;
                        case 8:
                            es.escriu(dibu.getLimitEsquerre());
                            break;
                        case 9:
                            es.escriu(dibu.getLimitDret());
                            break;
                        case 10:
                            es.escriu(dibu.getLimitSuperior());
                            break;
                        case 11:
                            es.escriu(dibu.getLimitInferior());
                            break;
                        case 12:
                            x = es.llegeix();
                            y = es.llegeix();
                            es.escriu(dibu.existeixPunt(x,y));
                            break;
                        case 13:
                            x = es.llegeix();
                            y = es.llegeix();
                            es.escriu(dibu.existeixFletxa(x,y));
                            break;
                        default:
                            es.escriu("Opcio de consulta invalida");
                    }
                    break;
                    case 2: 
                    subop = es.llegeix();
                    switch (subop) {
                        case 1:
                            x = es.llegeix();
                            y = es.llegeix();
                            dibu.setSize (x,y);
                            break;
                        case 2:
                            r = es.llegeix();
                            dibu.setRadi (r);
                            break;
                        case 3:
                            r = es.llegeix();
                            g = es.llegeix();
                            b = es.llegeix();
                            dibu.setColorPunts (new Color(r,g,b));
                            break;
                        case 4:
                            x = es.llegeix();
                            y = es.llegeix();
                            r = es.llegeix();
                            g = es.llegeix();
                            b = es.llegeix();
                            dibu.setColorFletxa(x,y,new Color(r,g,b));
                            break;
                        case 5:
                            r = es.llegeix();
                            g = es.llegeix();
                            b = es.llegeix();
                            dibu.setColorSeleccionat
                                 (new Color(r,g,b));
                            break;
                        case 6:
                            id = es.llegeix();
                            dibu.setSeleccionat(id);
                            break;
                        case 7:
                            dibu.deseleccionar();
                            break;
                        case 8:
                            x = es.llegeix();
                            y = es.llegeix();
                            es.escriu(dibu.dibuixarPunt (x,y));
                            break;
                        case 9:
                            id = es.llegeix();
                            x = es.llegeix();
                            y = es.llegeix();
                            dibu.mourePunt(id,x,y);
                            break;
                        case 10:
                            id = es.llegeix(); 
                            dibu.suprimirPunt(id);
                            break;
                        case 11:
                            x = es.llegeix();
                            y = es.llegeix();
                            dibu.dibuixarFletxa (x,y);
                            break;
                        case 12:
                            x = es.llegeix();
                            y = es.llegeix();
                            dibu.suprimirFletxa(x,y);
                            break;
                        case 13:
                            x = es.llegeix();
                            y = es.llegeix();
                            r = es.llegeix();
                            b = es.llegeix();
                            dibu.enquadrar(x,y,r,b);
                            break;
                        case 14:
                            x = es.llegeix();
                            dibu.zoom (x);
                            break;
                        case 15:
                            x = es.llegeix();
                            y = es.llegeix();
                            dibu.moure(x,y);
                            break;
                        default:
                            es.escriu ("Opcio invalida");             
                    }
                    break;
                    default:
                    es.escriu ("Opcio invalida");
                }
                op = es.llegeix();
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }*/
        //</editor-fold>
    }
}