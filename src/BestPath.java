import java.util.*;

public class BestPath {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;

  public static void bestPath(ArrayList<Airport> graph, Airport src, Airport target) {
    //Initialize a priority queue as the visited unchecked nodes list
    PriorityQueue<Airport> pq = new PriorityQueue<Airport>(11, comparator);
    //initialize a hashset as the checked nodes list
    Set<Airport> checked = new HashSet<Airport>();
    // Initialize source node to 0 cost and add to priority queue
    src.setTripCost(0);
    pq.add(src);
    Airport min;
    ArrayList<Flight> flightsFromMin;
    boolean found = false;
    int tempCost;
    //while the visited nodes list is not empty
    while(pq.peek() != null) {
      //poll the lowest cost node
      min = pq.poll();
      if (min.getIATACode() == target.getIATACode()) { //if min is destination airport
        found = true;
        break;
      }
      flightsFromMin = min.getOutgoingFlights(); //flights from min = all neighbours of min
      for (Flight flight: flightsFromMin) { //check all neighbours of min
        if (!checked.contains(flight.getDest())) { //if the neighbour has not already been checked
          tempCost = min.getTripCost() + flight.getCost();
          if (tempCost < flight.getDest().getTripCost()) { //if this path is cheaper than any previous path to neighbour
            flight.getDest().setTripCost(tempCost);
            //set previous of flights destination to min.
            flight.getDest().setPrevious(min);
            flight.getDest().setPath(flight);
            pq.add(flight.getDest()); //add destination to the visited list
          }
        }
      }
      checked.add(min);
    }
    if (!found) System.out.println("Destination was not found");

  }


  public static void main(String args[]) {
    //Generate world graph
    FlightNetworkGenerator fng = new FlightNetworkGenerator();
    flightNetwork = fng.createWorldGraph(false);

    

    //take in user's input
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the IATACode of the source airport: ");
    String srcCode = sc.nextLine();
    System.out.print("Enter the IATACode of the destination airport: ");
    String destCode = sc.nextLine();
    
    //find required nodes in the flightNetwork
    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

    //made bestPath not return anything, if Airport has a previous
    //it can be used like a backwards linked list from destination
    long time = 0;
    if (source != null && destination != null) {
      time = System.currentTimeMillis();
      bestPath(flightNetwork.getAirportNetwork(), source, destination);
      time = System.currentTimeMillis() - time;
    } else if (source == null) {
      System.out.println("source was null");
    } else if (destination == null) {
      System.out.println("destination was null");
    }
    Airport current = destination;
    System.out.println("Finding this path took: "+time+" ms.");
    System.out.println("Trip outline to get to "+destination.getName()+" from "+source.getName());
    System.out.println("This will cost: "+destination.getTripCost());
    //print out the route taken
    while (!current.getName().equals(source.getName())) {
      System.out.println(current.getIATACode());
      current = current.getPrevious();
    }
    System.out.println(source.getIATACode()+" - start");
  }

}
