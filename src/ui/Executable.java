package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Controller;
import model.Department;
import model.Kingdom;

public class Executable {

	private Scanner reader; // Scanner Attribute
	private Controller control; // Relations with Controller

	public static void main(String[] args) {
		Executable exe = new Executable();
		exe.menu();

	}

	public Executable() {
		reader = new Scanner(System.in);
		control = new Controller();

	}

	/**
	 * Description: This is the main Menu, which derivates into 2 Submenus:
	 * Administrative and Consultations Menus.
	 * pre: Scanner must be initialized prior.
	 */

	public void menu() {
		boolean flag = true;

		do {

			System.out.println("Welcome to the COP 16 Interactive Software, Please Select what do you want to do.");
			System.out.println("Please type The option you desire.");
			System.out.println("1) Administrative Menu");
			System.out.println("2) Consults Menu");
			System.out.println("0) Exit");

			int option = reader.nextInt();

			switch (option) {

				case 1:

					boolean flagAdmin = true;
					do {

						System.out.println(
								"Welcome to the Administrative Menu, Please Select the Operation you want to do.");
						System.out.println("1) Register a Community.");
						System.out.println("2) Register a Place (Name must be Unique)");
						System.out.println("3) Register a Product to a Community.");
						System.out.println("4) Delete a Product from a Community.");
						System.out.println("5) Register a Species to a BioPlace.");
						System.out.println("6) Modify the Species of the Bio-Place.");
						System.out.println("6) Trial Cases Showing.");
						System.out.println("0) Exit");

						int optionAdmin = reader.nextInt();

						switch (optionAdmin) {

							case 1:
								registerCommunity();
								break;

							case 2:
								registerBioPlace();
								break;

							case 3:
								registerProductToCommunity();
								break;

							case 4:
								deleteProductOfCommunity();
								break;

							case 5:
								registerSpeciesToBioPlace();
								break;

							case 6:
								modifySpeciesOfBioPlace();
								break;

							case 0:
								flagAdmin = false;
								System.out.println("Thank you for using our services!");
								break;

							default:
								System.out.println("Invalid Option, Please Try Again");
								break;
						}

					} while (flagAdmin);

					break;

				case 2:
					boolean flagConsult = true;

					do {

						System.out.println(
								"This is the Consultations Menu, Please Prompt the Operation you want to do Today!");
						System.out.println("1) Consult information about a Bio-Place");
						System.out.println("2) Consult information about the Communities of a Specified Department.");
						System.out.println("3) Consult information about the Communities which Biggest Issues are: Absence of Hospitals or Lack of Education Services.");
						System.out.println("4) Consult the name of the place with more assigned Species.");
						System.out.println("5) Consult the 3 Largest places per Km^2.");
						System.out.println("0) Exit");

						int optionConsult = reader.nextInt();

						switch (optionConsult) {

							case 1:
								consultBioPlaces();
								break;

							case 2:
								consultCommunitiesOfADepartment();
								break;

							case 3:
								consultCommunitiesMajorIssuesHospOrEdu();
								break;

							case 4:
								consultBioPlaceWithMoreSpecies();
								break;

							case 5:
								consultTheThreeLargestPlacesByKm2();
								break;

							case 0:
								flagConsult = false;
								System.out.println("Thank you for using our services!");
								break;

							default:
								System.out.println("Invalid Option, Please Try Again");
								break;
						}

					} while (flagConsult);

					break;

				case 0:
					flag = false;
					System.out.println("Thank you for using our services!");
					break;

				default:
					System.out.println("Invalid Option, Please Try Again");
					break;
			}

		} while (flag);

	}

	/**
	 * Description: Asks the user to input the neccesary attributes for registering a community.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: Community is registered or not.
	 */
	public void registerCommunity() {

		reader.nextLine();

		System.out.println("Type the name of the community you want to register.");
		String communityName = reader.nextLine();

		System.out.println("Please select the corresponding race of your community.");
		System.out.println(control.listRace());
		int race = reader.nextInt();

		System.out.println("Please Type the number of habitants your Community has.");
		int inhabitants = reader.nextInt();

		reader.nextLine();

		System.out.println("Please type the name of the representant of your community");
		String representantName = reader.nextLine();

		System.out.println("Please type the phone number of the representant of your community.");
		String representantNumber = reader.nextLine();

		List<Integer> biggestIssuesList = new ArrayList<>();

		do {
			System.out.println("Please specify an issue referring to your community, or enter -1 to finish.");
			System.out.println(control.listBiggestIssues());
			int issue = reader.nextInt();

			if (issue == -1) {
				break;
			}

			biggestIssuesList.add(issue);
		} while (biggestIssuesList.size() < 4);

		int[] biggestIssues = biggestIssuesList.stream().mapToInt(Integer::intValue).toArray();

		boolean result = control.registerCommunity(communityName, race, inhabitants, representantName,
				representantNumber, biggestIssues);

		if (result) {
			System.out.println("Community Registered Successfully");
		} else {
			System.out.println("Error, The Community could not be registered.");
		}
	}

