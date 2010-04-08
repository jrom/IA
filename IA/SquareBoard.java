package IA;

import java.util.Arrays;

public class SquareBoard
{
  // Globals and misc
  // Global N value
  public static int N = 20;

  // Data

  public SquareBoard()
  {

  }

  public String toString()
  {
    String out = "";
    int i,j;

    out += "########################################\n";
    out += "Soc un tauler de Squaretown!\n";

    for(i = 0; i < N; i++)
    {
      for(j = 0; j < N; j++)
      {
        out += 0+" "; // TODO show the fucking matrix of something
      }
      out +="\n";
    }
    out += "########################################\n";

    return out;
  }
}
