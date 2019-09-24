/**
 * This class creates and manages the array of pegs in the game PegMaster.
 *
 * @author Anna Chen
 * @version October 2, 2015
 */
 
public class PegArray {
	
	private Peg [] pegs;
	
	// the number of exact and partial matches
	private int exactMatch, partialMatch;
	private boolean [] matched; // for exact matches
	
	public PegArray() {
		pegs = new Peg[4];
		for(int i=0; i<4; i++) 
			pegs[i] = new Peg();
		exactMatch = partialMatch = -1;
	}
	
	// n is the number of pegs in the array
	public PegArray(int n) {
		pegs = new Peg[n];
		for(int i=0; i<n; i++)
			pegs[i] = new Peg();
		exactMatch = partialMatch = -1;
	}
	
	/**
	 * Returns a single peg at a specified index of the pegs array.
	 *
	 * @param i  The index of the inner peg array (pegs)
	 * @return  The peg at the index of pegs array
	 */
	public Peg getPeg(int i) {
		return pegs[i];
	}

	/**
	 * Finds the number of exact matches between the player's guesses and 
	 * the master key code.
	 *
	 * @param master  The master key code
	 * @param pegArray  The player's guesses
	 * @param col  The column of the guess board being checked
	 * @param totalPegs  The total number of pegs
	 * @return the number of exact matches
	 */
	public int findMatches(PegArray master, PegArray[] pegArray, int col, int totalPegs) {
		matched=new boolean [totalPegs];
		exactMatch=-1;
		if(pegArray[col].getPeg(1).getLetter()!='X')  exactMatch=0;
		if(exactMatch==0) {
			for(int z=0; z<totalPegs; z++) {
				if(pegArray[col].getPeg(z).getLetter()==master.getPeg(z).getLetter()) {
					exactMatch++;
					matched[z]=true;
				}
			}
		}
		return exactMatch;
	}
	
	/**
	 * Finds the number of partial matches between the player's guesses and 
	 * the master key code.
	 *
	 * @param master  The master key code
	 * @param pegArray  The player's guesses
	 * @param col  The column of the guess board being checked
	 * @param totalPegs  The total number of pegs
	 * @return the number of partial matches
	 */
	public int findPartials(PegArray master, PegArray[] pegArray, int col, int totalPegs) {
		partialMatch=-1;
		if(pegArray[col].getPeg(1).getLetter()!='X')  partialMatch=0; 
		if(partialMatch==0) {
			String key="", guesses="";
			for(int y=0; y<totalPegs; y++) {
				key+=master.getPeg(y).getLetter();
				guesses+=pegArray[col].getPeg(y).getLetter();
			}
			for(int z=0; z<totalPegs; z++) {
				if(matched[z]==false) {
					int i=guesses.indexOf(key.charAt(z));
					if(i!=-1)  { // match found
						if(i!=z) {
							partialMatch++;
							guesses=guesses.substring(0, i)+" "+guesses.substring(i+1);
						}
					}
				}
			}
		}
		return partialMatch;
	}
}