class Room {
    // field, constructor, and 
    // method declarations
    int hotelID;
    int roomType;
    boolean[] isAvailable;
    String[] date;
    String[] bookedBy;

    public Room(int hotelID, int roomType, boolean[] isAvailable, String[] date	
    		, String[] bookedBy){

        this.hotelID = hotelID;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.date = date;
        this.hotelID = hotelID;
        this.bookedBy = bookedBy;
    }

    public boolean[] getIsAvailabel(){
    	return isAvailable;
    }

    public String[] getDate(){
    	return date;
    }

    public String[] getBooker(){
    	return bookedBy;
    }
}