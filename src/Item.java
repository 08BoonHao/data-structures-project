
public class Item implements Comparable<Item>{

	private String name;
	private int weight;
	private int value;
	
	public Item(String name, int weight, int value)
	{
		this.name=name;
		this.weight=weight;
		this.value=value;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getWeight()
	{
		return weight;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int compareTo(Item i)
	{
		double ratio1=(double)this.value/(double)this.weight;
		double ratio2=(double)i.value/(double)i.weight;
		
		if(ratio1<ratio2)
			return 1;
		else if(ratio1>ratio2)
			return -1;
		else 
			if(this.value<i.value)
				return 1;
			else if(this.value>i.value)
				return -1;
			else
				return 0;
		
	}
	
	public String toString() {
		return name + "\t" + weight + "\t\t" + value;
	}
}
