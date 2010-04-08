/***********************************************************************
 ***                                                                 ***
 ***   Pr�ctica IA Q1 / 2005                                         ***
 ***                                                                 ***
 ***   Alumno: Rafael Olivera Cano                                   ***
 ***   DNI:    47785413-S                                            ***
 ***   Grupo:  12                                                    ***
 ***                                                                 ***
 ***   LocalSearch.java                                              ***
 ***                                                                 ***
 ***********************************************************************/

package IA.Marenostrum;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Calendar;
import java.util.Date;


import aima.search.framework.GraphSearch;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.TreeSearch;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.HeuristicFunction;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.GreedyBestFirstSearch;

public class MarenostrumJFrame extends JFrame
{
 	/* Constantes */
	private final int HORAS = 168;
 	
 	/* Variables */
 	private int idGenerador;
 	private int idHeuristico;
 	private int idSucesores;
 	
 	/* Colores */
 	private Color				colorBackgroundWindow; 	
 	private Color				colorForegroundWindow1;
 	private Color				colorForegroundWindow2; 	 	
 	private Color				colorNoAsignado; 	
 	private Color				colorSiAsignado;
 	 	
 	/* Fuentes */
 	private Font				fuente; 	
 	
 	/* Paneles */
 	private JPanel			panelResultado; 	
 	private JPanel			panelProcesos;
 	private JPanel			panelBusqueda; 	
 	private JPanel			panelInfo;
 	
 	/* Components panelResultado */ 	 	
 	private JLabel[]		labelHora;
 	private JLabel			labelDia;
 	private JLabel			labelDiaNombre;
 	
 	/* Components panelProcesos */
 	private JLabel			labelNProcesos;
 	private JTextField	textNProcesos;
 	private JButton			buttonGenerarProcesos;
 	private JTextArea		areaProcesos;
 	private JScrollPane scrollProcesos;
 	 	
 	/* Components panelBusqueda */	
 	private JLabel 			labelAlgoritmos;
 	private JLabel 			labelGeneradores;
 	private JLabel 			labelSucesores;
 	private JLabel 			labelHeuristicos;
 	private JButton 		buttonHillClimbing;
 	private JButton			buttonSimulatedAnnealing;
 	private JRadioButton radioGenerador1;
 	private JRadioButton radioGenerador2;
 	private JRadioButton radioGenerador3;
 	private JRadioButton radioGenerador4;
 	private JRadioButton radioHeuristico1;	
 	private JRadioButton radioHeuristico2;	
 	private JRadioButton radioHeuristico3;	
 	private JRadioButton radioHeuristico4;
 	private JRadioButton radioHeuristico5;
 	private JRadioButton radioSucesor1;
 	private JRadioButton radioSucesor2;
 	private JRadioButton radioSucesor3;
 	private JRadioButton radioSucesor4;
 	private JRadioButton radioSucesor5;
 	private JRadioButton radioSucesor6;
 	private JRadioButton radioSucesor7;
 	private JRadioButton radioSucesor8;
	private ButtonGroup groupGeneradores;
	private ButtonGroup groupHeuristicos;
	private ButtonGroup groupSucesores;
 	
 	//int steps, int stiter, int k, double lamb
 	private JLabel 			labelSteps;
 	private JLabel 			labelStiter;
 	private JLabel			labelK;
 	private JLabel			labelLamb;
 	private JTextField	textSteps;
 	private JTextField	textStiter;
 	private JTextField	textK;
 	private JTextField	textLamb;
 	
 	/* Components panelInfo */
 	private JLabel 			labelAlgoritmo;
 	private JLabel 			labelNodosExpandidos;
 	private JLabel			labelTiempo; 	
 	private JLabel			labelHorasLibres;
 	private JLabel 			labelProcesosAsignados; 	
 	private JLabel 			labelProcs1;
 	private JLabel 			labelProcs2;
 	private JLabel 			labelProcs3;
 	private JLabel 			labelProcs4;
 	private JLabel 			labelProcs5;
 	private JTextArea 	areaAcciones;
 	private JScrollPane	scrollAcciones;
 	 	 	 	
 	
        private javax.swing.JMenuItem Salir;
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenuBar jMenuBar1;
        
 	/* Contenedor */
 	private Container contentPane;
 	private GridBagLayout gridBag;
 	private GridBagConstraints gridConstraints;
 	
