
public class HotelManager {

	
	public Hotel[] searchHotel(int date, int nrOfNights, String Loc, String name,
									int stars, int[] roomAmounts, int MaxPrice){
		DBManager dbsearch = new DBManager();
		Hotel[] foundHotels = dbsearch.searchHotel(date, nrOfNights, Loc, name, stars, roomAmounts, MaxPrice);
		return foundHotels;
	}
	
	public String bookHotel(Hotel hotel, int date, int nrOfNights, int roomtype, String bookingName, int[] roomAmounts){
		DBManager dbbook = new DBManager();
		String booking = dbbook.bookHotel(hotel, date, nrOfNights, roomtype, bookingName, roomAmounts);
		return booking;
	}
	
	
	// reviews = HotelManager.getReviews()
	public Review[] getReviews(){
		//need to find reviews
		Review[] reviews = new Review[1];
		return reviews;
	}
	
}
