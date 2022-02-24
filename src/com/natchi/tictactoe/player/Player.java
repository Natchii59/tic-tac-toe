package com.natchi.tictactoe.player;

import com.natchi.tictactoe.utils.Symbol;


public class Player {

  public String name;
  public Symbol symbol;
  public boolean win = false;

  public Player(String name) {

    this.name = name;
  }

  /**
   * Permet de définir le symbole du joueur
   *
   * @param symbol Symbole
   *               {@code String}
   */
  public void setSymbol(Symbol symbol) {

    this.symbol = symbol;
  }

}
