package ia.tp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sudoku {
	private String gridPath;
	private int[][] intGrid;
	private IntVar[][] rows, columns, boxes;
	
	public Sudoku(String gridPath) {
		this.gridPath = gridPath;
	}
	
	// Lit un fichier texte au chemin donné contenant une grille de Sudoku et renvoie cette grille dans un tableau d'entiers
	public int[][] parseSudokuFile(String gridPath) {
		try {
			if (gridPath == null) {
				System.out.println("Aucun chemin vers un fichier texte contenant une grille de Sudoku à résoudre passé en argument.\nUtilisation du fichier par défaut \"grid.txt\".\n");
				this.gridPath = "./grid.txt";
			}
				
			File gridFile = new File(this.gridPath);
			Scanner fileScanner = new Scanner(gridFile);
			
			int[][] grid = new int[9][9];
			String[][] textGrid = new String[9][9];
			
			int i = 0;
			while ((fileScanner.hasNext()) && (i < 9)) {
				textGrid[i] = fileScanner.nextLine().split(" ", 9);
				
				if (textGrid[i].length != 9) {
					fileScanner.close();
					throw new IllegalArgumentException("Une ligne de Sudoku doit être de 9 caractères de long, séparés par 1 seule espace.");
				}
				
				int j = 0;
				for(String n : textGrid[i]) {
					if ((n.length() == 1) && (Character.isDigit(n.charAt(0))) && !(n.equals("0"))) {
						grid[i][j] = Integer.parseInt(n);
					} else if (n.equals(".")) {
						grid[i][j] = 0;
					} else {
						fileScanner.close();
						throw new IllegalArgumentException("Présence d'un caractère invalide dans le fichier : il ne peut être composé que d'entiers entre 1 et 9 et de points (\".\"), séparés par 1 seule espace.");
					}
					j++;
				}
				i++;
			}
			
			fileScanner.close();
			return grid;
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public void modelAndSolve() {
		// A new model instance
		Model model = new Model("Résolution de Sudoku");
		System.out.println("\n\n===== " + model.getName() + " =====\n");
		
		this.intGrid = parseSudokuFile(gridPath);
		if (intGrid == null) {
			return;
		}
		
		// VARIABLES
		rows = new IntVar[9][9];
		columns = new IntVar[9][9];
		boxes = new IntVar[9][9];
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (intGrid[i][j] > 0) {
					rows[i][j] = model.intVar(intGrid[i][j]);
				} else {
					rows[i][j] = model.intVar("grid[" + i + "][" + j + "]", 1, 9);
				}
				columns[j][i] = rows[i][j];
			}
		}
		
		// On déconstruit les 9 carrés du Sudoku en 9 lignes (dans un tableau de 9*9)
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boxes[i][j] = rows[(j/3) + 3*((i/3)%3)][3*(i%3) + (j%3)];
			}
		}

		// CONSTRAINTS
		for (int i = 0; i < 9; i++) {
			model.allDifferent(rows[i], "BC").post();
			model.allDifferent(columns[i], "BC").post();
			model.allDifferent(boxes[i], "BC").post();
		}
			
		// SOLVING
		Solution solution = model.getSolver().findSolution();
		
		if (solution != null) {
			System.out.println("Grille initiale :");
			System.out.println(Arrays.deepToString(intGrid).replace("], ", "\n").replace("[", "").replace("]]", "\n").replace("0", ".").replace(", ", " "));
			
			System.out.println("Grille résolue :");
			for(IntVar[] i : rows) {
				for(IntVar j : i) {
					System.out.print(j.getValue() + " ");
				}
				System.out.println("");
			}
		} else {
			System.out.println("Pas de solution trouvée. Vérifiez que la grille entrée dans le fichier " + gridPath + " respecte les règles du Sudoku.");
		}
	}
}