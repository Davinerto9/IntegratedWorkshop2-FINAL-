package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Controller {

	private BioPlace[] bioPlaceStorage;
	private Community[] communityStorage;

	public Controller() {

		bioPlaceStorage = new BioPlace[200];
		communityStorage = new Community[200];

	}

	/**
	 * Description: Generates a formatted list of biological places with their names
	 * and areas.
	 * 
	 * @return A string containing the formatted list of biological places.
	 */
	public String bioPlaceLists() {

		String list = "";

		for (int d = 0; d < bioPlaceStorage.length; d++) {

			if (bioPlaceStorage[d] != null) {

				list += "\n" + (d + 1) + "-" + bioPlaceStorage[d].getPlaceName() + "-" + bioPlaceStorage[d].getArea();

			}
		}

		return list;
	}

	/**
	 * Description: Generates a formatted list of Communities with their names
	 * and representant name.
	 * 
	 * @return A string containing the formatted list of Communities.
	 */
	public String communityLists() {

		String list = "";

		for (int f = 0; f < communityStorage.length; f++) {

			if (communityStorage[f] != null) {

				list += "\n" + (f + 1) + "-" + communityStorage[f].getCommunityName() + "-"
						+ communityStorage[f].getRepresentantName();

			}
		}

		return list;
	}

	/**
	 * Description: This method here displays the toString() method of the class
	 * BioPlace through this method.
	 * 
	 * @param placeName name of the BioPlace to show.
	 *               pre: A BioPlace object should exist in order to display it's
	 *               information attributes.
	 * @return The toString() method of the BioPlace class according to the
	 *         query is displayed.
	 */

	public String showBioPlace(String placeName) {

		BioPlace temporal = searchBioPlace(placeName);

		if (temporal == null) {

			return "The queried place isn't avaibable.";
		}

		return temporal.toString();
	}

	/**
	 * Description: Registers a new community with the specified details.
	 * 
	 * @param communityName      The name of the community.
	 * @param race               The race of the community (1 for Afrodescendant, 2
	 *                           for Indigenous, 3 for Raizal).
	 * @param inhabitants        The number of inhabitants in the community.
	 * @param representantName   The name of the community's representative.
	 * @param representantNumber The contact number of the community's
	 *                           representative.
	 * @param issues             An array of integers representing major issues in
	 *                           the community:
	 *                           1 for absence of hospitals, 2 for lack of education
	 *                           services,
	 *                           3 for lack of drinkable water services, 4 for
	 *                           access absence of basic alimentation.
	 * @return true if the community is successfully registered, false if the
	 *         community already exists or storage is full.
	 */

	public boolean registerCommunity(String communityName, int race, int inhabitants, String representantName,
			String representantNumber, int[] issues) {
		Race newRace = Race.AFRODESCENDANT;

		switch (race) {
			case 1:
				newRace = Race.AFRODESCENDANT;
				break;
			case 2:
				newRace = Race.INDIGENOUS;
				break;
			case 3:
				newRace = Race.RAIZAL;
				break;
		}

		MajorIssues[] majorIssues = new MajorIssues[issues.length];
		for (int i = 0; i < issues.length; i++) {
			switch (issues[i]) {
				case 1:
					majorIssues[i] = MajorIssues.ABSENCE_OF_HOSPITALS;
					break;
				case 2:
					majorIssues[i] = MajorIssues.LACK_OF_EDUCATION_SERVICES;
					break;
				case 3:
					majorIssues[i] = MajorIssues.LACK_OF_DRINKABLE_WATER_SERVICES;
					break;
				case 4:
					majorIssues[i] = MajorIssues.ACCESS_ABSENCE_OF_BASIC_ALIMENTATION;
					break;
				default:
					majorIssues[i] = null;
					break;
			}
		}

		Community newCommunity = new Community(communityName, newRace, inhabitants, representantName,
				representantNumber, majorIssues);

		for (int c = 0; c < communityStorage.length; c++) {
			if (communityStorage[c] == null) {
				communityStorage[c] = newCommunity;
				return true;
			} else if (communityStorage[c].getCommunityName().equals(communityName)) {
				return false;
			}
		}

		return false;
	}

	/**
	 * Description: Registers a product to a community's inventory.
	 *
	 * @param communityName     The name of the community to which the product will
	 *                          be registered.
	 * @param productName       The name of the product to register.
	 * @param productPercentage The percentage of the product's contribution to the
	 *                          community.
	 * @param productType       The type of the product (e.g., food, craft, etc.).
	 * @param handMade          true if the product is handmade, false otherwise.
	 * @return true if the product is successfully registered to the community,
	 *         false otherwise (community not found).
	 */
	public boolean registerProductToCommunity(String communityName, String productName, double productPercentage,
			int productType, boolean handMade) {

		for (int i = 0; i < communityStorage.length; i++) {

			if (communityStorage[i] != null && communityStorage[i].getCommunityName().equals(communityName)) {

				return communityStorage[i].registerProductToCommunity(productName, productPercentage, productType,
						handMade);
			}
		}

		return false;
	}

	/**
	 * Description: Deletes a product from a community's inventory.
	 * pre: The Method deleteProductOfCommunity must be initialized in the Community
	 * class.
	 * 
	 * @param communityName The name of the community from which to delete the
	 *                      product.
	 * @param productName   The name of the product to delete.
	 * @return true if the product is successfully deleted from the community's
	 *         inventory, false otherwise (community not found or product not in
	 *         inventory).
	 */

	public boolean deleteProductOfCommunity(String communityName, String productName) {

		for (Community community : communityStorage) {

			if (community != null && community.getCommunityName().equals(communityName)) {

				return community.deleteProductOfCommunity(productName);
			}
		}
		return false;
	}

	/**
	 * Description: Retrieves a list of communities in a specified department
	 * according to the BioPlace they belong to.
	 * pre: A Community object must be linked with a BioPlace object in order to
	 * show the list.
	 * 
	 * @param departmentCode The code representing the department (1 for Choco, 2
	 *                       for Valle del Cauca, 3 for Cauca, 4 for Narino).
	 * @return A string containing the names of communities in the specified
	 *         department, or an error message if the department code is invalid.
	 */

	public String queryCommunityByDepartment(int departmentCode) {
		Department department;

		switch (departmentCode) {
			case 1:
				department = Department.CHOCO;
				break;
			case 2:
				department = Department.VALLE_DEL_CAUCA;
				break;
			case 3:
				department = Department.CAUCA;
				break;
			case 4:
				department = Department.NARINO;
				break;

			default:
				return "Invalid department code.";
		}

		String msg = "";

		for (int d = 0; d < bioPlaceStorage.length; d++) {
			if (bioPlaceStorage[d].getDepartment() == department) {
				msg += bioPlaceStorage[d].getCommunities().getCommunityName();
			}
		}

		return msg;
	}

	/**
	 * Description: Searches for a community by its name.
	 * 
	 * @param communityName The name of the community to search for.
	 * @return The Community object representing the found community, or null if not
	 *         found.
	 */
	public Community searchCommunity(String communityName) {

		for (int i = 0; i < communityStorage.length; i++) {

			if (communityStorage[i] != null && communityStorage[i].getCommunityName().equals(communityName)) {

				return communityStorage[i];
			}
		}

		return null;

	}

	/**
	 * Description: Registers a new biological place with the specified details.
	 * 
	 * @param placeName     The name of the biological place.
	 * @param department    The department where the place is located (1 for Choco,
	 *                      2 for Valle del Cauca, 3 for Cauca, 4 for Narino).
	 * @param area          The area of the place in square kilometers.
	 * @param placeType     The type of the place (1 for protected area, 2 for
	 *                      national park, 3 for private area).
	 * @param openingDate   The opening date of the place in the format dd-mm-yyyy.
	 * @param photo         The photo URL of the place.
	 * @param resources     The economic resources needed for habitat care.
	 * @param communityName The name of the community associated with the place.
	 * @return true if the place is successfully registered, false if the place
	 *         already exists or storage is full.
	 */
	public boolean registerBioPlace(String placeName, int department, double area, int placeType, String openingDate,
			String photo, double resources, String communityName) {

		Department newDepartment = Department.CHOCO;

		switch (department) {

			case 1:
				newDepartment = Department.CHOCO;
				break;

			case 2:
				newDepartment = Department.VALLE_DEL_CAUCA;
				break;

			case 3:
				newDepartment = Department.CAUCA;
				break;

			case 4:
				newDepartment = Department.NARINO;
				break;

		}

		PlaceType newPlaceType = PlaceType.PROTECTED_AREA;

		switch (placeType) {

			case 1:
				newPlaceType = PlaceType.PROTECTED_AREA;
				break;

			case 2:
				newPlaceType = PlaceType.NATIONAL_PARK;
				break;

			case 3:
				newPlaceType = PlaceType.PRIVATE_AREA;
				break;

		}

		// String > Calendar
		// openingDate -> dd-mm-yyyy
		String[] dateArray = openingDate.split("-");
		int day = Integer.parseInt(dateArray[0]); // Wrapper
		int month = Integer.parseInt(dateArray[1]) - 1; // Wrapper
		int year = Integer.parseInt(dateArray[2]); // Wrapper

		Calendar date = Calendar.getInstance();
		date.set(year, month, day);

		Community community = searchCommunity(communityName);

		BioPlace newBioPlace = new BioPlace(placeName, newDepartment, area, newPlaceType, date, photo, resources,
				community);

		for (int i = 0; i < bioPlaceStorage.length; i++) {

			if (bioPlaceStorage[i] == null) {

				bioPlaceStorage[i] = newBioPlace;
				return true;

			} else if (bioPlaceStorage[i].getPlaceName().equals(placeName)) {

				return false;
			}

		}

		return false;
	}

	/**
	 * Description: Registers a species to a biological place.
	 * 
	 * @param placeName   The name of the biological place to which the species will
	 *                    be registered.
	 * @param speciesName The name of the species to register.
	 * @param kingdom     The kingdom of the species (1 for flora, 2 for fauna).
	 * @param photo       The photo URL of the species.
	 * @param specimens   The number of specimens of the species.
	 * @return true if the species is successfully registered to the biological
	 *         place, false otherwise (place not found).
	 * 
	 */
	public boolean registerSpeciesToBioPlace(String placeName, String speciesName, int kingdom, String photo,
			int specimens) {
		BioPlace bioPlace = searchBioPlace(placeName);

		if (bioPlace != null) {
			Kingdom newKingdom = Kingdom.FLORA;

			switch (kingdom) {
				case 1:
					newKingdom = Kingdom.FLORA;
					break;
				case 2:
					newKingdom = Kingdom.FAUNA;
					break;
			}

			Specie newSpecie = new Specie(speciesName, newKingdom, photo, specimens);

			return bioPlace.addSpecies(newSpecie);
		}

		return false;
	}

	/**
	 * Description: Modifies a species in a biological place.
	 * 
	 * @param placeName    The name of the biological place where the species is
	 *                     located.
	 * @param speciesName  The name of the species to modify.
	 * @param newKingdom   The new kingdom of the species.
	 * @param newPhoto     The new photo URL of the species.
	 * @param newSpecimens The new number of specimens of the species.
	 * @return true if the species is successfully modified, false otherwise (place
	 *         or species not found).
	 * 
	 */

	public boolean modifySpeciesOfBioPlace(String placeName, String speciesName, Kingdom newKingdom, String newPhoto,
			int newSpecimens) {

		BioPlace bioPlace = searchBioPlace(placeName);

		if (bioPlace != null) {

			Specie species = bioPlace.searchSpecies(speciesName);

			if (species != null) {

				species.setKingdom(newKingdom);
				species.setPhoto(newPhoto);
				species.setSpecimens(newSpecimens);

				return true;
			} else {
				System.out.println("Species not found in the bio place.");
			}
		} else {
			System.out.println("Bio place not found.");
		}

		return false;
	}

	/**
	 * Description: Searches for a biological place by its name.
	 * 
	 * @param placeName The name of the biological place to search for.
	 * @return The BioPlace object representing the found biological place, or null
	 *         if not found.
	 * 
	 */

	public BioPlace searchBioPlace(String placeName) {

		for (int i = 0; i < bioPlaceStorage.length; i++) {

			if (bioPlaceStorage[i] != null && bioPlaceStorage[i].getPlaceName().equals(placeName)) {

				return bioPlaceStorage[i];
			}
		}

		return null;
	}

	/**
	 * Description: Consults communities with major issues related to absence of
	 * hospitals or lack of education services.
	 * 
	 * @return A list of community names that have major issues related to absence
	 *         of hospitals or lack of education services.
	 */

	public List<String> consultCommunitiesMajorIssuesHospOrEdu() {

		List<String> communitiesWithIssues = new ArrayList<>();

		for (Community community : communityStorage) {
			if (community != null) {
				boolean hasHospitalsIssue = hasMajorIssue(community, MajorIssues.ABSENCE_OF_HOSPITALS);
				boolean hasEducationIssue = hasMajorIssue(community, MajorIssues.LACK_OF_EDUCATION_SERVICES);

				if (hasHospitalsIssue || hasEducationIssue) {
					communitiesWithIssues.add(community.getCommunityName());
				}
			}
		}

		return communitiesWithIssues;

	}

	/**
	 * Description: Checks if a specified community has a particular major issue.
	 * 
	 * @param community   the community object that will be checked for major issues
	 * @param majorIssues the specific major issue to be checked within the
	 *                    community
	 * @return {@code true} if the community has the specified major issue,
	 *         otherwise {@code false}
	 */

	private boolean hasMajorIssue(Community community, MajorIssues majorIssues) {

		for (MajorIssues issue : community.getMajorIssues()) {

			if (issue == majorIssues) {

				return true;
			}
		}
		return false;
	}

	/**
	 * Description: Consults the biological place with the most species.
	 * pos:Prints the name of the place and the number of species it has.
	 * Prints a message if no biological places are found or if there is
	 * insufficient data.
	 * 
	 */

	public void consultBioPlaceWithMoreSpecies() {

		int maxSpecies = 0;
		BioPlace bioPlaceWithMaxSpecies = null;

		for (BioPlace bioPlace : bioPlaceStorage) {
			if (bioPlace != null) {
				int numSpecies = bioPlace.getMaxSpecies();
				if (numSpecies > maxSpecies) {
					maxSpecies = numSpecies;
					bioPlaceWithMaxSpecies = bioPlace;
				}
			}
		}

		if (bioPlaceWithMaxSpecies != null) {
			System.out.println("BioPlace with the most species:");
			System.out.println("Name: " + bioPlaceWithMaxSpecies.getPlaceName());
			System.out.println("Number of Species: " + maxSpecies);
		} else {
			System.out.println("No BioPlaces found or insufficient data.");
		}
	}

	/**
	 * Description: Consults the three largest places by area in square kilometers
	 * (Km^2).
	 * pos: Prints the names and areas of the three largest places if data is
	 * available.
	 * Prints a message if there are no places to consult or insufficient data.
	 */
	public void consultTheThreeLargestPlacesByKm2() {

		int s = bioPlaceStorage.length;

		if (bioPlaceStorage != null && s > 1) {
			for (int i = 0; i < s - 1; i++) {
				for (int j = 0; j < s - i - 1; j++) {

					if (bioPlaceStorage[j] != null && bioPlaceStorage[j + 1] != null &&
							bioPlaceStorage[j].getArea() < bioPlaceStorage[j + 1].getArea()) {

						BioPlace temporal = bioPlaceStorage[j];
						bioPlaceStorage[j] = bioPlaceStorage[j + 1];
						bioPlaceStorage[j + 1] = temporal;
					}
				}
			}

			System.out.println("The three biggest places by Km^2:");

			for (int i = 0; i < 3 && i < s; i++) {

				if (bioPlaceStorage[i] != null) {

					System.out.println((i + 1) + ". " + bioPlaceStorage[i].getPlaceName() + ": " +
							bioPlaceStorage[i].getArea() + " km^2");
				}
			}
		} else {

			System.out.println("No places to consult or insufficient data.");
		}
	}

	/**
	 * Description: Retrieves a list of communities in a specified department
	 * according to the BioPlace they belong to.
	 * pre: A Community object must be linked with a BioPlace object in order to
	 * show the list.
	 * 
	 * @param department department The object code representing the department (1
	 *                   for Choco, 2
	 *                   for Valle del Cauca, 3 for Cauca, 4 for Narino).
	 * 
	 * @return A string containing the names of communities in the specified
	 *         department, or an error message if the department code is invalid.
	 */
	public List<String> getCommunitiesByDepartment(Department department) {
		List<String> departmentCommunities = new ArrayList<>();

		for (int i = 0; i < communityStorage.length; i++) {
			if (communityStorage[i] != null && bioPlaceStorage[i] != null &&
					bioPlaceStorage[i].getDepartment() == department) {
				departmentCommunities.add(communityStorage[i].getCommunityName());
			}
		}

		return departmentCommunities;
	}

	/**
	 * Description: Generates a list of the biggest issues based on the MajorIssues
	 * enum values.
	 * 
	 * @return A formatted string listing the biggest issues with their
	 *         corresponding enumeration values.
	 * 
	 */
	public String listBiggestIssues() {

		MajorIssues[] majorIssuesArray = MajorIssues.values();

		String list = "";

		for (int i = 0; i < majorIssuesArray.length; i++) {

			list += "\n" + (i + 1) + "-" + majorIssuesArray[i];
		}

		return list;

	}

	/**
	 * Description: Generates a list of races based on the Race enum values.
	 * 
	 * @return A formatted string listing the races with their corresponding
	 *         enumeration values.
	 */
	public String listRace() {

		Race[] raceArray = Race.values();

		String list = "";

		for (int i = 0; i < raceArray.length; i++) {

			list += "\n" + (i + 1) + "-" + raceArray[i];
		}

		return list;

	}

	/**
	 * Description: Generates a list of Kingdoms based on the Kingdom enum values.
	 * pre:
	 * 
	 * @return A formatted string listing the kingdoms with their corresponding
	 *         enumeration values.
	 */
	public String listKingdom() {

		Kingdom[] kingdomArray = Kingdom.values();

		String list = "";

		for (int i = 0; i < kingdomArray.length; i++) {

			list += "\n" + (i + 1) + "-" + kingdomArray[i];
		}

		return list;
	}

	/**
	 * Description: Generates a list of Departments based on the kingdom enum
	 * values.
	 * pre:
	 * 
	 * @return A formatted string listing the departments with their corresponding
	 *         enumeration values.
	 */
	public String listDepartment() {

		Department[] departmentArray = Department.values();

		String list = "";

		for (int q = 0; q < departmentArray.length; q++) {

			list += "\n" + (q + 1) + "-" + departmentArray[q];
		}

		return list;
	}

	/**
	 * Description: Provides a the Enumeration PlaceType as a String chain.
	 * pre: PlaceType Enum Class must be initialized prior.
	 * pos: Returns the enum class as a String type list.
	 */

	public String listPlaceType() {

		PlaceType[] placeTypeArray = PlaceType.values();

		String list = "";

		for (int e = 0; e < placeTypeArray.length; e++) {

			list += "\n" + (e + 1) + "-" + placeTypeArray[e];
		}

		return list;
	}

	/**
	 * Description: Provides a the Enumeration ProductType as a String chain.
	 * pre: ProductType Enum Class must be initialized prior.
	 * pos: Returns the enum class as a String type list.
	 */

	public String listProductType() {

		ProductType[] productTypeArray = ProductType.values();

		String list = "";

		for (int g = 0; g < productTypeArray.length; g++) {

			list += "\n" + (g + 1) + "-" + productTypeArray[g];
		}

		return list;
	}

	/**
	 * Description: This method provides a full list of products of all communities
	 * in one String list.
	 * pre: productsLists() method on Controller must be initialized prior.
	 * pos: Return Full List of products when called,
	 */

	public String productsLists() {

		String productsLists = "";

		for (Community community : communityStorage) {

			if (community != null) {

				productsLists += community.productLists() + "\n";

			}
		}

		return productsLists;

	}

	/**
	 * Description: Creates Trial Cases of the Methods referrring to storaging.
	 * 
	 */
	public void createTrialCases() {

		/*
		 * registerCommunity("Guaming", 2, 250, "Wilder", "3006075890", new
		 * issues[]{MajorIssues.ABSENCE_OF_HOSPITALS,
		 * MajorIssues.LACK_OF_EDUCATION_SERVICES});
		 */

		/*
		 * registerCommunity("Brillitos", 2, 250, "Brayan", "3006075890", new
		 * issues[]{MajorIssues.ABSENCE_OF_HOSPITALS});
		 */

		registerBioPlace("Rural Farm", 1, 50.9, 2, "10-05-2023",
				"https://arbolabc.nyc3.cdn.digitaloceanspaces.com/Science/animals/articles/animales-de-la-granja/cows.jpg",
				5000.9, "Guaming");
		registerBioPlace("Humedal San Juan", 3, 500.8, 2, "12-12-2000",
				"https://cider.uniandes.edu.co/sites/default/files/img/Boletines/2023/febrero-2023/humedales-m.jpg",
				8000.2, "Brillitos");

		registerSpeciesToBioPlace("Humedal San Juan", "Guacamaya Azul", 2,
				"https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSexNb1s9o6evGDv3rVbWIB11zT_uRPLdJrAuaz1FiSozQo7GKP",
				49);
		registerSpeciesToBioPlace("Rural Farm", "Cow", 2,
				"https://taxonomiaanimal.wordpress.com/wp-content/uploads/2018/03/vaca.png", 34);
		registerProductToCommunity("Guaming", "ManosVerdes", 34.5, 2, true);
		registerProductToCommunity("Brillitos", "Gafas", 1.9, 2, false);

	}

}