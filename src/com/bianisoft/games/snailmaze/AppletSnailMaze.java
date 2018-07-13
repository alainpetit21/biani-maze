package com.bianisoft.games.snailmaze;


//Bianisoft imports
import com.bianisoft.engine.Applet;


public class AppletSnailMaze extends Applet{
	public static final int IDCTX_TITLE= 0x0;
	public static final int IDCTX_GAME= 0x1;
	public static final int IDCTX_QUIT= 0x2;


	public AppletSnailMaze(){
		super("Snail Maze", 640, 480);
	}

	public String getVersion(){
		return "0.1";
	}

	public void load(){
		addContext(new CtxTitle());
		addContext(new CtxGame());
		addContext(new CtxQuit());
		setCurContext(IDCTX_TITLE);
	}
}