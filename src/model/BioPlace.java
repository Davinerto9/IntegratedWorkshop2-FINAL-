package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class BioPlace {

	// Atributes
	private String placeName;
	private Department department;
	private double area;
	private PlaceType placeType;
	private Calendar openingDate;
	private String photo;
	private Community communities;
	private double resources;
	private Specie[] species;
	private int speciesCount;

	// Constructor

	public BioPlace(String placeName, Department department, double area, PlaceType placeType, Calendar openingDate,
			String photo, double resources, Community communities) {

		this.placeName = placeName;
		this.department = department;
		this.area = area;
		this.placeType = placeType;
		this.openingDate = openingDate;
		this.photo = photo;
		this.resources = resources;
		this.communities = communities;
		species = new Specie[50];
		this.speciesCount = 0;
	}

	public String toString() {

		String msg = "";

		msg += "Name: " + placeName;
		msg += "\nDepartment where is from: " + department;
		msg += "\nArea: " + area + "Km^2";
		msg += "\nPlace Type: " + placeType;
		msg += "\nOpening Date: " + new SimpleDateFormat("dd-MM-yyyy").format(openingDate.getTime());
		msg += "\nPhoto: " + photo;
		if (communities != null) {
			msg += "\nCommunity which protects it: " + communities.getCommunityName();
		} else {
			msg += "\nCommunity: None assigned";
		}
		msg += "\nResources: " + resources;
		if (species != null && species.length > 0) {
			msg += "\nSpecies that belong to the place: ";
			for (Specie specie : species) {
				msg += "\n- " + specie.getSpeciesName();
			}
		} else {
			msg += "\nSpecies: None";
		}

		return msg;

	}

	/**
	 * Description: Adds a new community to the array of communities.
	 * 
	 * @param newCommunity The new Community to add.
	 * @return True if the Community is successfully added, false if the array is full
	 *         and the Community cannot be added.
	 */
	public boolean addCommunity(Community newCommunity) {

		if (this.communities == null) {

			this.communities = newCommunity;

			newCommunity.setPlace(this);

			return true;

		} else {

			return false;
		}
	}

	/**
	 * Description: Adds a new species to the array of species.
	 * 
	 * @param newSpecies The new species to add.
	 * @return True if the species is successfully added, false if the array is full
	 *         and the species cannot be added.
	 */
	public boolean addSpecies(Specie newSpecies) {

		if (speciesCount < species.length) {

			species[50] = newSpecies;

			speciesCount++;

			return true;

		}

		return false;

	}

	/**
	 * Description: Searches for a specific species by its name in the array of
	 * species.
	 * 
	 * @param speciesName The name of the species to search for.
	 * @return The species object if found, or null if not found.
	 */

	public Specie searchSpecies(String speciesName) {

		for (int i = 0; i < species.length; i++) {

			if (species[i].getSpeciesName().equals(speciesName)) {

				return species[i];
			}
		}
		return null;
	}

	// Gets and Sets

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	public Calendar getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Calendar openingDate) {
		this.openingDate = openingDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Community getCommunities() {
		return communities;
	}

	public void setCommunities(Community communities) {
		this.communities = communities;
	}

	public double getResources() {
		return resources;
	}

	public void setResources(double resources) {
		this.resources = resources;
	}

	public Specie[] getSpecies() {
		return species;
	}

	public void setSpecies(Specie[] species) {
		this.species = species;
	}

	/**
	 * Description: This method works as counter increment for the maxSpecies variable. 
	 * @return The maxSpecies counter with it's addition if applicable.
	 */
	public int getMaxSpecies() {

		int maxSpecies = 0;

		if (species != null) {
			for (Specie specie : species) {
				if (specie != null) {

					maxSpecies++;
				}
			}
		}

		return maxSpecies;
	}

}
