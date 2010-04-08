/*
 *Pr�ctica de IA (B�squeda)
 * FIB - UPC
 * Curso 2006-2007
 * Cuatrimestre de Oto�o
 *
 * Daniel Garc�a P�rez
 * Sergio Vico Marfil
 *
 * DistribucionGrafico.java
 *
 */

package IA.Electricidad;

import java.util.Random;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import java.io.*;

import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.GreedyBestFirstSearch;
import javax.swing.UnsupportedLookAndFeelException;


public class DistribucionGrafico extends javax.swing.JFrame {
    
    private DistribucionHeuristic heuristico;
    private javax.swing.table.DefaultTableModel model1;
    private javax.swing.table.DefaultTableModel model2;
    private long tiempoEjecucion;
     private long tiempoGreedy = 0;
    private boolean ejecutando = false;
    
    /** Creates new form DistribucionGrafico */
    public DistribucionGrafico() {
        try {
           UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        crearModelTablas();
        initComponents();
    }
    
    private void ejecutar() {
        progress.setIndeterminate(true);
        //cogemos los valores e inicializamos
        inicializarDatos();
        
        Distribucion dist = new Distribucion();
              
        //lanzar greedy u otros
        if( !IniSin.isSelected()) {
            tiempoGreedy = System.currentTimeMillis();
            DistribucionGreedy greedy = new DistribucionGreedy();
                       
            if( geco.isSelected()){
                dist = greedy.inicializarEcologia();
            }else if( gahorro.isSelected()) {
                dist = greedy.inicializarAhorro();
            }else if( gecoahorro.isSelected()){
                dist = greedy.inicializarEcoAhorro();
            }else{
                dist = greedy.inicializarEcoAhorroBeneficio();
            }
            
            tiempoGreedy = System.currentTimeMillis() - tiempoGreedy;
        }
                
         Res.append("\n\nEstado inicial despues del Greedy\n"+dist.toString());
         
        //elegimos el Heuristico
        if(Heco.isSelected()) {       
            heuristico = new DistribucionHeuristicFunction1();
        }else if(Hahorro.isSelected()) {
            heuristico = new DistribucionHeuristicFunction2();
        }else if(Hecoahorro.isSelected()) {
            heuristico = new DistribucionHeuristicFunction3();
        }else{
            heuristico = new DistribucionHeuristicFunction4();
        }
        //elegimos el tipo de algoritmo
        if( hillc.isSelected()) {
            DistHillClimbingSearch(dist);
        }else{
           DistSimulatedAnnealingSearch(dist); 
        }
    
        ejecutando = false;
        progress.setIndeterminate(false);
    }
    
    private void inicializarDatos() {
        OfertaDemanda od = OfertaDemanda.getOfertaDemanda();
        od.resetearDatos();
        int maxC = model2.getRowCount();
        int maxP = model1.getRowCount();
        
        for (int i = 0; i < maxC; i++ ) {
            if(((String)model2.getValueAt(i,1)).length() == 0) { model2.setRowCount(i); break;}
            if(((Integer)model2.getValueAt(i,2)) == null){ model2.setRowCount(i); break;}
            if(((Integer)model2.getValueAt(i,3)) == null){ model2.setRowCount(i); break;}
            if(((Integer)model2.getValueAt(i,4)) == null){ model2.setRowCount(i); break;}
            od.addConsumidor(i,(String)model2.getValueAt(i,1),((Integer)model2.getValueAt(i,3)).intValue(),((Integer)model2.getValueAt(i,2)).intValue(),((Integer)model2.getValueAt(i,4)).intValue());
        }
        for (int j = 0; j < maxP; j++ ) {
            if(((String)model1.getValueAt(j,1)).length() == 0) { model1.setRowCount(j); break;}
            if(((Integer)model1.getValueAt(j,2)) == null){ model1.setRowCount(j); break;}
            if(((Integer)model1.getValueAt(j,3)) == null){ model1.setRowCount(j); break;}
            if(((Integer)model1.getValueAt(j,4)) == null){ model1.setRowCount(j); break;}
            od.addPaquete(j,(String)model1.getValueAt(j,1),((Integer)model1.getValueAt(j,3)).intValue(),((Integer)model1.getValueAt(j,2)).intValue(),((Integer)model1.getValueAt(j,4)).intValue(),((Integer)model1.getValueAt(j,5)).intValue());
        }
    }
    
    private void DistHillClimbingSearch(Distribucion dist) {
        Res.append("\n--HillClimbing--\n");
        
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),heuristico);
            HillClimbingSearch search =  new HillClimbingSearch();
            
