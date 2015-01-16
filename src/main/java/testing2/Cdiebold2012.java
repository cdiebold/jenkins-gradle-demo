package testing2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author christopherdiebold
 * 
 */

public class Cdiebold2012 {
	public static class Pair{
		private int x;
		private int y;

		public Pair(int x, int y) {
			this.setX(x);
			this.setY(y);
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isEqual(Pair other) {
			if (this.getX() == other.getX() && this.getY() == other.getY())
				return true;
			else
				return false;

		}

		public boolean inverse(Pair other) {
			if (other.getX() == this.getY() && other.getY() == this.getX())
				return true;
			else
				return false;
		}
	}

	Pair p;

	HashMap<Integer, Set<Integer>> pairMap;
	int universeSize;

	public Cdiebold2012() {
		pairMap = new HashMap<Integer, Set<Integer>>();
		universeSize = 0;
	}

	public Cdiebold2012(int universeSize) {
		pairMap = new HashMap<Integer, Set<Integer>>();
		this.universeSize = universeSize;
	}

	public Cdiebold2012(int universeSize, HashMap<Integer, Set<Integer>> map) {
		this.universeSize = universeSize;
		pairMap = map;
	}

	/**
	 * A function is one to one if every element in the domain corresponds to
	 * exactly one element in the range. Example: Given the set of points: (1,2)
	 * (2,4) (3,6) This is 1-1 since every x maps only to one y Example: Given
	 * the set of points: (1,2) (1,3) (2,4) This is NOT 1-1 since 1 maps to 2
	 * and 3.
	 * 
	 * @param p
	 * @return
	 */
	public boolean is_one_to_one() {
		// loop through values and see if an ArrayList.size() > 1
		for (Set<Integer> al : this.pairMap.values()) {
			if (al.size() > 1)
				return false;
		}
		return true;
	}

	/**
	 * A function is onto if in a set every Y (range or codomain) is mapped.
	 * Example: Given the set of points: (1,1) (2,2) (3,2) This is onto since
	 * every y is mapped to an x. Example: Given the set of points: (1,1) (2,1)
	 * (3,1) This is NOT onto since every y is NOT mapped to an x. In this
	 * example nothing maps to 2.
	 * 
	 * @param pairList
	 * @return
	 */

	public boolean is_onto() 
	{
		// loop through values and see if an ArrayList.size() ==0
		int err = 0;
		Iterator<Set<Integer>> vals = pairMap.values().iterator();
		while(vals.hasNext())
		{
			//err = 0;
			Object [] v =  vals.next().toArray();
			for(int i = 1; i <= universeSize; i ++)
			{
				for(int j = 0; j < v.length; j++)
				{
					if(v[j].equals(i))
					{
						err++;
					}
				}
				
			}
		}
		if(err == universeSize)
			return true;
		
		return false;
			
	}

	/**
	 * A reflexive relation is a binary relation on a set for which every
	 * element is related to itself. In other words, a relation ~ on a set S is
	 * reflexive when x ~ x holds true for every x in S. Example: Given the set
	 * of points: (1,1) (2,2) (3,3) This is reflexive since every x maps to
	 * itself Example: Given the set of points: (1,1) (2,2) (3,5) This is NOT
	 * reflexive since every x does NOT map to itself
	 * 
	 * @param p
	 * @return
	 */
	public boolean is_reflexive() {

		for (Map.Entry<Integer, Set<Integer>> entry : this.pairMap.entrySet()) {
			int key = entry.getKey();
			Collection<Set<Integer>> al = this.pairMap.values();
			for(Set<Integer> temp : al){
				if (temp.contains(key)) {
					return true;
				}
			}
			
		}

		return false;
	}

