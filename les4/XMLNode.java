package les4;

import java.util.ArrayList;

public class XMLNode 
{
	private String name;
	private String value;
	private ArrayList<XMLNode> childs;
	private ArrayList<Attribute> attributes;
	
	public XMLNode(String nameIn)
	{
		childs = new ArrayList<XMLNode>();
		attributes = new ArrayList<Attribute>();
		setName(nameIn);
	}
	
	public void setName(String nameIn)
	{
		name = nameIn;
	}
	
	public void setValue(String valueIn)
	{
		value = valueIn;
	}
	
	public void addChild(XMLNode child)
	{
		childs.add(child);
	}
	
	public void addAttribute(Attribute att)
	{
		attributes.add(att);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public ArrayList<XMLNode> getChilds()
	{
		return childs;
	}
	
	public ArrayList<Attribute> getAttributes()
	{
		return attributes;
	}
	
	public String toString()
	{
		String result = "<" + getName();
		
		ArrayList<Attribute> att = getAttributes();
		for (int i = 0; i < att.size(); i++)
		{
			result += " " + att.get(i).toString();
		}
		
		result += ">";
		
		if (getValue() != null)
		{
			result += getValue();
		}
		
		ArrayList<XMLNode> chi = getChilds();
		for (int i = 0; i < chi.size(); i++)
		{
			result += chi.get(i).toString();
		}
		
		result += "</" + getName() + ">\n";
		
		return result;
	}
}
