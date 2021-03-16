
public class Flight { //this is similar to Java's Edge class
	
	int flightNum;
	Airport source; //where flight departs from
	Airport dest;	// where flight lands
	int depTime;	// departure time (minutes)
	int travelTime; // time of arrival (minutes)
	
	public Flight(int flightNum, Airport source, Airport dest, int depTime, int arrTime) {
		this.flightNum = flightNum;
		this.source = source;
		this.dest = dest;
		this.depTime = depTime;
		this.travelTime = arrTime;
	}
	
	public Flight(Airport source, Airport dest) {
		this.source = source;
		this.dest = dest;
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
