package IA.petroli;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;




import aima.search.framework.HeuristicFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.informed.HillClimbingSearch;
import aima.search.informed.SimulatedAnnealingSearch;

public class PetroliWindow extends JFrame {

    public static final int ALTURA_MAXIMA = 600;
    public static final int AMPLADA_MAXIMA = 800;
    private JPanel fondo, nord, sud, est, oest, centre, heuristicPane;
    private JTabbedPane pestanyesCentrals, pestanyesManeigEstats, pestanyesCalcular;
    public static final int HEURISTIC_A = 0;
    public static final int HEURISTIC_B = 1;
    public static final int HEURISTIC_C = 2;
    public static final int HEURISTIC_D = 3;
    public static final String[] nomsHeuristics = {"Petroli>Cost", "Ponderacio cost amb constant", "Maximitzar petroli", "Ponderar cost amb distancia mitja"};
    public static final int ESTAT_INICIAL_BUIT = 0;
    public static final int ESTAT_INICIAL_MES_PROPER = 1;
    public static final int ESTAT_INICIAL_TOT_CONECTAT = 2;
    public static final String[] nomsEstatsInicials = {"Estat inicial buit", "Estat inicial \"més proper cap avall\"", "Estat inicial \"més proper cap amunt\""};
    private int indexPestanyaSave, tipusHeuristic, tipusEstatInicial, platPetr, platDist;
    private PetroliLocalSearchBoard estatInicial;
    private boolean calcularHillClimbing;
    JTextField platDistInput, platPetrInput;
    JFileChooser fileOpenChooser, fileSaveChooser;
    File fitxerOpen, fitxerSave;
    OpenFileAction openAction;
    SaveFileAction saveAction;
    JTextField SA1, SA2, SA3, SA4, n;
    Border blackline, raisedetched, loweredetched,
            raisedbevel, loweredbevel, empty, paneEdge, bordeRaro;
    public List informacioTotal = new LinkedList();
    //botons:
    JButton generarAleatori, guardar, guardatRapid, carregar, carregarRapid, calcularHC, calcularSA, nCalcularSA, sortir;

    public PetroliWindow() {


        init();
        estatInicial = new PetroliLocalSearchBoard();


    }

    public void init() {

        setTitle("Hill-Climbing / Simulated Annealing)");
        //A border that puts 10 extra pixels at the sides and
        //bottom of each pane.
        paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        blackline = BorderFactory.createLineBorder(Color.black);
        raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        empty = BorderFactory.createEmptyBorder();

        calcularHillClimbing = true;


        fondo = new JPanel(new BorderLayout());
        add(fondo);

        pestanyesCentrals = new JTabbedPane();
        pestanyesCentrals.setBorder(paneEdge);
        pestanyesCentrals.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        nord = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sud = new JPanel(new GridLayout(1, 0));
        est = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        oest = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centre = new JPanel(new GridLayout(1, 1));

        setSize(getPreferredSize());

        JPanel botons = new JPanel(new FlowLayout());

        // Botons:
        JButton copiarTot = new JButton("Generar informe pla (format excel)");
        copiarTot.setMnemonic(KeyEvent.VK_C);
        copiarTot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String copy = generarExcel();
                mostrarInformacio.setClipboard(copy);
//				try {
//					String nom = mostrarInformacio.getNomFitxer() + ".txt";
//					PrintStream ps=new PrintStream(new FileOutputStream(nom));
//					ps.println(copy.toString());
//					ps.close();
//					JOptionPane.showMessageDialog(centre, "S'ha generat el fitxer: " + nom, "Fitxer generat", JOptionPane.INFORMATION_MESSAGE);
//				} catch (FileNotFoundException exc) {
//					System.out.println("Error al generar el fitxer");
//				}
            }
        });
        botons.add(copiarTot);

        JButton generarInformeTot = new JButton("Genera informe global");
        generarInformeTot.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                generarInforme();
            }
        });
        botons.add(generarInformeTot);

        sortir = new JButton("Sortir");
        sortir.setMnemonic(KeyEvent.VK_S);
        sortir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        botons.add(sortir);


        centre.add(pestanyesCentrals);

        sud.add(getEstatInicialPane());
        sud.add(getDadesPane());
        sud.add(getHeuristicPane());
        sud.add(getCalcularPane());

        fondo.add(sud, BorderLayout.SOUTH);
        fondo.add(centre, BorderLayout.CENTER);
        getContentPane().add(botons, BorderLayout.SOUTH);
        activarBotons(false);
    }

    @SuppressWarnings("empty-statement")
    private JTabbedPane getDadesPane() {

        //Dimension dimensioCaixa = new Dimension(((int)getSize().getWidth()/4)-10, 70);
        pestanyesManeigEstats = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        pestanyesManeigEstats.setMinimumSize(new Dimension(400, 200));
        pestanyesManeigEstats.setBorder(paneEdge);


        // PESTANYA ALEATORI:
        JPanel aleatoriPane = new JPanel(new GridLayout(3, 1));
        //aleatoriPane.setLayout(new BoxLayout(aleatoriPane, BoxLayout.Y_AXIS));
        aleatoriPane.setBorder(paneEdge);

        JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.setBorder(empty);
        platDistInput = new JTextField("10", 2);
        JLabel platDistLabel = new JLabel("plataformes distribuidores");
        temp.add(platDistInput);
        temp.add(platDistLabel);
        aleatoriPane.add(temp);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.setBorder(empty);
        platPetrInput = new JTextField("50", 2);
        JLabel platPetrLabel = new JLabel("plataformes petroliferes");
        temp.add(platPetrInput);
        temp.add(platPetrLabel);
        aleatoriPane.add(temp);

        temp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        temp.setBorder(empty);
        generarAleatori = new JButton("Generar estat inicial aleatori");
//		generarAleatori.setPreferredSize(new Dimension(dimensioCaixa.width-10,25));
        generarAleatori.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                generarEstatInicialAleatori();
            }
        });
        temp.add(generarAleatori);
        aleatoriPane.add(temp);

        // PESTANYA CARREGA D'UN FITXER
        JPanel fitxerPane = new JPanel(new GridLayout(4, 1, 5, 10));
        fitxerPane.setBorder(paneEdge);
