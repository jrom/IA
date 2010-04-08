package IA.Centrals;


import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;

import javax.swing.*;

/**
 *
 * @author javier
 */
public class CentralsPanel extends javax.swing.JPanel{
	
	private static double gapX=50.0;
	private static double gapY=50.0;
	private static double boardSize=300.0;
	double sep;
	boolean init=false;
	int plano[][];
	
	private static Color[] vcolors = { Color.DARK_GRAY , Color.BLUE, Color.CYAN ,Color.ORANGE ,Color.PINK ,Color.GREEN ,Color.YELLOW ,Color.MAGENTA , Color.RED, Color.WHITE };
	
	private Vector sol;
	private int numc=15;
	private Nodo [] vNodos;
	public int amplada = 10;
	public int altura = 8;
	//public int nr = 3;
	
	
	public void paintComponent(Graphics g){
		Font fnt= new Font("Helvetica",Font.PLAIN,15);
		int max;
		if(amplada>altura){
			sep=boardSize/amplada;
			max = amplada;
		}else{
			sep=boardSize/altura;
			max=altura;
		}
		super.paintComponent(g);
		Graphics2D g2 =(Graphics2D)g;
		g2.setFont(fnt);
		
		g2.setPaint(Color.black);
		
		for (int i=0;i<amplada+1;i++){
			Line2D line1=new Line2D.Double((i*sep)+gapX,gapY,(i*sep)+gapY,boardSize -(sep*(max-altura))+gapY); 
			g2.draw(line1);
		}
		for (int i=0;i<altura+1;i++){
			Line2D line2=new Line2D.Double(gapX,(i*sep)+gapY,boardSize -(sep*(max-amplada))+gapX,(i*sep)+gapY); 
			g2.draw(line2);
		}       
		if (init) {
			for(int i=0;i<altura;i++)
				for (int j=0;j<amplada;j++) 
					if (plano[i][j]!=0) {
						if (plano[i][j]==1) g2.setPaint(Color.black);
						if (plano[i][j]==2) g2.setPaint(Color.blue);
						if (plano[i][j]==3) g2.setPaint(Color.red);
						if (plano[i][j]==4) g2.setPaint(Color.green);
						
						//Rectangle2D rect = new Rectangle2D.Double((j*sep)+gapX,(i*sep)+gapY,sep,sep);
						Ellipse2D circle = new Ellipse2D.Double((j*sep)+gapX,(i*sep)+gapY,sep,sep);
						//g2.fill(rect);
						//g2.setPaint(Color.red);
						g2.fill(circle);
					}
		}
		if(sol!=null){
			
			for (int i=0;i<this.sol.size();i++) {
				Vector v = (Vector) this.sol.get(i);
				if(v.size()!=0) {
					Nodo primero = vNodos[Integer.parseInt(v.get(0).toString())];
					for (int j=1;j<v.size();j++) {
						if(Integer.parseInt( v.get(j).toString())>i){
							int sig = Integer.parseInt( v.get(j).toString());
							Nodo siguiente = vNodos[Integer.parseInt( v.get(j).toString())];
							Line2D line1=new Line2D.Double((primero.getCordY()*sep)+gapX+sep/2,(primero.getCordX()*sep)+gapY+sep/2,(siguiente.getCordY()*sep)+gapX+sep/2,(siguiente.getCordX()*sep)+gapY+sep/2);
							
							if(i<numc&&sig<numc)g2.setColor( new Color(200,200,255-(i*sig)%120));//vcolors[i%vcolors.length]);
							if(i<numc&&sig>=numc)g2.setColor( new Color(0,255-(i*sig)%120,0));//vcolors[i%vcolors.length]);
							if(i>=numc&&sig>=numc)g2.setColor( new Color(0,0,255-(i*sig)%120));//vcolors[i%vcolors.length]);
							
							// Definim el tamany de les linies en funcio del tamany de la cuadricula
							if (max>30) g2.setStroke(new BasicStroke(1));
							else if (max>25) g2.setStroke(new BasicStroke(2));
							else if (max>15) g2.setStroke(new BasicStroke(3));
							else g2.setStroke(new BasicStroke(4));
							
							g2.draw(line1);
						}
						
					}
				}
				g2.setColor(Color.WHITE);
				if (max>30	) g2.setStroke(new BasicStroke(1));
				else if (max>20) g2.setStroke(new BasicStroke(2));
				else if (max>10) g2.setStroke(new BasicStroke(3));
				else g2.setStroke(new BasicStroke(4));
				if(i<10)g2.drawString((i+""),((float)((vNodos[i].getCordY()*sep)+gapY+sep/2)),((float)((vNodos[i].getCordX()*sep)+gapX+sep/2)));
				else g2.drawString((i+""),((float)((vNodos[i].getCordY()*sep)+gapY+sep/4)),((float)((vNodos[i].getCordX()*sep)+gapX+sep/2)));
				
				
			}
			
			
			
		}
		
	}
	
	public void modifyPlano(int x, int y) {
		plano[x][y]=4;
		repaint();
	}
	
	public void setPlano(int n, int m, int[][] p, Vector vector, Nodo[] vnod,int nc) {
		init=true;
		amplada=n;
		altura = m;
		numc = nc;
		plano= new int[altura][amplada];
		vNodos=vnod;
		
		sol = vector;
		
		for(int i=0;i<altura;i++)
			for (int j=0;j<amplada;j++) 
				plano[i][j]=p[i][j];
		repaint();
	}
	
	public void changeState(double x, double y){
		int ix,iy,i,j;
		ix=(int)x;
		iy=(int)y;
		i=(int)((ix-gapX)/sep);
		j=(int)((iy-gapY)/sep);
		if (i>=0&&i<altura&&j>=0&&j<amplada){
			if (plano[i][j]==1) plano[i][j]=0;
			else if (plano[i][j]==0||plano[i][j]==4) plano[i][j]=1;  
			repaint();
		}
	}
	
	public int [][] getPlano(){return(plano);}
	
}
