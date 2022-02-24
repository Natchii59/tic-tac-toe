package com.natchi.tictactoe.player;

import com.natchi.tictactoe.utils.Symbol;

import java.util.Random;
import java.util.Scanner;


/**
 * Manager des joueurs
 */
public class PlayerManager {

  public Player player1;
  public Player player2;
  public Player currentPlayer;

  public PlayerManager() {

    requestPlayerName();
  }

  /**
   * Permet de demander aux joueurs leur nom
   */
  private void requestPlayerName() {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Nom du joueur 1: ");
    this.player1 = new Player(scanner.next());

    System.out.print("Nom du joueur 2: ");
    this.player2 = new Player(scanner.next());

    this.currentPlayer = chooseFirstPlayer();
  }

  /**
   * Permet de choisir aléatoirement qui jouera au premier tour
   *
   * @return Le joueur
   */
  private Player chooseFirstPlayer() {

    Random random = new Random();
    int randomInt = random.nextInt(2);

    if (randomInt == 0) {
      this.player1.setSymbol(Symbol.X);
      this.player2.setSymbol(Symbol.O);
      return player1;
    } else {
      this.player2.setSymbol(Symbol.X);
      this.player1.setSymbol(Symbol.O);
      return player2;
    }

  }

  /**
   * Permet d'afficher le joueur qui doit jouer
   */
  public void showCurrentPlayer() {

    System.out.println("C'est à " + this.currentPlayer.name + " de jouer");
  }

  /**
   * Permet de changer le joueur actuel
   */
  public void changePlayer() {

    this.currentPlayer = this.player1 == this.currentPlayer ? this.player2 : this.player1;
  }

}
