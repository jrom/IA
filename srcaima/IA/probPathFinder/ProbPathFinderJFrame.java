/*
 * probPathFinderJFrame.java
 *
 * Created on 5 de agosto de 2005, 12:07
 */

package IA.probPathFinder;

import java.awt.Point;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.AStarSearch;
import aima.search.informed.IterativeDeepeningAStarSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
/**
 *
 * @author  javier
 */
public class ProbPathFinderJFrame extends javax.swing.JFrame {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  private static int DIFICULTAD = 5;
  private ProbPathFinderBoard p;
  private int alg=1;
  private int heur=1;
  private int nc=8;
  private int dif=DIFICULTAD;
            
    /** Creates new form probPathFinderJFrame */
    public ProbPathFinderJFrame() {
        initComponents();
        
        p=new ProbPathFinderBoard(nc,dif);
        drawPanel1.setPlano(nc,p.getPlano());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        size = new javax.swing.JLabel();
        sizes = new javax.swing.JComboBox();
        sizes.addItem("5x5");
        sizes.addItem("6x6");
        sizes.addItem("7x7");
        sizes.addItem("8x8");
        sizes.addItem("9x9");
        sizes.addItem("10x10");
        sizes.addItem("11x11");
        sizes.addItem("12x12");
        sizes.setSelectedIndex(3);
        jLabel5 = new javax.swing.JLabel();
        dificultad = new javax.swing.JComboBox();
        dificultad.addItem("Facil");
        dificultad.addItem("Dificil");
        dificultad.addItem("Muy Dificil");
        dificultad.setSelectedIndex(0);
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        DF = new javax.swing.JRadioButton();
        ID = new javax.swing.JRadioButton();
        AStar = new javax.swing.JRadioButton();
        IDAStar = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        camino = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        euclidean = new javax.swing.JRadioButton();
        cityblock = new javax.swing.JRadioButton();
        mixed = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tiempo = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        reiniciar = new javax.swing.JButton();
        ejecutar = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        drawPanel1 = new IA.mySwing.DrawPanel();
        instrum = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Salir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        tipoA = new javax.swing.JMenuItem();
        tipoB = new javax.swing.JMenuItem();
        tipoC = new javax.swing.JMenuItem();
        tipoD = new javax.swing.JMenuItem();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PathFinder");
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        size.setText("Tama\u00f1o: ");
        jPanel3.add(size);

        sizes.setToolTipText("Tama\u00f1o del mapa");
        sizes.setPreferredSize(new java.awt.Dimension(70, 24));
        sizes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sizesActionPerformed(evt);
            }
        });

        jPanel3.add(sizes);

        jLabel5.setText("Dificultad: ");
        jPanel3.add(jLabel5);

        dificultad.setPreferredSize(new java.awt.Dimension(180, 24));
        dificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificultadActionPerformed(evt);
            }
        });

        jPanel3.add(dificultad);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel4.setText("Algoritmo: ");
        jPanel2.add(jLabel4, new java.awt.GridBagConstraints());

        buttonGroup1.add(DF);
        DF.setSelected(true);
        DF.setText("Deph First");
        DF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DFActionPerformed(evt);
            }
        });

        jPanel2.add(DF, new java.awt.GridBagConstraints());

        buttonGroup1.add(ID);
        ID.setText("ID");
        ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDActionPerformed(evt);
            }
        });

        jPanel2.add(ID, new java.awt.GridBagConstraints());

        buttonGroup1.add(AStar);
        AStar.setText("A*");
        AStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AStarActionPerformed(evt);
            }
        });

        jPanel2.add(AStar, new java.awt.GridBagConstraints());

        buttonGroup1.add(IDAStar);
        IDAStar.setText("IDA*");
        IDAStar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IDAStarActionPerformed(evt);
            }
        });

        jPanel2.add(IDAStar, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 6;
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Camino: ");
        jPanel4.add(jLabel2, new java.awt.GridBagConstraints());

        camino.setColumns(35);
        camino.setEditable(false);
        jPanel4.add(camino, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Heuristicas: ");
        jPanel5.add(jLabel3, new java.awt.GridBagConstraints());

        buttonGroup2.add(euclidean);
        euclidean.setSelected(true);
        euclidean.setText("Euclidean");
        euclidean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                euclideanActionPerformed(evt);
            }
        });

        jPanel5.add(euclidean, new java.awt.GridBagConstraints());

        buttonGroup2.add(cityblock);
        cityblock.setText("City Block");
        cityblock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityblockActionPerformed(evt);
            }
        });

        jPanel5.add(cityblock, new java.awt.GridBagConstraints());

        buttonGroup2.add(mixed);
        mixed.setText("City Block + bl");
        mixed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mixedActionPerformed(evt);
            }
        });

        jPanel5.add(mixed, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Tiempo: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(1, 27, 0, 0);
        jPanel6.add(jLabel1, gridBagConstraints);

        tiempo.setColumns(10);
        tiempo.setEditable(false);
        jPanel6.add(tiempo, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jPanel6, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMinimumSize(new java.awt.Dimension(100, 35));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 35));
        reiniciar.setText("Actualizar parametros");
        reiniciar.setToolTipText("Reiniciar el mapa");
        reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reiniciarMousePressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel1.add(reiniciar, gridBagConstraints);

        ejecutar.setText("Ejecutar");
        ejecutar.setToolTipText("Ejecutar el algoritmo seleccionado");
        ejecutar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ejecutarMousePressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel1.add(ejecutar, gridBagConstraints);

        limpiar.setToolTipText("Borrar el camino encontrado");
        limpiar.setText("Limpiar");
        limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                limpiarMousePressed(evt);
            }
        });

        jPanel1.add(limpiar, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(600, 400));
        drawPanel1.setPreferredSize(new java.awt.Dimension(400, 400));
        drawPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                drawPanel1MousePressed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 1);
        jPanel7.add(drawPanel1, gridBagConstraints);

        instrum.setColumns(10);
        instrum.setEditable(false);
        instrum.setLineWrap(true);
        instrum.setRows(10);
        instrum.setPreferredSize(new java.awt.Dimension(180, 150));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel7.add(instrum, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        getContentPane().add(jPanel7, gridBagConstraints);

        jMenu1.setText("Menu");
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Problemas");
        tipoA.setText("Tipo A");
        tipoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAActionPerformed(evt);
            }
        });

        jMenu2.add(tipoA);

        tipoB.setText("Tipo B");
        tipoB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoBActionPerformed(evt);
            }
        });

        jMenu2.add(tipoB);

        tipoC.setText("Tipo C");
        tipoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoCActionPerformed(evt);
            }
        });

        jMenu2.add(tipoC);

        tipoD.setText("Tipo D");
        tipoD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDActionPerformed(evt);
            }
        });

        jMenu2.add(tipoD);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void drawPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_drawPanel1MousePressed
