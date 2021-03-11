import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(Color color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        try {
            Square targetLocation = this.getLocation().getBoard().getSquareAt(to);
            int rowDistance = targetLocation.getRowDistance(this.getLocation());
            int columnDistance = targetLocation.getColumnDistance(this.getLocation());

            boolean canMove = false;

            if(((rowDistance != 0 || columnDistance != 0) && (Math.abs(rowDistance + columnDistance) == Math.abs(rowDistance - columnDistance) || Math.abs(rowDistance) == Math.abs(columnDistance)))) {
                ArrayList<Square> between = this.getLocation().getBoard().getSquaresBetween(this.getLocation(),
                        targetLocation);

                boolean pieceExistsBetweenLocations = false;
                for(int i = 0; i < between.size(); i++) {
                    if(!between.get(i).isEmpty()) {
                        pieceExistsBetweenLocations = true;

                        break;
                    }
                }

                canMove = !pieceExistsBetweenLocations && (targetLocation.isEmpty() || ((this.getColor() == Color.BLACK && targetLocation.getPiece().getColor() == Color.WHITE) || (this.getColor() == Color.WHITE && targetLocation.getPiece().getColor() == Color.BLACK)));
            }

            return canMove;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "Q" : "q";
    }
}
