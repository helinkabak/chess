public class Knight extends Piece {
    public Knight(Color color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = this.getLocation().getBoard().getSquareAt(to);

        int rowDistance = targetLocation.getRowDistance(this.getLocation());
        int columnDistance = targetLocation.getColumnDistance(this.getLocation());

        boolean canMove = false;

        if(Math.abs(rowDistance) + Math.abs(columnDistance) == 3 && (Math.abs(rowDistance) == 2 || Math.abs(rowDistance) == 1)) {
            canMove = (targetLocation.isEmpty() || ((this.getColor() == Color.BLACK && targetLocation.getPiece().getColor() == Color.WHITE) || (this.getColor() == Color.WHITE && targetLocation.getPiece().getColor() == Color.BLACK)));
        }

        return canMove;
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "N" : "n";
    }
}
