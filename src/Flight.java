import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight { //this is similar to Java's Edge class
	
	private Airport source; //where flight departs from
	private Airport dest;	// where flight lands
	private Airline airline;
	private int cost;		// cost of the flight
	private int depTime;	// departure time (minutes)
	private int travelTime; // time taken to reach destination (minutes)
	
	//Used for testing the order of the priority queue by cost
	public Flight(int cost) {
		this.cost = cost;
	}
	
	// Used for testing
	public Flight(Airport source, Airport dest, Airline airline) {
		this.source = source;
		this.dest = dest;
		this.airline = airline;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDest() {
		return dest;
	}

	public void setDest(Airport dest) {
		this.dest = dest;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDepTime() {
		return depTime;
	}

	public void setDepTime(int depTime) {
		this.depTime = depTime;
	}

	public int getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}

	public void display(){
		System.out.println("Airline: " + this.airline.getName() + " | Destination: " + this.dest.getCity());
	}

	public static ArrayList<String[]> getFlights(){
        ArrayList<String[]> flightList = new ArrayList<String[]>();

        try {
            Scanner sc = new Scanner(new File("C:\\Users\\vfrunza\\320-Project\\src\\network-data\\routes.data"));
            sc.useDelimiter(","); 

            while (sc.hasNext())  
            {  
                flightList.add(sc.nextLine().split(","));
            }   
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  

        return flightList;
    }

}
