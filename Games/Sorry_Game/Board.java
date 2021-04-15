public class Board extends Game {

    private static char board[] = new char[48]; //initialize the board array

    private static char redHome [] = new char[6];
    private static char blueHome [] = new char[6];
    private static char greenHome [] = new char[6];
    private static char yellowHome [] = new char[6];


    public Board() {
        // initializes the array to all 'e'

        for (int i = 0; i < 48; i++) {
            board[i] = 'e';
        }
        for (int j = 0; j < 6; j++) {
            redHome[j] = 'e';
            blueHome[j] = 'e';
            greenHome[j] = 'e';
            yellowHome[j] = 'e';
        }

    }//Board (constructor)

    public static void printBoard() {
        //print the board with the pieces in their current state
        // e = empty space, r = red pawn, b = blue pawn, g = green pawn, y = yellow pawn.

        for (int i = 0; i < 48; i++) {
            System.out.print(board[i] + " " + " ");
        }
        System.out.println();
        for (int j = 0; j < 48; j++) {
            System.out.print("-" + "-" + " ");
        }
        System.out.println();
        for (int k = 0; k < 48; k++) {
            if (k < 10) {
                System.out.print(k + " " + " ");
            }
            else
                System.out.print(k + " ");
        }
        System.out.println();

    }//printBoard

    public static void setSpace(/*player*/char player, /*current position of pawn*/int o, /*card value*/int y) {

        int cardValue = y;
        int position = o;
		int oldposition = o;
		char p = player;
		
		if (board[position] == p) {
			
			for (int i = cardValue; i > 0; i--) {
				
				if (position > 47) {
					position = 0;
				}
				
				if (p == 'r' && position == 10) {
					if (i < 6) {
						redHome[i] = 'r';
						red.pawnMadeItHome();
					}
				}
				else if (p == 'y' && position == 46){
					if (i < 6) {
						yellowHome[i] = 'y';
						yellow.pawnMadeItHome();
					}
				}
				else if (p == 'g' && position == 34) {
					if (i < 6) {
						greenHome[i] = 'g';
						green.pawnMadeItHome();

					}
				}
				else if (p == 'b' && position == 22) {
					if (i < 6) {
						blueHome[i] = 'b';
						blue.pawnMadeItHome();

					}
				}
				
				position++;
			} //for 
		}
		else {
			System.out.println("You entered a space that wasn't yours");
		} //else
			
		if (board[oldposition] == p) {
			char newSpace = board[position];
			
			if (newSpace != 'e'){
				if (newSpace == 'r'){
					red.addToStart();
				} else if (newSpace == 'y'){
					yellow.addToStart();
				} else if (newSpace == 'b'){
					blue.addToStart();
				} else if (newSpace == 'g'){
					green.addToStart();
				}
			} // if 
			
			else {
				board[position] = p;
				board[oldposition] = 'e';
			} //else 
		}
		
    } // setSpace()

    public static void sendToStart(/*position of their pawn*/ int o, char x) {

        //sends enemy pawn back to start (happens with sorry card)

        char player = x;
        int position = o;

        player = board[o]; // sets player to value of board
        board[o] = 'e'; // empties board of that player's pawn


        // increments players piecesInStart since one just went back in;
        if (player == 'r') {
            red.addToStart();
        }
        if (player == 'g') {
            green.addToStart();
        }
        if (player == 'y') {
            yellow.addToStart();
        }
        if (player == 'b') {
            blue.addToStart();
        }

    }

    public static void switchSpace(/*your piece*/ int x, /*other piece*/ int y) {

        //swaps spaces with another player (may happen when cardValue = 11)
		
        int yourIndex = x;
        int theirIndex = y;

        char hold1, hold2;
		
		if (board[x] != 'e' && board[y] != 'e') {
			hold1 = board[x];
			hold2 = board[y];

			board[x] = hold2;
			board[y] = hold1;
		}
		else {
			System.out.println("FAILED: Tried to switch with an empty peice");
		}
    }

    public static void getPawnOut(/*player*/ char c) {

        //places pawn at starting block (happens when 1 or 2 is drawn);


        if (c == 'r') {
            board[47] = 'r';
        }

        if (c == 'g') {
            board[35] = 'g';
        }

        if (c == 'y') {
            board[11] = 'y';
        }

        if (c == 'b') {
            board[23] = 'b';
        }
    }
    /*     NOTE:
           We don't need a setBoard() method since we have a board contructor which already sets up the board.


           public String[] setBoard() {
//create the board
//initialize starting positions for each player
//initialize reference to space where the home arrays will begin

return null;
           }//setBoard */

}//Board (class)
