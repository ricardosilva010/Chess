package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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

            // en passant
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() - 1][right.getColumn()] = true;
                }
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
            // en passant
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
                    moves[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
                    moves[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return moves;
    }
}
