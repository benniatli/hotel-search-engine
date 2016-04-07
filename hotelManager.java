
public class HotelManager {

	
	public static Hotel[] searchHotel(String date, int nrOfNights, String Loc, String name,
									int stars, int MaxPrice, int MinPrice){
		Hotel[] foundHotels = DBManager.searchHotel(date, nrOfNights, Loc, name, stars, MaxPrice, MinPrice);
		return foundHotels;
	}
	
	public static String bookHotel(Hotel hotel, String date, int nrOfNights, int roomtype, String bookingName){
		String booking = DBManager.bookHotel(hotel, date, nrOfNights, roomtype, bookingName);
		return booking;
	}
	
	
	// reviews = HotelManager.getReviews()
	public static Review[] getReviews(){
		//need to find reviews
		Review[] reviews = new Review[1];
		return reviews;
	}
	
}
