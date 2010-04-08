/*
 * DrawPanel.java
 *
 * Created on 5 de agosto de 2005, 16:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package IA.mySwing;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author javier
 */
public class DrawPanel extends javax.swing.JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1390605443082598731L;
	private static double gapX=50.0;
    private static double gapY=50.0;
    private static double boardSize=300.0;
    double sep;
    boolean init=false;
    int plano[][];
    public int nc = 8;
 

    @Override
    public void paintComponent(Graphics g){
       Font fnt= new Font("Helvetica",Font.PLAIN,20);
       sep=boardSize/nc;
       super.paintComponent(g);
       Graphics2D g2 =(Graphics2D)g;
        
       g2.setPaint(Color.black);
       g2.setFont(fnt);
       g2.drawString("N",(int)(boardSize/2+gapX),(int)(gapY/2));
       g2.drawString("S",(int)(boardSize/2+gapX),(int)(gapY+ boardSize+(gapY/2)));
       g2.drawString("W",(int)(gapX/2),(int)(boardSize/2+gapY));
       g2.drawString("E",(int)(gapX+ boardSize+(gapX/2)),(int)(boardSize/2+gapY));
      
       
       g2.setPaint(Color.black);
       
       for (int i=0;i<nc+1;i++){
         Line2D line1=new Line2D.Double((i*sep)+gapX,gapY,(i*sep)+gapY,boardSize+gapY); 
         g2.draw(line1);
         Line2D line2=new Line2D.Double(gapX,(i*sep)+gapY,boardSize+gapX,(i*sep)+gapY); 
         g2.draw(line2);
       }       
      if (init) {
      for(int i=0;i<nc;i++) {
                for (int j = 0; j < nc; j++) {
                    if (plano[i][j] != 0) {
                        if (plano[i][j] == 1) {
                            g2.setPaint(Color.green);
                        }
                        if (plano[i][j] == 2) {
                            g2.setPaint(Color.blue);
                        }
                        if (plano[i][j] == 3) {
                            g2.setPaint(Color.red);
                        }
                        if (plano[i][j] == 4) {
                            g2.setPaint(Color.black);
                        }
                        Rectangle2D rect = new Rectangle2D.Double((i * sep) + gapX, (j * sep) + gapY, sep, sep);
                        g2.fill(rect);
                    }
                }
            }
      }
    }

    public void modifyPlano(int x, int y) {
     plano[x][y]=4;
     repaint();
    }

    public void setPlano(int n, int [][]p) {
     init=true;
     nc=n;
     plano= new int[nc][nc];
     
     for(int i=0;i<nc;i++) {
            for (int j = 0; j < nc; j++) {
                plano[i][j] = p[i][j];
            }
        }
     repaint();
    }

    public void changeState(double x, double y){
        int ix,iy,i,j;
        ix=(int)x;
        iy=(int)y;
        i=(int)((ix-gapX)/sep);
        j=(int)((iy-gapY)/sep);
        if (i>=0&&i<nc&&j>=0&&j<nc){
         if (plano[i][j]==1) plano[i][j]=0;
         else if (plano[i][j]==0||plano[i][j]==4) plano[i][j]=1;  
         repaint();
        }
    }
    
    public int [][] getPlano(){return(plano);}

    public void clean(){
     for(int i=0;i<nc;i++) {
            for (int j = 0; j < nc; j++) {
                if (plano[i][j] == 4) {
                    plano[i][j] = 0;
                }
            }
        }
     repaint();
    }


}
