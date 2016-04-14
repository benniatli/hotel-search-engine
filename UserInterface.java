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
	int nrOfPersons;
	int[] roomAmounts;


	public Hotel[] searchHotel(String date, int nrOfNights,  int nrOfPersons, String loc, String name, int stars,
			int[] roomAmounts, int minPrice, int maxPrice) throws ParseException{
		HotelManager manager = new HotelManager();

		int dateInt = (int) convertDate(date);
		
		
		return  manager.searchHotel(dateInt, nrOfNights, loc, name, stars, roomAmounts, maxPrice);	
	}

	
	
	public String bookHotel(Hotel hotel, String date, int nrOfNights, int roomType, String bookingName, int[] roomAmounts, int whichLine) throws ParseException{
		HotelManager manager = new HotelManager();

		int dateInt = (int) convertDate(date);
		return manager.bookHotel(hotel, dateInt, nrOfNights, roomType, bookingName, roomAmounts);
	}



	public static  void displayResult(Hotel[] hotels){
		//niðurstöður leitarinnar birtar í númaraðri röð
		for(int i = 0;i<hotels.length;i++){
			System.out.println(i + ". " + hotels[i].name);
		}

	}

	//Breytir date streng  í numer dags frí 1 jan 2016 af tagi long.
	public long convertDate(String date) throws ParseException{
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = df.parse(date);
		int dateInt = (int) (d.getTime()/ (24 * 60 * 60 * 1000)-16801);// breyta í 16800 ef fyrsti dagur � t�flu er 1 en ekki 0
		return dateInt;
	}



	public static  void main(String[] args) throws ParseException{

		UserInterface session = new UserInterface();	
		int[] arr = {1,2,0,0};

		displayResult(session.searchHotel("20161231", 2,2, "dd", "ff",4,arr, 2, 8));

			
	}	
	

}