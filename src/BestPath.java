import java.util.*;

public class BestPath {

  //to be changed when graph is added
  //FlightNetwork flightNetwork = new FlightNetwork(...);
  private static  int infinity = Integer.MAX_VALUE;
  private static ArrayList<Airport> bestRoute = new ArrayList<Airport>();
  private static Comparator<Airport> comparator = new CostComparator();
  private static PriorityQueue<Airport> pq;


  public static void bestPath(ArrayList<Airport> graph, Airport src, Airport target) {
    pq = new PriorityQueue<>(graph.size(), comparator);
    src.setTripCost(0);
    pq.add(src);
    for (Airport current: graph) {
      current.setTripCost(infinity);
      //previous attribute in airport?
      if (current != src) {
        pq.add(current);
      }
    }
    Airport min;
    ArrayList<Flight> flightsFromMin;
    while(pq.peek() != null) {
      min = pq.poll();
      flightsFromMin = min.getOutgoingFlights();
      int tempCost;
      for (Flight flight: flightsFromMin) {
        tempCost = min.getTripCost() + flight.getCost();
        if (tempCost < flight.getDest().getTripCost()) {
          flight.getDest().setTripCost(tempCost);
          //set previous of flights destination to min.
          flight.getDest().setPrevious(min);
        }
        if (flight.getDest() == target) break;
      }
    }

  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the IATACode of the source airport: ");
    String srcCode = sc.nextLine();
    System.out.print("Enter the IATACode of the destination airport: ");
    String destCode = sc.nextLine();
    FlightNetworkGenerator fng = new FlightNetworkGenerator();
    FlightNetwork flightNetwork = fng.createCanadaGraph(true);


    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

    //made bestPath not return anything, if Airport has a previous
    //it can be used like a backwards linked list from destination
    bestPath(flightNetwork.getAirportNetwork(), source, destination);
    Airport current = destination;
    System.out.print("Trip outline, to get to destination: ");
    while (current.getPrevious() != null) {
      System.out.println(current.getIATACode());
      current = current.getPrevious();
    }
  }

}
