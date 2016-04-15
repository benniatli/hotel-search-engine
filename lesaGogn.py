import sqlite3 as lite
import sys
import random 
files = open("hotelGogn.txt",'r')

file_list =  files.readlines()

con = lite.connect('HotelSearch.db')
con.text_factory = str


stringList = file_list[1].split('"')

with con:
	cur = con.cursor()    
	cur.execute("DROP TABLE IF EXISTS Hotels")
	cur.execute("CREATE TABLE Hotels(Id INTEGER PRIMARY KEY, Name TEXT, Location TEXT, Stars INTEGER, Price INTEGER, Description TEXT)")
	for line in file_list[1:len(file_list)]:
		line = line.strip();
		stringList = line.split('"')
		hotelID = int(stringList[1])
		hotelName = stringList[3]
		hotelLocation = stringList[5]
		hotelStars = int(stringList[7])
		price = stringList[9][1:len(stringList[9])]
		hotelPrice = int(price)
		hotelDescription = stringList[11]
		cur.execute("INSERT INTO Hotels VALUES(?,?,?,?,?,?)",(hotelID, hotelName, hotelLocation, hotelStars, hotelPrice, hotelDescription))

	cur.execute("DROP TABLE IF EXISTS Rooms")
	cur.execute("CREATE TABLE Rooms(Id INTEGER PRIMARY KEY AUTOINCREMENT, hotelID INTEGER REFERENCES Hotels(Id), RoomSpace INT)")
	for i in range(1,368):
		cur.execute("ALTER TABLE Rooms ADD COLUMN '{cn}' TEXT DEFAULT '{df}'".format(cn = i, df = ""))
	#104 hotel eins og er
	for i in range(1, 105):
		numRooms = random.randint(2,10)
		for j in range(1,numRooms):
			numPeople = random.randint(1,4)
			cur.execute("INSERT INTO Rooms(hotelID, RoomSpace) VALUES (?,?)",(i,numPeople))
