public class Piece {
    private Color color;
    private Square location;

    public Piece(Color color, Square location) {
        this.color=color;
        this.location=location;
    }

    public Color getColor() {
        return color;
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square location) {
        this.location = location;
    }

    public boolean canMove(String to) {
         return false;
    }

    public void move(String to) {
        Square targetLocation = this.getLocation().getBoard().getSquareAt(to);

        this.getLocation().clear();

        targetLocation.setPiece(this);

        this.setLocation(targetLocation);

        targetLocation.getBoard().nextPlayer();
    }
}
