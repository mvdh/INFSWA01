package les4;

import java.io.File;
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
import org.xml.sax.SAXException;

public class XMLTest 
{
	public XMLTest()
	{
		try 
		{
			// Load xml file
	        DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        Document document = parser.parse(new File("src/les4/basis.xml"));
	        
	        // Load schema file
	        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Source schemaFile = new StreamSource(new File("src/les4/xmlschema.xsd"));
	        Schema schema = factory.newSchema(schemaFile);
	
	        // Validate xml by schema
	        Validator validator = schema.newValidator();
	        DOMSource source = new DOMSource(document);
	        validator.validate(source);

	        // Transform xml file to html file
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Source xslFile = new StreamSource(new File("src/les4/basis.xsl"));
	        Transformer transformer = tFactory.newTransformer(xslFile);
	        transformer.transform(source, new StreamResult(new FileOutputStream("src/les4/basis.html")));
	        System.out.println("************* The result is in src/les4/basis.html *************");

	        // Transform xml file to different xml file
	        Source XMLToXMLFile = new StreamSource(new File("src/les4/xmlnaarxml.xsl"));
	        Transformer trans = tFactory.newTransformer(XMLToXMLFile);
	        trans.transform(source, new StreamResult(new FileOutputStream("src/les4/basis.new.xml")));
	        System.out.println("************* The result is in src/les4/basis.new.xml *************");
	    } 
		catch (ParserConfigurationException e) {e.printStackTrace();} 
		catch (SAXException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (TransformerConfigurationException e) {e.printStackTrace();} 
		catch (TransformerException e) {e.printStackTrace();} 
	}
	
	public static void main(String[] args)
	{
		new XMLTest();
	}
}
