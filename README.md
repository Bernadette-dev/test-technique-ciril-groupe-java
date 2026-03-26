# Simulation de propagation d’un feu de forêt

## Description

Ce projet implémente une simulation de propagation d’un feu de forêt sur une grille 2D.

La simulation est discrète dans le temps et se déroule étape par étape.

### Règles de propagation :

* Une case en feu devient cendre à l’étape suivante
* Le feu peut se propager aux 4 cases adjacentes (haut, bas, gauche, droite)
* La propagation est probabiliste (paramètre `p`)
* Une case en cendre ne peut plus brûler
* La simulation s’arrête lorsqu’il n’y a plus de cases en feu


## Configuration

Les paramètres de la simulation sont définis dans un fichier `config.json`.

### Exemple :

```json
{
  "height": 5,
  "width": 5,
  "propagationProbability": 0.5,
  "initialFires": [
    { "row": 2, "col": 2 }
  ]
}
```

### Paramètres :

* `height` : hauteur de la grille
* `width` : largeur de la grille
* `propagationProbability` : probabilité de propagation du feu (entre 0 et 1)
* `initialFires` : liste des positions initialement en feu



## Lancement du projet

### 1. Compilation

```bash
mvn clean compile
```

### 2. Exécution

#### Sur CMD (la commande a été testée sous CMD Windows.) :

```bash
mvn exec:java -Dexec.mainClass="com.forest.simulation.app.Main"
```


## Choix techniques et conception

### Modélisation

* `CellState` : représente l’état d’une case (`TREE`, `FIRE`, `ASH`)
* `Position` : représente une coordonnée (ligne, colonne)
* `Forest` : représente la grille de la forêt

### Logique métier

* `FireSimulationService` :

  * calcule l’état suivant de la forêt
  * gère la propagation du feu
  * applique les règles métier

### Configuration

* `SimulationConfig` : modèle de configuration
* `ConfigLoader` : chargement du fichier JSON via Jackson

### Point d’entrée

* `Main` :

  * charge la configuration
  * initialise la forêt
  * lance la simulation
  * affiche les résultats


## Choix de conception importants

* Utilisation d’une **copie de la grille** à chaque étape pour éviter les effets de bord :

```java
Forest nextForest = currentForest.copy();
```

* Séparation claire entre :

  * données (`model`)
  * logique métier (`service`)
  * configuration (`config`)
  * exécution (`app`)

* Utilisation de boucles `for` pour privilégier la lisibilité sur les streams


## Tests

Des tests unitaires ont été implémentés avec JUnit 5 :

* Vérification qu’une case en feu devient cendre
* Vérification de la propagation du feu lorsque la probabilité est maximale

### Lancer les tests :

```bash
mvn test
```

## Auteur

Projet réalisé dans le cadre d’un test technique Java.
