package com.bianisoft.games.snailmaze;


//Bianisoft imports
import com.bianisoft.engine.App;
import com.bianisoft.engine.Camera;
import com.bianisoft.engine.Context;
import com.bianisoft.engine.Timer;
import com.bianisoft.engine.backgrounds.Background;
import com.bianisoft.engine.backgrounds.BackgroundTiled;
import com.bianisoft.engine.labels.Label;
import com.bianisoft.engine.manager.MngHandlerTMX;
import com.bianisoft.engine.manager.MngInput;
import com.bianisoft.engine.sprites.Button;
import com.bianisoft.engine.sprites.Sprite;

//Snail-Maze Game import
import com.bianisoft.games.snailmaze.levels.*;


public class CtxGame extends Context{
	public static final int NO_WALL		= 0x0;
	public static final int WALL_RIGHT	= 0x1;
	public static final int WALL_DOWN	= 0x2;
	public static final int WALL_LEFT	= 0x4;
	public static final int WALL_UP		= 0x8;
	public static final int START		= 0x10;
	public static final int GOAL		= 0x20;
	

	private Camera			m_objCam;
	private Sprite			m_sprCursor;

	private Background		m_backTileBankWall;
	private BackgroundTiled	m_backWalls;
	private SprSnail		m_sprSnail;
	private Sprite			m_sprStart;
	private Sprite			m_sprGoal;

	private Button	m_btBack;
	private Label	m_lblRound;
	private Label	m_lblCptStep;
	private Label	m_lblTime;

	private boolean	m_isGameOver= false;
	private int		m_nDelayGameOver;

	private boolean	m_hasWon= false;
	private int		m_nDelayWon;


	public void activate(){
		super.activate();

		MngHandlerTMX.loadTMX("/res/level/game.tmx", this);

		m_sprCursor= (Sprite)findByTextID("sprCursor");
		m_sprCursor.setHotSpot(0, 16);
		setCursor(m_sprCursor);
		
		m_objCam= Camera.getCur(Camera.TYPE_2D);
		
		m_backTileBankWall= new Background("/res/back/bkTileBankSnail_Walls.png");
		m_backTileBankWall.setTextID("bkTileBankSnail_Walls");
		m_backTileBankWall.load();

		m_backWalls= new BackgroundTiled();
		m_backWalls.setTextID("bkTiledSnailWall");
		m_backWalls.setPos(0, 61, 50);
		m_backWalls.m_nTileSize= 20;

		switch(GlobalSettings.m_nRound){
			case 0:	m_backWalls.setMap(Level0.load());		break;
			case 1:	m_backWalls.setMap(Level1.load());		break;
			case 2:	m_backWalls.setMap(Level2.load());		break;
			case 3:	m_backWalls.setMap(Level3.load());		break;
			case 4:	m_backWalls.setMap(Level4.load());		break;
			case 5:	m_backWalls.setMap(Level5.load());		break;
			case 6:	m_backWalls.setMap(Level6.load());		break;
			case 7:	m_backWalls.setMap(Level7.load());		break;
			case 8:	m_backWalls.setMap(Level8.load());		break;
			case 9:	m_backWalls.setMap(Level9.load());		break;
			case 10:m_backWalls.setMap(Level10.load());		break;
			case 11:m_backWalls.setMap(Level11.load());		break;
			default:m_backWalls.setMap(LevelTest.load());	break;
		}
		
		m_backWalls.loadWithTileBank(m_backTileBankWall);
		addChild(m_backWalls);
		
		m_sprSnail= new SprSnail("/res/sprites/sprSnail.png");
		m_sprSnail.setTextID("sprSnail");
		m_sprSnail.setPos(Level.getStartX(), Level.getStartY());
		m_sprSnail.addState("state_Idle-Right", 1, 0.0f);
		m_sprSnail.addState("state_Idle-Down", 1, 0.0f);
		m_sprSnail.addState("state_Idle-Left", 1, 0.0f);
		m_sprSnail.addState("state_Idle-Up", 1, 0.0f);
		m_sprSnail.addState("state_Walk-Right", 3, 0.25f);
		m_sprSnail.addState("state_Walk-Down", 3, 0.25f);
		m_sprSnail.addState("state_Walk-Left", 3, 0.25f);
		m_sprSnail.addState("state_Walk-North", 3, 0.25f);
		m_sprSnail.load();
		addChild(m_sprSnail, false, false);
		
		m_sprStart= new Sprite("/res/sprites/sprStart.png");
		m_sprStart.setTextID("sprStart");
		m_sprStart.setPos(Level.convertBoardPosX(Level.getStartX()), Level.convertBoardPosY(Level.getStartY()), 48);
		m_sprStart.addState("state_Idle", 1, 0.0f);
		m_sprStart.load();
		addChild(m_sprStart, false, false);

		m_sprGoal= new Sprite("/res/sprites/sprGoal.png");
		m_sprGoal.setTextID("sprGoal");
		m_sprGoal.setPos(Level.convertBoardPosX(Level.getEndX()), Level.convertBoardPosY(Level.getEndY()), 48);
		m_sprGoal.addState("state_Idle", 2, 0.075f);
		m_sprGoal.load();
		m_sprGoal.setHotSpot(8, 60);
		addChild(m_sprGoal, false, false);

		m_lblRound= (Label)findByTextID("lblRoundValue");
		m_lblCptStep= (Label)findByTextID("lblMovesValue");
		m_lblTime= (Label)findByTextID("lblTimeValue");

		m_btBack= (Button)findByTextID("btBack");
		m_btBack.m_objCallback= new CtxGame.BtBack_Callback();

		addTimer(1000, new CtxGame.MyTimer(), this);
	}

