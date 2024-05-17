package model;

public class Product{
	
	//Atributes
	private String productName;
	private double productPercentage;
	private ProductType productType;
	private boolean handMade;
	
	
	//Builder
	
	public Product(String productName, double productPercentage, ProductType productType, boolean handMade){
		
		this.productName = productName;
		this.productPercentage = productPercentage;
		this.productType = productType;
		this.handMade = handMade;
	}
	
	public String toString(){
		
		String msg = "";
		
		msg += "Name: "+productName;
		msg += "\nNatural Products Percentage: "+productPercentage +"%";
		msg += "\nProduct Type: "+productType;
		msg += "\nHandMade?: "+handMade;
		
		return msg;
		
		
	}
	
	//Analizer
	public String getProductName(){
		
		return this.productName;
		
	}
	
	public double getProductPercentage(){
		
		return this.productPercentage;
		
	}
	
	public boolean isHandMade(){
		
        return handMade;
    }
	
	public void setHandMade(boolean handMade){
		
        this.handMade = handMade;
    }

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	

}