 	/* LocalSearchBoard */
 	private LocalSearchBoard LSBoard;
 	
/***********************************************************************
 ***                                                                 ***
 ***   main                                                          ***
 ***                                                                 ***
 ***********************************************************************/
 	
public static void main (String[] args)
{ 		
	MarenostrumJFrame LS = new MarenostrumJFrame(); 		
}
 	
/***********************************************************************
 ***                                                                 ***
 ***   LocalSearch                                                   ***
 ***                                                                 ***
 ***********************************************************************/
 	
public MarenostrumJFrame()
{
	/* Constructura padre */ 		
 	super();
 	
 	/* Inicializamos LocalSearchBoard */
 	LSBoard = new LocalSearchBoard();
 	
 	/* Inicializamos variables */
 	idGenerador = 1;
 	idHeuristico = 1;
 	idSucesores = 1;
 	
 	/* Inicializamos el entorno grafico (ventana) */
 	inicializarEntornoGrafico(); 		 		
	

}
 	

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {                                      
// TODO add your handling code here:
       dispose();
    }                                     

/***********************************************************************
 ***                                                                 ***
 ***   inicializarEntornoGrafico                                     ***
 ***                                                                 ***
 ***********************************************************************/
  	
private void inicializarEntornoGrafico()
{ 		
	/* Colores */
 	colorBackgroundWindow = Color.GRAY.darker();	 	 	
 	colorForegroundWindow1 = Color.ORANGE;
 	colorForegroundWindow2 = Color.ORANGE.darker(); 	
 	colorNoAsignado = Color.ORANGE.darker();
 	colorSiAsignado = Color.ORANGE;
        
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Salir = new javax.swing.JMenuItem();
        
        jMenu1.setText("Menu");
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });

        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

 	 	
 	/* Contenedor */
 	gridBag = new GridBagLayout();
 	gridConstraints = new GridBagConstraints();
 		
 	/* Configuraci�n de la ventana */		
 	this.setTitle("Local Search (IA)");
 	this.setResizable(false); 		
 		
 	/* Configuramos Fonts */
 	fuente = new Font("Verdana", Font.BOLD, 10);
 		
 	/* Configuramos Paneles */
 	inicializarPanelResultado();
 	inicializarPanelProcesos();
 	inicializarPanelBusqueda();
 	inicializarPanelInfo();
 	
 	/* Organizacion de la vista principal */    
	contentPane = getContentPane();    
  contentPane.setLayout(gridBag);
  contentPane.setBackground(colorBackgroundWindow);
	
	gridConstraints.gridx = 0;
	gridConstraints.gridy = 0;
	gridConstraints.gridheight = 1;
	gridConstraints.gridwidth = 2;
	gridConstraints.fill = GridBagConstraints.NONE;
	gridConstraints.insets = new Insets(15,15,15,15);
	gridBag.setConstraints(panelResultado, gridConstraints);
  contentPane.add(panelResultado);    	     	
 		
 	gridConstraints.gridx = 0;
	gridConstraints.gridy = 1;
	gridConstraints.gridheight = 1;
	gridConstraints.gridwidth = 1;
	gridConstraints.fill = GridBagConstraints.NONE;
	gridConstraints.insets = new Insets(0,15,15,15);
	gridBag.setConstraints(panelProcesos, gridConstraints);
  contentPane.add(panelProcesos);    	     	
  
  gridConstraints.gridx = 0;
	gridConstraints.gridy = 2;
	gridConstraints.gridheight = 1;
	gridConstraints.gridwidth = 2;
	gridConstraints.fill = GridBagConstraints.NONE;
	gridConstraints.insets = new Insets(0,15,15,15);
	gridBag.setConstraints(panelBusqueda, gridConstraints);
  contentPane.add(panelBusqueda);
  
  gridConstraints.gridx = 1;
	gridConstraints.gridy = 1;
	gridConstraints.gridheight = 1;
	gridConstraints.gridwidth = 1;
	gridConstraints.fill = GridBagConstraints.NONE;
	gridConstraints.insets = new Insets(0,0,15,15);
	gridBag.setConstraints(panelInfo, gridConstraints);
  contentPane.add(panelInfo);
  
  /* Evento cerrar */
  this.addWindowListener(new WindowAdapter() { public void windowClosing(WindowEvent we) { System.exit(0); } });
  
 	/* Empaquetamos y mostramos */
 	this.pack();
 	this.setVisible(true); 		
}

/***********************************************************************
 ***                                                                 ***
 ***   inicializarPanelResultado                                     ***
 ***                                                                 ***
 ***********************************************************************/
 	
private void inicializarPanelResultado()
{
	TitledBorder title = BorderFactory.createTitledBorder("Asignaci�n de horas semanales");
	title.setTitleFont(fuente);
	title.setTitleColor(colorForegroundWindow2);
	
	panelResultado = new JPanel();
 	panelResultado.setLayout(null); 	
 	panelResultado.setBackground(colorBackgroundWindow); 				
 	panelResultado.setBorder(title);
 	
 	/* Organizacion panelHill */        
  panelResultado.setLayout(gridBag);  
 	
 	for (int i = 0; i < 24; i++)
 	{
 		labelDia = new JLabel(String.valueOf(i));
 		labelDia.setFont(fuente);
 		labelDia.setBackground(colorBackgroundWindow);
 		labelDia.setForeground(colorForegroundWindow1);
 		labelDia.setPreferredSize(new Dimension(25, 25));
 		labelDia.setHorizontalAlignment(JLabel.CENTER);
 		anyadirJComponent(panelResultado, labelDia, i + 1, 0, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	}
 	
 	labelDiaNombre = new JLabel("LUN");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	 	
 	labelDiaNombre = new JLabel("MAR");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 2, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));	
 	 	 
 	labelDiaNombre = new JLabel("MIE");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 3, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	
 	labelDiaNombre = new JLabel("JUE");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 4, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	
 	labelDiaNombre = new JLabel("VIE");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 5, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	
 	labelDiaNombre = new JLabel("SAB");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 6, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	
 	labelDiaNombre = new JLabel("DOM");
 	labelDiaNombre.setFont(fuente);
 	labelDiaNombre.setBackground(colorBackgroundWindow);
 	labelDiaNombre.setForeground(colorForegroundWindow1);
 	labelDiaNombre.setPreferredSize(new Dimension(38, 25));
 	labelDiaNombre.setHorizontalAlignment(JLabel.CENTER);
 	anyadirJComponent(panelResultado, labelDiaNombre, 0, 7, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	
 	labelHora = new JLabel[HORAS];
 	for (int i = 0; i < HORAS; i++)
 	{ 		
 		labelHora[i] = new JLabel();
 		labelHora[i].setFont(fuente); 		
 		labelHora[i].setOpaque(true);
 		labelHora[i].setBackground(colorNoAsignado);
 		labelHora[i].setPreferredSize(new Dimension(25, 25));	 
 		labelHora[i].setHorizontalAlignment(JLabel.CENTER); 
 		anyadirJComponent(panelResultado, labelHora[i], (i % 24) + 1, (i / 24) + 1, 1, 1, GridBagConstraints.NONE, new Insets(1, 1, 1, 1));
 	} 		
}

