package IA.petroli;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class printPetroli extends Canvas {
	
	private PetroliLocalSearchBoard plsb;
	
	private static int MARGES = 50;
	
	public printPetroli(PetroliLocalSearchBoard plsb) {
		setBackground(Color.WHITE);
		Font fontPetita = new Font("Arial", Font.PLAIN, 10);
		this.setFont(fontPetita);
		this.plsb = plsb;
	}
	
	public Dimension getPreferredSize() 
	{
		int max_x = 1024;
		int max_y = 600;
		
		int x = max_x;
		int y = max_y;
		if (PetroliUtils.MAX_EIX_X > PetroliUtils.MAX_EIX_Y) y = PetroliUtils.MAX_EIX_Y*x/PetroliUtils.MAX_EIX_X;
		else	x = PetroliUtils.MAX_EIX_X*y/PetroliUtils.MAX_EIX_Y;
		return new Dimension( x+MARGES*2, y+MARGES*2);


	}
	
	
	/**
	 * Pinta un punt
	 * @param x
	 * @param y
	 */
	public void pintarPunt(double x, double y, Color c) {
		
		int diametre = 10;
		Graphics g = this.getGraphics();
		
		g.setColor(c);
		g.fillOval(situarX(x)-diametre/2, situarY(y)-diametre/2, diametre, diametre);
		//g.setColor(Color.BLACK);
		//g.drawOval(situarX(x), situarY(y), 10, 10);
		
	}
	
	/**
	 * Pinta una linia segons el sistema de coordenades d'aplicacio
	 * @param x
	 * @param y
	 */
	public void pintarLinia(double x1, double y1, double x2, double y2, Color c) {
		
		Graphics g = this.getGraphics();
		g.setColor(c);
		g.drawLine(situarX(x1), situarY(y1), situarX(x2), situarY(y2));
	}
	
	/**
	 * Pinta la "tuberia" que conecta la plataforma origen amb la seva destinacio
	 * i en posa la quantitat de petroli que hi passa per alla
	 * @param origen
	 * @param desti
	 */
	private void pintarTuberia(int plataformaOrigen) {
		if (plsb.plataformes[plataformaOrigen].isPlataformaPetrolifera()) 
		{
			PlataformaPetrolifera temp = (PlataformaPetrolifera)plsb.plataformes[plataformaOrigen];
			PlataformaConectada tempCon = plsb.connexions[plataformaOrigen];
			
			if (tempCon.isConnected()){
				pintarLinia(temp.getX(), temp.getY(), plsb.plataformes[tempCon.getSortida()].getX(),plsb.plataformes[tempCon.getSortida()].getY(), Color.RED);
//				double x = situarX( temp.getX() + plsb.plataformes[tempCon.getSortida()].getX() ) / 2;
//				double y = situarY( temp.getY() + plsb.plataformes[tempCon.getSortida()].getY() ) / 2;
//				Graphics g = getGraphics();
//				g.setColor(Color.BLACK);
//				g.drawString(plsb.connexions[plataformaOrigen].getPetBombejat() + "", (int)x, (int)y);
			}
		}
	}
	
	
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		// pintar marc
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getSize().width-1, getSize().height-1);
		
		// pintar la graella
		pintarGraella();
		
		//pintar tuberies
		for (int i = 0; i < plsb.plataformes.length; i++) {
			if (plsb.plataformes[i].isPlataformaPetrolifera()){
				//pintem tuberia on desemboca
				PlataformaPetrolifera temp = (PlataformaPetrolifera) plsb.plataformes[i];
				if (plsb.connexions[i].isConnected()) //pintarLinia(temp.getX(), temp.getY(), temp.getPlataformaDesti().getX(), temp.getPlataformaDesti().getY(), Color.RED);
					pintarTuberia(i);
			}
		}
		
		//pintar plataformes
		for (int i = 0; i < plsb.plataformes.length; i++) {
			if (plsb.plataformes[i].isPlataformaDistribuidora()) {
				g.setColor(Color.GREEN);
			} else if (plsb.plataformes[i].isPlataformaPetrolifera()){
				g.setColor(Color.BLUE);
			}
			pintarPunt(plsb.plataformes[i].getX(), plsb.plataformes[i].getY(), g.getColor());
		}

		//pintem els id's de cada plataforma
		for (int i = 0; i < plsb.plataformes.length; i++) {
			g.setColor(Color.BLACK);
			String infoPlataforma = "[" + i + "]";
//			if (plataformes[i].isPlataformaPetrolifera()) {
//				PlataformaPetrolifera temp = (PlataformaPetrolifera) plataformes[i];
//				infoPlataforma += "(" + temp.petroliEntrada + "->" + temp.petroliProduit + "->" + temp.getPetroliExportat() + ")";
//			} else {
//				infoPlataforma += "(->" + plataformes[i].getPetroliExportat() + " | cost: " + plataformes[i].getCostTotal() + ")";
//			}
			g.drawString(infoPlataforma, situarX(plsb.plataformes[i].getX()), situarY(plsb.plataformes[i].getY())+20);			
		}
		
	}
	
	public void pintarGraella() {
		//pintar graella
		Graphics g = this.getGraphics();
		Color c = new Color(230,230,230);
		int incrementX = (int) PetroliUtils.MAX_EIX_X/10;
		int incrementY = (int) PetroliUtils.MAX_EIX_Y/10;
		incrementX = 1;
		incrementY = 1;
		// linies verticals
		for (int i=0; i<=PetroliUtils.MAX_EIX_X; i++) {
			pintarLinia(i*incrementX, 0, i*incrementX, PetroliUtils.MAX_EIX_Y, c);
		}
		// linies horitzontals
		for (int i=0; i<=PetroliUtils.MAX_EIX_Y; i++) {
			pintarLinia(0, i*incrementY, PetroliUtils.MAX_EIX_X, i*incrementY, c);
		}
	}
	
	private int situarX(double coordenadaX) {
		Dimension tamany = getSize();
		double Xwindow = tamany.getWidth()-MARGES*2;
		double minuend = coordenadaX * Xwindow;
		int resultat = (int) (MARGES + minuend/PetroliUtils.MAX_EIX_X) ;
		return resultat;
	}
	
	private int situarY(double coordenadaY) {
		Dimension tamany = getSize();
		double Ywindow = tamany.getHeight()-MARGES*2;
		int resultat = (int) (MARGES + coordenadaY*Ywindow/PetroliUtils.MAX_EIX_Y);
		return resultat;
	}
	
	public void guardarImatge() {
        int w = this.getSize().width;
        int h = this.getSize().height;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = img.createGraphics();
        
//        g2.translate(-this.getX(), -this.getY());
//        paint(g2);
//        g2.dispose();
        g2 = this.getGraphics();
        String ext = "jpg";  // or "png"; "bmp" okay in j2se 1.5
        try
        {
            ImageIO.write(img, "jpg", new File("canvasClip.jpg"));
        }
        catch(IOException ioe)
        {
            System.err.println("write error: " + ioe.getMessage());
        }
	}
}
