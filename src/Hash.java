import java.util.Arrays;
import java.util.Scanner;

public class Hash {
	public static int[] hash;
	public static int[] collisions;
	public static int total_collisions = 0;
	public static int total_comparisons = 0;

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		String query = "y";

		while (query.equalsIgnoreCase("y")) {
			System.out.print("Enter the number of items: ");
			int numberOfItems = scan.nextInt();
			System.out.print("Enter the load factor: ");
			double loadFactor = scan.nextDouble();
			int arrayLength = (int) Math.round(Math.pow(loadFactor, -1) * numberOfItems);

			hash = new int[arrayLength];
			Arrays.fill(hash, 0);
			collisions = new int[arrayLength];
			Arrays.fill(collisions, 0);
			total_collisions = 0;
			total_comparisons = 0;

			makeHash(numberOfItems, arrayLength);
			printHash();

			scan.nextLine();
			System.out.print("Run again?[y/n]");
			query = scan.nextLine();
			System.out.println();
		}
	}

	public static void makeHash(int numberOfItems, int arrayLength) {
		int constant;
		for (int i = 0; i < numberOfItems; i++) {
			String str = "Item " + i;
			int hashcode = str.hashCode();
			int index = Math.abs(hashcode % arrayLength); // 0 to 19
			constant = 2;
			
			while (hash[index] != 0) {
				index = Math.abs((hashcode + constant * constant) % arrayLength);
				constant++;
				++total_comparisons;
			}
			
			hash[index] = hashcode;
			++collisions[index];
		}
		for (int x : collisions) {
			if (x > 0)
				--x;
			total_collisions += x;
		}
	}

	public static void printHash() {
		for (int i = 0; i < hash.length; i++)
			System.out.println("Index: " + i + " Hash: " + hash[i]);
		System.out.println("Total collisions: " + total_collisions);
		System.out.println("Total comparisons: " + total_comparisons);
	}
}
