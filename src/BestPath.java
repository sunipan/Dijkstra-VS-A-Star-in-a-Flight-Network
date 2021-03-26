import java.util.*;

public class BestPath {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;

  public static void bestPath(ArrayList<Airport> graph, Airport src, Airport target) {

    PriorityQueue<Airport> pq = new PriorityQueue<Airport>(11, comparator);
    Set<Airport> checked = new HashSet<Airport>();
    src.setTripCost(0);
    pq.add(src);
    /*for (Airport current: graph) {
      if (current != src) {
        current.setTripCost(infinity);
        current.setPrevious(null);
        pq.add(current);
      }
    }*/
    Airport min;
    ArrayList<Flight> flightsFromMin;
    boolean found = false;
    int tempCost;
    while(pq.peek() != null) {
      min = pq.poll();
      flightsFromMin = min.getOutgoingFlights();
      for (Flight flight: flightsFromMin) {
        if (!checked.contains(flight.getDest())) {
          tempCost = min.getTripCost() + flight.getCost();
          if (tempCost < flight.getDest().getTripCost()) {
            flight.getDest().setTripCost(tempCost);
            //set previous of flights destination to min.
            flight.getDest().setPrevious(min);
            //System.out.println(flight.getDest().getIATACode());
            if (flight.getDest() == target) {
              found = true;
              break;
            }
            pq.add(flight.getDest());
          }
        }
      }
      checked.add(min);
    }
    if (!found) System.out.println("Destination was not found");

  }

  public static void main(String args[]) {
    if (args.length != 0) {
      if (args[0].equals("test")){

      }
    } else {
      FlightNetworkGenerator fng = new FlightNetworkGenerator();
      flightNetwork = fng.createCanadaGraph(false);

    }


    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the IATACode of the source airport: ");
    String srcCode = sc.nextLine();
    System.out.print("Enter the IATACode of the destination airport: ");
    String destCode = sc.nextLine();

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
    while (!current.getName().equals(source.getName())) {
      System.out.println(current.getIATACode());
      current = current.getPrevious();
    }
    System.out.println(source.getIATACode()+" - start");
  }

}
