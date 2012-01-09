package les4;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeesXML
{
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder domBuilder;
	private Document document;
	
	private ArrayList<Sporthal> sporthallen; //TODO
	private ArrayList<Team> teams; //TODO
	private ArrayList<Vereniging> verenigingen; //TODO
	private ArrayList<Wedstrijd> wedstrijden; //TODO
	

	public LeesXML(String src) {
		try {
			domFactory = DocumentBuilderFactory.newInstance();
			domBuilder = domFactory.newDocumentBuilder();
			document = domBuilder.parse(new File(src));
		} catch (Exception e) {
			return;
		}
		
		NodeList nL = document.getElementsByTagName("wedstrijd");
		leesWedstrijd(nL.item(0));
	}
	
	public ArrayList leesZaalvoetbal() {
		Element root = document.getDocumentElement();
		return null;
	}
	
	public Sporthal leesSporthal(Node nSporthal) {
		Sporthal sporthal = new Sporthal();
		Node n = nSporthal.getFirstChild();
		
		sporthal.setSporthalnummer(Integer.parseInt(((Element) nSporthal).getAttribute("sporthalnummer")));
		while(n != null) 
		{
			if(n instanceof Element) 
			{
				String nodeName = n.getNodeName();
				String nodeValue = n.getFirstChild().getNodeValue();
				
				if(nodeName.compareTo("naam") == 0) 
				{
					sporthal.setNaam(nodeValue);
				}
				else if(nodeName.compareTo("telefoonnummer") == 0) 
				{
					sporthal.setTelefoonnummer(nodeValue);
				}
				else if(nodeName.compareTo("adres") == 0) 
				{
					Node adres = n.getFirstChild();
					while (adres != null)
					{
						if(adres instanceof Element) 
						{
							String adresNodeName = adres.getNodeName();
							String adresNodeValue = "";
							if(adres.getFirstChild() != null) {
								adresNodeValue = adres.getFirstChild().getNodeValue();
							}
							 
							if(adresNodeName.compareTo("postcode") == 0)
							{
								sporthal.setPostcode(adresNodeValue);
							}
							else if (adresNodeName.compareTo("huisnummer") == 0) 
							{
								sporthal.setHuisnummer(Integer.parseInt(adresNodeValue));
							}
							else if (adresNodeName.compareTo("huisnummertoevoeging") == 0) 
							{
								sporthal.setHuisnummertoevoeging(adresNodeValue);
							}
							else if (adresNodeName.compareTo("straatnaam") == 0) 
							{
								sporthal.setStraatnaam(adresNodeValue);
							}
							else if (adresNodeName.compareTo("plaats") == 0) 
							{
								sporthal.setPlaats(adresNodeValue);
							}
						}
						
						adres = adres.getNextSibling();
					}
				}
			}	
			
			n = n.getNextSibling();
		}
		System.out.println(sporthal.toString());
		return new Sporthal();
	}

	public Vereniging leesVereniging(Node nVereniging) {
		Vereniging vereniging = new Vereniging();
		Node n = nVereniging.getFirstChild();
		
		vereniging.setVerenigingnummer(Integer.parseInt(((Element) nVereniging).getAttribute("verenigingnummer")));
		while(n != null) 
		{
			if(n instanceof Element) 
			{
				String nodeName = n.getNodeName();
				String nodeValue = n.getFirstChild().getNodeValue();
				
				if(nodeName.compareTo("naam") == 0) 
				{
					vereniging.setName(nodeValue);
				}
				else if(nodeName.compareTo("teams") == 0) 
				{
					Node nTeam = n.getFirstChild();
					while (nTeam != null)
					{
						Team team = new Team();
						team.setVereniging(vereniging);
						if(nTeam instanceof Element) 
						{
							Element t = (Element) nTeam;
							team.setTeamnummer(Integer.parseInt(t.getAttribute("teamnummer")));
						}
						vereniging.addTeam(team);
						teams.add(team);
						
						nTeam = nTeam.getNextSibling();
					}
				}
			}
			n = n.getNextSibling();
		}
		return vereniging;
	}

	public Wedstrijd leesWedstrijd(Node nWedstrijdn) {
		Wedstrijd wedstrijd = new Wedstrijd();
		Node n = nWedstrijdn.getFirstChild();
		boolean datumToegevoegd = false;
		boolean tijdToegevoegd = false;
		
		Calendar cal = Calendar.getInstance();
		wedstrijd.setWedstrijdnummer(Integer.parseInt(((Element) nWedstrijdn).getAttribute("wedstrijdnummer")));
		
		
		while(n != null) 
		{
			if(n instanceof Element) 
			{
				String nodeName = n.getNodeName();
				String nodeValue = n.getFirstChild().getNodeValue();
				
				if(nodeName.compareTo("datum") == 0) 
				{
					String[] s = Pattern.compile("-").split(nodeValue);
					cal.set(Calendar.YEAR, Integer.parseInt(s[0]));
					cal.set(Calendar.MONTH, Integer.parseInt(s[1])-1);
					cal.set(Calendar.DATE, Integer.parseInt(s[2]));
					datumToegevoegd = true;
				}
				else if(nodeName.compareTo("tijdstip") == 0) 
				{
					String[] s = Pattern.compile(":").split(nodeValue);
					cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
					cal.set(Calendar.MINUTE, Integer.parseInt(s[1]));
					cal.set(Calendar.SECOND, 0);
					tijdToegevoegd = true;
				}
				else if(nodeName.compareTo("uitslag") == 0) 
				{
					Node nScore = n.getFirstChild();
					
					while (nScore != null)
					{
						if(nScore instanceof Element) 
						{
							String ScoreNodeName = nScore.getNodeName();
							String ScoreNodeValue = nScore.getFirstChild().getNodeValue();
							
							if(ScoreNodeName.compareTo("scorethuis") == 0) {
								wedstrijd.setScoreThuis(Integer.parseInt(ScoreNodeValue));
							}
							if(ScoreNodeName.compareTo("scoreuit") == 0) {
								wedstrijd.setScoreUit(Integer.parseInt(ScoreNodeValue));
							}
						}
						nScore = nScore.getNextSibling();
					}
				}
			}
			n = n.getNextSibling();
		}
		
		if(datumToegevoegd && tijdToegevoegd)
		{
			wedstrijd.setDatumTijd(cal.getTime());	
		}
		
		return wedstrijd;
	}
}
