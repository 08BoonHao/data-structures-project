import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/*
 * Algorithm:
 * 1) Sort all the items according to the descending order of value-per-weight ratio
 * 2) Iterate through all the sorted items:
 * 	2.1) If adding the current item doesn't exceed the current capacity,
 *  	2.1.1)  add current item value to the total value
 *  	2.1.2)  minus the current capacity with the item weight
 *      2.1.3)  add current item weight to the current weight
 *      2.1.4) 	add the item to the tree set
 */

public class GreedyMethod implements Algorithm{
	
	public LinkedList<Item> solve (PriorityQueue <Item> queue, int capacity){
		PriorityQueue <Item> queue2 = new PriorityQueue<Item> ();
		LinkedList<Item> knapsackSet = new LinkedList<Item>();
		
		//Add the items in queue to the new priority queue
		while(queue.size() > 0)
			queue2.add(queue.remove());
		/*
		 * Iterate through the sorted items
		 * if adding the current item doesn't exceed the current capacity, 
		 * 	add its value to the total value
		 *  minus the current capacity with the item weight
		 * 	add its weight to the current weight
		 * 	add the item to the linked list 
		 */
			while (queue2.size() > 0 && capacity > 0) {
				Item currentItem = queue2.poll();
				if (currentItem.getWeight() <= capacity) {
					capacity -= currentItem.getWeight();
					knapsackSet.add(currentItem);
				} 
			}
		return knapsackSet;
	}
	
	public int computeWeight(LinkedList<Item> list)
	{
		if(list!=null) 
		{
			Iterator<Item> iterator = list.iterator();
			int sum=0;
			while(iterator.hasNext())
				sum+=iterator.next().getWeight();
			
			return sum;
		}
		else return 0;  //return 0 when list == null
	}
	
	public int computeValue(LinkedList<Item> list)
	{
		if(list!=null) 
		{
			Iterator<Item> iterator = list.iterator();
			int sum=0;
			while(iterator.hasNext())
				sum+=iterator.next().getValue();
			
			return sum;
		}
		else return 0;
	}
}