/***********************************************************************
 ***                                                                 ***
 ***   inicializarPanelBusqueda                                      ***
 ***                                                                 ***
 ***********************************************************************/
 	
private void inicializarPanelBusqueda()
{
	TitledBorder title = BorderFactory.createTitledBorder("Busqueda Local");
	title.setTitleFont(fuente);
	title.setTitleColor(colorForegroundWindow2);
	
	panelBusqueda = new JPanel();
 	panelBusqueda.setLayout(null); 	
 	panelBusqueda.setBackground(colorBackgroundWindow); 				
 	panelBusqueda.setBorder(title);
 
 	/* Organizacion panelHill */        
  panelBusqueda.setLayout(gridBag);
  
  labelAlgoritmos = new JLabel("Algoritmos de b�squeda"); 	
 	labelAlgoritmos.setFont(fuente);
 	labelAlgoritmos.setForeground(colorForegroundWindow2);
 	labelAlgoritmos.setPreferredSize(new Dimension(223, 20));
 	anyadirJComponent(panelBusqueda, labelAlgoritmos, 0, 0, 1, 2, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	  	 
 	buttonHillClimbing = new JButton("Hill Climbing"); 	
 	buttonHillClimbing.setFont(fuente);
 	buttonHillClimbing.setPreferredSize(new Dimension(223, 25));
 	buttonHillClimbing.setEnabled(false);
 	buttonHillClimbing.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { realizarLocalSearch(0); } });
 	anyadirJComponent(panelBusqueda, buttonHillClimbing, 0, 1, 1, 2, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	 	
 	buttonSimulatedAnnealing = new JButton("Simulated Annealing"); 	
 	buttonSimulatedAnnealing.setFont(fuente);
 	buttonSimulatedAnnealing.setPreferredSize(new Dimension(223, 25));
 	buttonSimulatedAnnealing.setEnabled(false);
 	buttonSimulatedAnnealing.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { realizarLocalSearch(1); } });
 	anyadirJComponent(panelBusqueda, buttonSimulatedAnnealing, 0, 2, 1, 2, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	labelSteps = new JLabel("Steps"); 	
 	labelSteps.setFont(fuente);
 	labelSteps.setForeground(colorForegroundWindow1);
 	labelSteps.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, labelSteps, 0, 3, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	textSteps = new JTextField("2000");
 	textSteps.setFont(fuente);
 	textSteps.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, textSteps, 1, 3, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	labelStiter = new JLabel("Stiter"); 	
 	labelStiter.setFont(fuente);
 	labelStiter.setForeground(colorForegroundWindow1);
 	labelStiter.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, labelStiter, 0, 4, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	textStiter = new JTextField("100");
 	textStiter.setFont(fuente);
 	textStiter.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, textStiter, 1, 4, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	labelK = new JLabel("K"); 	
 	labelK.setFont(fuente);
 	labelK.setForeground(colorForegroundWindow1);
 	labelK.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, labelK, 0, 5, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	textK = new JTextField("5");
 	textK.setFont(fuente);
 	textK.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, textK, 1, 5, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	labelLamb = new JLabel("Lamb"); 	
 	labelLamb.setFont(fuente);
 	labelLamb.setForeground(colorForegroundWindow1);
 	labelLamb.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, labelLamb, 0, 6, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	textLamb = new JTextField("0.001");
 	textLamb.setFont(fuente);
 	textLamb.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelBusqueda, textLamb, 1, 6, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	
 	labelGeneradores = new JLabel("Generadores"); 	
 	labelGeneradores.setFont(fuente);
 	labelGeneradores.setForeground(colorForegroundWindow2);
 	labelGeneradores.setPreferredSize(new Dimension(223, 20));
 	anyadirJComponent(panelBusqueda, labelGeneradores, 3, 0, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	 	
 	labelSucesores = new JLabel("Sucesores"); 	
 	labelSucesores.setFont(fuente);
 	labelSucesores.setForeground(colorForegroundWindow2);
 	labelSucesores.setPreferredSize(new Dimension(223, 20));
 	anyadirJComponent(panelBusqueda, labelSucesores, 4, 0, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	labelHeuristicos = new JLabel("Heuristicos");
 	labelHeuristicos.setFont(fuente);
 	labelHeuristicos.setForeground(colorForegroundWindow2);
 	labelHeuristicos.setPreferredSize(new Dimension(223, 20));
 	anyadirJComponent(panelBusqueda, labelHeuristicos, 3, 5, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 
 	radioGenerador1 = new JRadioButton("Estado inicial aleatorio");
 	radioGenerador1.setFont(fuente);
 	radioGenerador1.setForeground(colorForegroundWindow1);
 	radioGenerador1.setBackground(colorBackgroundWindow);
 	radioGenerador1.setPreferredSize(new Dimension(223, 20));   	
 	radioGenerador1.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idGenerador = 1; } });
 	anyadirJComponent(panelBusqueda, radioGenerador1, 3, 1, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioGenerador2 = new JRadioButton("Estado inicial vacio");
 	radioGenerador2.setFont(fuente);
 	radioGenerador2.setForeground(colorForegroundWindow1);
 	radioGenerador2.setBackground(colorBackgroundWindow);
 	radioGenerador2.setPreferredSize(new Dimension(223, 20));   	
 	radioGenerador2.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idGenerador = 2; } });
 	anyadirJComponent(panelBusqueda, radioGenerador2, 3, 4, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	 
 	radioGenerador3 = new JRadioButton("Estado inicial por rango");
 	radioGenerador3.setFont(fuente);
 	radioGenerador3.setForeground(colorForegroundWindow1);
 	radioGenerador3.setBackground(colorBackgroundWindow);
 	radioGenerador3.setPreferredSize(new Dimension(223, 20));   	
 	radioGenerador3.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idGenerador = 3; } });
 	anyadirJComponent(panelBusqueda, radioGenerador3, 3, 2, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioGenerador4 = new JRadioButton("Estado inicial por duraci�n");
 	radioGenerador4.setFont(fuente);
 	radioGenerador4.setForeground(colorForegroundWindow1);
 	radioGenerador4.setBackground(colorBackgroundWindow);
 	radioGenerador4.setPreferredSize(new Dimension(223, 20));   	
 	radioGenerador4.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idGenerador = 4; } });
 	anyadirJComponent(panelBusqueda, radioGenerador4, 3, 3, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	 	 	
 	groupGeneradores = new ButtonGroup();
 	groupGeneradores.add(radioGenerador1);
 	groupGeneradores.add(radioGenerador2);
 	groupGeneradores.add(radioGenerador3);
 	groupGeneradores.add(radioGenerador4);
 	radioGenerador1.setSelected(true);
 	
 	radioSucesor1 = new JRadioButton("Asignar procesos");
 	radioSucesor1.setFont(fuente);
 	radioSucesor1.setForeground(colorForegroundWindow1);
 	radioSucesor1.setBackground(colorBackgroundWindow);
 	radioSucesor1.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor1.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 1; } });
 	anyadirJComponent(panelBusqueda, radioSucesor1, 4, 1, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor2 = new JRadioButton("Asignar / Quitar procesos");
 	radioSucesor2.setFont(fuente);
 	radioSucesor2.setForeground(colorForegroundWindow1);
 	radioSucesor2.setBackground(colorBackgroundWindow);
 	radioSucesor2.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor2.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 2; } });
 	anyadirJComponent(panelBusqueda, radioSucesor2, 4, 2, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor3 = new JRadioButton("Asignar / Mover procesos");
 	radioSucesor3.setFont(fuente);
 	radioSucesor3.setForeground(colorForegroundWindow1);
 	radioSucesor3.setBackground(colorBackgroundWindow);
 	radioSucesor3.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor3.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 3; } });
 	anyadirJComponent(panelBusqueda, radioSucesor3, 4, 3, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor4 = new JRadioButton("Asignar / Quitar / Mover procesos");
 	radioSucesor4.setFont(fuente);
 	radioSucesor4.setForeground(colorForegroundWindow1);
 	radioSucesor4.setBackground(colorBackgroundWindow);
 	radioSucesor4.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor4.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 4; } });
 	anyadirJComponent(panelBusqueda, radioSucesor4, 4, 4, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor5 = new JRadioButton("Forzar procesos");
 	radioSucesor5.setFont(fuente);
 	radioSucesor5.setForeground(colorForegroundWindow1);
 	radioSucesor5.setBackground(colorBackgroundWindow);
 	radioSucesor5.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor5.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 5; } });
 	anyadirJComponent(panelBusqueda, radioSucesor5, 4, 5, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor6 = new JRadioButton("Forzar / Mover procesos");
 	radioSucesor6.setFont(fuente);
 	radioSucesor6.setForeground(colorForegroundWindow1);
 	radioSucesor6.setBackground(colorBackgroundWindow);
 	radioSucesor6.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor6.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 6; } });
 	anyadirJComponent(panelBusqueda, radioSucesor6, 4, 6, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor7 = new JRadioButton("Apretar procesos");
 	radioSucesor7.setFont(fuente);
 	radioSucesor7.setForeground(colorForegroundWindow1);
 	radioSucesor7.setBackground(colorBackgroundWindow);
 	radioSucesor7.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor7.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 7; } });
 	anyadirJComponent(panelBusqueda, radioSucesor7, 4, 7, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioSucesor8 = new JRadioButton("Apretar / Quitar procesos");
 	radioSucesor8.setFont(fuente);
 	radioSucesor8.setForeground(colorForegroundWindow1);
 	radioSucesor8.setBackground(colorBackgroundWindow);
 	radioSucesor8.setPreferredSize(new Dimension(223, 20));   	
 	radioSucesor8.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idSucesores = 8; } });
 	anyadirJComponent(panelBusqueda, radioSucesor8, 4, 8, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	groupSucesores = new ButtonGroup();
 	groupSucesores.add(radioSucesor1);
 	groupSucesores.add(radioSucesor2);
 	groupSucesores.add(radioSucesor3);
 	groupSucesores.add(radioSucesor4);
 	groupSucesores.add(radioSucesor5);
 	groupSucesores.add(radioSucesor6);
 	groupSucesores.add(radioSucesor7);
 	groupSucesores.add(radioSucesor8);
 	radioSucesor1.setSelected(true);
 	
 	radioHeuristico1 = new JRadioButton("procs. asignados");
 	radioHeuristico1.setFont(fuente);
 	radioHeuristico1.setForeground(colorForegroundWindow1);
 	radioHeuristico1.setBackground(colorBackgroundWindow);
 	radioHeuristico1.setPreferredSize(new Dimension(223, 20));   	
 	radioHeuristico1.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idHeuristico = 1; } });
 	anyadirJComponent(panelBusqueda, radioHeuristico1, 3, 6, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioHeuristico2 = new JRadioButton("procs. duraci�n");
 	radioHeuristico2.setFont(fuente);
 	radioHeuristico2.setForeground(colorForegroundWindow1);
 	radioHeuristico2.setBackground(colorBackgroundWindow);
 	radioHeuristico2.setPreferredSize(new Dimension(223, 20));   	
 	radioHeuristico2.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idHeuristico = 2; } });
 	anyadirJComponent(panelBusqueda, radioHeuristico2, 3, 7, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioHeuristico3 = new JRadioButton("procs. duraci�n > intervalos");
 	radioHeuristico3.setFont(fuente);
 	radioHeuristico3.setForeground(colorForegroundWindow1);
 	radioHeuristico3.setBackground(colorBackgroundWindow);
 	radioHeuristico3.setPreferredSize(new Dimension(223, 20));   	
 	radioHeuristico3.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idHeuristico = 3; } });
 	anyadirJComponent(panelBusqueda, radioHeuristico3, 3, 8, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioHeuristico4 = new JRadioButton("procs. duraci�n > rango");
 	radioHeuristico4.setFont(fuente);
 	radioHeuristico4.setForeground(colorForegroundWindow1);
 	radioHeuristico4.setBackground(colorBackgroundWindow);
 	radioHeuristico4.setPreferredSize(new Dimension(223, 20));   	
 	radioHeuristico4.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idHeuristico = 4; } });
 	anyadirJComponent(panelBusqueda, radioHeuristico4, 3, 9, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	radioHeuristico5 = new JRadioButton("procs. duraci�n > intervalos > rango");
 	radioHeuristico5.setFont(fuente);
 	radioHeuristico5.setForeground(colorForegroundWindow1);
 	radioHeuristico5.setBackground(colorBackgroundWindow);
 	radioHeuristico5.setPreferredSize(new Dimension(223, 20));   	
 	radioHeuristico5.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { idHeuristico = 5; } });
 	anyadirJComponent(panelBusqueda, radioHeuristico5, 3, 10, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	groupHeuristicos = new ButtonGroup();
 	groupHeuristicos.add(radioHeuristico1);
 	groupHeuristicos.add(radioHeuristico2);
 	groupHeuristicos.add(radioHeuristico3);
 	groupHeuristicos.add(radioHeuristico4);
 	groupHeuristicos.add(radioHeuristico5);
 	radioHeuristico1.setSelected(true);
}