            tiempoEjecucion = System.currentTimeMillis();
            
            SearchAgent agent = new SearchAgent(problem,search);
            
            tiempoEjecucion = System.currentTimeMillis() - tiempoEjecucion;
            
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            Res.append("\n\nEstado final\n"+((Distribucion)search.getLastSearchState()).toString());
            Res.append("\nTiempo del Greedy\n  "+tiempoGreedy+" ms");
            Res.append("\nTiempo de ejecucion\n  "+tiempoEjecucion+" ms");
            Res.append("\nTiempo Total\n  "+(tiempoEjecucion+tiempoGreedy)+" ms");
            calcularValorSol((Distribucion)search.getLastSearchState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void DistSimulatedAnnealingSearch(Distribucion dist) {
        Res.append("\n--Simulated Annealing--\n");
        Res.append("Iteraciones="+iter.getText()+" Pasos="+pasos.getText()+" K="+k.getText()+" Landa="+landa.getText()+"\n\n");
       
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),heuristico);
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(Integer.parseInt(iter.getText()),Integer.parseInt(pasos.getText()),Integer.parseInt(k.getText()),Double.parseDouble(landa.getText()));
            //search.traceOn();
            tiempoEjecucion = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem,search);
            tiempoEjecucion = System.currentTimeMillis() - tiempoEjecucion;
            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());
            Res.append("\n\nTiempo del Greedy\n  "+tiempoGreedy+" ms");
            Res.append("\nTiempo de ejecucion\n  "+tiempoEjecucion+" ms");
            Res.append("\nTiempo Total\n  "+ (tiempoEjecucion+tiempoGreedy) +" ms");
            calcularValorSol((Distribucion)search.getLastSearchState());
        } catch (Exception e) {
            Res.append("Se han introducido los valores erroneamente");
            e.printStackTrace();
        }
    }
    
    private void printInstrumentation(Properties properties) {
        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            Res.append(key + " : " + property);
        }
        
    }
    
    private void printActions(List actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = (String) actions.get(i);
            Res.append(action);
        }
    }
    
    private String printData() {
        OfertaDemanda od = OfertaDemanda.getOfertaDemanda();
        String res = "\nDatos de paquetes\n";
        for (int i = 0; i < od.getNumOferta(); i++) {
            res +="Id:"+i+"\tCompany:"+od.getPCompany(i)+"\tCo2:"+od.getPCo2(i)+"\tkWh:"+od.getPKWhTotal(i)+"\tPrecio:"+od.getPPrecio(i)+"\tBeneficio:"+od.getPBeneficio(i)+"\n";
        }
        res +="\nDatos de consumidores\n";
        for (int j = 0; j < od.getNumDemanda(); j++) {
            res +="Id:"+j+"\tNom:"+od.getCNom(j)+"\tCo2Min:"+od.getCCo2Min(j)+"\tkWh:"+od.getCKWh(j)+"\tPrecio:"+od.getCPrecioMax(j)+"\n";
        }
        return res;
    } 
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        hillc = new javax.swing.JRadioButton();
        simulateda = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        iter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pasos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        k = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        landa = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        Heco = new javax.swing.JRadioButton();
        Hahorro = new javax.swing.JRadioButton();
        Hecoahorro = new javax.swing.JRadioButton();
        Htodo = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        IniSin = new javax.swing.JRadioButton();
        geco = new javax.swing.JRadioButton();
        gahorro = new javax.swing.JRadioButton();
        gecoahorro = new javax.swing.JRadioButton();
        gtotal = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        RellenoRandom = new javax.swing.JButton();
        npaqs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ncons = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Res = new javax.swing.JTextArea();
        Ejecucion = new javax.swing.JButton();
        progress = new javax.swing.JProgressBar();
        Exportar = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bpaqs = new javax.swing.JTextField();
        bcons = new javax.swing.JTextField();
        bpruebs = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bsiter = new javax.swing.JTextField();
        bspasos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        bsk = new javax.swing.JTextField();
        bslanda = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        bRes = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        Bejecutar = new javax.swing.JButton();
        bprogress = new javax.swing.JProgressBar();
        bExportar = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        Salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Practica de IA");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("ventana");
        setResizable(false);
        jTabbedPane1.setName("tabbed");
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Algoritmo"));
        buttonGroup1.add(hillc);
        hillc.setSelected(true);
        hillc.setText("Hill Climbing");
        hillc.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        hillc.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup1.add(simulateda);
        simulateda.setText("Simulated Annealing");
        simulateda.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        simulateda.setMargin(new java.awt.Insets(0, 0, 0, 0));
        simulateda.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                simulatedaStateChanged(evt);
            }
        });

        iter.setColumns(8);
        iter.setEditable(false);
        iter.setText("2000");

        jLabel1.setText("N\u00ba Iteraciones");

        jLabel2.setText("N\u00ba Pasos");

        pasos.setColumns(8);
        pasos.setEditable(false);
        pasos.setText("100");

        jLabel3.setText("K");

        k.setColumns(8);
        k.setEditable(false);
        k.setText("15");

        jLabel4.setText("\u03bb");

        landa.setColumns(8);
        landa.setEditable(false);
        landa.setText("0.03");

        jButton1.setText("Reset");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 44, Short.MAX_VALUE)
                        .add(pasos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(iter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5Layout.createSequentialGroup()
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel3)
                            .add(jLabel4))
                        .add(33, 33, 33)
                        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, landa)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, k)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(iter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(pasos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(k, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(landa, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(simulateda)
                            .add(hillc)))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(35, 35, 35)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(hillc)
                .add(14, 14, 14)
                .add(simulateda)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Heur\u00edstico"));
        buttonGroup2.add(Heco);
        Heco.setText("Preferir ecolog\u00eda");
        Heco.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Heco.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup2.add(Hahorro);
        Hahorro.setText("Preferir ahorro del consumidor");
        Hahorro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Hahorro.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup2.add(Hecoahorro);
        Hecoahorro.setText("Ecolog\u00eda y ahorro");
        Hecoahorro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Hecoahorro.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup2.add(Htodo);
        Htodo.setSelected(true);
        Htodo.setText("Ecolog\u00eda, ahorro y maximizar beneficio");
        Htodo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Htodo.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(Heco)
                    .add(Hahorro)
                    .add(Hecoahorro)
                    .add(Htodo))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(Heco)
                .add(16, 16, 16)
                .add(Hahorro)
                .add(17, 17, 17)
                .add(Hecoahorro)
                .add(19, 19, 19)
                .add(Htodo)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicializaci\u00f3n"));
        buttonGroup3.add(IniSin);
        IniSin.setSelected(true);
        IniSin.setText("Sin asignaciones iniciales");
        IniSin.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        IniSin.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup3.add(geco);
        geco.setText("Greedy ecol\u00f3gico");
        geco.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        geco.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup3.add(gahorro);
        gahorro.setText("Greedy ahorrativo");
        gahorro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gahorro.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup3.add(gecoahorro);
        gecoahorro.setText("Greedy eco ahorrativo");
        gecoahorro.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gecoahorro.setMargin(new java.awt.Insets(0, 0, 0, 0));

        buttonGroup3.add(gtotal);
        gtotal.setText("Greedy total");
        gtotal.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        gtotal.setMargin(new java.awt.Insets(0, 0, 0, 0));

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(IniSin)
                    .add(geco)
                    .add(gecoahorro)
                    .add(gahorro)
                    .add(gtotal))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(IniSin)
                .add(23, 23, 23)
                .add(geco)
                .add(20, 20, 20)
                .add(gahorro)
                .add(25, 25, 25)
                .add(gecoahorro)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 26, Short.MAX_VALUE)
                .add(gtotal)
                .addContainerGap())
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Cr\u00e9ditos"));
        jTextArea1.setBackground(new java.awt.Color(236, 233, 216));
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setRows(5);
        jTextArea1.setText("Pr\u00e1ctica 1\u00aa de IA\n2006/2007 Q1\n\nAutores:\nDaniel Garc\u00eda P\u00e9rez\nSergio Vico Marfil");
        jScrollPane1.setViewportView(jTextArea1);

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .add(32, 32, 32)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(145, 145, 145))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Configuraci\u00f3n", jPanel1);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Paquetes"));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTable1.setModel(model1);
        jScrollPane2.setViewportView(jTable1);

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton2)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 390, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel9Layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumidores"));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jTable2.setModel(model2);
        jScrollPane3.setViewportView(jTable2);

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel10Layout = new org.jdesktop.layout.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel10Layout.createSequentialGroup()
                .add(33, 33, 33)
                .add(jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 332, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton3))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel10Layout.createSequentialGroup()
                .add(jScrollPane3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones de relleno"));
        RellenoRandom.setText("Rellenar");
        RellenoRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RellenoRandomActionPerformed(evt);
            }
        });

        npaqs.setColumns(8);
        npaqs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        npaqs.setText("0");

        jLabel5.setText("N\u00ba Paquetes:");

        jLabel6.setText("N\u00ba Consumidores:");

        ncons.setColumns(8);
        ncons.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        ncons.setText("0");

        jLabel7.setText("(0 = Random)");

        org.jdesktop.layout.GroupLayout jPanel11Layout = new org.jdesktop.layout.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel7)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(RellenoRandom))
                    .add(jPanel11Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(npaqs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(jLabel6)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(ncons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel11Layout.createSequentialGroup()
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(npaqs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6)
                    .add(ncons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                .add(jPanel11Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel7)
                    .add(RellenoRandom))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));
        Res.setColumns(20);
        Res.setEditable(false);
        Res.setLineWrap(true);
        Res.setRows(5);
        jScrollPane4.setViewportView(Res);

        Ejecucion.setText("Ejecuci\u00f3n");
        Ejecucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EjecucionActionPerformed(evt);
            }
        });

        Exportar.setText("Exportar Resultado");
        Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(Ejecucion)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(Exportar))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jScrollPane4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 366, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(Ejecucion)
                    .add(progress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 9, Short.MAX_VALUE)
                .add(Exportar)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanel11, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel10, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jTabbedPane1.addTab("Datos y Ejecuci\u00f3n", jPanel2);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuraci\u00f3n"));
        jLabel8.setText("N\u00ba Paquetes");

        jLabel9.setText("N\u00ba Consumidores");

        jLabel10.setText("N\u00ba Pruebas");

        bpaqs.setColumns(8);
        bpaqs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bpaqs.setText("0");

        bcons.setColumns(8);
        bcons.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bcons.setText("0");

        bpruebs.setColumns(8);
        bpruebs.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bpruebs.setText("0");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel11.setText("Configuraci\u00f3n del Simulated Annealing");

        jLabel12.setText("Iteraciones");

        jLabel13.setText("Pasos");

        bsiter.setColumns(8);
        bsiter.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bsiter.setText("2000");

        bspasos.setColumns(8);
        bspasos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bspasos.setText("100");

        jLabel14.setText("K");

        jLabel15.setText("\u03bb");

        bsk.setColumns(8);
        bsk.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bsk.setText("15");

        bslanda.setColumns(8);
        bslanda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        bslanda.setText("0.03");

        org.jdesktop.layout.GroupLayout jPanel13Layout = new org.jdesktop.layout.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel9)
                    .add(jLabel10)
                    .add(jLabel8))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(bpaqs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bpruebs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bcons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(56, 56, 56)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 50, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel11)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jPanel13Layout.createSequentialGroup()
                                .add(jLabel13)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(bspasos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel13Layout.createSequentialGroup()
                                .add(jLabel12)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(bsiter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 63, Short.MAX_VALUE)
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel14)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel15))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(bsk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bslanda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel13Layout.createSequentialGroup()
                .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel8)
                            .add(bpaqs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel9)
                            .add(bcons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel10)
                            .add(bpruebs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 93, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel13Layout.createSequentialGroup()
                        .add(jLabel11)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel12)
                            .add(bsiter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel14)
                            .add(bsk, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel13Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel13)
                            .add(jLabel15)
                            .add(bslanda, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(bspasos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));
        bRes.setColumns(20);
        bRes.setEditable(false);
        bRes.setRows(5);
        jScrollPane5.setViewportView(bRes);

        org.jdesktop.layout.GroupLayout jPanel14Layout = new org.jdesktop.layout.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel14Layout.createSequentialGroup()
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Ejecuci\u00f3n"));
        Bejecutar.setText("Ejecutar");
        Bejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BejecutarActionPerformed(evt);
            }
        });

        bExportar.setText("Exportar Resultado");
        bExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExportarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel15Layout = new org.jdesktop.layout.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .add(Bejecutar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bprogress, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(bExportar)
                .add(154, 154, 154))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .add(jPanel15Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(bprogress, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(bExportar)
                    .add(Bejecutar))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel12Layout = new org.jdesktop.layout.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jTabbedPane1.addTab("Bater\u00eda de pruebas", jPanel12);

        jMenu2.setText("Menu");
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        jMenu2.add(Salir);

        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
// TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void bExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExportarActionPerformed
// TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        if( file.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = file.getSelectedFile().getAbsolutePath();
            try
            { 
                BufferedWriter bw =  new BufferedWriter(new FileWriter(filename));
                bw.write(printData());
                bw.write(bRes.getText());
                bw.flush();
                bw.close();
            } catch (IOException ioe) {}
        }
        
    }//GEN-LAST:event_bExportarActionPerformed

    private void BejecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BejecutarActionPerformed
