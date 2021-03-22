import java.util.ArrayList;

public class FlightNetwork {
    
    private Airport start;
    private Airport end;

    private int airportCount;
    private int flightCount;

    private ArrayList<Airport> airportNetwork;
    private ArrayList<Flight> flightNetwork;

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
    }

    public void addRoute(String A1_IATACode, String A2_IATACode){
 
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
            if (IATACode == airportNetwork.get(i).getIATACode()){
                return airportNetwork.get(i);
            }
        }
        return null;
    }
}
