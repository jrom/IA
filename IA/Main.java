package IA;

public class Main
{
  public static void main (String[] args)
  {
    SquareBoard board = new SquareBoard(10, 50);
    System.out.println(board);
		// board.canviarRuta(1,4);
		// 		board.canviarRuta(23,5);
		board.moureParada(23);
    System.out.println(board);
    // TODO Loads of works
  }
}
