#!/bin/sh

java -classpath 'dist:dist/lib:dist/JavaTestApp.jar' -Dsun.java2d.noddraw=true -Djava.library.path='dist:dist/lib/macosx:dist/JavaTestApp.jar' com.bianisoft.game.snailmaze.AppSnailMaze