//		fitxerPane.setPreferredSize(dimensioCaixa);

        fileOpenChooser = new JFileChooser();
        fitxerOpen = new File("initial.pet");
        openAction = new OpenFileAction(this, fileOpenChooser);
        carregar = new JButton(openAction);
//		carregar.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        fitxerPane.add(carregar);

        carregarRapid = new JButton("Càrrega ràpida!");
//		carregarRapid.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        carregarRapid.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                carregarEstatInicial();
            }
        });
        fitxerPane.add(carregarRapid);

        // PESTANYA DESAR UN FITXER:
        JPanel desarPane = new JPanel(new GridLayout(4, 1, 5, 10));
        desarPane.setBorder(paneEdge);
//		desarPane.setPreferredSize(dimensioCaixa);

        fileSaveChooser = new JFileChooser();
        fitxerSave = new File("initial.pet");
        saveAction = new SaveFileAction(this, fileSaveChooser);
        guardar = new JButton(saveAction);
//		guardar.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        desarPane.add(guardar);

        guardatRapid = new JButton("Desat ràpid!");
//		guardatRapid.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        guardatRapid.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                guardarEstatInicial();
            }
        });
        desarPane.add(guardatRapid);


        pestanyesManeigEstats.addTab("Aleatori", aleatoriPane);
        pestanyesManeigEstats.addTab("Carregar", fitxerPane);
        pestanyesManeigEstats.addTab("Desar", desarPane);
        indexPestanyaSave = pestanyesManeigEstats.getTabCount() - 1;

        return pestanyesManeigEstats;
    }

    private JPanel getEstatInicialPane() {

        JPanel estatInicialPane = new JPanel(new GridLayout(0, 1));
        estatInicialPane.setBorder(getBorderAmbLiniaAlVoltant("Estat inicial"));

        tipusEstatInicial = ESTAT_INICIAL_BUIT;
        JRadioButton estatInicialRadio1 = new JRadioButton(nomsEstatsInicials[ESTAT_INICIAL_BUIT], true);
        estatInicialRadio1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setEstatInicial(ESTAT_INICIAL_BUIT);
            }
        });
        estatInicialPane.add(estatInicialRadio1);

        JRadioButton estatInicialRadio2 = new JRadioButton(nomsEstatsInicials[ESTAT_INICIAL_MES_PROPER]);
        estatInicialRadio2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setEstatInicial(ESTAT_INICIAL_MES_PROPER);
            }
        });
        estatInicialPane.add(estatInicialRadio2);

        JRadioButton estatInicialRadio3 = new JRadioButton(nomsEstatsInicials[ESTAT_INICIAL_TOT_CONECTAT]);
        estatInicialRadio3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setEstatInicial(ESTAT_INICIAL_TOT_CONECTAT);
            }
        });
        estatInicialPane.add(estatInicialRadio3);

        ButtonGroup estatInicial = new ButtonGroup();
        estatInicial.add(estatInicialRadio1);
        estatInicial.add(estatInicialRadio2);
        estatInicial.add(estatInicialRadio3);

        return estatInicialPane;
    }

    private JPanel getHeuristicPane() {

        heuristicPane = new JPanel(new GridLayout(0, 1));
        heuristicPane.setBorder(getBorderAmbLiniaAlVoltant("Heuristic"));
//	    Dimension DimensioCaixa = new Dimension((int)getSize().getWidth()/4-10, 70);

        tipusHeuristic = HEURISTIC_A;
        JRadioButton heuristicRadio1 = new JRadioButton(nomsHeuristics[HEURISTIC_A], true);
        heuristicRadio1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setHeuristic(HEURISTIC_A);
            }
        });
        heuristicPane.add(heuristicRadio1);

        JRadioButton heuristicRadio2 = new JRadioButton(nomsHeuristics[HEURISTIC_B]);
        heuristicRadio2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setHeuristic(HEURISTIC_B);
            }
        });
        heuristicPane.add(heuristicRadio2);

        JRadioButton heuristicRadio3 = new JRadioButton(nomsHeuristics[2]);
        heuristicRadio3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setHeuristic(HEURISTIC_C);
            }
        });
        heuristicPane.add(heuristicRadio3);

        JRadioButton heuristicRadio4 = new JRadioButton(nomsHeuristics[3]);
        heuristicRadio4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setHeuristic(HEURISTIC_D);
            }
        });
        heuristicPane.add(heuristicRadio4);

        ButtonGroup grupHeuristic = new ButtonGroup();
        grupHeuristic.add(heuristicRadio1);
        grupHeuristic.add(heuristicRadio2);
        grupHeuristic.add(heuristicRadio3);
        grupHeuristic.add(heuristicRadio4);

        return heuristicPane;
    }

    private JTabbedPane getCalcularPane() {

//		Dimension dimensioCaixa = new Dimension((int)getSize().getWidth()/4-10, 70);
        pestanyesCalcular = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        pestanyesCalcular.setBorder(paneEdge);

        // PESTANYA HILL-CLIMBING
        JPanel HCPanel = new JPanel(new FlowLayout());
        HCPanel.setBorder(paneEdge);
        calcularHC = new JButton("Calcular estat final (Hill-Climbing)");
//		calcularHC.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        calcularHC.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                calcularHillClimbing = true;
                calcularEstatFinal();
            }
        });
        HCPanel.add(calcularHC);

        // PESTANYA SIMULATED-ANNEALING
        JPanel SAPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        SAPanel.setBorder(paneEdge);

        JPanel temp = new JPanel(new FlowLayout());
        SA1 = new JTextField("10000", 4);
        SA1.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        SA2 = new JTextField("100", 3);
        SA2.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        SA3 = new JTextField("20", 2);
        SA3.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        SA4 = new JTextField("0.005", 4);
        SA4.setAlignmentY(JTextField.RIGHT_ALIGNMENT);
        temp.add(SA1);
        temp.add(SA2);
        temp.add(SA3);
        temp.add(SA4);
        SAPanel.add(temp);

        temp = new JPanel(new FlowLayout());
        calcularSA = new JButton("Calcular Simulated Annealing");
