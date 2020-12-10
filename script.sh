#!/bin/bash

help="\
error: missing argument
usage:
  $0 build
  $0 run <sudoku_grid_path>
  $0 buildjar
  $0 runjar <sudoku_grid_path>
  $0 clean
"

manifest="\
Manifest-Version: 1.0
Class-Path: ./build/ ./libs/choco-solver-4.10.5-jar-with-dependencies.jar
Main-Class: ia.tp.Main
"

case $1 in
	build)
		javac -d build -cp ".:./libs/*" src/*.java
	shift
	;;
	run)
		java -cp ".:./build:./libs/*" ia.tp.Main $2
	shift
	;;
	clean)
		find . -name \*.class -type f -delete
		rm -rf TP-IA.jar
	shift
	;;
	buildjar)
		javac -d build -cp ".:./libs/*" src/*.java
		echo "$manifest" > Manifest.txt
		jar cvfm TP-IA.jar ./Manifest.txt -C . ./build/ ./libs/
		rm -rf Manifest.txt
	shift
	;;
	runjar)
		java -jar TP-IA.jar $2
	shift
	;;
	*)
		echo "$help"
	shift
	;;
esac