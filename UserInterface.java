import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


//Athuga, daemi um notkun er i main fallinu, thar eru leidbeiningar um notkun.
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
	//Fyrir:
	//	date = "yyyymmdd" er dagsetningu komu, skal vera a arinu 2016. Dagsetning+fjoldi
	//		gistinotta ma ekki fara fram yfir arid 2016.
	//	nrOfNights er fjoldi gistinotta. Passa skal ad fara ekki yfir a arid 2017.
	//	loc er stadsetning hotels. strengur. Ef loc er tomi strengurinn er ekki gerd krafa a stadsetningu
	//	name er nafn hotels. Leitad er eftir strengjum sem likjast name. Ekki tharf ad skrifa allt nafnid.
	//		ef name er tomi strengurinn er ekki gerd krafa a nafn hotels
	//	stars er lagmarksfjoldi stjarna sem leitad er eftir. Ef stars er neikvaed tala er ekki gerd krafa
	//		a neinn stjornufjolda
	//	maxPrice er hamarksverd allrar bokunarinnar. Ef maxPrice er neikvaett er ekki gerd krafa a kostnad bokunar.
	//	Fylkid roomAmounts segir til um hve morg herbergi
	//		skal boka af hverri herbergistegund. Fyrsta stakid taknar fjolda einstaklingsherbergja,
	//		annad stakid taknar fjolda 2-manna herbergja, thridja stakid fjolda 3-manna herbergja
	//		og fjorda stakid fjolda 4-manna herbergja.
	//Eftir:
	//	Fallid skilar array af hotel objectum sem uppfylla leitarkrofurnar
	public Hotel[] searchHotel(String date, int nrOfNights, String loc, String name, int stars,
			int maxPrice, int[] roomAmounts) throws ParseException{
		HotelManager manager = new HotelManager();

		int dateInt = (int) convertDate(date);
		
		
		return  manager.searchHotel(dateInt, nrOfNights, loc, name, stars, maxPrice, roomAmounts);	
	}

	
	//Fyrir:
	//	hotel er hotel Object. Faest ut fra search adferdinni.
	//	date = "yyyymmdd" er dagsetningu komu, skal vera a arinu 2016. Dagsetning+fjoldi
	//		gistinotta ma ekki fara fram yfir arid 2016.
	//	nrOfNights er fjoldi gistinotta. Passa skal ad fara ekki yfir a arid 2017.
	//	bookingName er nafn bokunar. MA EKKI VERA TOMI STRENGURINN.
	// ATH ATH ATH, EKKI LATA BOOKINGNAME VERA TOMA STRENGINN, OK!!!!??
	//	Fylkid roomAmounts segir til um hve morg herbergi
	//		skal boka af hverri herbergistegund. Fyrsta stakid taknar fjolda einstaklingsherbergja,
	//		annad stakid taknar fjolda 2-manna herbergja, thridja stakid fjolda 3-manna herbergja
	//		og fjorda stakid fjolda 4-manna herbergja.
	//Eftir:
	//	Buid er ad boka hotelid hotel fyrir gefnu dagsetninguna.
	public String bookHotel(Hotel hotel, String date, int nrOfNights, String bookingName, int[] roomAmounts) throws ParseException{
		HotelManager manager = new HotelManager();

		int dateInt = (int) convertDate(date);
		return manager.bookHotel(hotel, dateInt, nrOfNights,bookingName, roomAmounts);
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
		int dateInt = (int) (d.getTime()/ (24 * 60 * 60 * 1000)-16800);
		return dateInt;
	}



	public static  void main(String[] args) throws ParseException{

		//Daemi um notkun:
		//Buum til nytt userinterface, thad hefur search og book adferdir
		UserInterface session = new UserInterface();
		
		//Buum til samsetningu af herbergjum. Fylkid arr segir til um hve morg herbergi
		//skal boka af hverri herbergistegund. Fyrsta stakid taknar fjolda einstaklingsherbergja,
		//annad stakid taknar fjolda 2-manna herbergja, thridja stakid fjolda 3-manna herbergja
		//og fjorda stakid fjolda 4-manna herbergja.
		int[] arr = {1,1,0,0};
		
		//Leytum ad hoteli fyrir gefna upphafsdagsetningu, 
		//dagsetning skal vera strengur a forminu "yyyymmdd". Tharf ad vera dagsetning
		//a arinu 2016 og dagsetning+nrOfNights ma ekki fara yfir arid 2016.
		String date = "20160801";
		
		//Fjoldi notta sem skal gista
		int nrOfNights = 2;
		
		//location tharf ad vera strengur, ma vera tomi strengurinn. Ef thad er tomi strengurinn er
		// ekk gerd krafa a stadsetningu i leitinni.
		String hotelLocation = "";
		
		//hotelName tharf ad vera strengur, ef tomi strengurinn er ekki gerd krafa a nafn
		//Leitad er eftir nofnum sem eru sem likust gefnum streng ef eitthvad er skrifad.
		String hotelName = "Fo";
		
		//Lagmarksfjoldi stjarna. Setja neikvaeda tolu ef alveg sama um lagmarks stjornur
		int minStars = 2;
		
		//Hamarksverd fyrir alla bokunina. Setja neikvaeda tolu ef alveg sama um hamarks verd
		int MaxPrice = -1;
		
		//searchHotel(date, nrOfNights,hotelLocation, hotelName, minStars, MaxPrice, arr )

		//Synum nidurstodurnar
		displayResult(session.searchHotel(date, nrOfNights, hotelLocation, hotelName,minStars,MaxPrice,arr));
		//Vistum nidrustodurnar i fylki af hotelum
		Hotel[] hotel = session.searchHotel(date, nrOfNights, hotelLocation, hotelName,minStars,MaxPrice,arr);
		//String bookHotel(Hotel hotel, String date, int nrOfNights, String bookingName, int[] roomAmounts, int whichLine)
		
		//Booking name er nafn adila sem bokar. MA EKKI VERA TOMI STRENGURINN!!!!!
		//NENNI EKKI AD LAGA THAD THANNIG BARA EKKI LATA TOMA STRENGINN, takk takk.
		String bookingName = "Balli";
		//Bokum hotel sem er hluti af listanum sem search skilar. 
		String a = session.bookHotel(hotel[0], date, nrOfNights, bookingName, arr);
		System.out.println(a);

			
	}	
	

}