
public class HotelManager {

	public Hotel[] searchHotel(int date, int nrOfNights, String Loc, String name,
									int stars, int MaxPrice, int[] roomAmounts){
		DBManager dbsearch = new DBManager();
		Hotel[] foundHotels = dbsearch.searchHotel(date, nrOfNights, Loc, name, stars, MaxPrice, roomAmounts);
		return foundHotels;
	}
	
	public String bookHotel(Hotel hotel, int date, int nrOfNights, String bookingName, int[] roomAmounts){
		DBManager dbbook = new DBManager();
		String booking = dbbook.bookHotel(hotel, date, nrOfNights, bookingName, roomAmounts);
		return booking;
	}
	

	
}
