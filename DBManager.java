import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	private static Hotel Hilton = new Hotel("Hotel Hilton", 5, 12000, "101", 1, "Great hotel downtown.");
	private static Hotel Hafnafjordur = new Hotel("Hotel Hafnafjordur", 3, 5000,"300", 2, "Faraway from everything.");
	private static Hotel Saga = new Hotel("Radison BLU", 4, 10000, "101", 3, "Hotel near the University");
	//private static Room hilton1 = new Room(1, 1, {"2016-02-21", "2016-02-22"}, {"",""}, {true, true});
	
	//Mock Object
	public static Hotel[] searchHotel(int date, int nrOfNights, String Loc, String name,
									int stars, int[] roomAmounts, int MaxPrice){
		//Search for available hotels
		Hotel[] listOfHotels = new Hotel[3];
		listOfHotels[0] = Hilton;
		listOfHotels[1] = Hafnafjordur;
		listOfHotels[2] = Saga;
		return listOfHotels;
	}
	
	//Mock Object
	public static String bookHotel(Hotel hotel, int date, int nrOfNights, int roomtype,
			String bookingName, int[] roomAmount){
  int roomSize;
  Boolean[] Booked = new Boolean[roomAmount.length];
  for (int j=0; j<roomAmount.length; j++){
	Connection c = null;
	Statement stmt = null;
	roomSize = roomAmount[j];
	
    try {
    	Class.forName("org.sqlite.JDBC");
    	c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
    	c.setAutoCommit(false);
    	stmt = c.createStatement();
    	int hotelID = hotel.getID();
    	
    	ResultSet rs = stmt.executeQuery("SELECT * FROM Rooms WHERE hotelId = " + hotelID +
    				" AND RoomSpace = " + roomSize + ";");
    	
    	Boolean avail = true;
    	String[] available = new String[368];
    	
    	while (rs.next() && !Booked[j]) {
            int id = rs.getInt("Id");
            for (int i=0; i<368; i++){
            	available[i] = rs.getString(i+1);
            	//System.out.println(i-2 + ": " +  available[i]);
            }
            //Try to find available room:
            for (int i=date; i<date+nrOfNights; i++){
            	if(!available[i+3].equals("")){
            		avail=false;
            	}
            	//System.out.println(avail);
            }
            if(avail){
            	String sql;
            	for (int i=date; i<date+nrOfNights; i++){
            		sql = "UPDATE Rooms set '" + i + "' = '" + bookingName +
            				"' WHERE Id = '" + id + "';";
            		stmt.executeUpdate(sql);
            		c.commit();
            	}
            	Booked[j] = true;
            }
            else{
            	Booked[j] = false;
            }
            avail = true;
        }

    	rs.close();
        stmt.close();
        c.close();
    } 
    catch ( Exception e ) {
    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    	System.exit(0);
    }
    
  }
  Boolean retn = false;
  for (int k=0;k<Booked.length;k++){
	  if(Booked[k]){
		  retn = true;
	  }
  }
  if(retn){
	  return "Booking complete.";
  }
  else{
	  return "Booking unsuccessful!";
  }
}
	
	/*public static Room[] getRooms(int hotelId, String date, int nrOfNights){
		
	}
	*/

}
