package ia.tp;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.variables.IntVar;

import java.util.Arrays;
import java.util.Comparator;

public class Zebra {
	public void modelAndSolve() {
		// A new model instance
		Model model = new Model("Problème du zèbre");

		// VARIABLES
		int[] groups = {0, 1, 2, 3, 4}; // les 5 groupes composés de : 1 maison, 1 personne, 1 animal, 1 boisson, 1 cigarette

		IntVar blanche = model.intVar("blanche", groups);
		IntVar rouge = model.intVar("rouge", groups);
		IntVar verte = model.intVar("verte", groups);
		IntVar jaune = model.intVar("jaune", groups);
		IntVar bleue = model.intVar("bleue", groups);
		
		IntVar norvegien = model.intVar("norvegien", groups);
		IntVar anglais = model.intVar("anglais", groups);
		IntVar ukrainien = model.intVar("ukrainien", groups);
		IntVar japonais = model.intVar("japonais", groups);
		IntVar espagnol = model.intVar("espagnol", groups);
		
		IntVar cheval = model.intVar("cheval", groups);
		IntVar renard = model.intVar("renard", groups);
		IntVar zebre = model.intVar("zebre", groups);
		IntVar escargot = model.intVar("escargot", groups);
		IntVar chien = model.intVar("chien", groups);
		
		IntVar the = model.intVar("the", groups);
		IntVar eau = model.intVar("eau", groups);
		IntVar lait = model.intVar("lait", groups);
		IntVar cafe = model.intVar("cafe", groups);
		IntVar vin = model.intVar("vin", groups);
		
		IntVar kools = model.intVar("kools", groups);
		IntVar chesterfields = model.intVar("chesterfields", groups);
		IntVar old_golds = model.intVar("old_golds", groups);
		IntVar cravens = model.intVar("cravens", groups);
		IntVar gitanes = model.intVar("gitanes", groups);
		
		IntVar[] maisons = new IntVar[]{blanche, rouge, verte, jaune, bleue};
		IntVar[] personnes = new IntVar[]{norvegien, anglais, ukrainien, japonais, espagnol};
		IntVar[] animaux = new IntVar[]{cheval, renard, zebre, escargot, chien};
		IntVar[] boissons = new IntVar[]{the, eau, lait, cafe, vin};
		IntVar[] cigarettes = new IntVar[]{kools, chesterfields, old_golds, cravens, gitanes};
		
		// CONSTRAINTS
		norvegien.eq(0).post();
		bleue.eq(norvegien.add(1)).post();
		lait.eq(2).post();
		anglais.eq(rouge).post();
		verte.eq(cafe).post();
		jaune.eq(kools).post();
		blanche.eq(verte.add(1)).post();
		espagnol.eq(chien).post();
		ukrainien.eq(the).post();
		japonais.eq(cravens).post();
		old_golds.eq(escargot).post();
		gitanes.eq(vin).post();
		chesterfields.dist(renard).eq(0).post();
		kools.dist(cheval).eq(0).post();
		
		model.allDifferent(maisons, "BC").post();
		model.allDifferent(personnes, "BC").post();
		model.allDifferent(animaux, "BC").post();
		model.allDifferent(boissons, "BC").post();
		model.allDifferent(cigarettes, "BC").post();
		
		// SOLVING
		Solution solution = model.getSolver().findSolution();
		
		System.out.println("\n\n===== " + model.getName() + " =====\n");
		
		if (solution != null) {
			Arrays.sort(maisons, Comparator.comparing(IntVar::getValue));
			Arrays.sort(personnes, Comparator.comparing(IntVar::getValue));
			Arrays.sort(animaux, Comparator.comparing(IntVar::getValue));
			Arrays.sort(boissons, Comparator.comparing(IntVar::getValue));
			Arrays.sort(cigarettes, Comparator.comparing(IntVar::getValue));
			
			for(int i = 0; i < 5; i++) {
				System.out.println(maisons[i]);
				System.out.println(personnes[i]);
				System.out.println(animaux[i]);
				System.out.println(boissons[i]);
				System.out.println(cigarettes[i]);
				System.out.println("");
			}
			
			System.out.println("Personne possèdant le zèbre : " + personnes[zebre.getValue()].getName());
			System.out.println("Personne buvant de l'eau : " + personnes[eau.getValue()].getName());
		} else {
			System.out.println("Pas de solution");
		}
	}
}