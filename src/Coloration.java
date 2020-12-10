package ia.tp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

public class Coloration {
	public void modelAndSolve() {
		// A new model instance
		Model model = new Model("Map Coloration");

		// VARIABLES
		int[] colors = {0, 1, 2, 3};
		String[] colorNames = {"Red", "Green", "Blue", "Yellow"};
		
		IntVar C1 = model.intVar("C1", colors);
		IntVar C2 = model.intVar("C2", colors);
		IntVar C3 = model.intVar("C3", colors);
		IntVar C4 = model.intVar("C4", colors);
		IntVar C5 = model.intVar("C5", colors);
		IntVar C6 = model.intVar("C6", colors);
		IntVar C7 = model.intVar("C7", colors);
		IntVar C8 = model.intVar("C8", colors);
		IntVar C9 = model.intVar("C9", colors);
		IntVar C10 = model.intVar("C10", colors);
		IntVar C11 = model.intVar("C11", colors);
		IntVar C12 = model.intVar("C12", colors);
		IntVar C13 = model.intVar("C13", colors);
		IntVar C14 = model.intVar("C14", colors);
		
		IntVar[] regionColors = {C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14};
		
		IntVar[][] neighbors = {{C1, C7}, {C1, C9}, {C1, C10}, {C1, C11}, {C1, C12}, {C1, C13}, {C2, C8}, {C2, C12}, {C2, C14}, {C3, C7}, {C3, C10}, {C3, C14}, {C4, C9}, {C4, C11}, {C4, C14}, {C5, C8}, {C5, C11}, {C5, C12}, {C6, C7}, {C6, C13}, {C6, C14}, {C7, C10}, {C7, C13}, {C7, C14}, {C8, C12}, {C9, C10}, {C9, C11}, {C9, C14}, {C10, C14}, {C11, C12}, {C12, C13}, {C12, C14}, {C13, C14}};
		
		// CONSTRAINTS
		for (IntVar[] n : neighbors)
			model.allDifferent(n).post();
		
		// SOLVING
		Solution solution = model.getSolver().findSolution();
		
		System.out.println("\n\n===== " + model.getName() + " =====\n");
		
		if (solution != null) {
			for (IntVar c : regionColors)
				System.out.println(c + " (" + colorNames[c.getValue()] + ")");
		} else {
			System.out.println("Pas de solution");
		}
	}
}