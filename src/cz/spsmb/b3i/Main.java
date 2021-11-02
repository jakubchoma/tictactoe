package cz.spsmb.b3i;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String[] message = {"hráč X táhne", "hráč O táhne"};
    private static Scanner sc = new Scanner(System.in);
    private static char[] board = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static int[] playerBoard = new int[9];
    private static byte playerBoardCount = 0;
    private static byte round = 0;
    private static int[][] combinations = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {1, 5, 9},
            {3, 5, 7},
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
    };
    private static void printBoard() {

        System.out.println(board[1] + " | " + board[2] + " | " + board[3]);
        System.out.println(board[4] + " | " + board[5] + " | " + board[6]);
        System.out.println(board[7] + " | " + board[8] + " | " + board[9]);
    }

    private static void gameTurn() {
        printBoard();
        int idChoose;
        int checkClear = 0;
        do {
            System.out.print("Choose number: ");
            idChoose = sc.nextInt();
            int arrayIdChoose = idChoose;
            if (Arrays.stream(playerBoard).anyMatch(i -> i == arrayIdChoose)) {
                System.out.println("Bad number");
            } else {
                checkClear++;
            }
        } while (checkClear == 0);
        if (round % 2 == 0) {
            board[idChoose] = 'X';
        } else {
            board[idChoose] = 'O';
        }
        playerBoard[playerBoardCount] = idChoose;
        playerBoardCount++;
        round++;
        gameCheck();
    }
    private static void gameStartTurn() {
        System.out.println("\n"+ message[round % 2]);
        gameTurn();
    }
    private static void gameCheck() {
        if (round < 10) {
            for (int i = 0; i < 2; i++) {
                char check;
                if (i == 0) {
                    check = 'X';
                } else {
                    check = 'O';
                }
                for (int j = 0; j < combinations.length; j++) {
                    if (board[combinations[j][0]] == check &&
                            board[combinations[j][1]] == check &&
                            board[combinations[j][2]] == check) {
                        printBoard();
                        gameEnds();
                        return;
                    }
                }
            }
            gameStartTurn();
        } else {
            printBoard();
            System.out.println("\n Draw!");
        }
    }
    private static void gameEnds() {
        if (round % 2 == 0) {
            System.out.println("\n Winner: O ");
        } else {
            System.out.println("\n Winner: X ");
        }
    }
    public static void main(String[] args) {
        gameStartTurn();
    }
}