package les4;

import java.util.ArrayList;

public class Vereniging
{
	private ArrayList<Team> teams;
	private String name;
	private int verenigingnummer;

	public int getVerenigingnummer() {
		return verenigingnummer;
	}

	public void setVerenigingnummer(int verenigingnummer) {
		this.verenigingnummer = verenigingnummer;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void addTeam(Team team) {
		this.teams.add(team);
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vereniging() {
		this.teams = new ArrayList<Team>();
	}
	
	@Override
	public String toString(){
		
		String s = "verenigingnummer = " + getVerenigingnummer() + "\n" +
				"vereniging = " + getName() + "\n";
		return s;
		
	}
}
