
public class Flight { //this is similar to Java's Edge class
	
	int flightNum;
	Airport source; //where flight departs from
	Airport dest;	// where flight lands
	int cost;		// cost of the flight
	int depTime;	// departure time (minutes)
	int travelTime; // time taken to reach destination (minutes)
	
	//Used for testing the order of the priority queue by cost
	public Flight(int cost) {
		this.cost = cost;
	}
	
	// Used for testing
	public Flight(Airport source, Airport dest, int cost) {
		this.source = source;
		this.dest = dest;
		this.cost = cost;
	}
	
	// Main constructor, possibly too many attributes
	public Flight(int flightNum, Airport source, Airport dest, int cost, int depTime, int travelTime) {
		this.flightNum = flightNum;
		this.source = source;
		this.dest = dest;
		this.cost = cost;
		this.depTime = depTime;
		this.travelTime = travelTime;
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
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

}