//		calcularSA.setPreferredSize(new Dimension(dimensioCaixa.width-10, 25));
        calcularSA.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                calcularHillClimbing = false;
                calcularEstatFinal();
            }
        });
        temp.add(calcularSA);
        SAPanel.add(temp);

        JPanel moltsCalculsPane = new JPanel(new FlowLayout());

        n = new JTextField("10", 2);

        nCalcularSA = new JButton("N-Calcular SA");
        nCalcularSA.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                calcularHillClimbing = false;
                Integer i = new Integer(n.getText());
                if (i.intValue() > 0) {
                    for (int j = 0; j < i.intValue(); j++) {
                        calcularEstatFinal();
                    }
                }
            }
        });
        moltsCalculsPane.add(n);
        moltsCalculsPane.add(nCalcularSA);

        SAPanel.add(moltsCalculsPane);

        // FI PESTANYES
        pestanyesCalcular.add("Hill-Climbing", HCPanel);
        pestanyesCalcular.add("Simulated Annealing", SAPanel);
        return pestanyesCalcular;
    }

    private void guardarEstatInicial() {
        try {
            estatInicial.saveInitialState(fitxerSave.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "S'ha produit algun tipus d'error a l'accedir al fitxer", "Error al guardar el fitxer", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings({"static-access", "static-access"})
    private void carregarEstatInicial() {
        try {
            estatInicial = new PetroliLocalSearchBoard();
            estatInicial.readInitialState(fitxerOpen.getAbsolutePath(), tipusEstatInicial + 1);
            platDist = estatInicial.numDist;
            platPetr = estatInicial.numPetro;
            pestanyesCentrals.removeAll();
            informacioTotal = new LinkedList();
            activarBotons(true);
            loadEstatInicial(estatInicial);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "S'ha produit algun tipus d'error a l'accedir al fitxer", "Error al carregar el fitxer", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarEstatInicialAleatori() {
        Integer myInt = null;
        try {
            myInt = new Integer(platDistInput.getText());
            platDist = myInt.intValue();
            myInt = new Integer(platPetrInput.getText());
            platPetr = myInt.intValue();
//			System.out.println("Estat inicial: " + tipusEstatInicial);
            estatInicial = new PetroliLocalSearchBoard();
            estatInicial.loadRandomScenario(platDist, platPetr, true, tipusEstatInicial + 1);
            pestanyesCentrals.removeAll();
            informacioTotal = new LinkedList();
            activarBotons(true);
            loadEstatInicial(estatInicial);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El valor dels camps ha de ser un enter", "Format no valid", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void activarBotons(boolean b) {
        guardar.setEnabled(b);
        guardatRapid.setEnabled(b);
        calcularHC.setEnabled(b);
        calcularSA.setEnabled(b);
        n.setEnabled(b);
        nCalcularSA.setEnabled(b);
        pestanyesManeigEstats.setEnabledAt(indexPestanyaSave, b);
    }

    private void setEstatInicial(int tipusEstatInicial) {
        this.tipusEstatInicial = tipusEstatInicial;
//	    System.out.println("Estat inicial: " + tipusEstatInicial);
    }

    private void setHeuristic(int tipusHeuristic) {
//		System.out.println("l'heurisic seleccionat es: " + tipusHeuristic);
        this.tipusHeuristic = tipusHeuristic;
//		System.out.println("Heuristic: " + tipusHeuristic);
    }

    @SuppressWarnings("unchecked")
    private void calcularEstatFinal() {

        String cerca = null;



        // Heuristic
        System.out.println("Heuristic seleccionat: " + tipusHeuristic);
        HeuristicFunction heur;
        switch (tipusHeuristic) {
            case HEURISTIC_A:
                heur = new PetroliHeuristic();
                break;
            case HEURISTIC_B:
                heur = new PetroliHeuristicB();
                break;
            case HEURISTIC_C:
                heur = new PetroliHeuristicC();
                break;
            case HEURISTIC_D:
                heur = new PetroliHeuristicD();
                break;
            default:
                return;
        }

        Problem problem = new Problem(estatInicial, new PetroliSuccesorsA(), new PetroliGoalTest(), heur);
        // Hill-climbing/Simulated Annealing
        int steps = -1, stiter = -1, k = -1;
        double lamb = -1;
        Search search = null;
        if (calcularHillClimbing) {
            search = new HillClimbingSearch();
            cerca = "Hill-Climbing";
        } else {
            try {
                cerca = "Simulated Annealing";
                Integer myInt = new Integer(SA1.getText());
                steps = myInt.intValue();
                myInt = new Integer(SA2.getText());
                stiter = myInt.intValue();
                myInt = new Integer(SA3.getText());
                k = myInt.intValue();
                Double myDouble = new Double(SA4.getText());
                lamb = myDouble.doubleValue();
                search = new SimulatedAnnealingSearch(steps, stiter, k, lamb);
            } catch (NumberFormatException e) {
                cerca = "";
                JOptionPane.showMessageDialog(this, "El valor dels camps ha de ser un numero", "Format no valid", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            long temps = System.currentTimeMillis();

            SearchAgent agent = new SearchAgent(problem, search);


            temps = System.currentTimeMillis() - temps;
            PetroliLocalSearchBoard estatFinal = null;
            if (search instanceof HillClimbingSearch) {
                estatFinal = (PetroliLocalSearchBoard) ((HillClimbingSearch) search).getLastSearchState();
            } else {
                estatFinal = (PetroliLocalSearchBoard) ((SimulatedAnnealingSearch) search).getLastSearchState();
            }
            List passosSeguits = agent.getActions();
            InformacioEstat ief;
            int successorsGenerats = PetroliSuccesorsA.numSuccessors;
            PetroliSuccesorsA.numSuccessors = 0;
            if ("Simulated Annealing".equals(cerca)) {
                ief = new InformacioEstat(temps, estatFinal, nomsEstatsInicials[tipusEstatInicial], nomsHeuristics[tipusHeuristic], cerca, steps, stiter, k, lamb, passosSeguits, successorsGenerats);
            } else {
                ief = new InformacioEstat(temps, estatFinal, nomsEstatsInicials[tipusEstatInicial], nomsHeuristics[tipusHeuristic], cerca, passosSeguits, successorsGenerats);
            }
            loadEstatFinal(ief, temps);
            informacioTotal.add(ief);
//			System.out.println("L'estat finalisim es:");
//			for (Iterator it = agent.getActions().iterator(); it.hasNext(); )
//			{
//				System.out.println((String)it.next());
//			}
//			
//			estatFinal.printBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("static-access")
    public void generarInforme() {
        StringBuffer informe = new StringBuffer();

        informe.append("Estat inicial: " + nomsEstatsInicials[tipusEstatInicial] + "\n");
        informe.append("Plataformes distribuidores: " + estatInicial.numDist + ". Plataformes petroliferes: " + estatInicial.numPetro + "\n");
        informe.append("Petroli exportat inicial: " + mostrarInformacio.getHumanReadableNumber(estatInicial.petroliTotal) + ". Cost inicial: " + mostrarInformacio.getHumanReadableNumber(estatInicial.costTotal) + "\n");
        informe.append("\n");
        for (Iterator iterator = informacioTotal.iterator(); iterator.hasNext();) {
            InformacioEstat ie = (InformacioEstat) iterator.next();
            if ("Simulated Annealing".equals(ie.tipusCerca)) {
                informe.append("\n" + "Algoritme de cerca: " + ie.tipusCerca + "(" + ie.steps + ", " + ie.stiter + ", " + ie.k + ", " + ie.lambda + ")" + "; Temps emprat: " + mostrarInformacio.getHumanReadableNumber(ie.tempsComput) + "; Petroli Exportat: " + mostrarInformacio.getHumanReadableNumber(ie.estatFinal.petroliTotal) + "; Cost Total: " + mostrarInformacio.getHumanReadableNumber(ie.estatFinal.costTotal));
            } else {
                informe.append("\n" + "Algoritme de cerca: " + ie.tipusCerca + ". Temps emprat: " + mostrarInformacio.getHumanReadableNumber(ie.tempsComput) + " ms. Petroli Exportat: " + mostrarInformacio.getHumanReadableNumber(ie.estatFinal.petroliTotal) + "m3/s. Cost Total: " + mostrarInformacio.getHumanReadableNumber(ie.estatFinal.costTotal) + " doblones");
            }
        }
        informe.append("\n");
//		System.out.println(informe.toString());
        try {
            String nom = mostrarInformacio.getNomFitxer() + ".txt";
            PrintStream ps = new PrintStream(new FileOutputStream(nom));
            ps.println(informe.toString());
            ps.close();
            JOptionPane.showMessageDialog(this, "S'ha generat el fitxer: " + nom, "Fitxer generat", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error al generar el fitxer", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error al generar el fitxer: " + e.getMessage());
        }
    }

    @SuppressWarnings("static-access")
    public String generarExcel() {
        StringBuffer informe = new StringBuffer();

        informe.append("Estat inicial:	" + nomsEstatsInicials[tipusEstatInicial] + "\n");
        informe.append("Plataformes distribuidores:	" + estatInicial.numDist + "\n");
        informe.append("Plataformes petroliferes:	" + estatInicial.numPetro + "\n\n");
        informe.append("Petroli exportat inicial:	" + mostrarInformacio.getHumanReadableNumber(estatInicial.petroliTotal) + "\nCost inicial:	" + mostrarInformacio.getHumanReadableNumber(estatInicial.costTotal) + "\n");
        informe.append("\n");
        if (calcularHillClimbing) {
            informe.append("\n" + "Heuristic" + "	Tipus cerca" + "	temps emprat (ms)" + "	Petroli Exportat" + "	Cost Total" + "	Temps emprat(ms)	Successors generats");
        } else {
            informe.append("\n" + "#pestanya" + "	Heuristic" + "	Tipus cerca" + "	steps" + "	stiter" + "	k" + "	lambda" + "	temps emprat (ms)" + "	Petroli Exportat" + "	Cost Total" + "	Successors generats");
        }
        int i = 1;
        for (Iterator iterator = informacioTotal.iterator(); iterator.hasNext();) {
            InformacioEstat ie = (InformacioEstat) iterator.next();
            if ("Simulated Annealing".equals(ie.tipusCerca)) {
                informe.append("\n" + i + "	" + ie.tipusHeuristic + "	" + ie.tipusCerca + "	" + ie.steps + "	" + ie.stiter + "	" + ie.k + "	" + ie.lambda + "	" + ie.tempsComput + "	" + ie.estatFinal.petroliTotal + "	" + ie.estatFinal.costTotal + "	" + ie.numSuccessors);
            } else {
                informe.append("\n" + i + "	" + ie.tipusHeuristic + "	" + ie.tipusCerca + "	" + ie.tempsComput + "	" + ie.estatFinal.petroliTotal + "	" + ie.estatFinal.costTotal + "	" + ie.numSuccessors);
            }
            i++;
        }
        return informe.toString();
    }

    public Dimension getPreferredSize() {
        Dimension screenSize = getToolkit().getScreenSize();
        screenSize.height -= 30;
        return screenSize;
    }

    public void loadEstatInicial(PetroliLocalSearchBoard plsb) {

        InformacioEstat ie = new InformacioEstat(0, plsb, nomsEstatsInicials[tipusEstatInicial], "", "", null, 0);
        UnEstat paneEstatInicial = new UnEstat(ie);
        pestanyesCentrals.addTab(nomsEstatsInicials[tipusEstatInicial], paneEstatInicial);
//		estatInicial = plsb;
//		UnEstat paneEstatInicial = new UnEstat(plsb);
//		paneEstatInicial.setBorder(paneEdge);
//		pestanyesCentrals.addTab("Estat inicial", paneEstatInicial);
//		setSize(getPreferredSize());
    }

    public void loadEstatFinal(InformacioEstat ief, double temps) {
        UnEstat paneEstatFinal = new UnEstat(ief);
        paneEstatFinal.setTemps(temps);
        pestanyesCentrals.addTab((pestanyesCentrals.getTabCount()) + ".-" + nomsHeuristics[tipusHeuristic], paneEstatFinal);
//		setSize(getPreferredSize());
    }

    private Border getBorderAmbLiniaAlVoltant(String titol) {
        return BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(titol), BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    // This action creates and shows a modal open-file dialog.
    class OpenFileAction extends AbstractAction {

        JFrame frame;
        JFileChooser chooser;

        OpenFileAction(JFrame frame, JFileChooser chooser) {
            super("Sel.leccionar un fitxer...");
            this.chooser = chooser;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent evt) {
            // Show dialog; this method does not return until dialog is closed
            chooser.showOpenDialog(frame);

            // Get the selected file
            fitxerOpen = chooser.getSelectedFile();
            carregarEstatInicial();
        }
    }

    // This action creates and shows a modal save-file dialog.
    class SaveFileAction extends AbstractAction {

        JFileChooser chooser;
        JFrame frame;

        SaveFileAction(JFrame frame, JFileChooser chooser) {
            super("Desar...");
            this.chooser = chooser;
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent evt) {
            // Show dialog; this method does not return until dialog is closed
            chooser.showSaveDialog(frame);

            // Get the selected file
            fitxerSave = chooser.getSelectedFile();
            guardarEstatInicial();
        }
    }
}

class UnEstat extends JPanel {

    printPetroli dibuix;
    JLabel informacions[];
    JTable informacio;
    JPanel fondo, nord, sud, est, oest, centre;
    Border paneEdge;
    JLabel petroliLabel, costLabel, tempsValue, tempsLabel;
    InformacioEstat ie;
    mostrarInformacio infoPane;

    public UnEstat(InformacioEstat ie2) {

//		this.setLayout(new FlowLayout(FlowLayout.CENTER));
        init(ie2);
    }

    public String getText() {
        String informacio = infoPane.getText();
        return informacio;
    }

    private void init(InformacioEstat ie2) {

        this.ie = ie2;
//		System.out.println("ie2.estatFinal == null ? " + ie2.estatFinal == null );

        this.setLayout(new BorderLayout());


        paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);

        nord = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sud = new JPanel(new GridLayout(1, 0, 10, 0));
        est = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        oest = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centre = new JPanel(new GridLayout(1, 1));

        sud.setBorder(paneEdge);

        add(nord, BorderLayout.NORTH);
        add(sud, BorderLayout.SOUTH);
        add(est, BorderLayout.EAST);
        add(oest, BorderLayout.WEST);
        add(centre, BorderLayout.CENTER);

        loadEstat(ie2.estatFinal, ie2.numSuccessors);
    }

    public void loadEstat(PetroliLocalSearchBoard plsb, int numSuccessors) {


        // GRAFIC
        dibuix = new printPetroli(plsb);
        centre.add(dibuix);


//		// RESULTAT
        JPanel resultats = new JPanel(new GridLayout(2, 2));
        resultats.setAlignmentY(Container.RIGHT_ALIGNMENT);
        resultats.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        petroliLabel = new JLabel("Petroli exportat: ");
        JLabel petroliValue = new JLabel("" + mostrarInformacio.getHumanReadableNumber(plsb.petroliTotal));
        petroliValue.setForeground(new Color(0, 200, 0));
        costLabel = new JLabel("Cost total: ");
        JLabel costValue = new JLabel("" + mostrarInformacio.getHumanReadableNumber(plsb.costTotal));
        costValue.setForeground(new Color(200, 0, 0));
        tempsLabel = new JLabel("Temps emprat: ");
        tempsLabel.setVisible(false);
        tempsValue = new JLabel();
        tempsValue.setFont(new Font("Arial", Font.BOLD, 14));
        tempsValue.setVisible(false);


        JLabel successorsLabel = new JLabel("Successors generats");
        JLabel successorsValue = new JLabel("" + mostrarInformacio.getHumanReadableNumber(numSuccessors));
        if (numSuccessors == 0) {
            successorsLabel.setVisible(false);
            successorsValue.setVisible(false);
        } else {
            successorsLabel.setVisible(true);
            successorsValue.setVisible(true);
        }

        nord.add(petroliLabel);
        nord.add(petroliValue);
        nord.add(costLabel);
        nord.add(costValue);
        nord.add(tempsLabel);
        nord.add(tempsValue);
        nord.add(successorsLabel);
        nord.add(successorsValue);



        JButton veureInformacio = new JButton("Mostrar tota la informació");
        veureInformacio.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JFrame informacio = new JFrame("Informacio de l'estat");
                infoPane = new mostrarInformacio(ie);
                informacio.add(infoPane);
                Dimension screenSize = getToolkit().getScreenSize();
                //informacio.setSize(screenSize);
                informacio.setVisible(true);
                informacio.setSize(informacio.getPreferredSize());
            }
        });
        sud.add(veureInformacio);

        JButton generarInforme = new JButton("Generar Informe");
        generarInforme.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            }
        });
        sud.add(generarInforme);

        JButton generarImatge = new JButton("Desar imatge");
        generarImatge.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                generarImatge();
            }
        });
        sud.add(generarImatge);

    }

    public void generarImatge() {
//		File imatge = new File(mostrarInformacio.getNomFitxer() + ".jpg");
//		try {
//			BufferedImage img = new BufferedImage(dibuix.getSize().width, dibuix.getSize().height, BufferedImage.TYPE_INT_RGB);
//			Graphics2D g2 = img.createGraphics();
//			g2.translate(-dibuix.getX(), -dibuix.getY());
//			paint(g2);
//			g2.dispose();
//			ImageIO.write(img, "jpg", imatge);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

        dibuix.guardarImatge();
    }

    public void setTemps(double temps) {
        tempsValue.setText("" + mostrarInformacio.getHumanReadableNumber(temps) + " ms");

        tempsLabel.setVisible(true);
        tempsValue.setVisible(true);
    }
}

