import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;  

public class FlightNetworkGenerator {

    public FlightNetworkGenerator(){

    }

    public void createCanadaGraph(boolean printStatistics){
        int routeCounter = 0;

        ArrayList<String[]> airportList = Airport.getAirports();
        ArrayList<String[]> flightList = Flight.getFlights();
        ArrayList<Airline> airlineList = Airline.getAirlines();

        ArrayList<Airport> airportNetwork = new ArrayList<Airport>();

        for (String[] ap : airportList){
            if (ap[3].equals("Canada") && !ap[4].equals("\\N")){
                Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), Double.parseDouble(ap[7]));
                airportNetwork.add(newAP);
            }
        } 

        for (String[] fl : flightList){
            String sourceAirportCode = fl[2];
            String destinationAirportCode = fl[4];
            String airline = fl[0];

            if (airportExists(airportNetwork, sourceAirportCode) && airportExists(airportNetwork, destinationAirportCode)){
                Airport s_ap = findAirport(airportNetwork, sourceAirportCode);
                Airport d_ap = findAirport(airportNetwork, destinationAirportCode);
                Airline ar = findAirline(airlineList, airline);

                if (ar == null){
                    System.out.println(airline);
                }
                
                Flight flight = new Flight(s_ap, d_ap, ar);

                s_ap.addRoute(flight);
                routeCounter++;
            }  
        }
    
        if (printStatistics) {
            System.out.println("Network Constructed");
            System.out.println("Number of airports: " + airportNetwork.size());
            System.out.println("Number of routes: " + routeCounter);
            System.out.println("================================================");
            System.out.println("Sample Airport: ");
            airportNetwork.get(25).display();
        }
    }

    private boolean airportExists(ArrayList<Airport> airportNetwork, String IATACode){
        for (Airport ap : airportNetwork){
            if (ap.getIATACode().equals(IATACode)){
                return true;
            }
        }
        return false;
    }

    private Airport findAirport(ArrayList<Airport> airportNetwork, String IATACode){
        for (Airport ap : airportNetwork){
            if (ap.getIATACode().equals(IATACode)){
                return ap;
            }
        }
        return null;
    }

    private Airline findAirline(ArrayList<Airline> airlineList, String IATACode){
        for (Airline ar : airlineList){
            if (ar.getCode().equals(IATACode) || ar.getCode().equals(IATACode)){
                return ar;
            }
        }
        return null;
    }
}
