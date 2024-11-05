import java.util.LinkedList;
import java.util.PriorityQueue;

public interface Algorithm {

	public LinkedList<Item> solve(PriorityQueue<Item> queue, int capacity);
	public int computeWeight(LinkedList<Item> list);
	public int computeValue(LinkedList<Item> list);
	
}
