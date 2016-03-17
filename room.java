class Room {
    // field, constructor, and 
    // method declarations
    int hotelID
    int roomType
    boolean[900] isAvailable
    String[900] date
    int hotelID
    String[900] bookedBy

    public Room(int hotelID, int roomType, boolean[900] isAvailable, String[900] date,
        int hotelID, String[900] bookedBy){

        this.hotelID = hotelID;
        this.roomType = roomType;
        this.isAvailable = isAvailable;
        this.date = date;
        this.hotelID = hotelID;
        this.bookedBy = bookedBy;
    }

    public string getIsAvailabel(){
    	return isAvailable
    }

    public string getDate(){
    	return date
    }

    public string getBooker(){
    	return bookedBy
    }
}