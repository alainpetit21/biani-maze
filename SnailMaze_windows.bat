@echo off

java -classpath "dist;dist/lib;dist/SnailMaze.jar" -Dsun.java2d.noddraw=true -Djava.library.path="dist;dist/lib/windows;dist/JavaTestApp.jar" com.bianisoft.game.snailmaze.AppSnailMaze