	/**
	 * Description: Asks the user to input the neccesary attributes for registering a BioPlace.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: BioPlace is registered or not.
	 */
	public void registerBioPlace() {
		reader.nextLine();

		System.out.println("Type the name of the Bio-Place (Must be Unique).");
		String placeName = reader.nextLine();

		System.out.println("Please select the Department which the place belongs to.");
		System.out.println(control.listDepartment());
		int departmentCode = reader.nextInt();

		System.out.println("Type the Area of size of the place in Km^2");
		double area = reader.nextDouble();

		System.out.println("Please select the type of place the Bio-Place is.");
		System.out.println(control.listPlaceType());
		int placeType = reader.nextInt();

		reader.nextLine();
		System.out.println("Please type the opening date of the Bio-Place (dd-mm-yyyy)");
		String openingDate = reader.nextLine();

		System.out.println("Please insert the URL address of the photo referring to the inputted place.");
		String photo = reader.nextLine();

		System.out.println("Please Type the amount in COP of resources destined to that Place.");
		double resources = reader.nextDouble();

		System.out.println("Please select the Community that Protects that place:");
		System.out.println(control.communityLists());
		int communityIndex = reader.nextInt();

		String communityCode = Integer.toString(communityIndex);

		boolean result = control.registerBioPlace(placeName, departmentCode, area, placeType, openingDate, photo,
				resources, communityCode);

		if (result) {
			System.out.println("Bio-Place Registered Successfully");
		} else {
			System.out.println("Error, The Bio-Place could not be registered.");
		}
	}

	/**
	 * Description: Asks the user to input the neccesary attributes for registering a Product in community.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: Product is registered or not.
	 */
	public void registerProductToCommunity() {

		reader.nextLine();

		System.out.println("Enter the name of the community:");
		System.out.println(control.communityLists());

		String communityName = reader.nextLine();

		System.out.println("Type the name of the product to register: ");
		String productName = reader.nextLine();

		System.out.println("Please input the Percentage of natural products used for fabrication.");
		double productPercentage = reader.nextDouble();

		System.out.println("Please choose the Product type of your Product:");
		System.out.println(control.listProductType());

		int productType = reader.nextInt();

		System.out.println("Please specify if the product is made Handmade or not: *Type True for yes, False for no.");
		boolean handMade = reader.nextBoolean();

		boolean result = control.registerProductToCommunity(communityName, productName, productPercentage, productType,
				handMade);

		if (result) {
			System.out.println("Product registered to community successfully.");
		} else {
			System.out.println("Failed to register product to community.");
		}

	}

	/**
	 * Description: Asks the user to input the neccesary attributes for deleting a Product in community.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: Product is deleted or not.
	 */
	public void deleteProductOfCommunity() {

		reader.nextLine();

		System.out.println("Enter the name of the community from which you want to delete a product:");
		System.out.println(control.communityLists());

		String communityName = reader.nextLine();

		System.out.println("Enter the name of the product to delete:");
		String productName = reader.nextLine();

		boolean result = control.deleteProductOfCommunity(communityName, productName);

		if (result) {
			System.out.println("Product deleted successfully.");
		} else {
			System.out.println("Failed to delete product. Product or community not found.");
		}

	}

	/**
	 * Description: Asks the user to input the neccesary attributes for registering a Species in a BioPlace.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: Species is registered or not.
	 */
	public void registerSpeciesToBioPlace() {

		reader.nextLine();

		System.out.println("Please Select The Bio-Place where you want to register the species.");
		System.out.println(control.bioPlaceLists());

		String placeChoosing = reader.nextLine();

		System.out.println("Please Type the name of the Species.");
		String speciesName = reader.nextLine();

		System.out.println("Please Select the Kingdom where the species belong to.");
		System.out.println(control.listKingdom());

		int kingdom = reader.nextInt();

		System.out.println("Please insert the URL adress of the photo referring to the inputted species.");
		String photo = reader.nextLine();

		System.out.println("Please type the number of specimens of the species you are going to register.");
		int specimens = reader.nextInt();

		boolean result = control.registerSpeciesToBioPlace(placeChoosing, speciesName, kingdom, photo, specimens);

		if (result) {
			System.out.println("Species Registered Succesfully");
		} else {
			System.out.println("Error, The Species could not be registered.");
		}

	}

