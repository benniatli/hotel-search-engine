import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	// searchhotel leitar ad hoteli fyrir gefna komudagsetningu, fjolda gistinotta,
	// stadsetningu, nafns a hoteli, stjornufjolda, hamarksverds og samsetningu a herbergjum 
	// sem oskad er eftir. Skilda er ad fylla ut i komudagsetningu, fjolda gistinotta og 
	// samsetningu herbergja.
	// Fallid skilar fylki af hotel objectum. 
	public static Hotel[] searchHotel(int date, int nrOfNights, String Loc, String HotelName,
			int stars, int MaxPrice, int[] roomAmounts){
		Connection c = null;
		Statement stmt = null;
		Hotel [] hotelsFound = new Hotel[200];
		Hotel [] returnHotels;


		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
			//System.out.println("Opened database successfully");
			stmt = c.createStatement();
			//String sql = "Select * from Hotels, Rooms where Hotels.Id = Rooms.HotelId;";
			String nameString = "";
			if(!HotelName.isEmpty()){
				nameString = "Hotels.Name LIKE'%"+HotelName+"%'";
			}
			else{
				nameString = "Hotels.Name IS NOT NULL";
			}
			String LocationString = "";
			if(!Loc.isEmpty()){
				LocationString = "Hotels.Location LIKE'%"+Loc+"%'";
			}
			else{
				LocationString = "Hotels.Location IS NOT NULL";
			}
			String StarString= "";
			if(stars>=0){
				StarString = "Hotels.Stars >='"+Integer.toString(stars)+"'";
			}
			else{
				StarString = "Hotels.stars IS NOT NULL";
			}
			if(MaxPrice <0){
				MaxPrice = Integer.MAX_VALUE;
			}
			String sql = "Select * from Hotels where "+ nameString+" AND " +LocationString+" AND "+StarString+ ";";

			ResultSet rs = stmt.executeQuery(sql);
			int countFound = 0;
			while ( rs.next() ) {
				int HotelID = rs.getInt("Id");
				String  currHotelName = rs.getString("Name");
				int HotelPrice  = rs.getInt("Price");
				int totalPrice = getTotalPrice(roomAmounts, HotelPrice);
				int HotelStars = rs.getInt("Stars");
				String Location = rs.getString("Location");
				String description = rs.getString("Description");
				if(areThereRoomsAvailable(date, nrOfNights, roomAmounts,HotelID) && MaxPrice >= totalPrice){
					Hotel newHotel = new Hotel(currHotelName, HotelStars,HotelPrice, Location, HotelID, description);
					hotelsFound[countFound] = newHotel;
					countFound++;
				}
			}
			returnHotels = new Hotel[countFound];
			for(int i = 0; i<countFound;i++){
				returnHotels[i] = hotelsFound[i];
			}
			c.close();

			//*/
		} catch ( Exception e ) {
			returnHotels = new Hotel[0];
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		

		return returnHotels;

	}
	private static int getTotalPrice(int [] roomAmounts, int HotelPrice){
		int totalPrice = 0;
		for(int i = 0;i<4;i++){
			totalPrice += HotelPrice * roomAmounts[i] * (i+1);
		}
		return totalPrice;
	}
	//athugar hvort tiltekid hotel hefur laus herbergi eftir uppskrift roomAmounts fyrir gefnar dagsetningar
	private static boolean areThereRoomsAvailable(int date, int nrOfNights,int[] roomAmounts, int HotelID){
		Connection c = null;
		Statement stmt = null;
		boolean isAvail = true;
		int[] count = {0,0,0,0};
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
			stmt = c.createStatement();
			String sql = "Select * from Rooms where hotelID="+HotelID+";";
			ResultSet rs = stmt.executeQuery(sql);
			///*
			while ( rs.next() ) {
				int roomSize = rs.getInt("RoomSpace");
				int roomID = rs.getInt("Id");
				boolean roomIsAvailable = isRoomAvailable(date,nrOfNights,roomID);
				if(roomSize == 1 && roomIsAvailable){
					count[0]++;
				}
				if(roomSize == 2 && roomIsAvailable){
					count[1]++;
				}
				if(roomSize == 3 && roomIsAvailable){
					count[2]++;
				}
				if(roomSize == 4 && roomIsAvailable){
					count[3]++;
				}
			} 
			c.close();

		}
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		for(int k = 0; k<4; k++){
			int numRoomsOfRoomSize = roomAmounts[k];
			if(count[0] < numRoomsOfRoomSize){
				isAvail=false;
			}
		}
		return isAvail;
	}
	// Athugar hvort tiltekid herbergi se laust fyrir gefnar dagsetningar
	private static boolean isRoomAvailable(int date,int nrOfNights, int RoomID){
		boolean isAvail = true;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
			stmt = c.createStatement();
			String sql = "Select * from Rooms where Id="+RoomID+";";
			ResultSet rs = stmt.executeQuery(sql);
			for(int i = date; i<date+nrOfNights;i++){
				String roomBooking = rs.getString(Integer.toString(i));
				if(!roomBooking.isEmpty()){
					isAvail = false;
				}
			}
			c.close();

		} 
		catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}

		return isAvail;

	}

	// Fall sem bokar tiltekid hotel fyrir tilteknar dagsetningu (komudagsetningu) og fjodla gistinotta
	// og bokunarnafn og samsetningu herbergja sem oskad er eftir.
	// Fallid skilar streng sem segir hvort bokun hafi tekist eda ekki.
	public static String bookHotel(Hotel hotel, int date, int nrOfNights, 
			String bookingName, int[] roomAmount){
		String bokunarSkilabod = "";
		Connection c = null;
		Statement stmt = null;
		if(!areThereRoomsAvailable(date,nrOfNights,roomAmount,hotel.getID())){
			bokunarSkilabod = "Thvi midur eru ekki thessi samsetning herbergja laus a thessu hoteli fyrir thessar dagsetningar.";
			
		}
		else{
			try{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				for(int i = 0; i<roomAmount.length;i++){
					int bookingCount = 0;
					ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms WHERE hotelId = " + hotel.getID() +
							" AND RoomSpace = " + (i+1) + ";");
					while(rs.next()){
						int roomID = rs.getInt("Id");
						if(isRoomAvailable(date, nrOfNights, roomID)){
							for(int j = date; j < date+nrOfNights;j++){
								String sql = "UPDATE Rooms set '" + Integer.toString(j) + "' = '" + bookingName +
										"' WHERE Id = '" + roomID + "';";
								stmt.executeUpdate(sql);
								c.commit();
								bookingCount++;
							}
							if(bookingCount>roomAmount[i]){
								break;
							}
						}
					}
					rs.close();

				}
				stmt.close();
				c.close();
				bokunarSkilabod = "Bokun tokst, Jeij.";
			}
			catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
		}

		return bokunarSkilabod;
	}
}
