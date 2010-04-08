package IA.Electrica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/*
 * 
 import java.awt.*;
 import java.awt.geom.*;
 import javax.swing.*;
 */

//falta acabar presentacio pero sembla que no calen funcions noves...
public class ElectricaPanel extends javax.swing.JPanel {

	private static double gapX = 50.0;

	private static double gapY = 50.0;

	private static double boardSize = 300.0;

	double sepy;

	double sepx;

	boolean initp = false;

	// inita=false;
	int plano[][];

	// int vx1[],vy1[],vx2[],vy2[];
	public int np = 1;

	public int nc = 3;

	// la matriu fa np*[nc+1]

	public void paintComponent(Graphics g) {
		Font fnt = new Font("Helvetica", Font.PLAIN, 15);
		sepy = boardSize / np;
		//canvi rep np+1
		sepx = boardSize / (nc + 2);
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(fnt);
		
		//columna de comapnyies
		g2.setPaint(Color.green);
		Rectangle2D rect = new Rectangle2D.Double(gapX,gapY,sepx,boardSize);
		g2.fill(rect);

		// columna de paquets
		g2.setPaint(Color.red);
		 rect = new Rectangle2D.Double(  (1 * sepx)+gapX, gapY, sepx,boardSize );
		g2.fill(rect);
		g2.setPaint(Color.blue);
		//canvi rep
	/*//fila de consumidors
		g2.setPaint(Color.blue);
		 rect = new Rectangle2D.Double(  gapX, gapY, boardSize ,(1 * sepy)
				 );
		g2.fill(rect);
		*/
		
		if (initp) {
			for (int i = 0; i < nc + 2; i++)
				for (int j = 0; j < np; j++)
					if (plano[i][j] != -1) {
						if (i!=1  && i!=0) {
						//canvi rep if(i!=0&&j!=0)
							g2.setPaint(Color.blue); 
						     rect = new Rectangle2D.Double((i*sepx)+gapX,(j*sepy)+gapY,sepx,sepy);
						    g2.fill(rect);
						    
						}
						if (i==0 && j>0 && plano[i][j]!= plano[i][j-1]) {
							g2.setPaint(Color.black);
							Line2D line2 = new Line2D.Double((i * sepx) + gapX,(j * sepy) + gapY ,(i * sepx) + sepx+gapX, (j * sepy) + gapY);
							g2.draw(line2);
							
						}
						g2.setPaint(Color.black);
					    g2.setFont(fnt);
						g2.drawString(Integer.toString(plano[i][j]),
								(int) ((i * sepx) + gapX + sepx / 2),
								(int) ((j * sepy) + gapY + sepy / 2));

					}
		}
		// resta de columnes i files
		g2.setPaint(Color.black);
		// linies verticals
		for (int i = 0; i < nc + 3; i++) {
			Line2D line1 = new Line2D.Double((i * sepx) + gapX, gapY, (i * sepx)
					+ gapX, boardSize + gapY);
			g2.draw(line1);
		}
		// linies horitzontals
		//canvi rep np+2
		g2.setPaint(Color.black);
		Line2D line2 = new Line2D.Double((0 * sepx) + gapX,(0 * sepy) + gapY ,(0 * sepx) + sepx+gapX, (0 * sepy) + gapY);
		g2.draw(line2);
		g2.setPaint(Color.black);
		 line2 = new Line2D.Double((0 * sepx) + gapX,((np) * sepy) + gapY ,(0 * sepx) + sepx+gapX, ((np) * sepy) + gapY);
		g2.draw(line2);
		for (int i = 0; i < np + 1; i++) {
			 line2 = new Line2D.Double(sepx+gapX, (i * sepy) + gapY, boardSize
					+ gapX, (i * sepy) + gapY);
			g2.draw(line2);
		}

	}

	public void modifyPlano(int x, int y) {
		plano[x][y] = 4;
		repaint();
	}

	public void setPlano(int np, int nc, int[][] p) {
		initp = true;

		plano = new int[nc + 2][np];
		//canvi rep np+2
		this.np = np;
		this.nc = nc;

		for (int i = 0; i < nc + 2; i++)
			for (int j = 0; j < np; j++)
				//canvi rep np`+1
				plano[i][j] = p[i][j];
		repaint();
	}

	public void changeState(double x, double y) {
		int ix, iy, i, j;
		ix = (int) x;
		iy = (int) y;
		i = (int) ((ix - gapX) / sepx);
		j = (int) ((iy - gapY) / sepy);
		if (i >= 0 && i < nc && j >= 0 && j < nc) {
			if (plano[i][j] == 2)
				plano[i][j] = 3;
			else if (plano[i][j] == 3)
				plano[i][j] = 2;
			else if (plano[i][j] == 0)
				plano[i][j] = 4;
			else if (plano[i][j] == 4)
				plano[i][j] = 0;
			repaint();
		}
	}

	public int[][] getPlano() {
		return (plano);
	}

}
