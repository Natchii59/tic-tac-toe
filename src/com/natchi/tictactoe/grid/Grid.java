package com.natchi.tictactoe.grid;

import com.natchi.tictactoe.player.Player;
import com.natchi.tictactoe.utils.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * Manager de la grille de jeu
 */
public class Grid {

  public Symbol[][] grid = new Symbol[3][3];

  /**
   * Permet d'afficher la grille de jeu
   */
  public void show() {

    int i = 1;

    System.out.println("  A   B   C");
    System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯");
    for (Symbol[] y : this.grid) {
      System.out.print("│");
      for (Symbol x : y) {
        System.out.print(" ");
        System.out.print(x != null ? x : " ");
        System.out.print(" │");
      }
      System.out.print(" " + i);
      System.out.println();
      System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯");
      i++;
    }
  }

  /**
   * Permet de demander la position du prochain symbole
   *
   * @param player Joueur actuel
   *               {@code Player}
   *
   * @return Coordonnée du symbole
   */
  public List<Integer> requestPosition(Player player) {

    List<Integer> coordinate = null;

    while (coordinate == null) {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Choissez une case: ");
      coordinate = getCoordinate(scanner.next().toLowerCase(Locale.ROOT));

      if (coordinate == null) System.out.println("Coordonnées invalides");
      else if (this.grid[coordinate.get(1)][coordinate.get(0)] != null) {
        coordinate = null;
        System.out.println("Cette case est déjà prise");
      }
    }

    placeSymbol(coordinate, player);

    return coordinate;

  }

  /**
   * Permet de placer un symbole dans la grille
   *
   * @param coordinate Coordonnées du symbole
   *                   {@code List<Integer>}
   * @param player     Joueur actuel
   *                   {@code Player}
   */
  private void placeSymbol(List<Integer> coordinate, Player player) {

    this.grid[coordinate.get(1)][coordinate.get(0)] = player.symbol;
  }

  /**
   * Permet d'obtenir les coordonnées d'un pion
   *
   * @param coordinate Coordonnées (b6, f2, a1, ...)
   *                   {@code String}
   *
   * @return Liste de coordonées avec [x, y]
   */
  private List<Integer> getCoordinate(String coordinate) {

    List<Integer> intCoordinate = new ArrayList<>();

    List<Character> letters = new ArrayList<>();
    letters.add('a');
    letters.add('b');
    letters.add('c');

    char letter = coordinate.charAt(0);
    if (!letters.contains(letter)) return null;
    intCoordinate.add(letters.indexOf(letter));

    try {
      int number = Integer.parseInt(String.valueOf(coordinate.charAt(1)));
      if (number < 1 || number > 3) return null;
      intCoordinate.add(number - 1);
    } catch (NumberFormatException e) {
      return null;
    }

    return intCoordinate;
  }

  /**
   * Permet de vérifier si la partie est terminée
   *
   * @param coordinate Coordonnée du symbole placé en dernier
   *                   {@code List<Integer>}
   * @param player     Joueur actuel
   *                   {@code Player}
   *
   * @return Booléen de la vérification
   */
  public boolean checkEndGame(List<Integer> coordinate, Player player) {

    int x = coordinate.get(0);
    int y = coordinate.get(1);
    Symbol symbol = player.symbol == Symbol.X ? Symbol.O : Symbol.X;

    List<Symbol> symbolsCheck = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
      symbolsCheck.add(this.grid[i][x]);
    }

    if (symbolContains(this.grid[y], symbol) && symbolContains(this.grid[y], null)) return true;
    else if (!symbolsCheck.contains(symbol) && !symbolsCheck.contains(null)) return true;
    else return checkDiagonale(symbol);
  }

  /**
   * Permet de vérifier si le symbole du joueur adversaire se trouve dans un Array de type Symbol
   *
   * @param symbols Array de symboles
   *                {@code Symbol[]}
   * @param symbol  Symbole du joueur adverse
   *
   * @return Booléen de la vérification
   */
  private boolean symbolContains(final Symbol[] symbols, final Symbol symbol) {

    for (Symbol s : symbols) {
      if (s == symbol) return false;
    }

    return true;
  }

  /**
   * Permet de vérifier les diagonales
   *
   * @param symbol Symbole du joueur adverse
   *               {@code Symbol}
   *
   * @return Booléen de la vérification
   */
  private boolean checkDiagonale(final Symbol symbol) {

    List<Symbol> symbolsCheck = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
      symbolsCheck.add(this.grid[i][i]);
    }

    if (!symbolsCheck.contains(symbol) && !symbolsCheck.contains(null)) return true;

    symbolsCheck = new ArrayList<>();

    symbolsCheck.add(this.grid[0][2]);
    symbolsCheck.add(this.grid[1][1]);
    symbolsCheck.add(this.grid[2][0]);

    return !symbolsCheck.contains(symbol) && !symbolsCheck.contains(null);
  }

  /**
   * Permet de vérifier si la partie est nulle
   *
   * @return Booléen de la vérification
   */
  public boolean checkEgality() {

    for (Symbol[] symbols : this.grid) {
      for (Symbol symbol : symbols) {
        if (symbol == null) return false;
      }
    }

    return true;
  }

}
