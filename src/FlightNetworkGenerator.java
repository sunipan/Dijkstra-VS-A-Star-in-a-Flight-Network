import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FlightNetworkGenerator {

    public static FlightNetwork createCanadaGraph(boolean printStatistics){

        ArrayList<String[]> airportList = Airport.getAirports();
        ArrayList<String[]> flightList = Flight.getFlights();
        ArrayList<Airline> airlineList = Airline.getAirlines();

        ArrayList<Airport> airportNetwork = new ArrayList<Airport>();
        ArrayList<Flight> flightNetwork = new ArrayList<Flight>();

        for (String[] ap : airportList){
            if (ap[3].equals("Canada") && !ap[4].equals("\\N")){
                Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), -Double.parseDouble(ap[7]));
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

                Flight flightTo = new Flight(s_ap, d_ap, ar, getMorningTime());
                flightNetwork.add(flightTo);
                s_ap.addRoute(flightTo);

                flightTo = new Flight(s_ap, d_ap, ar, getEveningTime());
                flightNetwork.add(flightTo);
                s_ap.addRoute(flightTo);
            }
        }

        //remove airports without routes
        for (int i = 0; i < airportNetwork.size(); i++){
            if (airportNetwork.get(i).getOutgoingFlights().size() == 0){
                airportNetwork.remove(i);
            }
        }

        FlightNetwork fn = new FlightNetwork(airportNetwork, flightNetwork);

        if (printStatistics) {
            System.out.println("Network Constructed");
            System.out.println("Number of airports: " + airportNetwork.size());
            System.out.println("Number of routes: " + flightNetwork.size());
            System.out.println("================================================");
            System.out.println("Sample Airport: ");

            airportNetwork.get(32).display();
            airportNetwork.get(18).display();

        }

        return fn;
    }

    public static FlightNetwork createNorthAmericaGraph(boolean printStatistics){
        ArrayList<String[]> airportList = Airport.getAirports();
        ArrayList<String[]> flightList = Flight.getFlights();
        ArrayList<Airline> airlineList = Airline.getAirlines();

        ArrayList<Airport> airportNetwork = new ArrayList<Airport>();
        ArrayList<Flight> flightNetwork = new ArrayList<Flight>();

        for (String[] ap : airportList){
            if ((ap[3].equals("Canada") || ap[3].equals("United States"))&& !ap[4].equals("\\N")){
                Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), -Double.parseDouble(ap[7]));
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

                if (ar != null){
                    Flight flightTo = new Flight(s_ap, d_ap, ar, getMorningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);

                    flightTo = new Flight(s_ap, d_ap, ar, getEveningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);
                }
            }
        }

        //remove airports without routes
        for (int i = 0; i < airportNetwork.size(); i++){
            if (airportNetwork.get(i).getOutgoingFlights().size() == 0){
                airportNetwork.remove(i);
            }
        }

        FlightNetwork fn = new FlightNetwork(airportNetwork, flightNetwork);

        if (printStatistics) {
            System.out.println("Network Constructed");
            System.out.println("Number of airports: " + airportNetwork.size());
            System.out.println("Number of routes: " + flightNetwork.size());
            System.out.println("================================================");
            System.out.println("Sample Airport: ");

            airportNetwork.get(32).display();
            airportNetwork.get(18).display();

        }

        return fn;
    }

    public static FlightNetwork createIcelandGraph(boolean printStatistics){
        ArrayList<String[]> airportList = Airport.getAirports();
        ArrayList<String[]> flightList = Flight.getFlights();
        ArrayList<Airline> airlineList = Airline.getAirlines();

        ArrayList<Airport> airportNetwork = new ArrayList<Airport>();
        ArrayList<Flight> flightNetwork = new ArrayList<Flight>();

        for (String[] ap : airportList){
            if ((ap[3].equals("ICeland")) && !ap[4].equals("\\N")){
                Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), -Double.parseDouble(ap[7]));
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

                if (ar != null){
                    Flight flightTo = new Flight(s_ap, d_ap, ar, getMorningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);

                    flightTo = new Flight(s_ap, d_ap, ar, getEveningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);
                }
            }
        }

        //remove airports without routes
        for (int i = 0; i < airportNetwork.size(); i++){
            if (airportNetwork.get(i).getOutgoingFlights().size() == 0){
                airportNetwork.remove(i);
            }
        }

        FlightNetwork fn = new FlightNetwork(airportNetwork, flightNetwork);

        if (printStatistics) {
            System.out.println("Network Constructed");
            System.out.println("Number of airports: " + airportNetwork.size());
            System.out.println("Number of routes: " + flightNetwork.size());
            System.out.println("================================================");
            System.out.println("Sample Airport: ");

            airportNetwork.get(32).display();
            airportNetwork.get(18).display();

        }

        return fn;
    }

    public static FlightNetwork createWorldGraph(boolean printStatistics){
        ArrayList<String[]> airportList = Airport.getAirports();
        ArrayList<String[]> flightList = Flight.getFlights();
        ArrayList<Airline> airlineList = Airline.getAirlines();

        ArrayList<Airport> airportNetwork = new ArrayList<Airport>();
        ArrayList<Flight> flightNetwork = new ArrayList<Flight>();

        for (String[] ap : airportList){
            if (!ap[4].equals("\\N")){
                try{
                    Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), -Double.parseDouble(ap[7]));
                    airportNetwork.add(newAP);
                }
                catch(Exception e){
                    // Ignore airports with missing data
                    // System.out.println(e.getMessage());
                }
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

                if (ar != null){
                    Flight flightTo = new Flight(s_ap, d_ap, ar, getMorningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);

                    flightTo = new Flight(s_ap, d_ap, ar, getEveningTime());
                    flightNetwork.add(flightTo);
                    s_ap.addRoute(flightTo);
                }
            }
        }

        //remove airports without routes
        for (int i = 0; i < airportNetwork.size(); i++){
            if (airportNetwork.get(i).getOutgoingFlights().size() == 0){
                airportNetwork.remove(i);
            }
        }

        FlightNetwork fn = new FlightNetwork(airportNetwork, flightNetwork);

        if (printStatistics) {
            System.out.println("Network Constructed");
            System.out.println("Number of airports: " + airportNetwork.size());
            System.out.println("Number of routes: " + flightNetwork.size());
            System.out.println("================================================");
            System.out.println("Sample Airport: ");

            airportNetwork.get(32).display();
            airportNetwork.get(18).display();

        }

        return fn;
    }

    private static int getMorningTime(){
        return (int)(420 + (Math.random() * 360));
    }

    private static int getEveningTime(){
        return (int)(780 + (Math.random() * 540));
    }

    private static boolean airportExists(ArrayList<Airport> airportNetwork, String IATACode){
        for (Airport ap : airportNetwork){
            if (ap.getIATACode().equals(IATACode)){
                return true;
            }
        }
        return false;
    }

    private static Airport findAirport(ArrayList<Airport> airportNetwork, String IATACode){
        for (Airport ap : airportNetwork){
            if (ap.getIATACode().equals(IATACode)){
                return ap;
            }
        }
        return null;
    }

    private static Airline findAirline(ArrayList<Airline> airlineList, String IATACode){
        for (Airline ar : airlineList){
            if (ar.getCode().equals(IATACode) || ar.getCode().equals(IATACode)){
                return ar;
            }
        }
        return null;
    }
}
