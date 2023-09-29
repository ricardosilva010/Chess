package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        aux.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        // Castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            Position positionRook1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(positionRook1)) {
                Position aux1 = new Position(position.getRow(), position.getColumn() + 1);
                Position aux2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(aux1) == null && getBoard().piece(aux2) == null) {
                    moves[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            Position positionRook2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(positionRook2)) {
                Position aux1 = new Position(position.getRow(), position.getColumn() - 1);
                Position aux2 = new Position(position.getRow(), position.getColumn() - 2);
                Position aux3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(aux1) == null && getBoard().piece(aux2) == null && getBoard().piece(aux3) == null) {
                    moves[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return moves;
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    private boolean testRookCastling(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece instanceof Rook && piece.getColor() == getColor() && piece.getMoveCount() == 0;
    }
}
