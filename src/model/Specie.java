package model;

public class Specie{
	
	private String speciesName;
	private Kingdom kingdom;
	private String photo;
	private int specimens;
	
	public Specie(String speciesName, Kingdom kingdom, String photo, int specimens){
		
		this.speciesName = speciesName;
		this.kingdom = kingdom;
		this.photo = photo;
		this.specimens = specimens;
	}
	
	public String toString(){
		
		String msg = "";
		
		msg += "Specie's Name: "+speciesName;
		msg += "\nSpecie's Kingdom: "+kingdom;
		msg += "\nSpecie's Photo: "+photo;
		msg += "\nNumber of Specimens: "+specimens;
		
		return msg;
		
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public Kingdom getKingdom() {
		return kingdom;
	}

	public void setKingdom(Kingdom kingdom) {
		this.kingdom = kingdom;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getSpecimens() {
		return specimens;
	}

	public void setSpecimens(int specimens) {
		this.specimens = specimens;
	}
	
	
}