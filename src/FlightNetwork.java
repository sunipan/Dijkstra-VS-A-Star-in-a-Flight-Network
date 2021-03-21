import java.util.ArrayList;

public class FlightNetwork {
    
    private Airport start;
    private Airport end;

    private ArrayList<Airport> airportNetwork;

    public FlightNetwork(ArrayList<Airport> apNetwork) {
        this.airportNetwork = apNetwork;
    }

    public void addAirport(Airport airport){
        airportNetwork.add(airport);
    }

    public void addRoute(String A1_IATACode, String A2_IATACode){
        Airport a1 = airportNetwork.get(indexOfAirport(A1_IATACode));
        Airport a2 = airportNetwork.get(indexOfAirport(A2_IATACode));
 
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
