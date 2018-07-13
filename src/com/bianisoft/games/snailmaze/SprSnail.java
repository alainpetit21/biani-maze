package com.bianisoft.games.snailmaze;


import com.bianisoft.games.snailmaze.levels.Level;
import com.bianisoft.engine.sprites.Sprite;


public class SprSnail extends Sprite{
	public int[]	m_ptPos= {0, 0};
	public int		m_nLastDirection= 0;
	public boolean	m_wasLastMoving;
	
	
	public SprSnail()		{super("");}
	public SprSnail(String p_stResFile)		{super(p_stResFile);}

	public void setPos(float p_nPosX, float p_nPosY){setPos(p_nPosX, p_nPosY, 40.0f);}
	public void setPos(float p_nPosX, float p_nPosY, float p_nPosZ){
		float posX= Level.convertBoardPosX((int)p_nPosX);
		float posY= Level.convertBoardPosY((int)p_nPosY);
		
		m_ptPos[0]= (int)p_nPosX;
		m_ptPos[1]= (int)p_nPosY;
		
		super.setPos(posX, posY, p_nPosZ);
	}
	
	public void moveTo(float p_nPosX, float p_nPosY){moveTo(p_nPosX, p_nPosY, 40.0f);}
	public void moveTo(float p_nPosX, float p_nPosY, float p_nPosZ){
		float posX= Level.convertBoardPosX((int)p_nPosX);
		float posY= Level.convertBoardPosY((int)p_nPosY);

		super.moveTo(posX, posY, p_nPosZ, 600);
	}
	
	public void moveRight(){
		if(m_ptPos[0] == Level.getLevelWidth()-1)
			return;
		
		m_nLastDirection= 0;
		m_ptPos[0]++;
		moveTo(m_ptPos[0], m_ptPos[1]);
		setCurState(4 + m_nLastDirection);
	}
	
	public void moveLeft(){
		if(m_ptPos[0] == 0)
			return;
		
		m_nLastDirection= 2;
		m_ptPos[0]--;
		moveTo(m_ptPos[0], m_ptPos[1]);
		setCurState(4 + m_nLastDirection);
	}
	
	public void moveUp(){
		if(m_ptPos[1] == 0)
			return;

		m_nLastDirection= 3;
		m_ptPos[1]--;
		moveTo(m_ptPos[0], m_ptPos[1]);
		setCurState(4 + m_nLastDirection);
	}
	
	public void moveDown(){
		if(m_ptPos[1] == Level.getLevelHeight()-1)
			return;
		
		m_nLastDirection= 1;
		m_ptPos[1]++;
		moveTo(m_ptPos[0], m_ptPos[1]);
		setCurState(4 + m_nLastDirection);
	}
	
	public void manage(float p_fTimeScaleFactor){
		if(m_wasLastMoving && !isMoving())
			setCurState(m_nLastDirection);
		
		m_wasLastMoving= isMoving();
		
		super.manage(p_fTimeScaleFactor);		
	}
}