// TODO add your handling code here:
        Point pnt=evt.getPoint();
        drawPanel1.changeState(pnt.getX(),pnt.getY());
        p.modifyPlano(drawPanel1.getPlano());
    }//GEN-LAST:event_drawPanel1MousePressed

    private void tipoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDActionPerformed
// TODO add your handling code here:
     p=new ProbPathFinderBoard(nc,new String("D"));
     drawPanel1.setPlano(nc,p.getPlano());    
    }//GEN-LAST:event_tipoDActionPerformed

    private void tipoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoCActionPerformed
// TODO add your handling code here:
     p=new ProbPathFinderBoard(nc,new String("C"));
     drawPanel1.setPlano(nc,p.getPlano());
    }//GEN-LAST:event_tipoCActionPerformed

    private void tipoBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoBActionPerformed
// TODO add your handling code here:
     p=new ProbPathFinderBoard(nc,new String("B"));
     drawPanel1.setPlano(nc,p.getPlano());
    }//GEN-LAST:event_tipoBActionPerformed

    private void tipoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAActionPerformed
// TODO add your handling code here:
        p=new ProbPathFinderBoard(nc,new String("A"));
        drawPanel1.setPlano(nc,p.getPlano());
    }//GEN-LAST:event_tipoAActionPerformed

    private void dificultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dificultadActionPerformed
// TODO add your handling code here:
        dif=DIFICULTAD-dificultad.getSelectedIndex();
    }//GEN-LAST:event_dificultadActionPerformed

    private void mixedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mixedActionPerformed
// TODO add your handling code here:
        heur=3;
    }//GEN-LAST:event_mixedActionPerformed

    private void euclideanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_euclideanActionPerformed
// TODO add your handling code here:
        heur=1;
    }//GEN-LAST:event_euclideanActionPerformed

    private void cityblockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityblockActionPerformed
// TODO add your handling code here:
        heur=2;
    }//GEN-LAST:event_cityblockActionPerformed

    private void IDAStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDAStarActionPerformed
// TODO add your handling code here:
         alg=4;
    }//GEN-LAST:event_IDAStarActionPerformed

    private void sizesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sizesActionPerformed
// TODO add your handling code here:
        nc=sizes.getSelectedIndex()+5;
        p=new ProbPathFinderBoard(nc,dif);
        drawPanel1.setPlano(nc,p.getPlano());
    }//GEN-LAST:event_sizesActionPerformed

    private void AStarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AStarActionPerformed
// TODO add your handling code here:
        alg=3;
    }//GEN-LAST:event_AStarActionPerformed

    private void IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IDActionPerformed
