class UserInterface {
    // field, constructor, and 
    // method declarations
    Hotel hotel;
    String firstName;
    String lastName;
    int date;
    String location;
    String hotelName;
    int postalCode;
    int stars;
    int maxPrice;
    int minPrice;
    int nrOfNights;
    private Hotel[] results;

    public Hotel[] searchHotel(String date, int nrOfNights, String loc, String name, int stars, int minPrice, int maxPrice){
		HotelManager2 manager = new HotelManager2();
		
		return results = manager.searchHotel(date, nrOfNights, loc, name, stars, minPrice, maxPrice);

    }

    public String bookHotel(Hotel hotel, String date, int nrOfNights, int roomType, String bookingName, int whichLine){
    HotelManager2 manager = new HotelManager2();
    
    return manager.bookHotel(hotel, date, nrOfNights, roomType, bookingName);
    }

    public String displayResult(Hotel[] hotels){
    System.out.println((results[1].getName()));
    return results[1].getName();
    }
    
	public static void main(String[] args){
		UserInterface notkun1 = new UserInterface();
		notkun1.displayResult(notkun1.results);
		System.out.println((notkun1.results[1].getName()));
		
	}
}