public class Player {

	private static char color;
	private static boolean gameOver = false;
	boolean pawnIsOut = false;
    private static int piecesInStart;
    private static int piecesInHome;

    public Player(char c){

		color = c;
		piecesInStart = 4;

    }//Player(constructor)

	public static char getColor() {

		return color;
	} // gets player Color

	public boolean isPawnOut() {
		if (piecesInStart == 4) {
			pawnIsOut = false;
		}
		else {
			pawnIsOut = true;
		}
		
		return pawnIsOut;
	} // does a player have a pawn out?

	public static void pawnEscape() {
        piecesInStart--;
	} // pawnEspace gets set to true when a player has a pawn on the board

	public static boolean gameOver() {

        if (piecesInHome == 4) {
			gameOver = true;
		}

		return gameOver;
	}
	public static void pawnMadeItHome() {
		piecesInHome++;
	}
	public static void addToStart() {
		piecesInStart++;
	}

	/* Removed color() method: this is handled by the constructor (Tyler)*/

}//Player (class)
