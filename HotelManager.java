
public class HotelManager {

	
	public Hotel[] searchHotel(String date, int nrOfNights, String Loc, String name,
									int stars, int MaxPrice, int MinPrice){
		DBManager dbsearch = new DBManager();
		Hotel[] foundHotels = dbsearch.searchHotel(date, nrOfNights, Loc, name, stars, MaxPrice, MinPrice);
		return foundHotels;
	}
	
	public String bookHotel(Hotel hotel, String date, int nrOfNights, int roomtype, String bookingName){
		DBManager dbbook = new DBManager();
		String booking = dbbook.bookHotel(hotel, date, nrOfNights, roomtype, bookingName);
		return booking;
	}
	
	
	// reviews = HotelManager.getReviews()
	public Review[] getReviews(){
		//need to find reviews
		Review[] reviews = new Review[1];
		return reviews;
	}
	
}
