package com.bianisoft.games.snailmaze;


//Bianisoft imports
import com.bianisoft.engine.App;


public class AppSnailMaze extends App{
	public static final int IDCTX_TITLE= 0x0;
	public static final int IDCTX_GAME= 0x1;
	public static final int IDCTX_QUIT= 0x2;


	public AppSnailMaze(){
		super("Snail Maze", 640, 480, false);
	}

	public String getVersion(){
		return "0.1";
	}

	public void load(){
		addContext(new CtxTitle());
		addContext(new CtxGame());
		addContext(new CtxQuit());
		setCurContext(IDCTX_GAME);
	}

   public static void main(String[] args){
		AppSnailMaze objGame= new AppSnailMaze();
		libMain(args);
    }
}