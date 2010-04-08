package IA.petroli;

import java.util.Random;

public class PetroliUtils 
{
	public static int CONST_NUM_TIPUS_BOMBEIG = 4;
	
	public static int CONST_TIPUS_BOMBEIG_NO = 0;
	public static int CONST_TIPUS_BOMBEIG_A = 2;
	public static int CONST_TIPUS_BOMBEIG_B = 4;
	public static int CONST_TIPUS_BOMBEIG_C = 8;
	public static int CONST_TIPUS_BOMBEIG_D = 16;
	
	public static int CONST_COST_BOMBEIG_NO = 0;
	public static int CONST_COST_BOMBEIG_A = 1;
	public static int CONST_COST_BOMBEIG_B = 3;
	public static int CONST_COST_BOMBEIG_C = 10;
	public static int CONST_COST_BOMBEIG_D = 50;
	
	public static int[] bombeigs;
	
	
	public static int MAX_EIX_X = 80;
	public static int MAX_EIX_Y = 40;
	
	
	static
	{
		bombeigs = new int[5];
		bombeigs[0]=CONST_TIPUS_BOMBEIG_NO;
		bombeigs[1]=CONST_TIPUS_BOMBEIG_A;
		bombeigs[2]=CONST_TIPUS_BOMBEIG_B;
		bombeigs[3]=CONST_TIPUS_BOMBEIG_C;
		bombeigs[4]=CONST_TIPUS_BOMBEIG_D;
		
		
	}
	
	
	private static Random rand= new Random();
	
	public static int getRandomIntBetween(int i, int y) { return i+rand.nextInt(y); }
	
	
	public static int getDistanceBetweenTwoPlattforms(Plataforma p1, Plataforma p2)
	{
		return getDistanceBetweenTwoPoints(p1.getX(),p1.getY(),p2.getX(),p2.getY());
	}
	
	public static int getDistanceBetweenTwoPoints(int x1, int y1, int x2, int y2)
	{
		return (new Double (Math.pow(((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)),0.5))).intValue();
	}
	
	public static int getCostBombeig(int bombeig)
	{
		switch (bombeig) {
		case 2:
			return PetroliUtils.CONST_COST_BOMBEIG_A;
		case 4:
			return PetroliUtils.CONST_COST_BOMBEIG_B;
		case 8:
			return PetroliUtils.CONST_COST_BOMBEIG_C;
		case 16:
			return PetroliUtils.CONST_COST_BOMBEIG_D;

		default:
			return 0;
		}
	}
	

}