// TODO add your handling code here:
        if(!ejecutando) {
            bRes.setText("");
            ejecutando = true;
            Thread t = new Thread (){
                public void run() {
                    ejecutarBateria();
                }
            };
            t.start();
           
        } 
    }//GEN-LAST:event_BejecutarActionPerformed

    private void  ejecutarBateria() {
        OfertaDemanda od = OfertaDemanda.getOfertaDemanda();
        Random r = new Random();
        int nump = Integer.valueOf(bpruebs.getText());
        int npaqs = Integer.valueOf(bpaqs.getText());
        int ncons = Integer.valueOf(bcons.getText());
        
        double resultadoH [] = new double[20];
        long tiempoH [] = new long[20];
        double resultadoS [] = new double[20];
        long tiempoS [] = new long[20];
        
        bprogress.setMaximum(40*nump);
        bprogress.setValue(0);
        
        for(int prueba = 0; prueba < nump; prueba++) {
            od.resetearDatos();
            int i;
            int j;
            int co2,precio,kwh,beneficio;
            
            //INICIALIZACION
            for ( i = 0; i < ncons; i++ ) {
                co2 = r.nextInt(3) + 1;
                           
                switch( co2 ) {
                    case 1: precio = r.nextInt(4)+1;
                        break;
                    case 2: precio = r.nextInt(5)+3;
                        break;
                    case 3: precio = r.nextInt(5)+6;
                        break;
                    default: precio = r.nextInt(10)+1;
                        break;
                }
                kwh = r.nextInt(301)+200;
                od.addConsumidor(i,"C-"+String.valueOf(i),co2,precio,kwh);
            }
            for ( j = 0; j < npaqs; j++ ) {
                co2 = r.nextInt(3)+1;
                            
                switch( co2 ) {
                    case 1: precio=r.nextInt(4)+1;
                            beneficio=r.nextInt(21)+30;
                         break;
                    case 2: precio=r.nextInt(5)+3;
                            beneficio=r.nextInt(21)+20;
                        break;
                    case 3: precio=r.nextInt(5)+6;
                            beneficio=r.nextInt(21)+5;
                        break;
                    default: precio=r.nextInt(10)+1;
                            beneficio=r.nextInt(46)+5;
                        break;
                }
                kwh = r.nextInt(4001)+1000;
                od.addPaquete(j,"P-"+String.valueOf(j),co2,precio,kwh,beneficio);
            }

            //EMPEZAMOS
            for(i=0; i< 4; i++) {
                DistribucionHeuristic h = null;
                switch(i) {
                    case 0: h = new DistribucionHeuristicFunction1();
                        break;
                    case 1: h = new DistribucionHeuristicFunction2();
                        break;
                    case 2: h = new DistribucionHeuristicFunction3();
                        break;
                    case 3: h = new DistribucionHeuristicFunction4();
                        break;
                }
                
                DistribucionGreedy greedy = new DistribucionGreedy();
                Distribucion dist = null;
                
                for(j = 0; j < 5; j++) {
                    switch(j) {
                        case 0: dist = new Distribucion();
                            break;
                        case 1: dist = greedy.inicializarEcologia();
                            break;
                        case 2: dist = greedy.inicializarAhorro();
                            break;
                        case 3: dist = greedy.inicializarEcoAhorro();
                            break;
                        case 4: dist = greedy.inicializarEcoAhorroBeneficio();
                            break;
                    }
                    
                    DistBateriaHillClimbingSearch(i+j*4,nump,resultadoH,tiempoH,dist,h);
                    bprogress.setValue(bprogress.getValue()+1);
                    DistBateriaSimulatedAnnealingSearch(i+j*4,nump,resultadoS,tiempoS,dist,h);
                    bprogress.setValue(bprogress.getValue()+1);
                }
                
                
                
            }
        }
        
        //YA TENEMOS RESULTADOS
        String heurist[] = {"Heur�stico Ecol�gico\n ","Heur�stico Ahorro\n ","Heur�stico Eco-Ahorro\n ","Heur�stico Eco-Ahorro-Benef\n "};
        String init[] = {"Sin inicializaci�n\n "," Greedy Ecol�gico\n "," Greedy Ahorro\n "," Greedy Eco-Ahorro\n "," Greedy Eco-Ahorro-Benef\n "};
        
        bRes.append("Configuraci�n\n\n");
        bRes.append("N� Pruebas:"+bpruebs.getText()+" N� Paquetes:"+bpaqs.getText()+" N� Consumidores:"+bcons.getText()+"\n\n");
        bRes.append("Resultados\n");
        bRes.append("\n--Hill Climbing--\n\n");
        for ( int k = 0; k < 4; k++) {
            bRes.append(heurist[k]);
            for( int q = 0; q < 5;q++) {
                bRes.append(init[q]);
                bRes.append(" Media:"+(((int)((-100)*resultadoH[k+q*4]))/100.0)+", Tiempo: "+tiempoH[k+q*4]+"ms\n");
            }
        }
        
        bRes.append("\n--Simulated Annealing--\n");
        bRes.append("Iteraciones="+bsiter.getText()+" Pasos="+pasos.getText()+" K="+bsk.getText()+" Lambda="+bslanda.getText()+"\n\n");
       
        
        for ( int k = 0; k < 4; k++) {
            bRes.append(heurist[k]);
            for( int q = 0; q < 5;q++) {
                bRes.append(init[q]);
                bRes.append(" Media:"+ (((int)((-100)*resultadoS[k+q*4]))/100.0)+", Tiempo: "+tiempoS[k+q*4]+"ms\n");
            }
        }
        
        ejecutando = false;
    }
    
    private void DistBateriaHillClimbingSearch(int id,int nump, double r[],long t[],Distribucion dist,DistribucionHeuristic h) {
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),h);
            HillClimbingSearch search =  new HillClimbingSearch();
            tiempoEjecucion = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem,search);
            t[id] += (System.currentTimeMillis() - tiempoEjecucion)/nump;
            r[id] += (double)(h.getHeuristicValue((Distribucion)search.getLastSearchState()))/(double)nump;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void DistBateriaSimulatedAnnealingSearch(int id, int nump, double r[],long t[],Distribucion dist,DistribucionHeuristic h) {
        try {
            Problem problem =  new Problem(dist,new DistribucionSuccessorFunction1(), new DistribucionGoalTest(),h);
            SimulatedAnnealingSearch search =  new SimulatedAnnealingSearch(Integer.parseInt(bsiter.getText()),Integer.parseInt(bspasos.getText()),Integer.parseInt(bsk.getText()),Double.parseDouble(bslanda.getText()));
            tiempoEjecucion = System.currentTimeMillis();
            SearchAgent agent = new SearchAgent(problem,search);
            t[id] += (System.currentTimeMillis() - tiempoEjecucion)/nump;
            r[id] += (double)(h.getHeuristicValue((Distribucion)search.getLastSearchState()))/(double)nump;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportarActionPerformed
// TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        if( file.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = file.getSelectedFile().getAbsolutePath();
            try
            { 
                BufferedWriter bw =  new BufferedWriter(new FileWriter(filename));
                bw.write(printData());
                bw.write(Res.getText());
                bw.flush();
                bw.close();
            } catch (IOException ioe) {}
        }
    }//GEN-LAST:event_ExportarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
        this.addCRow();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        this.addPRow();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void RellenoRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RellenoRandomActionPerformed
