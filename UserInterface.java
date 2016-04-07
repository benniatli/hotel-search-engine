import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class UserInterface {
    // field, constructor, and 
    // method declarations
    Hotel hotel;
    String firstName;
    String lastName;
    String date;
    String location;
    String hotelName;
    int postalCode;
    int stars;
    int maxPrice;
    int minPrice;
    int nrOfNights;
    private Hotel[] results;

    public Hotel[] searchHotel(String date, int nrOfNights, String loc, String name, int stars, int minPrice, int maxPrice) throws ParseException{
    	 System.out.println("n");
    	HotelManager manager = new HotelManager();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = df.parse(date);
		long dateInt = d.getTime()/ (24 * 60 * 60 * 1000)-16801; // breyta í 16800 ef fyrsti dagur í töflu er 1 en ekki 0
		System.out.println(dateInt);
		
		return results = manager.searchHotel(dateInt, nrOfNights, loc, name, stars, minPrice, maxPrice);	
    }

    public String bookHotel(Hotel hotel, String date, int nrOfNights, int roomType, String bookingName, int whichLine) throws ParseException{
    HotelManager manager = new HotelManager();
	DateFormat df = new SimpleDateFormat("yyyyMMdd");
	Date d = df.parse(date);
	long dateInt = d.getTime()/ (24 * 60 * 60 * 1000)-16801; // breyta í 16800 ef fyrsti dagur í töflu er 1 en ekki 0
	System.out.println(dateInt);
    
    return manager.bookHotel(hotel, dateInt, nrOfNights, roomType, bookingName);
    }

    public String displayResult(Hotel[] hotels){
    System.out.println("n");
    return results[1].getName();
    }
    
	public static void main(String[] args) throws ParseException{
		UserInterface notkun1 = new UserInterface();
		notkun1.searchHotel("20161231", 2, "dd", "ff", 4, 2, 8);
	}
}