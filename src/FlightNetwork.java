import java.util.ArrayList;

public class FlightNetwork {
    
    private Airport start;
    private Airport end;

    private int airportCount;
    private int flightCount;

    private ArrayList<Airport> airportNetwork;
    private ArrayList<Flight> flightNetwork;

    public FlightNetwork(){
        this.airportNetwork = new ArrayList<Airport>();
        this.flightNetwork = new ArrayList<Flight>();

        this.airportCount = 0;
        this.flightCount = 0;
    }

    public FlightNetwork(ArrayList<Airport> airportNetwork, ArrayList<Flight> flightNetwork) {
        this.airportNetwork = airportNetwork;
        this.flightNetwork = flightNetwork;

        this.airportCount = airportNetwork.size();
        this.flightCount = flightNetwork.size();
    }

    public ArrayList<Airport> getAirportNetwork() {
        return this.airportNetwork;
    }

    public void addAirport(Airport airport){
        airportNetwork.add(airport);
        this.airportCount++;
    }

    public void addRoute(String A1_IATACode, String A2_IATACode, Airline ar, int depTime){
        Airport a1 = this.findAirport(A1_IATACode);
        Airport a2 = this.findAirport(A2_IATACode);

        Flight flightTo = new Flight(a1, a2, ar, depTime); // between 0-1440 ex 60 = 1:00am
        a1.addRoute(flightTo);
        this.flightNetwork.add(flightTo);
        this.flightCount++;
    }

    private int indexOfAirport(String IATACode){
        for (int i = 0; i < this.airportNetwork.size(); i++){
            if (IATACode == airportNetwork.get(i).getIATACode()){
                return i;
            }
        }
        return -1;
    }

    public Airport findAirport(String IATACode){
        for (int i = 0; i < this.airportNetwork.size(); i++){
            if (IATACode.equals(airportNetwork.get(i).getIATACode())){
                return airportNetwork.get(i);
            }
        }
        return null;
    }
}
