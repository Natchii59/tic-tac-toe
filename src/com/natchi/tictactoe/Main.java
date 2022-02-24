package com.natchi.tictactoe;

import com.natchi.tictactoe.grid.Grid;
import com.natchi.tictactoe.player.PlayerManager;

import java.util.List;


public class Main {

  public static void main(String[] args) {

    PlayerManager playerManager = new PlayerManager();
    Grid grid = new Grid();

    boolean end = false;

    while (!end) {
      grid.show();
      playerManager.showCurrentPlayer();
      List<Integer> coordinate = grid.requestPosition(playerManager.currentPlayer);
      if (grid.checkEndGame(coordinate, playerManager.currentPlayer)) {
        end = true;
        grid.show();
        System.out.println(playerManager.currentPlayer.name + " a gagné");
      } else if (grid.checkEgality()) {
        end = true;
        grid.show();
        System.out.println("Égalité");
      } else playerManager.changePlayer();
    }

  }

}
