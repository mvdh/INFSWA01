package les4;

import java.util.Date;

public class Wedstrijd
{
	private int wedstrijdnummer;
	private Team thuisTeam;
	private Team uitTeam;
	private Sporthal sporthal;
	private Date datumTijd;
	private int scoreThuis;
	private int scoreUit;

	public int getWedstrijdnummer() {
		return wedstrijdnummer;
	}

	public void setWedstrijdnummer(int wedstrijdnummer) {
		this.wedstrijdnummer = wedstrijdnummer;
	}

	public Team getThuisTeam() {
		return thuisTeam;
	}

	public void setThuisTeam(Team thuisTeam) {
		this.thuisTeam = thuisTeam;
	}

	public Team getUitTeam() {
		return uitTeam;
	}

	public void setUitTeam(Team uitTeam) {
		this.uitTeam = uitTeam;
	}

	public Sporthal getSporthal() {
		return sporthal;
	}

	public void setSporthal(Sporthal sporthal) {
		this.sporthal = sporthal;
	}

	public Date getDatumTijd() {
		return datumTijd;
	}

	public void setDatumTijd(Date datumTijd) {
		this.datumTijd = datumTijd;
	}

	public int getScoreThuis() {
		return scoreThuis;
	}

	public void setScoreThuis(int scoreThuis) {
		this.scoreThuis = scoreThuis;
	}

	public int getScoreUit() {
		return scoreUit;
	}

	public void setScoreUit(int scoreUit) {
		this.scoreUit = scoreUit;
	}

	@Override
	public String toString() {
		String s = "";
		
		s += "WEDSTRIJD INFORMATIE:" + "\n";
		s += "Wedstrijdnummer: " + getWedstrijdnummer() + "\n";
		s += "Datum: " + getDatumTijd() + "\n";
		s += "Score thuis:" + getScoreThuis() + "\n";
		s += "Score uit: " + getScoreUit() + "\n\n";
		
		s += "VERENIGING/TEAM THUIS:" + "\n";
		s += "Team " + getThuisTeam().getTeamnummer() + "\n";
		s += getThuisTeam().getVereniging().toString() + "\n\n";
		
		s += "VERENIGING/TEAM UIT:" + "\n";
		s += "Team " + getUitTeam().getTeamnummer() + "\n";
		s += getUitTeam().getVereniging().toString() + "\n\n";
		
		s += "SPORTHAL" + "\n";
		s += sporthal.toString() + "\n";
		
		return s;
	}
}
