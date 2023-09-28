package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    public String toString() {
        return "N";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        aux.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        aux.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(aux) && canMove(aux)) {
            moves[aux.getRow()][aux.getColumn()] = true;
        }

        return moves;
    }

    private boolean canMove(Position position) {
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }
}