// TODO add your handling code here:
        alg=2;
    }//GEN-LAST:event_IDActionPerformed

    private void DFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DFActionPerformed
// TODO add your handling code here:
        alg=1;
    }//GEN-LAST:event_DFActionPerformed

    private void limpiarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiarMousePressed
// TODO add your handling code here:
         drawPanel1.clean();
         instrum.setText("");
    }//GEN-LAST:event_limpiarMousePressed

    private void reiniciarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reiniciarMousePressed
// TODO add your handling code here:
        p=new ProbPathFinderBoard(nc,dif);
        drawPanel1.setPlano(nc,p.getPlano());
    }//GEN-LAST:event_reiniciarMousePressed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
// TODO add your handling code here:
          dispose();   
    }//GEN-LAST:event_SalirActionPerformed

    private void ejecutarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ejecutarMousePressed
// TODO add your handling code here:
     Search search=null; //new DepthLimitedSearch(2*nc);;
     Problem problem =null;//  new Problem(p,new ProbPathFinderSuccessorFunction(), new ProbPathFinderGoalTest(),new ProbPathFinderHeuristicFunction());
      
     if (heur==1) problem =  new Problem(p,new ProbPathFinderSuccessorFunction(), new ProbPathFinderGoalTest(),new ProbPathFinderHeuristicFunction());
     if (heur==2) problem =  new Problem(p,new ProbPathFinderSuccessorFunction(), new ProbPathFinderGoalTest(),new ProbPathFinderHeuristicFunction2());
     if (heur==3) problem =  new Problem(p,new ProbPathFinderSuccessorFunction(), new ProbPathFinderGoalTest(),new ProbPathFinderHeuristicFunction3());
     
     if (alg==1){ 
         search =  new DepthLimitedSearch(2*nc);
     }
     if (alg==2) search = new IterativeDeepeningSearch();
     if (alg==3) search =  new AStarSearch(new GraphSearch());
     if (alg==4) search =  new IterativeDeepeningAStarSearch();

     //System.out.println(alg);
     
     //     Search search =  new BreadthFirstSearch(new TreeSearch());
     //Search search =  new HillClimbingSearch();
     try {
       Date d1,d2;
       Calendar a,b;
       
       d1=new Date();
       SearchAgent agent = new SearchAgent(problem,search);
       d2=new Date();

       a= Calendar.getInstance();
       b= Calendar.getInstance();
       a.setTime(d1);
       b.setTime(d2);

       long m=b.getTimeInMillis()-a.getTimeInMillis();

       tiempo.setText(m+" ms");
    //   System.err.println(m+" Milisegundos");

    
       List actions=agent.getActions();
     
       int x=0,y=0;
       camino.setText("");
       for (int i = 0; i < actions.size()-1; i++) {
            String action = (String) actions.get(i);
            //System.out.println(action);
            camino.setText(camino.getText()+action);
            if (action.compareTo("S")==0) y=y+1;
            if (action.compareTo("N")==0) y=y-1;
            if (action.compareTo("W")==0) x=x-1;
            if (action.compareTo("E")==0) x=x+1;         
            drawPanel1.modifyPlano(x,y);
       }
       String action = (String) actions.get(actions.size()-1);
      camino.setText(camino.getText()+action+" ("+ actions.size()+ "pasos)");
      printInstrumentation(agent.getInstrumentation());
    } catch (Exception e) {
            e.printStackTrace();
     }
     

    }//GEN-LAST:event_ejecutarMousePressed
    
    private void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        instrum.setText("");
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            instrum.append(key + " : " + property+"\n");
            //System.out.println(key + " : " + property);
        }
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProbPathFinderJFrame().setVisible(true);
            }
        });             
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton AStar;
    private javax.swing.JRadioButton DF;
    private javax.swing.JRadioButton ID;
    private javax.swing.JRadioButton IDAStar;
    private javax.swing.JMenuItem Salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField camino;
    private javax.swing.JRadioButton cityblock;
    private javax.swing.JComboBox dificultad;
    private IA.mySwing.DrawPanel drawPanel1;
    private javax.swing.JButton ejecutar;
    private javax.swing.JRadioButton euclidean;
    private javax.swing.JTextArea instrum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JButton limpiar;
    private javax.swing.JRadioButton mixed;
    private javax.swing.JButton reiniciar;
    private javax.swing.JLabel size;
    private javax.swing.JComboBox sizes;
    private javax.swing.JTextField tiempo;
    private javax.swing.JMenuItem tipoA;
    private javax.swing.JMenuItem tipoB;
    private javax.swing.JMenuItem tipoC;
    private javax.swing.JMenuItem tipoD;
    // End of variables declaration//GEN-END:variables
    
}