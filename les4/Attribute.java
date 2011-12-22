package les4;

public class Attribute 
{
	private String name;
	private int value;
	
	public Attribute(String nameIn, int valueIn)
	{
		setName(nameIn);
		setValue(valueIn);
	}
	
	public void setName(String nameIn)
	{
		name = nameIn;
	}
	
	public void setValue(int valueIn)
	{
		value = valueIn;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public String toString()
	{
		return getName() + "=\"" + getValue() + "\"";
	}
}
