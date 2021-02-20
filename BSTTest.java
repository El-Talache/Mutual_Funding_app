import java.util.Comparator;

public class BSTTest {

	public static void main(String args[]) {

		BST<Integer> Tree1 = new BST<Integer>();
		System.out.println("Empty Tree1 height: " + Tree1.getHeight());
		Tree1.insert(5, new IntComparator());
		Tree1.insert(4, new IntComparator());
		Tree1.insert(7, new IntComparator());
		System.out.println("Tree1 in, pre and post print:");
		Tree1.inOrderPrint();
		Tree1.preOrderPrint();
		Tree1.postOrderPrint();

		BST<Integer> Tree2 = new BST<Integer>(Tree1, new IntComparator());

		System.out.println("\nTree 2 in, pre and post print: ");
		Tree2.inOrderPrint();
		Tree2.preOrderPrint();
		Tree2.postOrderPrint();

		BST<Integer> Tree3 = new BST<Integer>();

		Tree3.insert(40, new IntComparator());
		Tree3.insert(25, new IntComparator());
		Tree3.insert(78, new IntComparator());
		Tree3.insert(10, new IntComparator());
		Tree3.insert(32, new IntComparator());

		System.out.println("\nTree 3: ");
		Tree3.inOrderPrint();
		Tree3.preOrderPrint();
		Tree3.postOrderPrint();

		/*
		 * Tree3.insert(30, new IntComparator()); Tree3.insert(455, new
		 * IntComparator()); Tree3.insert(72, new IntComparator()); Tree3.insert(69, new
		 * IntComparator()); Tree3.insert(36, new IntComparator());
		 */

		System.out.println("\nTree 3: ");
		Tree3.inOrderPrint();
		Tree3.preOrderPrint();
		Tree3.postOrderPrint();

		System.out.println("\nTree 3 Root: ");
		System.out.println(Tree3.getRoot());
		System.out.println("\nTree 3 Min: ");
		System.out.println(Tree3.findMin());
		System.out.println("\nTree 3 Max: ");
		System.out.println(Tree3.findMax());
		System.out.println("\nTree 3 Height: ");
		System.out.println(Tree3.getHeight());
		System.out.println("\nTree 3 size: ");
		System.out.println(Tree3.getSize());
		System.out.println("\nTree 3 empty?: ");
		System.out.println(Tree3.isEmpty());

		System.out.println("\nIs 40 in Tree 3? " + Tree3.search(40, new IntComparator()));
		System.out.println("Removing 40... ");
		Tree3.remove(40, new IntComparator());
		System.out.println("\nTree 3 Rootafter removing 40: ");
		System.out.println(Tree3.getRoot());
		System.out.println("\nTree 3 size after removing 40: ");
		System.out.println(Tree3.getSize());
		System.out.println("\nTree 3 inorderprint after removing 40: ");
		Tree3.inOrderPrint();
		System.out.println("\nIs 40 in Tree 3? " + Tree3.search(40, new IntComparator()));
		System.out.println("\nTree 3 empty?: ");
		System.out.println(Tree3.isEmpty());

		BST<Integer> Tree4 = new BST<Integer>(Tree3, new IntComparator());
		System.out.println("______________________________");

		System.out.println("\nTree 4: ");
		Tree4.inOrderPrint();
		Tree4.preOrderPrint();
		Tree4.postOrderPrint();
		System.out.println("\nIs 78 in Tree 4? " + Tree4.search(78, new IntComparator()));
		System.out.println("\nTree 4 Min: ");
		System.out.println(Tree4.findMin());
		System.out.println("\nTree 4 Max: ");
		System.out.println(Tree4.findMax());

		System.out.println("Removing 78... ");
		Tree4.remove(78, new IntComparator());
		System.out.println("\nRoot of Tree 4 after removing 78: ");
		System.out.println(Tree4.getRoot());
		System.out.println("\nTree 4 size after removing 78: ");
		System.out.println(Tree4.getSize());
		System.out.println("\nTree 4 inorderprint after removing 78: ");
		Tree4.inOrderPrint();
		System.out.println("\nIs 78 in Tree 3? " + Tree4.search(78, new IntComparator()));

	}

}

class IntComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		return Double.compare(o1, o2);
	}

}