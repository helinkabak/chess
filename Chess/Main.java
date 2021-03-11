import java.util.*;


public class Main {
    static void showInstructions() {
        System.out.println("\n\nHow to play:\n\tEnter the location of the chess piece you want to pick (e.g. \"d2\").");
        System.out.println("\tEnter;\n\t\tthe location of the square that you want the picked chess piece to move,\n\t\t\"r\" in order to drop the chess piece you have previously picked (only if you have picked a piece to move).\n\n");
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        ChessBoard board = new ChessBoard();
        System.out.println("Welcome to Chess™.");

        Main.showInstructions();

        System.out.println("Type \"Help\" for commands.\n");

        System.out.println("\n" + board + "\n");

        while(!board.isGameOver()) {
            System.out.println("It is " + board.getCurrentPlayer() + "'s turn\n");

            boolean bypassMoving = false;
            Piece piece = null;

            do {
                System.out.print("Enter the location of the piece:");
                String from = reader.next();

                if(from.equalsIgnoreCase("help")) {
                    Main.showInstructions();

                    continue;
                }

                try {
                    piece = board.getPieceAt(from);

                    if(piece == null) {
                        System.out.println("\nYou have picked an empty square, please pick a square with a piece on it.");
                    }
                    else if(piece.getColor() != board.getCurrentPlayer()) {
                        System.out.println("\nIt is " + piece.getColor() + "'s turn. Please pick accordingly.");
                    }
                }
                catch(IllegalArgumentException e) {
                    System.out.println("\nPlease enter a valid location.");
                }
            } while(piece == null || piece.getColor() != board.getCurrentPlayer());

            String to = null;

            boolean canMove = false;
            do {
                System.out.println("\nYou have picked " + piece);
                System.out.print("Enter the new location of the piece:");

                to = reader.next();

                if(to.equals("r")) {
                    bypassMoving = true;
                    break;
                }
                else if(to.equalsIgnoreCase("help")) {
                    Main.showInstructions();

                    continue;
                }

                try {
                    canMove = piece.canMove(to);

                    if(!canMove) {
                        System.out.println("\nIllegal move.");

                    }
                }
                catch(IllegalArgumentException e) {
                    System.out.println("\nPlease enter a valid location.");
                }
            } while(!canMove);

            if(!bypassMoving) {
                piece.move(to);
            }

            System.out.println("\n" + board + "\n");
        }

        System.out.println("\n\n" + board.getWinner() + " is the winner! Congratulations!!!!");

        reader.close();
    }
}