// TODO add your handling code here:
        Random r = new Random();
        int c,p;
        
        try{
            c = Integer.parseInt(ncons.getText());
            if (c == 0) {
                c = r.nextInt(400);
                ncons.setText(String.valueOf(c));
            }
            
            p = Integer.parseInt(npaqs.getText());
            if (p == 0) {
                p = r.nextInt(150);
                npaqs.setText(String.valueOf(p));
            }
            
            rellenarTablas(c,p);
        }catch(Exception e){}
    }//GEN-LAST:event_RellenoRandomActionPerformed

    private void rellenarTablas(int numC, int numP) {
        Random r = new Random();
        int maxC = numC;
        int maxP = numP;
        
        model1.setNumRows(maxP);
        model2.setNumRows(maxC);
        
        int co2,precio,beneficio;
        
        for (int j = 0; j < maxP; j++ ) {
            model1.setValueAt(j,j,0);
            model1.setValueAt("P-"+String.valueOf(j),j,1);
            
            co2 = r.nextInt(3)+1;
            model1.setValueAt(co2,j,3);
            
            switch( co2 ) {
                case 1: model1.setValueAt(r.nextInt(4)+1,j,2);
                        model1.setValueAt(r.nextInt(21)+30,j,5);
                    break;
                case 2: model1.setValueAt(r.nextInt(5)+3,j,2);
                        model1.setValueAt(r.nextInt(21)+20,j,5);
                    break;
                case 3: model1.setValueAt(r.nextInt(5)+6,j,2);
                        model1.setValueAt(r.nextInt(21)+5,j,5);
                    break;
                default: model1.setValueAt(r.nextInt(10)+1,j,2);
                        model1.setValueAt(r.nextInt(46)+5,j,5);
                    break;
            }
            model1.setValueAt(r.nextInt(4000)+1000,j,4);
        }
        
        for (int i = 0; i < maxC; i++ ) {
            model2.setValueAt(i,i,0);
            model2.setValueAt("C-"+String.valueOf(i),i,1);
            
            co2 = r.nextInt(3)+1;
            model2.setValueAt(co2,i,3);
            
            switch( co2 ) {
                case 1: model2.setValueAt(r.nextInt(4)+1,i,2);
                    break;
                case 2: model2.setValueAt(r.nextInt(5)+3,i,2);
                    break;
                case 3: model2.setValueAt(r.nextInt(5)+6,i,2);
                    break;
                default: model2.setValueAt(r.nextInt(10)+1,i,2);
                    break;
            }
            model2.setValueAt(r.nextInt(300)+200,i,4);
        }
    
    }
    
    private void EjecucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EjecucionActionPerformed
