package model;

public class Community {

	// Attributes

	private String communityName;
	private Race race;
	private int inhabitants;
	private Product[] productInventory;
	private String representantName;
	private String representantNumber;
	private MajorIssues[] majorIssues;
	private int productCount;
	private BioPlace place;

	public Community(String communityName, Race race, int inhabitants, String representantName,
			String representantNumber, MajorIssues[] majorIssues) {

		this.communityName = communityName;
		this.race = race;
		this.inhabitants = inhabitants;
		productInventory = new Product[20];
		this.representantName = representantName;
		this.representantNumber = representantNumber;
		this.majorIssues = majorIssues;
		this.productCount = 0;

	}

	public String toString() {

		String msg = "";

		msg += "Name of the Community: " + communityName;
		msg += "\nRace of the Community: " + race;
		msg += "\nNumber of Habitants of the Community: " + inhabitants;
		if (productInventory != null && productInventory.length > 0) {

			msg += "\nProduct Inventory the Community Offers: ";

			for (Product product : productInventory) {

				msg += "\n- " + product.getProductName() + " (" + product.getProductType() + ")";
			}

		} else {

			msg += "\nProduct Inventory: None";

		}
		msg += "\nName of the Representant of the Community: " + representantName;
		msg += "\nPhone Number of the Representant of the Community: " + representantNumber;
		msg += "\nBiggest Issues assesed to the Community: " + majorIssues;

		return msg;
	}

	public Product[] getProducts() {

		return productInventory;

	}

	/**
	 * Description:
	 * pre:
	 * pos:
	 */
	public boolean addProduct(Product newProduct) {

		if (productCount < productInventory.length) {

			productInventory[20] = newProduct;

			productCount++;

			return true;

		}

		return false;

	}

	/**
	 * Description: Registers a product to the community with the specified
	 * attributes.
	 * 
	 * @param productName       the name of the product to be registered
	 * @param productPercentage the percentage value associated with the product
	 * @param productType       the type of the product (1 for FOOD, 2 for
	 *                          HANDICRAFTS)
	 * @param handMade          a boolean indicating if the product is handmade
	 * @return {@code true} if the product was successfully registered, otherwise
	 *         {@code false}
	 */

	public boolean registerProductToCommunity(String productName, double productPercentage, int productType,
			boolean handMade) {

		ProductType newProductType = ProductType.FOOD;

		switch (productType) {
			case 1:
				newProductType = ProductType.FOOD;
				break;
			case 2:
				newProductType = ProductType.HANDICRAFTS;
				break;
		}

		Product newProduct = new Product(productName, productPercentage, newProductType, handMade);

		return addProduct(newProduct);
	}

	/**
	 * Description: Searches for a product with the specified name in the product inventory.
	 *
	 * @param productName the name of the product to search for
	 * @return the {@code Product} object if found, otherwise {@code null}
	 */
	public Product searchProduct(String productName) {

		for (int i = 0; i < productInventory.length; i++) {

			Product temporal = productInventory[i];

			if (temporal != null) {

				if (productName.equals(temporal.getProductName())) {

					return temporal;

				}
			}

		}

		return null;
	}

	/**
	 * Description:Generates a formatted list of Products with their name and
	 * natural percentage.
	 * 
	 * @return A string containing the formatted list of Products.
	 */
	public String productLists() {

		String list = "";

		for (int i = 0; i < productInventory.length; i++) {

			if (productInventory[i] != null) {

				list += "\n" + (i + 1) + "-" + productInventory[i].getProductName() + "-"
						+ productInventory[i].getProductPercentage();
			}

		}

		return list;

	}

	/**
	 * Description: Deletes a product with the specified name from the community's
	 * product
	 * inventory.
	 *
	 * @param productName the name of the product to be deleted
	 * @return {@code true} if the product was successfully deleted, otherwise
	 *         {@code false}
	 */

	public boolean deleteProductOfCommunity(String productName) {

		int index = searchProductIndex(productName);

		if (index != -1) {
			productInventory[index] = null;
			return true;
		}

		return false;
	}

	/**
	 * Description: Searches for the index of a product with the specified name in
	 * the product
	 * inventory.
	 *
	 * @param productName the name of the product to search for
	 * @return the index of the product if found, otherwise {@code -1}
	 */
	public int searchProductIndex(String productName) {

		for (int i = 0; i < productInventory.length; i++) {

			Product temporal = productInventory[i];

			if (temporal != null) {
				if (productName.equals(temporal.getProductName())) {

					return i;

				}
			}

		}

		return -1;

	}

	public String getCommunityName() {

		return this.communityName;
	}

	public String getRepresentantName() {

		return this.representantName;
	}

	public String getRepresentantNumber() {

		return this.representantNumber;
	}

	public MajorIssues[] getMajorIssues() {
		return majorIssues;
	}

	public void setMajorIssues(MajorIssues[] biggestIssues) {
		this.majorIssues = biggestIssues;
	}

	public BioPlace getBioPlace() {

		return place;
	}

	public void setPlace(BioPlace place) {
		this.place = place;
	}

}