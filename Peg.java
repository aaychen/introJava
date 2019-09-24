/**
 * This class defines a peg in the game PegMaster.
 *
 * @author Anna Chen
 * @version October 2, 2015
 */
 
public class Peg {

	// value 'A' through 'F' and 'X'
	private char letter;
	
	public Peg() { 
		letter = 'X'; 
	}
	
	public Peg(char l) { 
		letter = l; 
	}
	
	/**
	 * Returns the letter of the peg.
	 * @return The letter of the peg
	 */
	public char getLetter() { 
		return letter;
	}
	
	/**
	 * Sets the letter of the peg.
	 */
	public void setLetter(char l) {
		letter = l; 
	}
}