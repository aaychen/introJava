import java.util.Scanner;
/**
 * Code breaking game where the player only gets 10 turns
 * to correctly guess the combination.
 *
 * @author Anna Chen
 * @version October 5, 2015
 */

public class PegMaster {

	boolean reveal;			// whether to reveal the master combination
	PegArray [] pegArray;	// the array of guess peg arrays
	PegArray master;		// the master, or key, peg array
	private final int TOTALPEGS, GUESSES;
	private int turn;
	private boolean win;

	public PegMaster() {
		reveal=win=false;
		TOTALPEGS=4;
		GUESSES=10;
		pegArray=new PegArray[GUESSES];
		for(int i=0; i<GUESSES; i++)
			pegArray[i]=new PegArray();
		master=new PegArray();
		turn=0;
	}

	public static void main(String[] args) {
		PegMaster pm=new PegMaster();
		pm.printIntroduction();
		pm.playGame();
		pm.gameOver();
	}

	/**
	 * Prints the introduction and instruction of the game.
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA PEGMASTER!                                                  |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of PegMaster is played on a four-peg gameboard, and six peg colors can    |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six colors (black, white, blue, green, yellow, and red).  Repeats are  |");
		System.out.println("| allowed, so there are 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master    |");
		System.out.println("| combination\" is then hidden from the player, and the player starts making guesses  |");
		System.out.println("| at the master combination.  The player has 10 turns to guess the combination.      |");
		System.out.println("| Each time the player makes a guess for the 4-peg combination, the number of exact  |");
		System.out.println("| matches and partial matches are then reported back to the user. If the player      |");
		System.out.println("| finds the exact combination, the game ends with a win.  If the player does not     |");
		System.out.println("| find the master combination after 10 turns, the game ends with a loss.             |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME PEGMASTER!                                                         |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}

	/**
	 * Prints the layout of the game board.
	 */
	public void printBoard() {						//reveal=true;
		System.out.println();
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println("| MASTER      1     2     3     4     5     6     7     8     9     10 |");
		System.out.println("+-------+  +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
		for (int row = 0; row < 4; row++) {
        
        	// reveal the master pegs
			if (reveal) System.out.printf("|   %c   |  |", master.getPeg(row).getLetter());
			else System.out.print("|  ***  |  |");
			for (int col = 0; col < 10; col++) {
				char c = pegArray[col].getPeg(row).getLetter();
				if (c != 'X') System.out.printf("  %c  |", c);
				else System.out.printf("     |");
			}
			System.out.println();
			System.out.println("+-------+  +-----+-----+-----+-----+-----+-----+-----+-----+-----+-----+");
		}
		System.out.print("| Exact    ");
		for (int col = 0; col < 10; col++) {
			int num = master.findMatches(master, pegArray, col, TOTALPEGS);
			if (num > -1) System.out.printf("   %d  ", num);
			else System.out.printf("      ");
		}
		System.out.println("|");
		System.out.print("| Partial  ");
		for (int col = 0; col < 10; col++) {
			int num;
			if(master.findMatches(master, pegArray, col, TOTALPEGS)==4)
				num = 0;
			else
				num = master.findPartials(master, pegArray, col, TOTALPEGS);
			if (num > -1) System.out.printf("   %d  ", num);
			else System.out.printf("      ");
		}
		System.out.println("|");
		System.out.println("+----------------------------------------------------------------------+");
		System.out.println();
	}

	/**
	 * Prompts user to start playing by pressing Enter key and
	 * begins playing the game.
	 */
	public void playGame() {
		PegArray pa=new PegArray();
		Prompt.getString("Hit the Enter key to start the game: ");
		setMaster();
		printBoard();
		int i=0;
		for(int t=1; t<=10; t++) {
			System.out.println("\n\tTurn "+t+"\n");
			guessPegs(i);
			int x=pa.findMatches(master, pegArray, i, TOTALPEGS);
			if(x==TOTALPEGS) {
				win=true;
				reveal=true;
				turn=t;
				t=100;
			}	
			if(t==10)
				reveal=true;
			printBoard();
			i++;
		}
	}

	/**
	 * Generates random characters (A-F) for the master key code
	 */
	public void setMaster() {
		for(int i=0; i<TOTALPEGS; i++) 
			master.getPeg(i).setLetter((char)((int)(Math.random()*6)+65)); // 65 is number value for 'A'
	}

	/**
	 * Prompts user to guess the pegs.
	 *
	 * @param i  The index of the entire guessing pegs array
	 */
	public void guessPegs(int i) {
		PegArray pa=new PegArray();
		for(int j=0; j<TOTALPEGS; j++) {
			int n=j+1; // n is peg number to guess
			String str="";
			while(str.length()<1)
				str=Prompt.getString("\tEnter the letter for peg "+n+" (A, B, C, D, E, or F): ").toUpperCase();
			char letter=str.charAt(0); 
			pegArray[i].getPeg(j).setLetter(letter);
		}
	}

	/**
	 * Prints out feedback to user at end of the game.
	 */
	public void gameOver() {
		System.out.println("\n");
		if(win)
			System.out.println("Nice work! You found the master code in "+turn+" turns.\n\n");
		else // lose
			System.out.println("Sorry, you were unable to find the solution in 10 turns.\n\n");
	}
}