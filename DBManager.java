import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	private static Hotel Hilton = new Hotel("Hotel Hilton", 5, 12000, "101", 1, "Great hotel downtown.");
	private static Hotel Hafnafjordur = new Hotel("Hotel Hafnafjordur", 3, 5000,"300", 2, "Faraway from everything.");
	private static Hotel Saga = new Hotel("Radison BLU", 4, 10000, "101", 3, "Hotel near the University");
	//private static Room hilton1 = new Room(1, 1, {"2016-02-21", "2016-02-22"}, {"",""}, {true, true});
	
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
	    	  nameString = "Hotels.Name ='"+HotelName+"'";
	      }
	      else{
	    	  nameString = "Hotels.Name IS NOT NULL";
	      }
	      String LocationString = "";
	      if(!Loc.isEmpty()){
	    	  LocationString = "Hotels.Location ='"+Loc+"'";
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
	      //System.out.println(sql);

		  ResultSet rs = stmt.executeQuery(sql);
		  int countFound = 0;
		  ///*
		  while ( rs.next() ) {
			  int HotelID = rs.getInt("Id");
			  String  currHotelName = rs.getString("Name");
			  int HotelPrice  = rs.getInt("Price");
			  int totalPrice = getTotalPrice(roomAmounts, HotelPrice);
			  int HotelStars = rs.getInt("Stars");
			  String Location = rs.getString("Location");
			  String description = rs.getString("Description");
			  if(areThereRoomsAvailable(date, nrOfNights, roomAmounts,HotelID) && MaxPrice >= totalPrice){
				  System.out.println(currHotelName);
				  Hotel newHotel = new Hotel(currHotelName, HotelStars,HotelPrice, Location, HotelID, description);
				  hotelsFound[countFound] = newHotel;
				  countFound++;
			  }
			  
			  //if(areThereRoomsAvailable(int date, int nrOfNights,int[] roomAmounts, int HotelID));	         
		  }
		  returnHotels = new Hotel[countFound];
		  for(int i = 0; i<countFound;i++){
			  returnHotels[i] = hotelsFound[i];
		  }
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
  	private static boolean areThereRoomsAvailable(int date, int nrOfNights,int[] roomAmounts, int HotelID){
  		Connection c = null;
	  	Statement stmt = null;
	  	boolean isAvail = true;
	  	int[] count = {0,0,0,0};
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:HotelSearch.db");
	      //System.out.println("Opened database successfully");
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
		} 
  		catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
	    }
	    
	    return isAvail;

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
