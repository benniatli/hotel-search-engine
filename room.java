public class Room {
	private int hotelID, roomType;
	private String[] date, bookedBy;
	private boolean[] isAvailable;
	
	public Room(int hotelID, int roomType, String[] date, String[] bookedBy, boolean[] isAvailable){
		this.hotelID = hotelID;
		this.roomType = roomType;
		this.date = date;
		this.bookedBy = bookedBy;
		this.isAvailable = isAvailable;
	}
	
}