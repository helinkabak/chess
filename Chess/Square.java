public class Square {
    private ChessBoard board;
    private int row;
    private int column;
    private Piece piece;

    public Square(ChessBoard board, int row, int column) {
        this.board = board;
        this.row = row;
        this.column = column;
    }

    public ChessBoard getBoard() {
        return board;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return this.piece == null;
    }

    public boolean isAtLastRow(Color color) {
        return (color == Color.WHITE && this.row == 0) || (color == Color.BLACK && this.row == 7);
    }

    public boolean isAtSameColumn(Square square) {
        return this.column == square.getColumn();
    }

    public void clear() {
        this.piece = null;
    }

    public int getRowDistance(Square square) {
        return square.row - this.row;
    }

    public int getColumnDistance(Square square) {
        return square.column - this.column;
    }

    public boolean isNeighborColumn(Square square) {
        return Math.abs(square.column - this.column) == 1;
    }

    public void putNewQueen(Color color) {
        this.piece = new Queen(color, this);
    }
}