class InformacioEstat {

    /**
     * Variables d'informacio de l'estat final
     */
    String tipusEstatInicial, tipusHeuristic, tipusCerca;
    int steps, stiter, k, numSuccessors;
    double lambda;
    PetroliLocalSearchBoard estatFinal;
    long tempsComput;
    List passosSeguits;

    /**
     * Inicialitzacio de la classe per l'algoritme de cerca Hill-Climbing
     * 
     * @param tempsComput
     * @param estatFinal
     * @param tipusEstatInicial
     * @param heuristic
     * @param cerca
     * @param passosSeguits 
     */
    public InformacioEstat(long tempsComput, PetroliLocalSearchBoard estatFinal, String tipusEstatInicial, String heuristic, String cerca, List passosSeguits, int numSuccessors) {
        this.tempsComput = tempsComput;
        this.tipusEstatInicial = tipusEstatInicial;
        this.estatFinal = new PetroliLocalSearchBoard(estatFinal);
        this.tipusHeuristic = heuristic;
        this.tipusCerca = cerca;
        this.passosSeguits = passosSeguits;
        this.numSuccessors = numSuccessors;

        this.steps = -1;
        this.stiter = -1;
        this.k = -1;
        this.lambda = -1;

    }

    /**
     * Inicialitzacio de la classe per l'algoritme de cerca Simulated Annealing
     * @param tempsComput
     * @param estatFinal
     * @param tipusEstatInicial
     * @param heuristic
     * @param cerca
     * @param steps
     * @param stiter
     * @param k
     * @param lambda
     * @param passosSeguits
     * @param successorsGenerats
     */
    public InformacioEstat(long tempsComput, PetroliLocalSearchBoard estatFinal, String tipusEstatInicial, String heuristic, String cerca, int steps, int stiter, int k, double lambda, List passosSeguits, int successorsGenerats) {
        this.tempsComput = tempsComput;
        this.tipusEstatInicial = tipusEstatInicial;
        this.estatFinal = new PetroliLocalSearchBoard(estatFinal);
        this.tipusHeuristic = heuristic;
        this.tipusCerca = cerca;
        this.passosSeguits = passosSeguits;
        this.numSuccessors = successorsGenerats;

        this.steps = steps;
        this.stiter = stiter;
        this.k = k;
        this.lambda = lambda;

    }
}

