/*I'm just initializing everything as public unless we specifically stated otherwise
 *I'm not the clearest on how all that works with these hierarchies
 */ /*COOL*/
/*

GOAL:

- Handle rules for every turn of the game.
- Define methods for handling player peices talking to the board
- Run through the code per player's turn

*/

import java.util.Scanner;

public class Game {

    private static Board board = new Board(); // initializes new board


    protected static Player yellow = new Player('y'); // initialize yellow player
    protected static Player red = new Player('r'); //initialize red player
    protected static Player blue = new Player('b'); // initialize blue player
    protected static Player green = new Player('g'); // initialize green player
	
    private static Deck deck = new Deck(); // initialize the deck

    public static void main(String args[]) { //Make sure the main method is always "public static void main"

        /*TODO:

          - detect if two pawns overlap
          -send non-moving pawn to start
          - for 7, check if the player enters a value larger than 7 for move 1 (or else they will move however many spaces they want)
          - for each player, tell the game to put the pawns in the home array if they make it around the board (and have a couple extra spaces)
          - despawn pawns if player makes it to end of home
          - increment piecesInHome if pawn makes it to player home
          -

          ADD TO LIST HERE ^^^^

          (Tyler)
          ==============================================================================*/
		int turn = 0;

        deck.Shuffle(); // shuffle deck

		System.out.println("Welcome to SORRY!");
		
        do {
			
			if (turn > 3) {
                turn = 0;
            } // if
			
			turn++;
			
            board.printBoard();
            System.out.print("It's ");
			
			if (currentPlayersColor(turn) == 'y') {
				System.out.print("Yellow's turn! \n");
			}
			
			else if (currentPlayersColor(turn) == 'r') {
				System.out.print("Red's turn! \n");
			}
			
			else if (currentPlayersColor(turn) == 'g') {
				System.out.print("Green's turn! \n");
			}
			
			else {
				System.out.print("Blue's turn! \n");
			}
			
            movePiece(currentPlayersColor(turn), turn);

			System.out.println();
			System.out.println();
			


        } // do-while
        while (!red.gameOver() && !blue.gameOver() && !green.gameOver() && !yellow.gameOver());

        System.out.println("Congrats " + currentPlayersColor(turn) + "!");
        System.out.println("You win!");

    }//main

    //Moved 'drawCard()' to the deck class where it belongs. (Tyler)