	/**
	 * For a point to be symmetric Example: given the point P(1,2) a symmetric
	 * point is P2(2,1)
	 * 
	 * @param pairList
	 * @return
	 */
	public boolean is_symmetric() {
		for (int i = 0; i <= this.universeSize; i++) {
			Set<Integer> temp = this.pairMap.get(i);
			if (temp != null) {
				Iterator<Integer> it = temp.iterator();
				for (int j = 0; j < temp.size(); j++) {
					int test = it.next();
					if (this.pairMap.get(test) != null) {
						if (!this.pairMap.get(test).contains(i)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public boolean is_transitive() {
		for (int i = 0; i <= this.universeSize; i++) {
			Set<Integer> temp = this.pairMap.get(i);
			if (temp != null) {
				Iterator<Integer> it = temp.iterator();
				for (int j = 0; j < temp.size(); j++) {
					int test = it.next();
					Set<Integer> secTemp = this.pairMap.get(test);
					if (secTemp != null) {
						Iterator<Integer> it2 = secTemp.iterator();
						for (int k = 0; k < secTemp.size(); k++) {
							int test2 = it2.next();
							if (test2 != i) {
								if (!this.pairMap.get(i).contains(test2)) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Given a Pair(x,y) a function is defined as every x pointing to exactly
	 * one y
	 * 
	 * @param map
	 * @return
	 */
	public boolean is_a_function() {
		// loop through values and see if an ArrayList.size() ==0
		for (Set<Integer> al : this.pairMap.values()) {
			if (al.size() != 1)
				return false;
		}
		return true;
	}

	/**
	 * To be an equivalence relation the following properties must hold: must me
	 * reflexive must be symmetric must be transitive
	 * 
	 * @param pairList
	 * @param universeSize
	 * @param map
	 * @return
	 */
	public boolean is_equivalence_relation() {
		if (is_reflexive() && is_symmetric() && is_transitive())
			return true;

		return false;
	}

	/**
	 * The functions prints out the equivalence classes.
	 * 
	 * @param map
	 *            -> HashMap<Integer, Set<Integer>>
	 */
	public void printPartitions() {

		Iterator<Entry<Integer, Set<Integer>>> it = this.pairMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Set<Integer>> pairs = (Map.Entry<Integer, Set<Integer>>) it
					.next();
			// int k = pairs.getKey();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
		}
	}

	public static void main(String[] args) {
		Scanner in = null;
		ArrayList<Pair> pairList = new ArrayList<Pair>();

		Pair pair;
		Cdiebold2012 inst = null;
		int universeSize = 1;

		if (args.length < 1) {
			in = new Scanner(System.in);// take in user input from the keyboard
			universeSize = in.nextInt(); // first number entered.

			while (in.hasNextInt()) {
				try {
					int x = in.nextInt();
					int y = in.nextInt();

					if ((x < 1 || x > universeSize)
							|| (y < 1 || y > universeSize)) {
						throw new InputMismatchException();
					}
					pair = new Pair(x, y);
					// pairMap.put(pair.getX(), pair.getY());
					pairList.add(pair);
				} catch (InputMismatchException e) {
					System.out
							.println("Bad Input. Your Pair (x,y) must be >= 1 and cannot exceed size: "
									+ universeSize);
					System.exit(1);
				}
			}
			in.close();
		} else {
			File file = null;
			try {
				file = new File(args[0]);
				in = new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out
						.println("File not found. Double check your file name for file: "
								+ file);
				System.exit(1);
			}
			universeSize = in.nextInt(); // first number entered.
			while (in.hasNextInt()) {
				try {
					int x = in.nextInt();
					int y = in.nextInt();

					if ((x < 1 || x > universeSize)
							|| (y < 1 || y > universeSize)) {
						throw new InputMismatchException();
					}
					pair = new Pair(x, y);
					pairList.add(pair);
				} catch (InputMismatchException e) {
					System.out
							.println("Bad Input. Your Pair (x,y) must be >= 1 and cannot exceed size: "
									+ universeSize);
					System.exit(1);
				}
			}
			in.close();

		}
		HashMap<Integer, Set<Integer>> pairMap = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < pairList.size(); i++) {
			Set<Integer> x = new TreeSet<Integer>();// A TreeSet is sorted.
			int key = pairList.get(i).getX(); // the key to the hashmap.
			for (int j = 0; j < pairList.size(); j++) {
				if (pairList.get(j).getX() == key)
					x.add(pairList.get(j).getY());

			}
			pairMap.put(key, x);

			inst = new Cdiebold2012(universeSize, pairMap);
		}

		// function calls here.....
		printTable(inst);
		//System.out.println(inst.is_onto());

	}

	public static void printTable(Cdiebold2012 obj) {

		String[] message = new String[8];
		message[0] = "Relations:";
		message[1] = "Is the relation one to one?";
		message[2] = "Is the relation onto?";
		message[3] = "Is the relation reflexive?";
		message[4] = "Is the relation symmetric?";
		message[5] = "Is the relation transitive?";
		message[6] = "Is the relation a function?";
		message[7] = "Is the relation an equivalence relation?";

		System.out
				.println("--------------------------------------------------------------------");
		System.out.printf("%-50s%n", message[0]);
		System.out
				.println("--------------------------------------------------------------------");
	
		System.out.printf("%-50s%5b%n", message[1], obj.is_one_to_one());
		System.out.printf("%-50s%5b%n", message[2], obj.is_onto());
		System.out.printf("%-50s%5b%n", message[3], obj.is_reflexive());
		System.out.printf("%-50s%5b%n", message[4], obj.is_symmetric());
		System.out.printf("%-50s%5b%n", message[5], obj.is_transitive());
		System.out.printf("%-50s%5b%n", message[6], obj.is_a_function());
		System.out.printf("%-50s%5b%n", message[7],
				obj.is_equivalence_relation());
		System.out
				.println("--------------------------------------------------------------------");
		System.out.printf("%s%n", "Equivalence Class Partitions: ");
		System.out
				.println("--------------------------------------------------------------------");
		if (!obj.is_equivalence_relation())
			System.out
					.println("Not an equivalence relation, so no partitions to print!!");
		else {
			obj.printPartitions();
		}
	}

}