class mostrarInformacio extends JPanel {

    JPanel nord, sud, est, oest, centre;
    Border paneEdge;
    JTable distTable, petrolTable;
    DefaultTableModel modelPetr, modelDist;
    JTabbedPane pestanyaCentral;
    InformacioEstat ief;

    public mostrarInformacio(InformacioEstat ief) {

        this.ief = ief;

        setSize(getPreferredSize());

        paneEdge = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(paneEdge);
        this.setLayout(new BorderLayout());

        nord = new JPanel(new GridLayout(1, 2));
        sud = new JPanel(new GridLayout(1, 0));
        est = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        oest = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centre = new JPanel(new GridLayout(1, 1));

        this.setLayout(new BorderLayout());

        this.add(nord, BorderLayout.NORTH);
        this.add(sud, BorderLayout.SOUTH);
        this.add(est, BorderLayout.EAST);
        this.add(oest, BorderLayout.WEST);
        this.add(centre, BorderLayout.CENTER);

        pestanyaCentral = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);


        JPanel resultats = new JPanel(new GridLayout(7, 2));
        resultats.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        Font f = new Font("Arial", Font.BOLD, 14);

        JLabel estatInicialLabel = new JLabel("Estat inicial");
        JLabel estatInicialValue = new JLabel(ief.tipusEstatInicial);
        estatInicialValue.setFont(f);
        resultats.add(estatInicialLabel);
        resultats.add(estatInicialValue);

