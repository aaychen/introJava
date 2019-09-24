import java.util.Scanner;
import java.util.ArrayList;
/**
 * Uses an inputted city name and database to find all cities that
 * match. The matches are outputted in a sorted list with their state
 * initials and population counts.
 *
 * @author Anna Chen
 * @version January 4, 2016
 */
 
public class Population {
	private String city;
	private ArrayList<City> cityDB, foundCities;
	
	public Population() {
		city="";
		cityDB=new ArrayList<City>();
		foundCities=new ArrayList<City>();
	}
	
	public static void main(String [] args) {
		Population p=new Population();
		p.addToDB();
		p.sortEntireDB();
		int x=0;
		while(x==0) {
			p.setCityName();
			p.findCity();
		}
	}
	
	/**
	 * Prompts the user to enter a city name to find in the database.
	 */
	public void setCityName() {
		foundCities.clear();
		city=Prompt.getString("Enter the city name to find (q to Quit) ");
		if(city.equals("q"))	
			System.exit(0);
	}
	
	/**
	 * Uses the class OpenFile to read file usCities.txt and saves each
	 * line after the first line to an array list.
	 */
	public void addToDB() {
		System.out.println("\nReading database from file usCities.txt\n");
		Scanner infile=OpenFile.openToRead("usCities.txt");
		boolean firstLine=true;
		while(infile.hasNext()) { 
			String str=infile.nextLine();
			if(!firstLine) {
				String[] x=str.split(",");
				int p=Integer.parseInt(x[2]);
				City c=new City(x[0], x[1], p);
				cityDB.add(c);
			}
			firstLine=false;
		}	
	}
	
	/**
	 * Uses merge sort to sort the entire database by city name 
	 * alphabetically.
	 */
	public void sortEntireDB() {
		System.out.println("Sorting database\n");
		CitySort.mergeSort(cityDB, 0, cityDB.size()-1);
	}

	/**
	 * Uses binary search to find the inputted city name in the entire
	 * database and adds it to a separate array list. The separate array
	 * list is then sorted using merge sort after no more cities with 
	 * the same name exist in the database.
	 */
	public void findCity() {
		ArrayList<City> temp=new ArrayList<City>(cityDB.size());
		for(City c: cityDB)
			temp.add(c);
		int x=0, count=0;
		while(x!=-1) {
			x=BinarySearch.binarySearch(temp, city);
			if(x!=-1) {
				foundCities.add(temp.get(x));
				temp.remove(x);
				count++;
			}
			else {
				if(count==0)
					System.out.println("\nNo cities found in database that match "+city+"\n");
				else {
					CitySort.mergeSort(foundCities, 0, foundCities.size()-1);
					output();
				}
			}
		}
	}
	
	/**
	 * Prints a sorted list of all the cities with the matching name to the 
	 * screen, along with the state initials and population counts.
	 */
	public void output() {
		System.out.print("\nCities that match:\n\t");
		System.out.printf("%-18s%-10s%10s", "City", "State", "Population");
		System.out.print("\n\t--------------    -----     ----------\n");

		for(City c: foundCities) 
			System.out.printf("\t%-20s%-5s%13d\n", c.getCity(), c.getState(), c.getPopulation());
		
		System.out.println();
	}
	
}