// TODO add your handling code here:
        if(!ejecutando) {
            Res.setText("");
            ejecutando = true;
            Thread t = new Thread (){
                public void run() {
                    ejecutar();
                }
            };
            t.start();
            
        }    
        
    }//GEN-LAST:event_EjecucionActionPerformed

    private void simulatedaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_simulatedaStateChanged
// TODO add your handling code here:
        iter.setEditable(simulateda.isSelected());
        pasos.setEditable(simulateda.isSelected());
        k.setEditable(simulateda.isSelected());
        landa.setEditable(simulateda.isSelected());
        jButton1.setEnabled(simulateda.isSelected());
    }//GEN-LAST:event_simulatedaStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        iter.setText("2000");
        pasos.setText("100");
        k.setText("15");
        landa.setText("0.03");
    }//GEN-LAST:event_jButton1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DistribucionGrafico df= new DistribucionGrafico();
                df.setVisible(true);
            }
        });
    }
    
    private void crearModelTablas() {
        
        //TABLA1
        model1 =  new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {0, "", null, null, null, null},
                {1, "", null, null, null, null},
                {2, "", null, null, null, null},
                {3, "", null, null, null, null},
                {4, "", null, null, null, null},
                {5, "", null, null, null, null},
                {6, "", null, null, null, null}
            },
            new String [] {
                "Id", "Compa��a", "Precio", "CO2", "kWh", "Beneficio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        
        //TABLA2
        model2 = new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {0, "", null, null, null},
                {1, "", null, null, null},
                {2, "", null, null, null},
                {3, "", null, null, null},
                {4, "", null, null, null},
                {5, "", null, null, null},
                {6, "", null, null, null}
            },
            new String [] {
                "Id", "Nombre", "PrecioMax", "CO2Min", "kWh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
    }
    
    private void addPRow() {
        model1.addRow(new Object[]{model1.getRowCount(),"",null,null,null,null});
    }
    
    private void addCRow() {
        model2.addRow(new Object[]{model2.getRowCount(),"",null,null,null});
    }

    private void calcularValorSol(Distribucion dist) {
        double val = 0;
        
        Res.append("\nConfiguraci�n utilizada\n\n");
        
        String heu = "";
        if(Heco.isSelected()) {       
            heu = "Ecol�gico";
            val = (-1)*(new DistribucionHeuristicFunction1().getHeuristicValue(dist));
        }else if(Hahorro.isSelected()) {
            heu = "Ahorro";
            val = (-1)*(new DistribucionHeuristicFunction2().getHeuristicValue(dist));
        }else if(Hecoahorro.isSelected()) {
            heu = "Ecol�gico y ahorro";
            val = (-1)*(new DistribucionHeuristicFunction3().getHeuristicValue(dist));
        }else{
            heu = "Ecol�gico, ahorro y beneficio";
            val = (-1)*(new DistribucionHeuristicFunction4().getHeuristicValue(dist));
        }
        
        Res.append("Heur�stico utilizado: "+heu+"\n");
        
        String gre = "";
        if( IniSin.isSelected()) {
            gre = "Sin greedy";
        }else if( geco.isSelected()){
            gre = "Ecol�gico";
        }else if( gahorro.isSelected()) {
            gre = "Ahorro";
        }else if( gecoahorro.isSelected()){
            gre = "Ecol�gico y ahorro";
        }else{
            gre = "Ecol�gico, ahorro y beneficio";
       }
        
        Res.append("Inicializaci�n utilizada: "+gre+"\n");
        Res.append("\nValoraci�n del estado final\n "+val);
     
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bejecutar;
    private javax.swing.JButton Ejecucion;
    private javax.swing.JButton Exportar;
    private javax.swing.JRadioButton Hahorro;
    private javax.swing.JRadioButton Heco;
    private javax.swing.JRadioButton Hecoahorro;
    private javax.swing.JRadioButton Htodo;
    private javax.swing.JRadioButton IniSin;
    private javax.swing.JButton RellenoRandom;
    private javax.swing.JTextArea Res;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JButton bExportar;
    private javax.swing.JTextArea bRes;
    private javax.swing.JTextField bcons;
    private javax.swing.JTextField bpaqs;
    private javax.swing.JProgressBar bprogress;
    private javax.swing.JTextField bpruebs;
    private javax.swing.JTextField bsiter;
    private javax.swing.JTextField bsk;
    private javax.swing.JTextField bslanda;
    private javax.swing.JTextField bspasos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton gahorro;
    private javax.swing.JRadioButton geco;
    private javax.swing.JRadioButton gecoahorro;
    private javax.swing.JRadioButton gtotal;
    private javax.swing.JRadioButton hillc;
    private javax.swing.JTextField iter;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField k;
    private javax.swing.JTextField landa;
    private javax.swing.JTextField ncons;
    private javax.swing.JTextField npaqs;
    private javax.swing.JTextField pasos;
    private javax.swing.JProgressBar progress;
    private javax.swing.JRadioButton simulateda;
    // End of variables declaration//GEN-END:variables
    
}
