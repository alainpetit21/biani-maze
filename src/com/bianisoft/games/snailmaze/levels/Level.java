package com.bianisoft.games.snailmaze.levels;


public class Level{
	public static int[][]	g_arData;
	public static int[]		g_ptStart= {0, 0};
	public static int[]		g_ptEnd= {0, 0};


	public static int getLevelWidth()	{return g_arData.length;};
	public static int getLevelHeight()	{return g_arData[0].length;};

	public static int convertBoardPosX(int p_nBoardPosX)	{return (p_nBoardPosX*20)-290;}
	public static int convertBoardPosY(int p_nBoardPosY)	{return (p_nBoardPosY*20)-90;}

	public static int getStartX()	{return g_ptStart[0];}
	public static int getStartY()	{return g_ptStart[1];}
	public static int getEndX()		{return g_ptEnd[0];}
	public static int getEndY()		{return g_ptEnd[1];}

	public static boolean hasWallNorth(int p_nX, int p_nY){
		if(p_nY == 0)
			return true;
		if((g_arData[p_nX][p_nY] & 0x8) == 0x8)
			return true;
		if((g_arData[p_nX][p_nY-1] & 0x2) == 0x2)
			return true;
			
		return false;
	}

	public static boolean hasWallWest(int p_nX, int p_nY){
		if(p_nX == 0)
			return true;
		if((g_arData[p_nX][p_nY] & 0x4) == 0x4)
			return true;
		if((g_arData[p_nX-1][p_nY] & 0x1) == 0x1)
			return true;
			
		return false;
	}

	public static boolean hasWallSouth(int p_nX, int p_nY){
		if(p_nY == getLevelHeight()-1)
			return true;
		if((g_arData[p_nX][p_nY] & 0x2) == 0x2)
			return true;
		if((g_arData[p_nX][p_nY+1] & 0x8) == 0x8)
			return true;
			
		return false;
	}

	public static boolean hasWallEast(int p_nX, int p_nY){
		if(p_nX == getLevelWidth()-1)
			return true;
		if((g_arData[p_nX][p_nY] & 0x1) == 0x1)
			return true;
		if((g_arData[p_nX+1][p_nY] & 0x4) == 0x4)
			return true;
			
		return false;
	}
	
	public static int[][] flip2DBuffer(int[][] p_arBuffer){
		int[][]ret= new int[p_arBuffer[0].length][p_arBuffer.length];
		
		for(int x= 0; x < ret.length; ++x){
			for(int y= 0; y < ret[0].length; ++y){
				ret[x][y]= p_arBuffer[y][x];
			}
		}
				
		return ret;
	}
}
