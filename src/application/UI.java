package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner sc) {
        try {
            String s = sc.nextLine();
            char column = s.charAt(0);
            int row = Integer.parseInt(s.substring(1));
            return new ChessPosition(column, row);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn());
        System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
        if (chessMatch.getCheck()) {
            System.out.println("CHECK!");
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        for (int i=0; i<pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j=0; j< pieces[i].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i=0; i<pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j=0; j< pieces[i].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }

    private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
        List<ChessPiece> whiteCaptured = capturedPieces.stream().filter(x -> x.getColor() == Color.WHITE).toList();
        List<ChessPiece> blackCaptured = capturedPieces.stream().filter(x -> x.getColor() == Color.BLACK).toList();

        System.out.println("Captured pieces:");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(whiteCaptured.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(blackCaptured.toArray()));
        System.out.print(ANSI_RESET);
    }
}