        JLabel tipusCercaLabel = new JLabel("Tipus de cerca");
        JLabel tipusCercaValue = new JLabel(ief.tipusCerca);
        tipusCercaValue.setFont(f);
        resultats.add(tipusCercaLabel);
        resultats.add(tipusCercaValue);

        JLabel tipusHeuristicLabel = new JLabel("Heuristic sel.leccionat");
        JLabel tipusHeuristicValue = new JLabel(ief.tipusHeuristic);
        tipusHeuristicValue.setFont(f);
        resultats.add(tipusCercaLabel);
        resultats.add(tipusCercaValue);

        JLabel petroliLabel = new JLabel("Petroli Exportat");
        JLabel petroliValue = new JLabel(getHumanReadableNumber(ief.estatFinal.petroliTotal) + " m3/s");
        petroliValue.setForeground(new Color(0, 200, 0));
        resultats.add(petroliLabel);
        resultats.add(petroliValue);

        JLabel costLabel = new JLabel("Cost total");
        JLabel costValue = new JLabel(getHumanReadableNumber(ief.estatFinal.costTotal) + " doblones");
        costValue.setForeground(new Color(200, 0, 0));
        resultats.add(costLabel);
        resultats.add(costValue);

        JLabel tempsLabel = new JLabel("Temps emprat");
        JLabel tempsValue = new JLabel("" + getHumanReadableNumber(ief.tempsComput) + " ms");
        tempsValue.setFont(f);
        resultats.add(tempsLabel);
        resultats.add(tempsValue);


