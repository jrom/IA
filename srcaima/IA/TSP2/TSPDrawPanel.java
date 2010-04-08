/*
 * DrawPanel.java
 *
 * Created on 5 de agosto de 2005, 16:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */
package IA.TSP2;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author javier
 */
//public class TSPDrawPanel extends javax.swing.JPanel {
public class TSPDrawPanel extends Canvas {

    /**
     * 
     */
    private static final long serialVersionUID = 1390605443082598731L;
    private static int gapX = 30;
    private static int gapY = 30;
    private static int boardSize = 200;
    int sep;
    boolean init = false;
    int plano[][];
    int city[][];
    int path[];
    public int gridsize = 20;
    public int nc = 8;
    Image im;
    Graphics g2;

    @Override
    public void addNotify() {
        super.addNotify();
        im = createImage(boardSize + 100, boardSize + 100);
        g2 = im.getGraphics();


    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);

        Font fnt = new Font("Helvetica", Font.PLAIN, 16);
        sep = (boardSize / gridsize);
        //     Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.setFont(fnt);


        if (init) {
            for (int i = 0; i < gridsize; i++) {
                for (int j = 0; j < gridsize; j++) {
                    if (plano[i][j] != 0) {
                        g2.setColor(Color.green);

                        g2.fillOval((i * sep) + gapX, (j * sep) + gapY, sep, sep);
                        g2.setColor(Color.black);
                        g2.setFont(fnt);
                        g2.drawString(String.valueOf(plano[i][j]), ((i * sep) + gapX) + 5, ((j * sep) + gapY) + 10);
                    }
                }
            }
            g2.setColor(Color.BLACK);
            for (int i = 0; i < nc - 1; i++) {
                g2.drawLine((city[path[i]][0] * sep) + gapX + 7, (city[path[i]][1] * sep) + gapY + 7, (city[path[i + 1]][0] * sep) + gapX + 7, (city[path[i + 1]][1] * sep) + gapY + 7);

            }
            g2.drawLine((city[path[nc - 1]][0] * sep) + gapX + 7, (city[path[nc - 1]][1] * sep) + gapY + 7, (city[path[0]][0] * sep) + gapX + 7, (city[path[0]][1] * sep) + gapY + 7);

        }

        g.drawImage(im, 0, 0, this);


    }

    public void setPlano(int n, int[][] p, int c[]) {
        init = true;
        nc = n;
        plano = new int[gridsize][gridsize];
        path = new int[nc];
        city = new int[nc][2];

        city = p;
        path = c;

        for (int i = 0; i < nc; i++) {
            plano[city[i][0]][city[i][1]] = i + 1;
        }
        actualiza();
    }

    public void changeState(int i, int j) {
        init = true;
        int t;

        t = path[i];
        path[i] = path[j];
        path[j] = t;
        actualiza();
    }

    public int[][] getPlano() {
        return (plano);
    }

    public void clean() {
        for (int i = 0; i < gridsize; i++) {
            for (int j = 0; j < gridsize; j++) {
                plano[i][j] = 0;
            }
        }
        repaint();
    }

    private void actualiza() {

        Graphics g = this.getGraphics();
     //   g2 = im.getGraphics();
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(im, 0, 0, this);
        paint(g);

        Font fnt = new Font("Helvetica", Font.PLAIN, 16);
        sep = (boardSize / gridsize);

        g2.setColor(Color.black);
        g2.setFont(fnt);


        g2.setColor(Color.BLACK);
        for (int i = 0; i < nc - 1; i++) {
            g2.drawLine((city[path[i]][0] * sep) + gapX + 7, (city[path[i]][1] * sep) + gapY + 7, (city[path[i + 1]][0] * sep) + gapX + 7, (city[path[i + 1]][1] * sep) + gapY + 7);

        }
        g2.drawLine((city[path[nc - 1]][0] * sep) + gapX + 7, (city[path[nc - 1]][1] * sep) + gapY + 7, (city[path[0]][0] * sep) + gapX + 7, (city[path[0]][1] * sep) + gapY + 7);



        g.drawImage(im, 0, 0, this);

    }

    @Override
    public void update(Graphics g) {
        super.update(g);
        g2.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(im, 0, 0, this);
        paint(g);
    }
}