    public static void movePiece(char currentplayer, int turns) {
		
		char current = currentplayer;
		int turn = turns;
		
		int cardValue = deck.drawCard();
		System.out.println("You drew a: " + cardValue);
        Scanner scan = new Scanner(System.in);
		int pawnPosition = 0;

			
        if (cardValue == 1 || cardValue == 2) {
			
            boolean check1 = true;
			
			while (check1) {
            //can choose whether to move a piece or leave the start positition
            System.out.println("Would you like to move a piece or get a piece out of the starting position?");
            System.out.println("Please enter \"MOVE\" or \"GETOUT\"");
            String response1or2 = scan.nextLine().toUpperCase();


                if (response1or2.equals("MOVE")) {
                    //move forward, like a normal turn

					System.out.println("Which piece would you like to move?");
					pawnPosition = scan.nextInt();
					
                    board.setSpace(current, pawnPosition, cardValue);
                    check1 = false;
                }

                else if (response1or2.equals("GETOUT")) {
                    /*Player.pawnEscape();*/ //I don't think this method is necessary
                    board.getPawnOut(current);
                    currentPlayer(turn).pawnEscape();
                    check1 = false;
                }

                else {
                    System.out.println("You have entered an incorrect command -- 1 or 2");
                }//if-else

            } // while
        }
		else if (!currentPlayer(turn).isPawnOut()) {
			System.out.println("Sorry, you need either a 1 or a 2 to move out of start!");
			System.out.println("For player: "+currentPlayer(turn).getColor());
		}
		else {
			if (cardValue == 0) { //SORRY! card
				//send an opponent's piece back to the starting position
				System.out.println("Enter the space of the opponent you would like to send back to the starting position");
				int position = scan.nextInt();
				board.sendToStart(position, current);

			}

			else if (cardValue == 4) {
				//moves backwards instead of forwards
				System.out.println("Which piece would you like to move?");
				pawnPosition = scan.nextInt();
				board.setSpace(current, pawnPosition, -4);

			} // else-if

			else if (cardValue == 11) {
				//can choose whether to switch with an opponent's piece or move a piece forward
				System.out.println("Would you like to move a piece or switch the position of your piece with another player's?");
				System.out.println("Please enter \"MOVE\" or \"SWITCH\"");
				String response11 = scan.nextLine().toUpperCase();
				
				boolean correct = false;

				while (!correct) {

					if (response11.equals("MOVE")) {
						//move forward, like a normal turn
						System.out.println("Which piece would you like to move?");
						pawnPosition = scan.nextInt();
						
						board.setSpace(current, pawnPosition, cardValue);

						correct = true;
					}

					else if (response11.equals("SWITCH")) {
						//***not sure how to reference the pieces rather than the space they are on. this is definitely wrong***
						//switch piece with another player's piece
						System.out.println("Enter the space of the opponent you would like to switch with");
						int otherPosition = scan.nextInt();
						
						System.out.println("Which piece is yours?");
						pawnPosition = scan.nextInt();

						//***not sure if this is how exactly to go about doing this***
						//temporarily save the spaces you and your opponent were occupying

						// since it is handling information from the board, Im moving it to the board class. (Tyler)
						board.switchSpace(pawnPosition, otherPosition);

						correct = true;
					}

					else {

						System.out.println("You have entered an incorrect command -- 11");
						System.out.println("Would you like to move a piece or switch the position of your piece with another player's?");
						System.out.println("Please enter \"MOVE\" or \"SWITCH\"");
						response11 = scan.nextLine().toUpperCase();

					}//if-else
				} // while
			} // else-if

			else if (cardValue == 7) {
				//player may split the move between two pieces
				System.out.println("Which piece would you like to move?");
				pawnPosition = scan.nextInt();
				
				System.out.println("How many spaces would you like to move it (1-7)?");
				int move1 = scan.nextInt();

				if(move1 > 7) {
					System.out.println("Invalid number of spaces. Try again.");
					while (move1 > 7) {
						move1 = scan.nextInt();
					} // while
				} // if
				board.setSpace(current, pawnPosition, move1);

				//move the second piece
				System.out.println("Enter the space of the second piece you would like to move");
				int nextPawn = scan.nextInt();
				int move2 = 7 - move1;
				board.setSpace(current, nextPawn, move2);

			} // else-if

			else {
				System.out.println("Which piece would you like to move?");
				pawnPosition = scan.nextInt();
				
				board.setSpace(current, pawnPosition, cardValue);
			}//if-else
		}	
		} // movePiece

        public static Player currentPlayer(int turn) {
		
			int turns = turn;
		
            if (turns == 0) {
				//System.out.println("Returned RED");
                return red;
            }
            else if (turns == 1) {
				//System.out.println("Returned YELLOW");				
                return yellow;
            }
            else if (turns == 2) {
				//System.out.println("Returned BLUE");
                return blue;
            }
            else if (turns == 3) {
				//System.out.println("Returned GREEN");
                return green;
            } // else-if
			
            return red;
        } // currentPlayer()
		
		public static char currentPlayersColor(int turn) {
			int turns = turn;
			
            if (turns == 0) {
				//System.out.println("Returned 'r'");
                return 'r';
            }
            else if (turns == 1) {
				//System.out.println("Returned 'y'");				
                return 'y';
            }
            else if (turns == 2) {
				//System.out.println("Returned 'b'");
                return 'b';
            }
            else if (turns == 3) {
				//System.out.println("Returned 'g'");
                return 'g';
            } // else-if			
			
			return 'r';
		}
		
}//Game (class)

