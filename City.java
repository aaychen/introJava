/**
 * Represents one city.
 *
 * @author Anna Chen
 * @version January 4, 2016
 */
 
public class City implements Comparable<City> {
	private String city, state;
	private int population;

	public City(String c, String s, int p) {
		city=c;
		state=s;
		population=p;
	}

	public String getCity() { return city; }

	public String getState() { return state; }

	public int getPopulation() { return population; }
	
	/**
	 * Compares attributes of the two City objects
	 *
	 * @param other  Another City object to compare to
	 * @return An integer that shows whether this City object 
	 * 		   comes before or after other in the array list
	 */
	public int compareTo(City other) {
		if(!city.equals(other.city))
			return city.compareTo(other.city);
		if(!state.equals(other.state))
			return state.compareTo(other.state); 
		return population-other.population;
	}
	
	public boolean equals(City other) {
		if(this.compareTo(other)==0)
			return true;
		return false;
	}
}
	
