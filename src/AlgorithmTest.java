import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AlgorithmTest {

	public static void main(String[] args)
	{
				
		PriorityQueue <Item> queue = new PriorityQueue<Item>();
		Algorithm gm = new GreedyMethod();
		Algorithm memo=new Memoization();
		Scanner scanner=new Scanner(System.in);
		String filename = "input.txt";
		String data;
				
		Scanner input;
		try {
			input = new Scanner(new File(filename));
			while(input.hasNext()) {
				data = input.nextLine();
				String[] words = data.split(",");
				Item item=new Item(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]));
				for(int i=0;i<Integer.parseInt(words[3]);i++)
					queue.offer(item);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR : input.txt file is missing.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("ERROR : input.txt file data have incorrect format");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
				System.out.println("ERROR : Argument is invalid");
			e.printStackTrace();
		} 
		
		printAllItems(queue);
		int choice;
		do 
		{
			//queueForGreedy and queueForDynamic have the same items
			PriorityQueue <Item> queueForGreedy = new PriorityQueue<Item>(queue);
			PriorityQueue <Item> queueForMemoization = new PriorityQueue<Item>(queue);
		
		
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println();
			
			//Requires maximum capacity from user
			System.out.print("Enter the maximum capacity(kg) of your luggage: ");
			int knapsackCapacity=scanner.nextInt();
			
			System.out.println();
			System.out.println("Using greedy algorithm: ");
			System.out.println();
			
			long start = System.currentTimeMillis();
			LinkedList<Item> knapsack = gm.solve(queueForGreedy, knapsackCapacity);
			long end = System.currentTimeMillis();
			printKnapsackItem(knapsack);
			
			System.out.println();
			System.out.println("Total value are " + gm.computeValue(knapsack)+".");
			
			System.out.println("Knapsack with maximum of "+knapsackCapacity+"kg has weight of " + gm.computeWeight(knapsack) + "kg.");
			System.out.println();
			System.out.println("The time spent to solve the knapsack problem using Greedy Algorithm is "+calculateTimeSpent(start,end)+"ms.");
			
			System.out.println("--------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Using Memoization algorithm: ");
			System.out.println();
			
			start=System.currentTimeMillis();
			knapsack = memo.solve(queueForMemoization, knapsackCapacity);
			end=System.currentTimeMillis();
			printKnapsackItem(knapsack);
			
			System.out.println();
			System.out.println("Total value are " + memo.computeValue(knapsack)+".");
			
			System.out.println("Knapsack with maximum of "+knapsackCapacity+"kg has weight of " + gm.computeWeight(knapsack) + "kg.");
			System.out.println();
			System.out.println("The time spent to solve the knapsack problem using Memoization Algorithm is "+calculateTimeSpent(start,end)+"ms.");
			
			System.out.println();
			System.out.println("Do you want to calculate using another knapsack capacity ?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			choice=scanner.nextInt();
			while(choice!=1&&choice!=2)
			{
				System.out.print("Enter 1 or 2: ");
				choice=scanner.nextInt();
			}
		}while(choice==1);
		
		scanner.close();
	}
	
	public static void printAllItems(PriorityQueue <Item> queue) {
		PriorityQueue<Item> printItemQueue = new PriorityQueue<Item>(queue);
		System.out.println("Item\tWeight(kg)\tValue");
		while(printItemQueue.size() > 0) {
			System.out.println(printItemQueue.remove().toString());
		}
	}

	public static void printKnapsackItem(LinkedList<Item> list) {
		if(!list.isEmpty())
		{
			System.out.println("Items include in knapsack: ");
			int i = 1;
			for (Item b: list) {
				System.out.println(i + ". " + b.getName());
				i++;
			}
		}
		else
			System.out.println("No items are found in the knapsack.");
	}
	
	public static long calculateTimeSpent(long startTime, long endTime)
	{
		return endTime-startTime;
	}
}