	public void deActivate(){
		super.deActivate();
	
		GlobalSettings.m_nCptStep= 0;
		m_isGameOver= false;
		m_hasWon= false;
	}

	public void doMove(){
		GlobalSettings.m_nCptStep++;
		
		if((m_sprSnail.m_ptPos[0] == Level.getEndX()) && 
		   (m_sprSnail.m_ptPos[1] == Level.getEndY())){
			m_hasWon= true;
			GlobalSettings.m_nSecondLeft+= 30;
			m_nDelayWon= 3000;
			removeAllTimers();
		}
	}

	public boolean keyboardManage(MngInput p_mngInput){
		if(!super.keyboardManage(p_mngInput))
			return false;

		if(m_isGameOver || m_hasWon)
			return true;

		if(p_mngInput.isKeyboardDown(MngInput.K_ARROW_DOWN)){
			if(!m_sprSnail.isMoving()){
				if(!Level.hasWallSouth(m_sprSnail.m_ptPos[0], m_sprSnail.m_ptPos[1])){
					m_sprSnail.moveDown();
					doMove();
				}
			}
		}
		if(p_mngInput.isKeyboardDown(MngInput.K_ARROW_UP)){
			if(!m_sprSnail.isMoving()){
				if(!Level.hasWallNorth(m_sprSnail.m_ptPos[0], m_sprSnail.m_ptPos[1])){
					m_sprSnail.moveUp();
					doMove();
				}
			}
		}
		if(p_mngInput.isKeyboardDown(MngInput.K_ARROW_LEFT)){
			if(!m_sprSnail.isMoving()){
				if(!Level.hasWallWest(m_sprSnail.m_ptPos[0], m_sprSnail.m_ptPos[1])){
					m_sprSnail.moveLeft();
					doMove();
				}
			}
		}
		if(p_mngInput.isKeyboardDown(MngInput.K_ARROW_RIGHT)){
			if(!m_sprSnail.isMoving()){
				if(!Level.hasWallEast(m_sprSnail.m_ptPos[0], m_sprSnail.m_ptPos[1])){
					m_sprSnail.moveRight();
					doMove();
				}
			}
		}
		
		return true;
	}
	
	public void manage(float p_nRatioMove){
		super.manage(p_nRatioMove);
		
		m_lblCptStep.set(GlobalSettings.m_nCptStep);
		m_lblRound.set(GlobalSettings.m_nRound+1);
		m_lblTime.set(GlobalSettings.m_nSecondLeft);

		if(m_isGameOver){
			removeAllTimers();
			m_nDelayGameOver-= (16*p_nRatioMove);
			
			if(m_nDelayGameOver <= 0)
				App.g_theApp.setCurContext(AppSnailMaze.IDCTX_TITLE);
		}
		
		if(m_hasWon){
			m_nDelayWon-= (16*p_nRatioMove);

			if(m_nDelayWon <= 0){
				GlobalSettings.m_nRound++;
				App.g_theApp.setCurContext(AppSnailMaze.IDCTX_GAME);
			}
		}
	}


	private class MyTimer implements Timer.I_Callback{
		public void TimerEvent(Timer p_obj, Object p_objContextHint){
			GlobalSettings.m_nSecondLeft--;
			
			if(GlobalSettings.m_nSecondLeft <= 0){
				m_isGameOver= true;
				m_nDelayGameOver= 3000;
			}else{
				p_obj.m_isEnable= true;
			}
		}
	}	


	class BtBack_Callback implements Button.I_Callback{
		public void callbackStateChanged(int p_nNewState, Button p_obj){
			if(p_nNewState == Button.ST_CLICKED){
				App.g_theApp.setCurContext(AppSnailMaze.IDCTX_TITLE);
			}
		}
	}	
}