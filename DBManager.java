public class DBManager {
	private static Hotel Hilton = new Hotel("Hotel Hilton", 5, 12000, 101, 1, "Great hotel downtown.");
	private static Hotel Hafnafjordur = new Hotel("Hotel Hafnafjordur", 3, 5000, 300, 2, "Faraway from everything.");
	private static Hotel Saga = new Hotel("Radison BLU", 4, 10000, 101, 3, "Hotel near the University");
	//private static Room hilton1 = new Room(1, 1, {"2016-02-21", "2016-02-22"}, {"",""}, {true, true});
	
	//Mock Object
	public static Hotel[] searchHotel(long date, int nrOfNights, String Loc, String name,
									int stars, int MaxPrice, int MinPrice){
		//Search for available hotels
		Hotel[] listOfHotels = new Hotel[3];
		listOfHotels[0] = Hilton;
		listOfHotels[1] = Hafnafjordur;
		listOfHotels[2] = Saga;
		return listOfHotels;
	}
	
	//Mock Object
	public static String bookHotel(Hotel hotel, long date, int nrOfNights, int roomtype, String bookingName){
		//Try to book hotel...

		return "DBManager bookHotel() tokst.";
	}
	
	/*public static Room[] getRooms(int hotelId, String date, int nrOfNights){
		
	}
	*/

}
