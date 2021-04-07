import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Flight { //this is similar to Java's Edge class

	private Airport source; //where flight departs from
	private Airport dest;	// where flight lands
	private Airline airline;
	private int cost;		// cost of the flight
	private int depTime;	// departure time (minutes)
	private int travelTime; // time taken to reach destination (minutes)
	private int travelDistance;	// distance

	//Used for testing the order of the priority queue by cost
	public Flight(int cost) {
		this.cost = cost;
	}

	//temp for heuristic function -- uses cost/km and travelDistance
	public Flight(Airport source, Airport dest) {
		this.source = source;
		this.dest = dest;

		calculateTravelTime();
	}

	// Used for testing
	public Flight(Airport source, Airport dest, Airline airline) {
		this.source = source;
		this.dest = dest;
		this.airline = airline;

		calculateTravelTime();
		calculateCost();
	}

	public Flight(Airport source, Airport dest, Airline airline, int depTime) {
		this.source = source;
		this.dest = dest;
		this.airline = airline;
		this.depTime = depTime;

		calculateTravelTime();
		calculateCost();
	}

	private void calculateCost() {
		this.cost = (int)(this.travelDistance *  (0.30 + ((Math.random() - 0.5) * 0.1)));
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

	public int getTravelDistance() {
		return this.travelDistance;
	}

	public void display(){
		System.out.println("Airline: " + this.airline.getName() + " | Destination: " + this.dest.getCity());
		//System.out.println("Distance: " + this.travelDistance + " | Time: " + this.travelTime + " | Cost: $" + this.cost);
		System.out.println("Departure time: " + this.depTime);
	}

	public static ArrayList<String[]> getFlights(){
        ArrayList<String[]> flightList = new ArrayList<String[]>();

        try {
						//Scanner sc = new Scanner(new File("network-data\\routes.data"));
            //Scanner sc = new Scanner(new File("C:\\Users\\vfrunza\\320-Project\\src\\network-data\\routes.data"));
            //sc.useDelimiter(",");
						BufferedReader csvReader = new BufferedReader(new FileReader("network-data/routes.data"));

            //while (sc.hasNext())
						String row;
						while ((row=csvReader.readLine()) != null)
            {
                flightList.add(row.split(","));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return flightList;
    }

	public void calculateTravelTime(){
		double lat1 = Math.toRadians(this.source.getLatitude());
		double lon1 = Math.toRadians(this.source.getLongitude());

		double lat2 = Math.toRadians(this.dest.getLatitude());
		double lon2 = Math.toRadians(this.dest.getLongitude());

		this.travelDistance = (int)Math.abs(greatCircle(lat1, lat2, lon1, lon2));
		this.travelTime = (int)Math.round(this.travelDistance * (60 / 1046.0)); //average airspeed of a 737
	}

	private double greatCircle(double lat1, double lat2, double lon1, double lon2){
		double earthRadius = 6371.009;


		double arcLength = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		return 6371.009 * arcLength;
	}

}
