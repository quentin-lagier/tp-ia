package ia.tp;

import java.io.*;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		// Permet de lancer un terminal avec un double-clic sur le jar
		AutoRunFromConsole.runYourselfInConsole(true);
		
		new Coloration().modelAndSolve();
		
		new Zebra().modelAndSolve();
		
		String gridPath = args.length > 0 ? args[0] : null;
		new Sudoku(gridPath).modelAndSolve();
	}
}
