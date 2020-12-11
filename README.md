# TP Intelligence Artificielle

[![Using Choco Solver](https://img.shields.io/badge/choco--solver-v4.10.5-blue)](https://github.com/chocoteam/choco-solver)  
Quentin LAGIER, Yann PLONSKI


## Problèmes traités

- Coloriage d’une carte avec 4 couleurs
- Problème du zèbre de Lewis Caroll
- Résolution d’un sudoku


## Utilisation

Compiler  
`$ ./script.sh build`

Exécuter (utilise le fichier "grid.txt" du même répertoire si sudoku_grid_path n'est pas donné)  
`$ ./script.sh run <sudoku_grid_path>`

Compiler et créer un jar  
`$ ./script.sh buildjar`

Exécuter le jar  
`$ ./script.sh runjar <sudoku_grid_path>`

Nettoyer les fichiers compilés (.class et .jar)  
`$ ./script.sh clean`


## Sortie
```
$ ./script.sh run

===== Map Coloration =====

C1 = 2 (Blue)
C2 = 0 (Red)
C3 = 2 (Blue)
C4 = 2 (Blue)
C5 = 2 (Blue)
C6 = 2 (Blue)
C7 = 1 (Green)
C8 = 3 (Yellow)
C9 = 1 (Green)
C10 = 0 (Red)
C11 = 0 (Red)
C12 = 1 (Green)
C13 = 0 (Red)
C14 = 3 (Yellow)


===== Problème du zèbre =====

jaune = 0
norvegien = 0
cheval = 0
eau = 0
kools = 0

bleue = 1
ukrainien = 1
renard = 1
the = 1
chesterfields = 1

rouge = 2
anglais = 2
escargot = 2
lait = 2
old_golds = 2

verte = 3
japonais = 3
zebre = 3
cafe = 3
cravens = 3

blanche = 4
espagnol = 4
chien = 4
vin = 4
gitanes = 4

Personne possèdant le zèbre : japonais
Personne buvant de l'eau : norvegien


===== Résolution de Sudoku =====

Aucun chemin vers un fichier texte contenant une grille de Sudoku à résoudre passé en argument.
Utilisation du fichier par défaut "grid.txt".

Grille initiale :
6 . . 5 . 3 . . 4
. 3 1 . . 4 2 . 7
5 . 9 . . . . . 3
. . . 4 6 9 8 . .
. . . . 8 . . . 5
. . . . . . 1 9 .
9 . 8 . . . 5 . .
. . . 1 . . . 2 .
. . 5 9 2 6 . 7 .

Grille résolue :
6 7 2 5 1 3 9 8 4
8 3 1 6 9 4 2 5 7
5 4 9 8 7 2 6 1 3
1 5 7 4 6 9 8 3 2
3 9 6 2 8 1 7 4 5
2 8 4 7 3 5 1 9 6
9 2 8 3 4 7 5 6 1
7 6 3 1 5 8 4 2 9
4 1 5 9 2 6 3 7 8
```
