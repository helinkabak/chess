import java.util.ArrayList;
import java.util.HashMap;

public class ChessBoard {
    private Color winner;
    private Color currentPlayer;
    private Square[][] chessBoard;
    private HashMap<String, Square> positionSquareMap;

    public ChessBoard() {
        this.initialize();
    }

    public void initialize() {
        this.winner = null;

        this.chessBoard = new Square[8][8];
        this.positionSquareMap = new HashMap<String, Square>();
        this.currentPlayer = Color.WHITE;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.chessBoard[i][j] = new Square(this, i, j);
            }

        }

        // black side
        this.chessBoard[0][0].setPiece(new Rook(Color.BLACK, this.chessBoard[0][0]));
        this.chessBoard[0][1].setPiece(new Knight(Color.BLACK, this.chessBoard[0][1]));
        this.chessBoard[0][2].setPiece(new Bishop(Color.BLACK, this.chessBoard[0][2]));
        this.chessBoard[0][3].setPiece(new Queen(Color.BLACK, this.chessBoard[0][3]));
        this.chessBoard[0][4].setPiece(new King(Color.BLACK, this.chessBoard[0][4]));
        this.chessBoard[0][5].setPiece(new Bishop(Color.BLACK, this.chessBoard[0][5]));
        this.chessBoard[0][6].setPiece(new Knight(Color.BLACK, this.chessBoard[0][6]));
        this.chessBoard[0][7].setPiece(new Rook(Color.BLACK, this.chessBoard[0][7]));

        this.chessBoard[1][0].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][0]));
        this.chessBoard[1][1].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][1]));
        this.chessBoard[1][2].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][2]));
        this.chessBoard[1][3].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][3]));
        this.chessBoard[1][4].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][4]));
        this.chessBoard[1][5].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][5]));
        this.chessBoard[1][6].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][6]));
        this.chessBoard[1][7].setPiece(new Pawn(Color.BLACK, this.chessBoard[1][7]));


        // white side
        this.chessBoard[6][0].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][0]));
        this.chessBoard[6][1].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][1]));
        this.chessBoard[6][2].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][2]));
        this.chessBoard[6][3].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][3]));
        this.chessBoard[6][4].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][4]));
        this.chessBoard[6][5].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][5]));
        this.chessBoard[6][6].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][6]));
        this.chessBoard[6][7].setPiece(new Pawn(Color.WHITE, this.chessBoard[6][7]));


        this.chessBoard[7][0].setPiece(new Rook(Color.WHITE, this.chessBoard[7][0]));
        this.chessBoard[7][1].setPiece(new Knight(Color.WHITE, this.chessBoard[7][1]));
        this.chessBoard[7][2].setPiece(new Bishop(Color.WHITE, this.chessBoard[7][2]));
        this.chessBoard[7][3].setPiece(new Queen(Color.WHITE, this.chessBoard[7][3]));
        this.chessBoard[7][4].setPiece(new King(Color.WHITE, this.chessBoard[7][4]));
        this.chessBoard[7][5].setPiece(new Bishop(Color.WHITE, this.chessBoard[7][5]));
        this.chessBoard[7][6].setPiece(new Knight(Color.WHITE, this.chessBoard[7][6]));
        this.chessBoard[7][7].setPiece(new Rook(Color.WHITE, this.chessBoard[7][7]));

        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
                positionSquareMap.put((char) ('a' + j - 1) + Integer.toString(9 - i), this.chessBoard[i - 1][j - 1]);
            }
        }
    }

    public boolean isGameOver() {
        boolean isWhitePresent = false;
        boolean isBlackPresent = false;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(!this.chessBoard[i][j].isEmpty()) {
                    if(this.chessBoard[i][j].getPiece().getColor() == Color.WHITE) {
                        isWhitePresent = true;
                    }
                    else if(this.chessBoard[i][j].getPiece().getColor() == Color.BLACK) {
                        isBlackPresent = true;
                    }
                }
            }
        }

        if(!isBlackPresent) {
            this.winner = Color.WHITE;
        }
        else if(!isWhitePresent) {
            this.winner = Color.BLACK;
        }

        return !isWhitePresent || !isBlackPresent;
    }

    public void nextPlayer() {
        this.currentPlayer = this.currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public ArrayList<Square> getSquaresBetween(Square from, Square to) throws IllegalArgumentException {
        int rowDistance = from.getRowDistance(to);
        int columnDistance = from.getColumnDistance(to);

        boolean isDistanceALine = Math.abs(rowDistance + columnDistance) == Math.abs(rowDistance - columnDistance);
        boolean isDistanceADiagonal = Math.abs(rowDistance) == Math.abs(columnDistance);

        if((rowDistance == 0 && columnDistance == 0) || (!isDistanceALine && !isDistanceADiagonal)) {
            throw new IllegalArgumentException("Target should be in the same column, or the same row.");
        }

        ArrayList<Square> returnArray = new ArrayList<>();

        if(isDistanceADiagonal) {
            for(int i = 1; i < Math.abs(rowDistance); i++) {
                int newRow = rowDistance < 0 ? -i : i;
                int newColumn = columnDistance < 0 ? -i : i;

                returnArray.add(this.chessBoard[from.getRow() + newRow][from.getColumn() + newColumn]);
            }
        }
        else if(rowDistance != 0) { // line in rows
            for(int i = 1; i < Math.abs(rowDistance); i++) {
                int newRow = rowDistance < 0 ? -i : i;

                returnArray.add(this.chessBoard[from.getRow() + newRow][from.getColumn()]);
            }
        }
        else { // line in columns
            for(int i = 1; i < Math.abs(columnDistance); i++) {
                int newColumn = columnDistance < 0 ? -i : i;

                returnArray.add(this.chessBoard[from.getRow()][from.getColumn() + newColumn]);
            }
        }

        return returnArray;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Square getSquareAt(String target) throws IllegalArgumentException{
        if(!this.positionSquareMap.containsKey(target)) {
            throw new IllegalArgumentException("Position does not exist on the board.");
        }

        return this.positionSquareMap.get(target);
    }

    public Piece getPieceAt(String target) {
        Square square = this.getSquareAt(target);

        return square.getPiece();
    }

    public Color getWinner() {
        return winner;
    }

    @Override
    public String toString() {
        String returnString = "\t\tA\tB\tC\tD\tE\tF\tG\tH";

        for(int i = 0; i < this.chessBoard.length; i++) {
            returnString += "\n\t  ---------------------------------\n\t" + (8 - i);
            for(int j = 0; j < this.chessBoard[i].length; j++) {
                returnString += " | " + (this.chessBoard[i][j].getPiece() != null ?  this.chessBoard[i][j].getPiece(): " ");
            }
            returnString += " | " + (8 - i);
        }
        return returnString + "\n\t  ---------------------------------\n\t\tA\tB\tC\tD\tE\tF\tG\tH";
    }
}