        JLabel successorsLabel = new JLabel("Successors generats");
        JLabel successorsValue = new JLabel("" + mostrarInformacio.getHumanReadableNumber(ief.numSuccessors));
        successorsValue.setFont(f);
        resultats.add(successorsLabel);
        resultats.add(successorsValue);
        if (ief.numSuccessors == 0) {
            successorsLabel.setVisible(false);
            successorsValue.setVisible(false);
        } else {
            successorsLabel.setVisible(true);
            successorsValue.setVisible(true);
        }
        nord.add(resultats);

        if (ief.steps > 0) {
            JPanel resultatsSAPanel = new JPanel(new GridLayout(6, 2));

            JLabel stepsLabel = new JLabel("Steps");
            JLabel stepsValue = new JLabel("" + getHumanReadableNumber(ief.steps));
            JLabel stiterLabel = new JLabel("Stiter");
            JLabel stiterValue = new JLabel("" + getHumanReadableNumber(ief.stiter));
            JLabel kLabel = new JLabel("k");
            JLabel kValue = new JLabel("" + getHumanReadableNumber(ief.k));
            JLabel lambdaLabel = new JLabel("lambda");
            JLabel lambdaValue = new JLabel("" + getHumanReadableNumber(ief.lambda));

            stepsValue.setAlignmentY(JLabel.RIGHT_ALIGNMENT);
            stiterValue.setAlignmentY(JLabel.RIGHT_ALIGNMENT);
            kValue.setAlignmentY(JLabel.RIGHT_ALIGNMENT);
            lambdaValue.setAlignmentY(JLabel.RIGHT_ALIGNMENT);

            stepsValue.setFont(f);
            stiterValue.setFont(f);
            kValue.setFont(f);
            lambdaValue.setFont(f);

            resultatsSAPanel.add(stepsLabel);
            resultatsSAPanel.add(stepsValue);
            resultatsSAPanel.add(stiterLabel);
            resultatsSAPanel.add(stiterValue);
            resultatsSAPanel.add(kLabel);
            resultatsSAPanel.add(kValue);
            resultatsSAPanel.add(lambdaLabel);
            resultatsSAPanel.add(lambdaValue);

            nord.add(resultatsSAPanel);

        }



        // INFORMACIO

        //PESTANYA PASSOS ALGORITME
        int afegir = 0, disminuir = 0, intercanviar = 0, treure = 0, n = 0;
        if (ief.passosSeguits != null) {
            JPanel passosPane = new JPanel();
            passosPane.setLayout(new BoxLayout(passosPane, BoxLayout.Y_AXIS));

            for (Iterator iterator = ief.passosSeguits.iterator(); iterator.hasNext();) {
                JLabel unPas = new JLabel((String) iterator.next());
                if (unPas.getText().startsWith("connectar")) {
                    afegir++;
                } else if (unPas.getText().startsWith("disminuirBombeig")) {
                    disminuir++;
                } else if (unPas.getText().startsWith("intercanviar")) {
                    intercanviar++;
                } else if (unPas.getText().startsWith("treure")) {
                    treure++;
                }
                passosPane.add(unPas);
                n++;
            }
            JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
            JLabel numPassos = new JLabel("S'han realitzat " + n + " operacions");
            double temporal = (double) afegir / n * 100;
            JLabel afegirPerc = new JLabel("Connectar: " + (int) temporal + "%");
            temporal = (double) disminuir / n * 100;
            JLabel disminuirPerc = new JLabel("Disminuir: " + (int) temporal + "%");
            temporal = (double) intercanviar / n * 100;
            JLabel intercanviarPerc = new JLabel("Intercanviar: " + (int) temporal + "%");
            temporal = (double) treure / n * 100;
            JLabel treurePerc = new JLabel("Treure: " + (int) temporal + "%");
            temp.add(numPassos);
            temp.add(afegirPerc);
            temp.add(disminuirPerc);
            temp.add(intercanviarPerc);
            temp.add(treurePerc);

            JScrollPane escrol = new JScrollPane(passosPane);
            temp.add(escrol);
            pestanyaCentral.addTab("Passos seguits", temp);

        }

