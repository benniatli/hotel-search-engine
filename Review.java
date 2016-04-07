public class Review {
	private int hotelID, rating, reviewID;
	private String description;
	
	public Review(int hotelID, int rating, int reviewID, String description){
		this.hotelID = hotelID;
		this.rating = rating;
		this.reviewID = reviewID;
		this.description = description;
	}
	
	public int getRating(){
		return rating;
	}
	
	public String getDescription(){
		return description;
	}
}