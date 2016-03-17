class Hotel {
    // field, constructor, and 
    // method declarations
    String hotelName
    int postalCode
    int avgCostPerNight
    int stars
    String description
    int rating
    int iD
    Review[] review
    Room[] room

    public Hotel(String hotelName,int postalCode, int avgCostPerNight, int stars, String description
        , int rating, int iD, Review[] review, Room[] room){
        this.postalCode = postalCode;
        this.avgCostPerNight = avgCostPerNight;
        this.stars = stars;
        this.description = description;
        this.rating = rating;
        this.iD = iD;
        this.review = review;
        this.room = room;
    }

    public String getName(){
    	return hotelName
    }

    public String getP(){
    	return postalCode
    }

    public int getCost(){
    	return avgCostPerNight
    }

    public String getDescr(){
    	return description
    }	

    public int getRating(){
    	return rating
    }

    public int getID(){
    	return iD
    }
}