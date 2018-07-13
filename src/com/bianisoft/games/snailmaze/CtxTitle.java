package com.bianisoft.games.snailmaze;


//Bianisoft imports
import com.bianisoft.engine.App;
import com.bianisoft.engine.Camera;
import com.bianisoft.engine.labels.Label;
import com.bianisoft.engine.Context;
import com.bianisoft.engine.manager.MngHandlerTMX;
import com.bianisoft.engine.sprites.Sprite;
//import com.bianisoft.engine.music.Music;
import com.bianisoft.engine.manager.MngInput;
import com.bianisoft.engine.sprites.Button;


public class CtxTitle extends Context{
	private Camera	m_objCam;
	private Sprite	m_sprCursor;
	private Sprite	m_sprBianisoftLogo;
	private Button	m_btPlay;
	private Button	m_btQuit;
	private Label	m_lblPresents;
	private Label	m_lblSegaBIOS;
	private Label	m_lblSnailMaze;
	private Label	m_lblPressEnter;
	private Label	m_lblCopyright;

	private int		m_nState;
	private int		m_nDelay;


	public void activate(){
		super.activate();

		MngHandlerTMX.loadTMX("/res/level/title.tmx", this);

		m_sprCursor= (Sprite)findByTextID("sprCursor");
		m_sprCursor.setHotSpot(0, 16);
		setCursor(m_sprCursor);
		
		m_objCam= Camera.getCur(Camera.TYPE_2D);
		
		m_sprBianisoftLogo= (Sprite)findByTextID("sprBianisoftLogo");
		m_lblPresents= (Label)findByTextID("lblPresents");				m_lblPresents.hide();
		m_lblSegaBIOS= (Label)findByTextID("lblSegaMasterBIOS");		m_lblSegaBIOS.hide();
		m_lblSnailMaze= (Label)findByTextID("lblSnailMaze");			m_lblSnailMaze.hide();
		m_lblPressEnter= (Label)findByTextID("lblPressEnter");			m_lblPressEnter.hide();
		m_lblCopyright= (Label)findByTextID("lblCopyright");			m_lblCopyright.hide();
		m_btPlay= (Button)findByTextID("btPlay");						m_btPlay.hide();
		m_btQuit= (Button)findByTextID("btQuit");						m_btQuit.hide();

		m_sprBianisoftLogo.moveTo(0, -240+64+32, 2000);
		m_nDelay= 1000;
		m_nState= 0;

		m_btQuit.m_objCallback= new BtQuit_Callback();
		m_btPlay.m_objCallback= new BtPlay_Callback();
		m_btPlay.setLabel(m_lblPressEnter);
	}

	//Move Bianisoft Logo Down
	public void manageState0(float p_fTimeScaleFactor){
		m_nDelay-= (16*p_fTimeScaleFactor);
		if(m_nDelay <= 0){
			m_nDelay= 500;
			m_nState++;
		}
	}

	//Show presents:
	public void manageState1(float p_fTimeScaleFactor){
		m_lblPresents.show();
		m_lblSegaBIOS.show();
		
		m_nDelay-= (16*p_fTimeScaleFactor);
		if(m_nDelay <= 0){
			m_nDelay= 2000;
			m_nState++;
		}
	}
	
	//Show Title
	public void manageState2(float p_fTimeScaleFactor){
		m_lblSnailMaze.show();
		
		m_nDelay-= (16*p_fTimeScaleFactor);
		if(m_nDelay <= 0){
			m_nDelay= 0;
			m_nState++;
		}
	}
	
	//After 1 second Show Press Enter and copyright aand wait
	public void manageState3(float p_fTimeScaleFactor){
		m_btPlay.show();
		m_btQuit.show();
		
		m_lblCopyright.show();

		MngInput mngInput= MngInput.get();

		if(mngInput.isKeyboardClicked(MngInput.K_ENTER)){
			App.g_theApp.setCurContext(AppSnailMaze.IDCTX_GAME);
		}
	}
	
	public void manage(float p_fTimeScaleFactor){
		super.manage(p_fTimeScaleFactor);
		
		if(m_nState == 0)		manageState0(p_fTimeScaleFactor);
		else if (m_nState == 1)	manageState1(p_fTimeScaleFactor);
		else if (m_nState == 2)	manageState2(p_fTimeScaleFactor);
		else if (m_nState == 3)	manageState3(p_fTimeScaleFactor);
	}


	class BtPlay_Callback implements Button.I_Callback{
		public void callbackStateChanged(int p_nNewState, Button p_obj){
			if(p_nNewState == Button.ST_CLICKED){
				GlobalSettings.m_nRound= 0;
				GlobalSettings.m_nSecondLeft= 60;
				App.g_theApp.setCurContext(AppSnailMaze.IDCTX_GAME);
			}
		}
	}
	
	class BtQuit_Callback implements Button.I_Callback{
		public void callbackStateChanged(int p_nNewState, Button p_obj){
			if(p_nNewState == Button.ST_CLICKED){
				App.g_theApp.setCurContext(AppSnailMaze.IDCTX_QUIT);
			}
		}
	}
}