        // PESTANYA ESTACIONS
        Plataforma[] plataformes = ief.estatFinal.plataformes;
        PlataformaConectada[] connexions = ief.estatFinal.connexions;
        int numDist = ief.estatFinal.numDist;
        int numPetr = ief.estatFinal.numPetro;

        JPanel chicha = new JPanel(new FlowLayout());
        //chicha.setLayout(new BoxLayout(chicha, BoxLayout.PAGE_AXIS));


        modelDist = new DefaultTableModel();
        distTable = new JTable(modelDist);
        modelDist.addColumn("Id. (posicio costa)");
        modelDist.addColumn("Estacio de bombeig");
        modelDist.addColumn("Petroli bombejat");
        modelDist.addColumn("Cost");
        for (int i = 0; i < numDist; i++) {
            JPanel unaDistPane = new JPanel();
            unaDistPane.setLayout(new BoxLayout(unaDistPane, BoxLayout.PAGE_AXIS));
            unaDistPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            JPanel temp = new JPanel(new FlowLayout());
            Plataforma plataforma = plataformes[i];
            JLabel distLabel = new JLabel("Distribuidora: " + i + " (" + plataforma.getX() + ")");
            JLabel petroliExportat = new JLabel("" + connexions[i].getPetBombejat());
            petroliExportat.setForeground(new Color(0, 200, 0));
            JLabel cost = new JLabel("" + connexions[i].getCost() + connexions[i].getCostTuberia());
            cost.setForeground(new Color(200, 0, 0));

            temp.add(distLabel);
            temp.add(petroliExportat);
            temp.add(cost);
            unaDistPane.add(temp);
            //chicha.add(unaDistPane);

            String[] unaFila = new String[4];
            unaFila[0] = i + " (" + plataforma.getX() + "," + plataforma.getY() + ")";
            unaFila[1] = "" + connexions[i].getBombeig();
            unaFila[2] = "" + connexions[i].getPetBombejat();
            unaFila[3] = "" + getHumanReadableNumber((connexions[i].getCost() + connexions[i].getCostTuberia()));
            modelDist.addRow(unaFila);
        }
        JScrollPane scrollPane = new JScrollPane(distTable);
        chicha.add(scrollPane);

        modelPetr = new DefaultTableModel();
        petrolTable = new JTable(modelPetr);
        modelPetr.addColumn("Id. (x,y)");
        modelPetr.addColumn("Producció");
        modelPetr.addColumn("Destinació");
        modelPetr.addColumn("Estació de bombeig");
        modelPetr.addColumn("Petroli bombejat");
        modelPetr.addColumn("Cost tuberia");
        for (int j = numDist; j < connexions.length; j++) {
            PlataformaConectada plataformaConectada = connexions[j];
//				if (plataformaConectada.desembocaA(plataforma distribuidora i))
            String[] unaFila = new String[6];
            unaFila[0] = j + " (" + plataformes[j].getX() + "," + plataformes[j].getY() + ")";
            unaFila[1] = "" + ((PlataformaPetrolifera) plataformes[j]).petroli;
            unaFila[2] = "" + plataformaConectada.getSortida();
            unaFila[3] = "" + plataformaConectada.getBombeig();
            unaFila[4] = "" + plataformaConectada.getPetBombejat();
            unaFila[5] = "" + getHumanReadableNumber(plataformaConectada.getCostTuberia());
            modelPetr.addRow(unaFila);
        }
        petrolTable.setEnabled(false);

        scrollPane = new JScrollPane(petrolTable);
        chicha.add(scrollPane);


        pestanyaCentral.addTab("Estacions", chicha);

        centre.add(pestanyaCentral);

        JButton addToClipBoard = new JButton("Copy");
        addToClipBoard.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                copiarAlClipboard();
            }
        });
        sud.add(addToClipBoard);


    }

    public String getText() {
        String text = "";
        return text;
    }

    private void copiarAlClipboard() {
    }
    // If a string is on the system clipboard, this method returns it;
    // otherwise it returns null.

    public static String getClipboard() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);

        try {
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String text = (String) t.getTransferData(DataFlavor.stringFlavor);
                return text;
            }
        } catch (UnsupportedFlavorException e) {
        } catch (IOException e) {
        }
        return null;
    }

    // This method writes a string to the system clipboard.
    // otherwise it returns null.	
    public static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    public static String getHumanReadableNumber(double original) {
        String resultat = "";
        Double myDoub = new Double(original);
        String originalString = myDoub.toString();
        int partEntera = (int) original;

        double partDecimal = original / partEntera;
        String partDecimalString = "";
        if (partDecimal > 1) {
            partDecimalString = ",";
            partDecimalString += originalString.substring(originalString.lastIndexOf(".") + 1, originalString.length());
        }

        String partEnteraString = "" + partEntera;
        char[] xifres = partEnteraString.toCharArray();
        int contador = 0;
        partEnteraString = "";
        for (int i = xifres.length - 1; i >= 0; i--) {
            if (contador == 3) {
                partEnteraString = "." + partEnteraString;
                contador = 0;
            }
            partEnteraString = xifres[i] + partEnteraString;
            contador++;
        }

        resultat = partEnteraString + partDecimalString;
        if (resultat.lastIndexOf("E") != -1) {
            return myDoub.toString();
        }
        return resultat;
    }

    public static String getNomFitxer() {
        String nomFitxer = "";

        Date ara = new Date();

        nomFitxer = DateFormat.getInstance().format(ara);
        nomFitxer = nomFitxer.replaceAll(":", ".");
        return nomFitxer.replaceAll("/", "-");
    }
}


