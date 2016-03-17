class Review {
    // field, constructor, and 
    // method declarations
    int hotelID
    int rating
    int reviewID
    String description

    public Review(int hotelID, int rating, int reviewID, String description){

        this.hotelID = hotelID;
        this.rating = rating;
        this.reviewID = reviewID;
        this.description = description;      
    }
    public int getRating(int){
    	return rating
    }

    public String getDescription(int){
    	return description
    }

}