	/**
	 * Description: Asks the user to input the neccesary attributes for modifying a Species in a BioPlace.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 * pos: Species is modified or not.
	 */

	public void modifySpeciesOfBioPlace() {

		System.out.println("Enter the name of the bio place:");
		String placeName = reader.nextLine();

		System.out.println("Enter the name of the species to modify:");
		String speciesName = reader.nextLine();

		System.out.println("Choose the new kingdom: ");
		System.out.println(control.listKingdom());

		int kingdomChoice = reader.nextInt();

		Kingdom newKingdom = null;

		switch (kingdomChoice) {
			case 1:
				newKingdom = Kingdom.FLORA;
				break;
			case 2:
				newKingdom = Kingdom.FAUNA;
				break;
			default:
				System.out.println("Invalid kingdom choice.");
				break;
		}

		if (newKingdom != null) {

			System.out.println("Enter the new photo URL:");
			String newPhoto = reader.nextLine();

			System.out.println("Enter the new number of specimens:");
			int newSpecimens = reader.nextInt();

			boolean result = control.modifySpeciesOfBioPlace(placeName, speciesName, newKingdom, newPhoto,
					newSpecimens);

			if (result) {

				System.out.println("Species modified successfully.");

			} else {

				System.out.println("Failed to modify species.");
			}

		}

	}

	/**
	 * Description: Shows the created Trial cases.
	 * pre: Method in Controller must be initialized.
	 * pos: None
	 */
	public void trialCaseCreation() {

		control.createTrialCases();

	}

	/**
	 * Description: Shows the user according to their preference the BioPlace is asking..
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 */
	public void consultBioPlaces() {

		reader.nextLine();

		System.out.println(control.bioPlaceLists());

		System.out.println("Type the BioPlace index.");
		int placeIndex = reader.nextInt();

		String placeName = Integer.toString(placeIndex);

		String msg = control.showBioPlace(placeName);

		if (msg.equals("The inquired place isn't aviabable.")) {

			System.out.println(msg);

		} else {

			System.out.println("The Details of the Bio-Place are:\n" + msg);
		}

	}

	/**
	 * Description: Shows the user according to their preference the Community according to a Department in a BioPlace the user is asking.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 */
	public void consultCommunitiesOfADepartment() {

		System.out.println("Please select the department:");

		System.out.println(control.listDepartment());

		int departmentCode = reader.nextInt();

		Department department = Department.values()[departmentCode - 1];

		List<String> departmentCommunities = control.getCommunitiesByDepartment(department);

		if (!departmentCommunities.isEmpty()) {

			System.out.println("Communities in department " + department + ":");

			for (String communityName : departmentCommunities) {

				System.out.println("- " + communityName);
			}

		} else {

			System.out.println("No communities found in department " + department);
		}

	}

	/**
	 * Description: Shows the user according to their preference the Communities which have as Major Issues Hospital absence or Educational Services Lacking.
	 * pre: Scanner must be innitialized and relations with Controller as well.
	 */
	public void consultCommunitiesMajorIssuesHospOrEdu() {

		List<String> communitiesWithIssues = control.consultCommunitiesMajorIssuesHospOrEdu();

		if (!communitiesWithIssues.isEmpty()) {

			System.out.println("Communities with major issues related to hospitals or education:");

			for (String communityName : communitiesWithIssues) {

				System.out.println("- " + communityName);
			}
		} else {

			System.out.println("No communities found with major issues related to hospitals or education.");
		}

	}

	/**
	 * Description: Consults the biological place with the most species.
	 * pre: Method in Controller must be Initialized.
	 */
	public void consultBioPlaceWithMoreSpecies() {

		control.consultBioPlaceWithMoreSpecies();

	}

	/**
	 * Description:Consults the three largest places by area in square kilometers
	 * (Km^2).
	 * pre: Method in Controller must be initialized.
	 */
	public void consultTheThreeLargestPlacesByKm2() {

		control.consultTheThreeLargestPlacesByKm2();

	}
}
