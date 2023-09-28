package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] moves = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position aux = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            aux.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() - 2, position.getColumn());
            Position aux2 = new Position(position.getRow() - 1, position.getColumn()) ;
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) && getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2) && getMoveCount() == 0) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
        }
        else {
            aux.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() + 2, position.getColumn());
            Position aux2 = new Position(position.getRow() + 1, position.getColumn()) ;
            if (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux) && getBoard().positionExists(aux2) && !getBoard().thereIsAPiece(aux2) && getMoveCount() == 0) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
            aux.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(aux) && isThereOpponentPiece(aux)) {
                moves[aux.getRow()][aux.getColumn()] = true;
            }
        }

        return moves;
    }
}
