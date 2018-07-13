/*
 *	ALL RIGHTS RESERVED
 *	Copyright 2011, Dorion Kozma.
 *  552 Gatensbury St. 
 *	Coquitlam, B.C. Canada
 *	V3J 5G2 
 *
 *	UNPUBLISHED -- Rights reserved under the copyright laws of Canada. Use of a
 *  copyright notice is precautionary only and does not imply publication or
 *  disclosure.
 *
 *	THE CONTENT OF THIS WORK CONTAINS CONFIDENTIAL AND PROPRIETARY INFORMATION
 *  OWNED BY DORION KOZMA. ANY DUPLICATION, MODIFICATION, DISTRIBUTION, OR
 *  DISCLOSURE IN ANY FORM, IN WHOLE, OR IN PART, IS STRICTLY PROHIBITED
 *  WITHOUT THE PRIOR EXPRESS WRITTEN PERMISSION OF DORION KOZMA.
*/
package com.bianisoft.games.snailmaze;


//LWJGL library imports
import org.lwjgl.util.Rectangle;

//Bianisoft imports
import com.bianisoft.engine.App;
import com.bianisoft.engine.Context;
import com.bianisoft.engine.labels.LabelGradual;
import com.bianisoft.engine.manager.MngInput;
import com.bianisoft.engine.sprites.Sprite;


public class CtxQuit extends Context{
	private LabelGradual	m_lblQuit;
	private Sprite			m_sprCursor;

	
	public void activate(){
		super.activate();

		m_lblQuit= new LabelGradual("/res/font/FreeMonoBold.ttf", 25, "", 0, false, new Rectangle(-220, -140, 440, 280));
		m_lblQuit.setTextID("Lbl_Quit");
		m_lblQuit.setPos(0, 0, 6);
		m_lblQuit.load();
		addChild(m_lblQuit);

		m_lblQuit.hide();
		m_lblQuit.set("Now exiting the game ...\nCompleted! You may now exit your browser...");
		m_lblQuit.setSpeed(0.01f);

		m_sprCursor= new Sprite("/res/sprites/Cursor.png");
		m_sprCursor.setTextID("Spr_Cursor");
		m_sprCursor.addState("Idle", 1, 0.0f);
		m_sprCursor.addState("Over", 1, 0.0f);
		m_sprCursor.load();
		addChild(m_sprCursor);
		setCursor(m_sprCursor);

		//Applet will ignore this and will be drawing the Label
		App.exit();
	}

	public boolean keyboardManage(MngInput p_mngInput){
		if(!super.keyboardManage(p_mngInput))
			return false;

		return true;
	}

	public void mouseManage(MngInput p_mngInput){
		super.mouseManage(p_mngInput);

		p_mngInput.ungrabMouse();
	}
	
	public void manage(float p_nRatioMove){
		super.manage(p_nRatioMove);
		m_lblQuit.show();
	}
}


