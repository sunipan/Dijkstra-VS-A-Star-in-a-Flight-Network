
public class Airport {
	
	private String name;
	private String city;
	private String country;

	private double latitude;
	private double longitude;

	private String IATACode;

	private int tripCost;  // this is how much it cost to reach this node from source node
	private Flight[] outgoingFlights;	// A priority queue object of this airports outgoing flights in natural order by cost
	

	// Empty constructor for testing
	public Airport(int tripCost) {
		this.tripCost = tripCost;
	}
	
	// Constructor used for graph if needed
	public Airport(String name, String city, String country, String IATACode, double latitude, double longitude, Flight[] flights) {
		this.name = name;
		this.city = city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.IATACode = IATACode;
		this.outgoingFlights = flights;
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

	public Flight[] getOutgoingFlights() {
		return outgoingFlights;
	}

	public void setOutgoingFlights(Flight[] outgoingFlights) {
		this.outgoingFlights = outgoingFlights;
	}

	public int getTripCost() {
		return tripCost;
	}

	public void setTripCost(int tripCost) {
		this.tripCost = tripCost;
	}

	public void display(){
		System.out.println(this.name);
		System.out.println(this.city + ", " + this.country + ", IATA Code: " + this.IATACode);
		System.out.println("Lat, Long: " + this.latitude + ", " + longitude);
		System.out.println("================================================");
	}
	
}
