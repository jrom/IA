package IA.Electrica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class canviarJP {

	public static void main(String[] args) {
		LlegirResultat("./","1.txt");
		LlegirResultat("./","2.txt");
		LlegirResultat("./","3.txt");
		LlegirResultat("./","4.txt");
		LlegirResultat("./","5-1.txt");
		LlegirResultat("./","5-2.txt");
		LlegirResultat("./","6.txt");
		LlegirResultat("./","7.txt");
		LlegirResultat("./","8-1.txt");
		LlegirResultat("./","8-2.txt");
		LlegirResultat("./","8-3.txt");
		LlegirResultat("./","8-4.txt");
		LlegirResultat("./","8-5.txt");
		LlegirResultat("./","8-6.txt");
		LlegirResultat("./","8-7.txt");
		
		
		/*System.out.print("1.txt ");Verifica("./1.txt");
		
		System.out.print("2.txt ");Verifica("./2.txt");
		
		System.out.print("3.txt ");Verifica("./3.txt");
		
		System.out.print("4.txt ");Verifica("./4.txt");
		
		System.out.print("5-1.txt ");Verifica("./5-1.txt");
		
		System.out.print("6.txt ");Verifica("./6.txt");
		
		System.out.print("7.txt ");Verifica("./7.txt");
		
		System.out.print("8-1.txt ");Verifica("./8-1.txt");
		
		System.out.print("8-2.txt ");Verifica("./8-2.txt");
		
		System.out.print("8-3.txt ");Verifica("./8-3.txt");
		
		System.out.print("9.txt ");Verifica("./1.txt");*/
		
	}

	public static void EscriureProblema(String novafrase,String ruta) {

		try {

			BufferedWriter bw;
			PrintWriter salida;
			bw = new BufferedWriter(new FileWriter("./mod".concat(ruta), true));
			salida = new PrintWriter(bw);
			salida.println(novafrase);
			salida.close();
		} catch (java.io.IOException ioex) {
		}

	}

	public static void LlegirResultat(String ruta2,String nom) {
		/**
		 * Error: ruta incorrecta no es troba--> llista buida?
		 */
		String ruta=ruta2.concat(nom);
		boolean fi = false;
		boolean fifrase = false;
		String paraula;
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			String frase = br.readLine();
			if (frase == null)
				fi = true;
			int linia = 0;
			while (!fi) {
				StringTokenizer st = new StringTokenizer(frase);
				System.out.println("linia " + linia);
				if (linia % 5 == 0) {
					paraula = "1";
					System.out.println("linia canviada" + linia);
				} else
					paraula = "2";
				String novafrase = paraula;
				// canviar primer token i posar la resta de tokens
				while (st.hasMoreTokens()) {
					novafrase = novafrase.concat(" ").concat(st.nextToken(" "));
				}

				// escriure al nou fitxer
				
				EscriureProblema(novafrase,nom);
				linia++;
				frase = br.readLine();
				if (frase == null)
					fi = true;

			}
			fr.close();
			br.close();
		} catch (java.io.FileNotFoundException e) {
			// no es fa res
		} catch (java.io.IOException e) {
			// no es fa res
		}

	}

	public static void Verifica(String ruta) {
		/**
		 * Error: ruta incorrecta no es troba--> llista buida?
		 */

		boolean fi = false;
		boolean fifrase = false;
		String paraula;
		try {
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			String frase = br.readLine();
			if (frase == null)
				fi = true;
			int linia = 0;
			int alg, si, heur, op;
			int a, s, h, o;
			while (!fi) {
				StringTokenizer st = new StringTokenizer(frase);
				int linia15 = 0;
				alg = Integer.parseInt(st.nextToken(" "));
				si = Integer.parseInt(st.nextToken(" "));
				heur = Integer.parseInt(st.nextToken(" "));

				while (linia15 < 15) {

					int linia5 = 0;
					op = Integer.parseInt(st.nextToken(" "));
					
					while (linia5 < 5) {

						
						if (frase == null)
							fi = true;
						else {
							st = new StringTokenizer(frase);
							a = Integer.parseInt(st.nextToken(" "));
							s = Integer.parseInt(st.nextToken(" "));
							h = Integer.parseInt(st.nextToken(" "));
							o = Integer.parseInt(st.nextToken(" "));
							System.out.println("linia "+linia+" l15 "+linia15+" l5 "+linia5+" a"+a+" s"+s+" h"+h+" o"+o+" alg"+alg+" si"+si+" heur"+heur+" op"+op);
							if (a == alg && s == si && h == heur && o == op) {
								frase = br.readLine();
								linia5++;
								linia15++;
								linia++;
							} else {
								linia5 = 1000;
								linia15 = 1000;
								fi = true;
								System.out.println("no correcte a la linia"
										+ linia);
							}
						}
					

					}
					
				}
				

			}
			fr.close();
			br.close();
		} catch (java.io.FileNotFoundException e) {
			// no es fa res
		} catch (java.io.IOException e) {
			// no es fa res
		}

	}
}
