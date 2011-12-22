package les4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLTest 
{
	private XMLNode head;
	
	public XMLTest()
	{
		try 
		{
			// Load xml file
	        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = parser.parse(new File("src/les4/basis.xml"));
	        DOMSource source = new DOMSource(document);
	        
	        checkXML(source);
	        XMLToHTML(source);
	        XMLToXML(source);

	        NodeList nodes = document.getElementsByTagName("zaalvoetbal");
	        getNodeInfo(nodes.item(0), null);
	        System.out.println(head.toString());
	    }
		catch (ParserConfigurationException e) {e.printStackTrace();} 
		catch (SAXException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (TransformerConfigurationException e) {e.printStackTrace();} 
		catch (TransformerException e) {e.printStackTrace();} 
	}
	
	public void checkXML(DOMSource source) throws SAXException, IOException
	{
		// Load schema file
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(new File("src/les4/xmlschema.xsd"));
        Schema schema = factory.newSchema(schemaFile);

        // Validate xml by schema
        Validator validator = schema.newValidator();
        validator.validate(source);
	}
	
	public void XMLToHTML(DOMSource source) throws FileNotFoundException, TransformerException
	{
		// Transform xml file to html file
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Source xslFile = new StreamSource(new File("src/les4/basis.xsl"));
        Transformer transformer = tFactory.newTransformer(xslFile);
        transformer.transform(source, new StreamResult(new FileOutputStream("src/les4/basis.html")));
	}
	
	public void XMLToXML(DOMSource source) throws FileNotFoundException, TransformerException
	{
		// Transform xml file to different xml file
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Source XMLToXMLFile = new StreamSource(new File("src/les4/xmlnaarxml.xsl"));
        Transformer trans = tFactory.newTransformer(XMLToXMLFile);
        trans.transform(source, new StreamResult(new FileOutputStream("src/les4/basis.new.xml")));
	}
	
	public void getNodeInfo(Node n, XMLNode parent)
	{
		XMLNode xn = new XMLNode(n.getNodeName());
		NamedNodeMap att = n.getAttributes();
		
		if (att != null)
		{
			for (int i = 0; i < att.getLength(); i++)
			{
				String[] x = att.item(i).toString().split("=");
				Attribute a = new Attribute(x[0], Integer.parseInt(x[1].replace("\"", "")));
				xn.addAttribute(a);
			}
		}
		if (n.getNodeValue() == null || (n.getNodeValue() != null && !n.getNodeValue().trim().equals("")))
		{				
			if (n.getNodeValue() != null)
			{
				if (!n.getNodeName().equals("#text"))
				{
					xn.setValue(n.getNodeValue());
				}
				else
				{
					parent.setValue(n.getNodeValue());
				}
			}
			
			if (parent != null)
			{
				if (!n.getNodeName().equals("#text"))
				{
					parent.addChild(xn);
				}
			}
			else
			{
				head = xn;
			}
		}
		
		NodeList nodes = n.getChildNodes();
		for (int i = 0; i < n.getChildNodes().getLength(); i++)
		{
			if (!n.getNodeName().equals("#text"))
			{
				getNodeInfo(nodes.item(i), xn);
			}
			else
			{
				getNodeInfo(nodes.item(i), parent);
			}
		}
	}
	
	public static void main(String[] args)
	{
		new XMLTest();
	}
}
