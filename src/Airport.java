import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Airport {
	// needed for fib heap pq
	FibonacciHeap.Entry<Airport> entry;

	private String name;
	private String city;
	private String country;

	private double latitude;
	private double longitude;

	private String IATACode;

	private int tripCost = 1000000000;  // this is how much it cost to reach this node from source node
	private int guessCost = 1000000000; //heuristic cost
	private ArrayList<Flight> departures;
	private Airport previous = null;
	private Flight path;


	// Empty constructor for testing
	public Airport(int tripCost) {
		this.tripCost = tripCost;
	}

	// Constructor used for graph if needed
	public Airport(String name, String city, String country, String IATACode, double latitude, double longitude) {
		this.name = name;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.IATACode = IATACode;

		this.departures = new ArrayList<Flight>();
	}


	// Property Getters and Setters
	//================================================================================
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry(){
		return this.country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getIATACode() {
		return this.IATACode;
	}

	public void addRoute(Flight fl){
		this.departures.add(fl);
	}

	public ArrayList<Flight> getOutgoingFlights() {
		return this.departures;
	}

	public void setOutgoingFlights(ArrayList<Flight> outgoingFlights) {
		this.departures = outgoingFlights;
	}

	public int getTripCost() {
		return tripCost;
	}

	public void setTripCost(int tripCost) {
		this.tripCost = tripCost;
	}

	public Flight getPath() {
		return path;
	}

	public void setPath(Flight path) {
		this.path = path;
	}

	public int getGuessCost() {
		return guessCost;
	}

	public void setGuessCost(int guessCost) {
		this.guessCost = guessCost;
	}

	public void display(){
		System.out.println(this.name);
		System.out.println(this.city + ", " + this.country + ", IATA Code: " + this.IATACode);
		System.out.println("Lat, Long: " + this.latitude + ", " + longitude);

		if (this.departures.size() != 0){
			System.out.println("Avaliable Flights:");
			for (Flight fl : this.departures) {
				fl.display();
			}
		}


		System.out.println("================================================");
	}

	public static ArrayList<String[]> getAirports(){

		ArrayList<String[]> airportList = new ArrayList<String[]>();

		try {
			//Scanner sc = new Scanner(new File("network-data\\airports.data"));
			//Scanner sc = new Scanner(new File("C:\\Users\\vfrunza\\320-Project\\src\\network-data\\airports.data"));
			//sc.useDelimiter(",");
			BufferedReader csvReader = new BufferedReader(new FileReader("src/network-data/airports.data"));
			String row;
			//while (sc.hasNext())
			while ((row = csvReader.readLine()) != null)
			{
				airportList.add(row.split(","));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return airportList;
	}

	public double getLatitude(){
		return this.latitude;
	}

	public double getLongitude(){
		return this.longitude;
	}

	public Airport getPrevious() {
		return this.previous;
	}
	public void setPrevious(Airport prev) {
		this.previous = prev;
	}
}
