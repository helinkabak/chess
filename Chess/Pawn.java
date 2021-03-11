import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean initialLocation = true;

    public Pawn(Color color, Square location) {
        super(color, location);
    }

    @Override
    public boolean canMove(String to) {
        try {
            boolean validMove = false;
            Square targetLocation = this.getLocation().getBoard().getSquareAt(to);
            int rowDistance = targetLocation.getRowDistance(this.getLocation());

            if(this.getLocation().isAtSameColumn(targetLocation)) {
                if(this.getColor() == Color.WHITE && rowDistance > 0 && rowDistance <= 2) {
                    if(rowDistance == 2) {
                        if(initialLocation) {
                            //pawn is moving twice, check two squares in front are empty
                            ArrayList<Square> between = this.getLocation().getBoard().getSquaresBetween(this.getLocation(),
                                    targetLocation);
                            validMove = targetLocation.isEmpty() && between.get(0).isEmpty();
                        }
                    }
                    else {
                        validMove = targetLocation.isEmpty();
                    }
                    return validMove;
                }
                else if(this.getColor() == Color.BLACK && rowDistance < 0 && rowDistance >= -2) {

                    if(rowDistance == -2) {
                        if(initialLocation) {
                            //pawn is moving twice, check two squares in front are empty
                            ArrayList<Square> between = this.getLocation().getBoard().getSquaresBetween(this.getLocation(),
                                    targetLocation);
                            validMove = targetLocation.isEmpty() && between.get(0).isEmpty();
                        }
                    }
                    else {
                        validMove = targetLocation.isEmpty();
                    }
                } // attacking diagonals
            }
            else if(this.getLocation().isNeighborColumn(targetLocation)) {
                if(this.getColor() == Color.WHITE && rowDistance == 1) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                            Color.BLACK;
                }
                else if(this.getColor() == Color.BLACK && rowDistance == -1) {
                    validMove = !targetLocation.isEmpty() && targetLocation.getPiece().getColor() ==
                            Color.WHITE;

                }
            }
            return validMove;
        }
        catch(IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void move(String to) {
        this.initialLocation = false;

        Square targetLocation = this.getLocation().getBoard().getSquareAt(to);

        this.getLocation().clear();

        if(targetLocation.isAtLastRow(this.getColor())) {
            targetLocation.putNewQueen(this.getColor());
        }
        else {
            targetLocation.setPiece(this);
        }

        this.setLocation(targetLocation);

        targetLocation.getBoard().nextPlayer();
    }

    @Override
    public String toString() {
        return this.getColor() == Color.WHITE ? "P" : "p";
    }
}



