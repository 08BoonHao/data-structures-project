import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

public class Memoization implements Algorithm{
	
	private SortedMap<Integer,LinkedList<Item>> memory;
	
	public Memoization()
	{
		memory=new TreeMap<Integer,LinkedList<Item>>();
		memory.put(0, null);
	}
	
	public LinkedList<Item> solve(PriorityQueue<Item> queue, int capacity)
	{
		displayMemory();
		LinkedList<Item> knapsack=new LinkedList<Item>();
		if(capacity==0)
		{
			return knapsack;
		}
		else if(capacity>memory.lastKey())
		{
			if(memory.lastKey()==0)
				return solve(queue,capacity,capacity,knapsack);
			else
			{
				PriorityQueue<Item> queueToCompare = new PriorityQueue<Item>(queue);
				LinkedList<Item> result = memory.get(memory.lastKey());
				
				System.out.println("Solution for knapsack with capacity = "+memory.lastKey()+" is found in the memory table.");
				System.out.println("Items added into the knapsack: ");
				
				for(Item item:result)
				{
					Item i=queueToCompare.poll();
					if(item.equals(i))
					{
						queue.remove(i);
						knapsack.add(i);
						System.out.println(i.getName()+"("+i.getWeight()+"kg)");
					}
				}
				System.out.println();
				int currentCapacity=computeWeight(knapsack);
				System.out.println("Current capacity: "+currentCapacity+"kg");
				int capacityLeft=capacity-currentCapacity;
				System.out.println("Capacity left: "+capacityLeft+"kg");
				System.out.println();
				return solve(queue,capacity,capacityLeft,knapsack);
			}
		}
		else
		{
			int memoIndex=capacity;
			while(!memory.containsKey(memoIndex))
				memoIndex--;
			LinkedList<Item> results= new LinkedList<Item>(memory.get(memoIndex));
			Iterator<Item> iterator= results.iterator();
			System.out.println("Solution for knapsack with capacity = "+memoIndex+" is found in the memory table.");
			System.out.println("Items added into the knapsack: ");
			while(iterator.hasNext())
			{
				Item i=iterator.next();
				queue.remove(i);
				knapsack.add(i);
				System.out.println(i.getName()+"("+i.getWeight()+"kg)");
			}
			System.out.println();
			int currentCapacity=computeWeight(knapsack);
			System.out.println("Current capacity: "+currentCapacity+"kg");
			int capacityLeft=capacity-currentCapacity;
			System.out.println("Capacity left: "+capacityLeft+"kg");
			System.out.println();
			return solve(queue,capacity,capacityLeft,knapsack);
		}
		
	}
	
	//recursive helper method for adding items
	public LinkedList<Item> solve(PriorityQueue<Item> queue, int totalCapacity, int capacityLeft, LinkedList<Item> knapsack)
	{
		if(capacityLeft==0)
		{
			System.out.println("Solution for knapsack with capacity of "+totalCapacity+"kg generated.");
			System.out.println();
			return knapsack;
		}
		//perform recursive calculation
		else
		{
			if(!queue.isEmpty())
			{
				Item i=queue.peek();
				System.out.println("Item: "+i.getName()+"("+i.getWeight()+"kg)");
				
				if(i.getWeight()<=capacityLeft)
				{
					queue.poll();
					knapsack.add(i);
					System.out.println("Added.");
					capacityLeft-=i.getWeight();
					int memoIndex=totalCapacity-capacityLeft;
					System.out.println("Current capacity: "+memoIndex+"kg");
					System.out.println("Capacity left: "+capacityLeft+"kg");
					if(!memory.containsKey(memoIndex))
					{
						LinkedList<Item> result=new LinkedList<Item>(knapsack);
						memory.put(memoIndex, result);    //add the result into memory for later use
						System.out.println("Solution for knapsack with "+memoIndex+"kg is added into memory table.");
					}
					System.out.println();
					return solve(queue,totalCapacity,capacityLeft,knapsack);
				}
				else
				{
					queue.poll();
					System.out.println("NOT added.");
					System.out.println();
					return solve(queue,totalCapacity,capacityLeft,knapsack);	
				}
			}
			else
			{
				System.out.println("Knapsack solution for "+totalCapacity+"kg generated.");
				if(!memory.containsKey(totalCapacity))
				{
					LinkedList<Item> result=new LinkedList<Item>(knapsack);
					memory.put(totalCapacity, result); //add the result into memory for later use
					System.out.println("Solution for knapsack with "+totalCapacity+"kg is added into memory table.");
				}
				System.out.println();
				return knapsack;
			}		
		}
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
	
	public void displayMemory()
	{
		if(memory.lastKey()==0)
		{
			System.out.println("No solution is stored in the memory yet.");
			System.out.println();
		}
		else
		{
			System.out.println("------------------------------");
			System.out.println("  Solutions stored in memory  ");
			System.out.println("------------------------------");
			for(int i=1;i<=memory.lastKey();i++)
			{
				if(memory.containsKey(i))
				{
					Iterator<Item> iterator=memory.get(i).iterator();
					System.out.print(i+"kg knapsack: ");
					while(iterator.hasNext())
					{
						System.out.print(iterator.next().getName()+", ");
					}
					System.out.println();
				}
			}
			System.out.println();
		}
	}
}