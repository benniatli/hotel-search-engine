public class Hotel {
	public String name, description, postalCode;
	private int stars, price, ID;
	
	public Hotel(String name, int stars, int price, String postalCode, int ID, String description){
		this.name = name;
		this.stars = stars;
		this.price = price;
		this.postalCode = postalCode;
		this.ID = ID;
		this.description = description;
	}

	public String getName(){
		return name;
	}
	
	public int getStars(){
		return stars;
	}
	
	public int getPrice(){
		return price;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getPostalCode(){
		return postalCode;
	}
	
	public int getID(){
		return ID;
	}
}
