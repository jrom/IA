/*
 * ProbTSPJFrame.java
 *
 * Created on 4 de agosto de 2005, 23:18
 */
package IA.TSP2;

import java.util.List;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.NumberFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author  javier
 */
public class ProbTSPJFrame extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -8877491224336005353L;
    private int nc;

    /** Creates new form ProbTSPJFrame */
    public ProbTSPJFrame() {


        numForm = DecimalFormat.getInstance();
        numForm.setParseIntegerOnly(true);
        numForm.setGroupingUsed(false);
        nfor = new NumberFormatter(numForm);
        nfor.setAllowsInvalid(false);
        formSeed = new DefaultFormatterFactory(nfor, nfor, nfor);


        numForm = DecimalFormat.getNumberInstance();
        numForm.setParseIntegerOnly(false);
        ((DecimalFormat) numForm).applyPattern("#####.#######");
        ((DecimalFormat) numForm).setDecimalSeparatorAlwaysShown(true);
        numForm.setGroupingUsed(false);
        nfor = new NumberFormatter(numForm);
        nfor.setAllowsInvalid(false);
        formLambda = new DefaultFormatterFactory(nfor, nfor, nfor);

        initComponents();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        HillClimbingL = new javax.swing.JLabel();
        AnnealingL = new javax.swing.JLabel();
        hillClimbTA = new IA.TSP2.TSPDrawPanel();
        Inicial = new IA.TSP2.TSPDrawPanel();
        jPanel1 = new javax.swing.JPanel();
        CiudadesS = new javax.swing.JSlider();
        ciudadesL = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        ejecutarB = new javax.swing.JButton();
        ejecutarPrB = new javax.swing.JButton();
        SemillaL = new javax.swing.JLabel();
        SemillaTF = new javax.swing.JFormattedTextField(new Integer(1));
        jPanel3 = new javax.swing.JPanel();
        NiterL = new javax.swing.JLabel();
        NiterS = new javax.swing.JSlider();
        ParKL = new javax.swing.JLabel();
        ParKS = new javax.swing.JSlider();
        LambdaFT = new javax.swing.JFormattedTextField();
        LambdaL = new javax.swing.JLabel();
        AnnealingPL = new javax.swing.JLabel();
        annealingTA = new IA.TSP2.TSPDrawPanel();
        tfHC = new javax.swing.JTextField();
        tfSA = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jcbAnimacion = new javax.swing.JCheckBox();
        tfIni = new javax.swing.JTextField();
        jMenuBar18 = new javax.swing.JMenuBar();
        jMenu18 = new javax.swing.JMenu();
        Salir17 = new javax.swing.JMenuItem();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Travelling Salesman Problem");

        HillClimbingL.setText("Hill Climbing");

        AnnealingL.setText("Simulated Annealing");

        CiudadesS.setMajorTickSpacing(5);
        CiudadesS.setMaximum(40);
        CiudadesS.setMinimum(10);
        CiudadesS.setMinorTickSpacing(5);
        CiudadesS.setPaintLabels(true);
        CiudadesS.setPaintTicks(true);
        CiudadesS.setSnapToTicks(true);
        CiudadesS.setToolTipText("Elige el número de ciudades");
        CiudadesS.setValue(10);

        ciudadesL.setText("Num Ciudades:");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(ciudadesL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 24, Short.MAX_VALUE)
                .add(CiudadesS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(ciudadesL, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(CiudadesS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        ejecutarB.setText("Ejecutar Prob Aleatorio");
        ejecutarB.setActionCommand(" Ejecutar");
        ejecutarB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ejecutarBMousePressed(evt);
            }
        });

        ejecutarPrB.setText("Ejecutar Prob Específico");
        ejecutarPrB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarPrBActionPerformed(evt);
            }
        });

        SemillaL.setText("Semilla:");

        SemillaTF.setFormatterFactory(formSeed);
        SemillaTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(2, 2, 2)
                .add(ejecutarB)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ejecutarPrB)
                .add(18, 18, 18)
                .add(SemillaL)
                .add(18, 18, 18)
                .add(SemillaTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ejecutarPrB)
                    .add(ejecutarB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(SemillaL)
                    .add(SemillaTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NiterL.setText("Num It:");

        NiterS.setMajorTickSpacing(1000);
        NiterS.setMaximum(10000);
        NiterS.setMinimum(1000);
        NiterS.setMinorTickSpacing(1000);
        NiterS.setOrientation(javax.swing.JSlider.VERTICAL);
        NiterS.setPaintLabels(true);
        NiterS.setPaintTicks(true);
        NiterS.setSnapToTicks(true);
        NiterS.setToolTipText("Numero de iteraciones total");

        ParKL.setText("K");

        ParKS.setMajorTickSpacing(100);
        ParKS.setMaximum(2000);
        ParKS.setMinimum(100);
        ParKS.setMinorTickSpacing(100);
        ParKS.setOrientation(javax.swing.JSlider.VERTICAL);
        ParKS.setPaintLabels(true);
        ParKS.setPaintTicks(true);
        ParKS.setSnapToTicks(true);
        ParKS.setToolTipText("Lambda");

        LambdaFT.setFormatterFactory(formLambda);
        LambdaFT.setToolTipText("Parametro Lambda");
        LambdaFT.setValue(new Double(0.01));

        LambdaL.setText("Lambda");

        AnnealingPL.setText("Parametros Annealing");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(NiterL)
                            .add(NiterS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(ParKL)
                                .add(21, 21, 21))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(ParKS, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)))
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(LambdaFT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(LambdaL)))
                    .add(AnnealingPL))
                .add(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(AnnealingPL)
                .add(25, 25, 25)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(NiterL)
                    .add(LambdaL)
                    .add(ParKL))
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(15, 15, 15)
                        .add(NiterS, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 100, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(ParKS, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                            .add(LambdaFT, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(124, 124, 124))
        );

        jLabel1.setText("Estado Inicial");

        jcbAnimacion.setText("Animación");

        jMenu18.setText("Menu");

        Salir17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/door_out.png"))); // NOI18N
        Salir17.setText("Salir");
        Salir17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jMenu18.add(Salir17);

        jMenuBar18.add(jMenu18);

        setJMenuBar(jMenuBar18);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(159, 159, 159)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(Inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 234, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(layout.createSequentialGroup()
                                        .add(39, 39, 39)
                                        .add(tfIni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 153, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(26, 26, 26)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(tfHC)
                                    .add(hillClimbTA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(tfSA)
                                    .add(annealingTA, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(192, 192, 192)
                        .add(jcbAnimacion)
                        .add(94, 94, 94)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(202, 202, 202))
                    .add(layout.createSequentialGroup()
                        .add(226, 226, 226)
                        .add(jLabel1)
                        .add(183, 183, 183)
                        .add(HillClimbingL)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 141, Short.MAX_VALUE)
                        .add(AnnealingL)
                        .add(74, 74, 74)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(39, 39, 39)
                                .add(jcbAnimacion)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(HillClimbingL)
                            .add(jLabel1)
                            .add(AnnealingL))
                        .add(14, 14, 14)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(hillClimbTA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(Inicial, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(annealingTA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 79, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(tfSA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(tfHC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(tfIni, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void ejecutarPrBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarPrBActionPerformed
// TODO add your handling code here:
        try {


            int sm = numForm.parse(SemillaTF.getText()).intValue();
            nc = CiudadesS.getValue();
            ProbTSPBoard TSPB = new ProbTSPBoard(nc, sm);
            Inicial.setPlano(nc, TSPB.getCityPos(), TSPB.getPath());
            Inicial.repaint();
            tfIni.setText("Coste=" + TSPB.pathCost());
            tfHC.setText(" ");
            tfSA.setText(" ");
            TSPHillClimbingSearch(TSPB, hillClimbTA);
            TSPSimulatedAnnealingSearch(TSPB, annealingTA);
        } catch (ParseException e) {
        }
    }//GEN-LAST:event_ejecutarPrBActionPerformed

    private void ejecutarBMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ejecutarBMousePressed
// TODO add your handling code here:

        nc = CiudadesS.getValue();
        ProbTSPBoard TSPB = new ProbTSPBoard(nc);
        Inicial.setPlano(nc, TSPB.getCityPos(), TSPB.getPath());
        Inicial.repaint();
        tfIni.setText("Coste=" + TSPB.pathCost());
        tfHC.setText(" ");
        tfSA.setText(" ");
        TSPHillClimbingSearch(TSPB, hillClimbTA);
        TSPSimulatedAnnealingSearch(TSPB, annealingTA);
    }//GEN-LAST:event_ejecutarBMousePressed

    private void TSPHillClimbingSearch(ProbTSPBoard TSPB, TSPDrawPanel a) {
        //System.out.println("\nTSP HillClimbing  -->");
        try {
            Problem problem = new Problem(TSPB, new ProbTSPSuccessorFunction(), new ProbTSPGoalTest(), new ProbTSPHeuristicFunction());
            HillClimbingSearch search = new HillClimbingSearch();
            a.setPlano(TSPB.getNCities(), TSPB.getCityPos(), TSPB.getPath());

            SearchAgent agent = new SearchAgent(problem, search);

            //System.out.println();
            if (jcbAnimacion.isSelected()) {
                printActions(agent.getActions(), a);
            }
            ProbTSPBoard ge = (ProbTSPBoard) search.getLastSearchState();
            a.setPlano(ge.getNCities(), ge.getCityPos(), ge.getPath());
            tfHC.setText("Pasos= " + agent.getActions().size() + " Coste: " + ge.pathCost());

        // printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void TSPSimulatedAnnealingSearch(ProbTSPBoard TSPB, TSPDrawPanel a) {
        //System.out.println("\nTSP Simulated Annealing  -->");
        try {
            Problem problem = new Problem(TSPB, new ProbTSPSuccessorFunctionSA(), new ProbTSPGoalTest(), new ProbTSPHeuristicFunction());
            SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(NiterS.getValue(), 1000, ParKS.getValue(), numForm.parse(LambdaFT.getText()).doubleValue());
            //search.traceOn();
            a.setPlano(TSPB.getNCities(), TSPB.getCityPos(), TSPB.getPath());

            SearchAgent agent = new SearchAgent(problem, search);
            if (jcbAnimacion.isSelected()) {
                printActions(agent.getActions(), a);
            }
            ProbTSPBoard ge = (ProbTSPBoard) search.getLastSearchState();
            a.setPlano(ge.getNCities(), ge.getCityPos(), ge.getPath());
            tfSA.setText("Pasos= " + agent.getActions().size() + " Coste: " + ge.pathCost());


        //printInstrumentation(agent.getInstrumentation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("empty-statement")
    private void printActions(List actions, TSPDrawPanel a) {
        a.repaint();
        for (int i = 0; i < actions.size(); i++) {

            try {
                String action = (String) actions.get(i);

                int x = DecimalFormat.getInstance().parse(action.substring(0, action.lastIndexOf('|'))).intValue();
                int y = DecimalFormat.getInstance().parse(action.substring(action.lastIndexOf('|') + 1, action.length())).intValue();
                Thread.sleep(200);

                a.changeState(x, y);
            //a.repaint();


            } catch (InterruptedException ex) {
                Logger.getLogger(ProbTSPJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ProbTSPJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ProbTSPJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AnnealingL;
    private javax.swing.JLabel AnnealingPL;
    private javax.swing.JSlider CiudadesS;
    private javax.swing.JLabel HillClimbingL;
    private IA.TSP2.TSPDrawPanel Inicial;
    private javax.swing.JFormattedTextField LambdaFT;
    private javax.swing.JLabel LambdaL;
    private javax.swing.JLabel NiterL;
    private javax.swing.JSlider NiterS;
    private javax.swing.JLabel ParKL;
    private javax.swing.JSlider ParKS;
    private javax.swing.JMenuItem Salir17;
    private javax.swing.JLabel SemillaL;
    private javax.swing.JFormattedTextField SemillaTF;
    private IA.TSP2.TSPDrawPanel annealingTA;
    private java.awt.Label ciudadesL;
    private javax.swing.JButton ejecutarB;
    private javax.swing.JButton ejecutarPrB;
    private IA.TSP2.TSPDrawPanel hillClimbTA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu18;
    private javax.swing.JMenuBar jMenuBar18;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JCheckBox jcbAnimacion;
    private javax.swing.JTextField tfHC;
    private javax.swing.JTextField tfIni;
    private javax.swing.JTextField tfSA;
    // End of variables declaration//GEN-END:variables
    private NumberFormat numForm;
    private NumberFormatter nfor;
    private DefaultFormatterFactory formSeed;
    private DefaultFormatterFactory formLambda;
}
