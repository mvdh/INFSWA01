package les4;

public class Sporthal
{
	private int sporthalnummer;
	private String naam;
	private String telefoonnummer; // telefoonnummer is niet om mee te rekenen
	private String postcode;
	private int huisnummer;
	private String huisnummertoevoeging;
	private String straatnaam;
	private String plaats;

	public int getSporthalnummer() {
		return sporthalnummer;
	}

	public void setSporthalnummer(int sporthalnummer) {
		this.sporthalnummer = sporthalnummer;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getHuisnummer() {
		return huisnummer;
	}

	public void setHuisnummer(int huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getHuisnummertoevoeging() {
		return huisnummertoevoeging;
	}

	public void setHuisnummertoevoeging(String huisnummertoevoeging) {
		this.huisnummertoevoeging = huisnummertoevoeging;
	}

	public String getStraatnaam() {
		return straatnaam;
	}

	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	@Override
	public String toString() {
		String s = "sporthalnummer = " + this.getSporthalnummer() + "\n" +
				"naam = " + this.getNaam() + "\n" +
				"telefoonnummer = " + this.getTelefoonnummer() + "\n" +
				"postcode = " + this.getPostcode() + "\n" +
				"huisnummer = " + this.getHuisnummer() + "\n" +
				"huisnummertoevoeging = " + this.getHuisnummertoevoeging() + "\n" +
				"straatnaam = " + this.getStraatnaam() + "\n" +
				"plaats = " + this.getPlaats() + "\n";
		return s;
	}
}
