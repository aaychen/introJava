import java.util.ArrayList;
/**
 * Sorts the array list of cities using merge sort.
 * 
 * @author Anna Chen
 * @version January 11, 2015
 */

public class CitySort {
	private static ArrayList<City> t;

	/**
	 * Splits the array list in half and calls merge to sort using recursion.
	 *
	 * @param list  An unprocessed array list of type City
	 * @param from  The "first" index of the array list (can be first of 
	 *		  a part of the list, not necessarily first of entire array list)
	 * @param to  The "last" index of the array list (can be last of a part 
	 * 		  of the list,not necessarily last of entire array list)
	 * @return the number of steps taken
	 */
	public static int mergeSort (ArrayList<City> a, int from, int to)
	{
		int steps = 0;
		t=new ArrayList<City>(a.size());
		for(int x=0; x<a.size(); x++) {
			t.add(a.get(x));
		}

		if(to-from<2) {
			if(to>from && a.get(to).compareTo(a.get(from))<0) { //if(to>from && a.get(to)<a.get(from)) {
				City temp=a.get(to);
				a.set(to, a.get(from));
				a.set(from, temp);
				
			}
			steps++;
		}
		else
		{
			int middle = (from + to) / 2;
			
			steps += mergeSort(a, from, middle);
			steps += mergeSort(a, middle + 1, to);
			steps += merge(a, from, middle, to);
		}
		return steps;
	}

	/**
	 * Sorts the array list by using merge sort.
	 *
	 * @param a  The array list of cities to sort
	 * @param from  The start index of a
	 * @param middle  The middle index of a
	 * @param to  The last index of a
	 * @return the number of steps taken
	 */
	public static int merge (ArrayList<City> a, int from, int middle, int to)
	{
		int steps = 0;
		int i=from, j=middle+1, k=from;
		
		//While both array have elements left unprocessed
		while(i<=middle && j<=to) {
			if(a.get(i).compareTo(a.get(j))<0) { //if(a.get(i)<a.get(j)) {
				t.set(k, a.get(i));
				i++;
			}
			else {
				t.set(k, a.get(j));
				j++;
			}
			k++;
			steps++;
		}
		
		//Copy tail of first half into temp
		while(i<=middle) {
			t.set(k, a.get(i));
			i++;
			k++;
			//steps++;
		}
		
		//Copy tail of second half into temp
		while(j<=to) {
			t.set(k, a.get(j));
			j++;
			k++;
			//steps++;
		}
		
		//Copy temp back into a
		for(k=from; k<=to; k++) 
			a.set(k, t.get(k));

		return steps;
	}
}