/***********************************************************************
 ***                                                                 ***
 ***   inicializarPanelProcesos                                      ***
 ***                                                                 ***
 ***********************************************************************/
 	
private void inicializarPanelProcesos()
{
	TitledBorder title = BorderFactory.createTitledBorder("Generar procesos");
	title.setTitleFont(fuente);
	title.setTitleColor(colorForegroundWindow2);
	
	panelProcesos = new JPanel();
 	panelProcesos.setLayout(null); 	
 	panelProcesos.setBackground(colorBackgroundWindow); 				 	
 	panelProcesos.setBorder(title);
 	
 	/* Organizacion panelHill */        
  panelProcesos.setLayout(gridBag);
 	
 	labelNProcesos = new JLabel("n� procesos");
 	labelNProcesos.setFont(fuente);
 	labelNProcesos.setForeground(colorForegroundWindow1);
 	labelNProcesos.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelProcesos, labelNProcesos, 0, 0, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	textNProcesos = new JTextField("200");
 	textNProcesos.setFont(fuente);
 	textNProcesos.setPreferredSize(new Dimension(100, 20));
 	anyadirJComponent(panelProcesos, textNProcesos, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	buttonGenerarProcesos = new JButton("Generar");
 	buttonGenerarProcesos.setFont(fuente);
 	buttonGenerarProcesos.setPreferredSize(new Dimension(100, 46));
 	anyadirJComponent(panelProcesos, buttonGenerarProcesos, 1, 0, 2, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	areaProcesos = new JTextArea();
 	areaProcesos.setFont(fuente); 	 	
 	scrollProcesos = new JScrollPane(areaProcesos);
 	scrollProcesos.setPreferredSize(new Dimension(210, 202));
 	anyadirJComponent(panelProcesos, scrollProcesos, 0, 2, 1, 2, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	
 	/* Eventos */
 	buttonGenerarProcesos.addActionListener(new ActionListener()	{ public void actionPerformed(ActionEvent e) { generarProcesos(); } });
 	   	
}

/***********************************************************************
 ***                                                                 ***
 ***   inicializarPanelInfo                                          ***
 ***                                                                 ***
 ***********************************************************************/
 	
private void inicializarPanelInfo()
{
	TitledBorder title = BorderFactory.createTitledBorder("Informaci�n de la soluci�n");
	title.setTitleFont(fuente);
	title.setTitleColor(colorForegroundWindow2);
	
	panelInfo = new JPanel();
 	panelInfo.setLayout(null); 	
 	panelInfo.setBackground(colorBackgroundWindow); 				
 	panelInfo.setBorder(title);
 	 		
 	/* Organizacion panelHill */        
  panelInfo.setLayout(gridBag); 
  
	labelAlgoritmo = new JLabel("Algoritmo:   -");
	labelAlgoritmo.setFont(fuente);
	labelAlgoritmo.setForeground(colorForegroundWindow1);
	labelAlgoritmo.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelAlgoritmo, 0, 0, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelNodosExpandidos = new JLabel("Nodos expandidos:   -");
	labelNodosExpandidos.setFont(fuente);
	labelNodosExpandidos.setForeground(colorForegroundWindow1);
	labelNodosExpandidos.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelNodosExpandidos, 0, 1, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelTiempo = new JLabel("Tiempo:   -");
	labelTiempo.setFont(fuente);
	labelTiempo.setForeground(colorForegroundWindow1);
	labelTiempo.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelTiempo, 0, 2, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
		
	labelHorasLibres = new JLabel("Horas libres:   -");
	labelHorasLibres.setFont(fuente);
	labelHorasLibres.setForeground(colorForegroundWindow1);
	labelHorasLibres.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelHorasLibres, 0, 3, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelProcesosAsignados = new JLabel("Procesos asignados:   -");
	labelProcesosAsignados.setFont(fuente);
	labelProcesosAsignados.setForeground(colorForegroundWindow1);
	labelProcesosAsignados.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcesosAsignados, 0, 4, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelProcs1 = new JLabel("Procesos de TE 1:   -");
	labelProcs1.setFont(fuente);
	labelProcs1.setForeground(colorForegroundWindow1);
	labelProcs1.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcs1, 0, 5, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
		
	labelProcs2 = new JLabel("Procesos de TE 2:   -");
	labelProcs2.setFont(fuente);
	labelProcs2.setForeground(colorForegroundWindow1);
	labelProcs2.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcs2, 0, 6, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));

	labelProcs3 = new JLabel("Procesos de TE 3:   -");
	labelProcs3.setFont(fuente);
	labelProcs3.setForeground(colorForegroundWindow1);
	labelProcs3.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcs3, 0, 7, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelProcs4 = new JLabel("Procesos de TE 4:   -");
	labelProcs4.setFont(fuente);
	labelProcs4.setForeground(colorForegroundWindow1);
	labelProcs4.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcs4, 0, 8, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
	
	labelProcs5 = new JLabel("Procesos de TE 5:   -");
	labelProcs5.setFont(fuente);
	labelProcs5.setForeground(colorForegroundWindow1);
	labelProcs5.setPreferredSize(new Dimension(190, 20));
	anyadirJComponent(panelInfo, labelProcs5, 0, 9, 1, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
		 
	areaAcciones = new JTextArea(); 	
	areaAcciones.setFont(fuente); 	
 	scrollAcciones = new JScrollPane(areaAcciones);
 	scrollAcciones.setPreferredSize(new Dimension(245, 250));
 	anyadirJComponent(panelInfo, scrollAcciones, 1, 0, 10, 1, GridBagConstraints.NONE, new Insets(3, 3, 3, 3));
 	  	
}

/***********************************************************************
 ***                                                                 ***
 ***   anyadirJComponent                                              ***
 ***                                                                 ***
 ***********************************************************************/

private void anyadirJComponent(JPanel pPanel, Component pComponent,int pX, int pY, int pGridHeight, int pGridWidth,int pFill, Insets pInsets)
{ 		
	gridConstraints.gridx = pX;
	gridConstraints.gridy = pY;
	gridConstraints.gridheight = pGridHeight;
	gridConstraints.gridwidth = pGridWidth;
	gridConstraints.fill = pFill;
	gridConstraints.insets = pInsets;
	gridBag.setConstraints(pComponent, gridConstraints);
  pPanel.add(pComponent); 
}
 	
/***********************************************************************
 ***                                                                 ***
 ***   generarProcesos                                               ***
 ***                                                                 ***
 ***********************************************************************/

private void generarProcesos()
{
	int nProcesos;	
	int procs1 = 0;
	int procs2 = 0;
	int procs3 = 0;
	int procs4 = 0;
	int procs5 = 0;
	String log = "";
	
	try
	{
		nProcesos = Integer.parseInt(textNProcesos.getText());	
	}
	catch (Exception e) 
	{
		JOptionPane.showMessageDialog(this, "Debe indicar un valor n�merico entero.");
		return;
	}
	
	if (nProcesos < 0)
	{
		JOptionPane.showMessageDialog(this, "Debe indicar un valor entero positivo.");
		return;
	}
	
	log = LSBoard.generarProcesos(nProcesos);
	
	for (int i = 0; i < nProcesos; i++)
	{
		switch (LSBoard.getTEProceso(i))
		{
			case 1:
				procs1++;
				break;
			case 2:
				procs2++;
				break;
			case 3:
				procs3++;
				break;
			case 4:
				procs4++;
				break;
			case 5:
				procs5++;
				break;
		}
	}
	
	log = log + "\nProcesos de TE 1 :   " + procs1 + "\n";
	log = log + "Procesos de TE 2 :   " + procs2 + "\n";
	log = log + "Procesos de TE 3 :   " + procs3 + "\n";
	log = log + "Procesos de TE 4 :   " + procs4 + "\n";
	log = log + "Procesos de TE 5 :   " + procs5 + "\n";
	
	areaProcesos.setText(log);	
	
	buttonHillClimbing.setEnabled(true);
	buttonSimulatedAnnealing.setEnabled(true);
}

/***********************************************************************
 ***                                                                 ***
 ***   realizarLocalSearch                                           ***
 ***                                                                 ***
 ***********************************************************************/

private void realizarLocalSearch(int pAlgoritmo)
{
	Date d1,d2;
	Calendar c1,c2;
	
	SuccessorFunction successor = null;	
	HeuristicFunction heuristico = null;
	Problem problem = null;
	Search search = null;
	
	int procs1 = 0;
	int procs2 = 0;
	int procs3 = 0;
	int procs4 = 0;
	int procs5 = 0;
	
	int steps;
	int stiter;
	int k;
	double lamb;
	
	try
	{
		steps = Integer.parseInt(textSteps.getText());
	}
	catch (Exception e) 
	{ 
		JOptionPane.showMessageDialog(this, "Debe indicar un valor n�merico entero (Steps).");
		return;
	}
	try
	{
		stiter = Integer.parseInt(textStiter.getText());
	}
	catch (Exception e) 
	{ 
		JOptionPane.showMessageDialog(this, "Debe indicar un valor n�merico entero (Stiter).");
		return;
	}
	try
	{
		k = Integer.parseInt(textK.getText());
	}
	catch (Exception e) 
	{ 
		JOptionPane.showMessageDialog(this, "Debe indicar un valor n�merico entero (K).");
		return;
	}
	try
	{
		lamb = Double.valueOf(textLamb.getText()).doubleValue();
	}
	catch (Exception e) 
	{ 
		JOptionPane.showMessageDialog(this, "Debe indicar un valor n�merico double (Lamb).");
		return;
	}
	
	try
	{	
		switch (idHeuristico)
		{
			case 1:
				heuristico = new LocalSearchHeuristicFunction();				
				break;			
			case 2:
				heuristico = new LocalSearchHeuristicFunction2();				
				break;		
			case 3:
				heuristico = new LocalSearchHeuristicFunction3();				
				break;	
			case 4:
				heuristico = new LocalSearchHeuristicFunction4();				
				break;
			case 5:
				heuristico = new LocalSearchHeuristicFunction5();				
				break;
		}
			
		switch (idSucesores)
		{
			case 1:
				successor = new LocalSearchSuccessorFunction();
				((LocalSearchSuccessorFunction)successor).setHeuristico(idHeuristico);
				break;
			case 2:
				successor = new LocalSearchSuccessorFunction2();
				((LocalSearchSuccessorFunction2)successor).setHeuristico(idHeuristico);
				break;
			case 3:
				successor = new LocalSearchSuccessorFunction3();
				((LocalSearchSuccessorFunction3)successor).setHeuristico(idHeuristico);
				break;
			case 4:
				successor = new LocalSearchSuccessorFunction4();
				((LocalSearchSuccessorFunction4)successor).setHeuristico(idHeuristico);
				break;
			case 5:
				successor = new LocalSearchSuccessorFunction5();
				((LocalSearchSuccessorFunction5)successor).setHeuristico(idHeuristico);
				break;
			case 6:
				successor = new LocalSearchSuccessorFunction6();
				((LocalSearchSuccessorFunction6)successor).setHeuristico(idHeuristico);
				break;
			case 7:
				successor = new LocalSearchSuccessorFunction7();
				((LocalSearchSuccessorFunction7)successor).setHeuristico(idHeuristico);
				break;
			case 8:
				successor = new LocalSearchSuccessorFunction8();
				((LocalSearchSuccessorFunction8)successor).setHeuristico(idHeuristico);
				break;
		}
		
		problem =  new Problem(LSBoard, successor, new LocalSearchGoalTest(), heuristico);
		
		/* Ini labels */
		labelAlgoritmo.setText("Algoritmo:   -");
		labelProcesosAsignados.setText("Procesos asignados:   -");
		labelTiempo.setText("Tiempo:   -");
		labelHorasLibres.setText("Horas libres:   -");
		
		/* Comprobamos generador a utilizar */
		if (idGenerador == 1) LSBoard.generarEIAleatorio();
		else if (idGenerador == 2) LSBoard.generarEIVacio();
		else if (idGenerador == 3) LSBoard.generarEIRango();
		else if (idGenerador == 4) LSBoard.generarEIDuracion();
		
		if (pAlgoritmo == 0)
		{
			labelAlgoritmo.setText("Algoritmo:   Hill Climbing");
			search =  new HillClimbingSearch();
		}
		else if (pAlgoritmo == 1)
		{
			labelAlgoritmo.setText("Algoritmo:   Simulated Annealing");
			search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
		}
		
		d1=new Date();
		SearchAgent agent = new SearchAgent(problem,search);
		d2=new Date();
		c1 = Calendar.getInstance();
		c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		long m = c2.getTimeInMillis() - c1.getTimeInMillis();
                LocalSearchBoard LSBoardGoal = null;
	  if (search instanceof HillClimbingSearch){
	    LSBoardGoal = (LocalSearchBoard)((HillClimbingSearch)search).getLastSearchState();
          } else {
                    LSBoardGoal = (LocalSearchBoard)((SimulatedAnnealingSearch)search).getLastSearchState();
          } 
	  
	  labelNodosExpandidos.setText("Nodos expandidos:   " + printInstrumentation(agent.getInstrumentation()));
	  labelTiempo.setText("Tiempo:   " + m + " ms");
	  labelHorasLibres.setText("Horas libres:   " + LSBoardGoal.getHorasLibres());
	  labelProcesosAsignados.setText("Procesos asignados:   " + LSBoardGoal.getProcesosSiAsignadosCount());
	  
	  for (int i = 0; i < LSBoardGoal.getProcesosSiAsignadosCount(); i++)
		{
			switch (LSBoardGoal.getTEProceso(LSBoardGoal.getIdProcesoSiAsignado(i)))
			{
				case 1:
					procs1++;
					break;
				case 2:
					procs2++;
					break;
				case 3:
					procs3++;
					break;
				case 4:
					procs4++;
					break;
				case 5:
					procs5++;
					break;
			}
		}
	  
	  labelProcs1.setText("Procesos de TE 1:   " + procs1);
	  labelProcs2.setText("Procesos de TE 2:   " + procs2);
	  labelProcs3.setText("Procesos de TE 3:   " + procs3);
	  labelProcs4.setText("Procesos de TE 4:   " + procs4);
	  labelProcs5.setText("Procesos de TE 5:   " + procs5);
	  
		printActions(agent.getActions());
			
		/* Actualizamos semana */
		actualizarSemana(LSBoardGoal);
		
	} 
	catch (Exception e) { System.out.println("Exception realizarLocalSearch: " + e.getMessage() + " : " + e.getClass()); }
}

/***********************************************************************
 ***                                                                 ***
 ***   actualizarSemana                                              ***
 ***                                                                 ***
 ***********************************************************************/

private void actualizarSemana(LocalSearchBoard pLSBoard)
{
	int[] horas = pLSBoard.getHoras();
	
	for (int i = 0; i < HORAS; i++)
	{			
		if (horas[i] == -1)
		{
			labelHora[i].setBackground(colorNoAsignado);
			labelHora[i].setText("");
		}
		else
		{
			labelHora[i].setBackground(colorSiAsignado);			
			labelHora[i].setForeground(colorSiAsignado.darker());									
			labelHora[i].setText(String.valueOf(horas[i]));
		}
	}
}

/***********************************************************************
 ***                                                                 ***
 ***   printInstrumentation                                          ***
 ***                                                                 ***
 ***********************************************************************/
	
	private String printInstrumentation(Properties pProperties) 
	{		
		Iterator keys = pProperties.keySet().iterator();		
		String pKey = (String) keys.next();
		String property = pProperties.getProperty(pKey);
		
		return property;		
	}

/***********************************************************************
 ***                                                                 ***
 ***   printActions                                                  ***
 ***                                                                 ***
 ***********************************************************************/
 
private void printActions(List actions) 
{
	areaAcciones.setText("");
	for (int i = 0; i < actions.size(); i++) 
	{
		String action = (String) actions.get(i);
		areaAcciones.append(action + "\n");			
	}
}
}