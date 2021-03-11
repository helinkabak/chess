public class King extends Piece {
    public King(Color color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        Square targetLocation = this.getLocation().getBoard().getSquareAt(to);
        int rowDistance = targetLocation.getRowDistance(this.getLocation());

        return ((this.getLocation().isNeighborColumn(targetLocation) && (rowDistance == 1 || rowDistance == -1 || rowDistance == 0)) || (!this.getLocation().isNeighborColumn(targetLocation) && (rowDistance == 1 || rowDistance == -1))) && (targetLocation.isEmpty() || ((this.getColor() == Color.BLACK && targetLocation.getPiece().getColor() == Color.WHITE) || (this.getColor() == Color.WHITE && targetLocation.getPiece().getColor() == Color.BLACK)));
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "K" : "k";
    }
}
