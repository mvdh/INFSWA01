package les4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class SchrijfXML extends JFrame
{
	private JButton load = new JButton("Load xml file");
	
	private ArrayList<Sporthal> sporthallen;
	private ArrayList<Vereniging> verenigingen;
	private ArrayList<Wedstrijd> wedstrijden;
	private DateFormat dfd = new SimpleDateFormat("dd-MM-yyyy");
	private DateFormat dft = new SimpleDateFormat("HH:mm");
	
	private File dir = new File("src/les4");
	private JFileChooser chooser = new JFileChooser(dir);
	
	public SchrijfXML()
	{	 
		init();
	}
	
	public void init()
	{
		setBounds(500, 100, 150, 80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setTitle("Schrijf XML");
		
		chooser.addChoosableFileFilter(new xmlFilter());
		
		load.setBounds(10, 10, 120, 30);
		load.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int returnVal = chooser.showOpenDialog(new JFrame());
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						File file = chooser.getSelectedFile();
						XMLtoXML(file);
					}
				}
			}
		);
		
		add(load);
		paintAll(getGraphics());
	}
		
		
	public void XMLtoXML(File file)
	{
		LeesXML xml = new LeesXML(file.getAbsolutePath());
		sporthallen = xml.getSporthallen();
		verenigingen = xml.getVerenigingen();
		wedstrijden = xml.getWedstrijden();
		
		try 
        {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("zaalvoetbal");
            doc.appendChild(root);

            Element wedstrijd = doc.createElement("wedstrijden");
            for (int i = 0 ; i < wedstrijden.size(); i++)
            {
	            Element game = doc.createElement("wedstrijd");
	            game.setAttribute("wedstrijdnummer", wedstrijden.get(i).getWedstrijdnummer() + "");
	            game.setAttribute("verenigingnummerthuis", wedstrijden.get(i).getThuisTeam().getVereniging().getVerenigingnummer() + "");
	            game.setAttribute("verenigingnummeruit", wedstrijden.get(i).getUitTeam().getVereniging().getVerenigingnummer() + "");
	            game.setAttribute("teamnummerthuis", wedstrijden.get(i).getThuisTeam().getTeamnummer() + "");
	            game.setAttribute("teamnummeruit", wedstrijden.get(i).getUitTeam().getTeamnummer() + "");
	            game.setAttribute("sporthalnummer", wedstrijden.get(i).getSporthal().getSporthalnummer() + "");

	            Element datum = doc.createElement("datum");
	            Text date = doc.createTextNode(dfd.format(wedstrijden.get(i).getDatumTijd()));
	            datum.appendChild(date);
	            game.appendChild(datum);
	            
	            Element tijdstip = doc.createElement("tijdstip");
	            Text tijd = doc.createTextNode(dft.format(wedstrijden.get(i).getDatumTijd()));
	            tijdstip.appendChild(tijd);
	            game.appendChild(tijdstip);
	            
	            Element uitslag = doc.createElement("uitslag");
	            Element scorethuis = doc.createElement("scorethuis");
	            Text scoret = doc.createTextNode(wedstrijden.get(i).getScoreThuis() + "");
	            scorethuis.appendChild(scoret);
	            uitslag.appendChild(scorethuis);
	            
	            Element scoreuit = doc.createElement("scoreuit");
	            Text scoreu = doc.createTextNode(wedstrijden.get(i).getScoreUit() + "");
	            scoreuit.appendChild(scoreu);
	            uitslag.appendChild(scoreuit);
	            game.appendChild(uitslag);
	            
	            wedstrijd.appendChild(game);
            }
            root.appendChild(wedstrijd);
            
            Element vereniging = doc.createElement("verenigingen");
            for (int i = 0 ; i < verenigingen.size(); i++)
            {
	            Element ver = doc.createElement("vereniging");
	            ver.setAttribute("verenigingnummer", verenigingen.get(i).getVerenigingnummer() + "");

	            Element naam = doc.createElement("naam");
	            Text name = doc.createTextNode(verenigingen.get(i).getName());
	            naam.appendChild(name);
	            ver.appendChild(naam);
	            
	            Element teams = doc.createElement("teams");
	            for (int j = 0; j < verenigingen.get(i).getTeams().size(); j++)
	            {
	            	Team team = verenigingen.get(i).getTeams().get(j);
	            	Element t = doc.createElement("team");
	            	t.setAttribute("teamnummer", team.getTeamnummer() + "");
	            	t.setAttribute("verenigingnummer", verenigingen.get(i).getVerenigingnummer() + "");
	            	teams.appendChild(t);
	            }
	            ver.appendChild(teams);
	             
	            vereniging.appendChild(ver);
            }
            root.appendChild(vereniging);

            Element sporthal = doc.createElement("sporthallen");
            for (int i = 0 ; i < sporthallen.size(); i++)
            {
	            Element sport = doc.createElement("sporthal");
	            sport.setAttribute("sporthalnummer", sporthallen.get(i).getSporthalnummer() + "");

	            Element naam = doc.createElement("naam");
	            Text name = doc.createTextNode(sporthallen.get(i).getNaam());
	            naam.appendChild(name);
	            sport.appendChild(naam);

	            Element tel = doc.createElement("telefoonnummer");
	            Text tele = doc.createTextNode(sporthallen.get(i).getTelefoonnummer() + "");
	            naam.appendChild(tele);
	            sport.appendChild(tel);
	            
	            Element adres = doc.createElement("adres");
	            Element post = doc.createElement("postcode");
	            Text postt = doc.createTextNode(sporthallen.get(i).getPostcode());
	            post.appendChild(postt);
	            adres.appendChild(post);

	            Element hn = doc.createElement("huisnummer");
	            Text hnt = doc.createTextNode(sporthallen.get(i).getHuisnummer() + "");
	            hn.appendChild(hnt);
	            adres.appendChild(hn);
	            
	            Element hntv = doc.createElement("huisnummertoevoeging");
	            Text hntvt = doc.createTextNode(sporthallen.get(i).getHuisnummertoevoeging());
	            hntv.appendChild(hntvt);
	            adres.appendChild(hntv);

	            Element sn = doc.createElement("straatnaam");
	            Text snt = doc.createTextNode(sporthallen.get(i).getStraatnaam());
	            sn.appendChild(snt);
	            adres.appendChild(sn);

	            Element plaats = doc.createElement("plaats");
	            Text plaatst = doc.createTextNode(sporthallen.get(i).getPlaats());
	            plaats.appendChild(plaatst);
	            adres.appendChild(plaats);
	            sport.appendChild(adres);
	             
	            sporthal.appendChild(sport);
            }
            root.appendChild(sporthal);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(doc);
    		File newFile = new File("src/les4/basis.gen.xml");
    		StreamResult result = new StreamResult(newFile);
    		transformer.transform(source, result);
    		Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + newFile.getAbsolutePath());
    		p.waitFor();
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
    }
	
	public static void main(String[] args)
	{
		new SchrijfXML();
	}
}
