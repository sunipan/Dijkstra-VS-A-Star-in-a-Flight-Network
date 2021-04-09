import java.util.*;

public class AStar {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;
  private static Flight tempFlight;

  public static int heuristic(Flight temp) {
    return (int)(temp.getTravelDistance() * 23.72);
  }

  public static void aStar(ArrayList<Airport> graph, Airport src, Airport target) {
    //Initialize a priority queue as the visited unchecked nodes list
    FibonacciHeapPQ pq = new FibonacciHeapPQ();
    //initialize a hashset as the checked nodes list
    Set<Airport> checked = new HashSet<Airport>();
    tempFlight = new Flight(src, target);
    //Initialize source node to 0 cost and add to priority queue
    src.setTripCost(0);
    src.setGuessCost(heuristic(tempFlight));
    pq.addAStar(src);

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
          if (tempCost < flight.getDest().getTripCost()) {
            tempFlight.setSource(flight.getDest());
            //tempFlight == current destination to trip destination
            //add heuristic cost to tempCost to set guess cost (heuristic cost)
            flight.getDest().setTripCost(tempCost);
            flight.getDest().setGuessCost(tempCost + heuristic(tempFlight));
            //set previous of flights destination to min.
            flight.getDest().setPrevious(min);
            flight.getDest().setPath(flight);

            pq.addAStar(flight.getDest());
          }
        }
      }
      checked.add(min);
    }
    if (!found) System.out.println("Destination was not found");

  }

  public static void main(String args[]) {
    FlightNetworkGenerator fng = new FlightNetworkGenerator();
    System.out.println("Input 0 for graph of world, 1 for graph of Canada (any input other than 0 will use the graph of Canada)");
    System.out.print("Input here: ");
    int bool;
    Scanner sc = new Scanner(System.in);
    bool = sc.nextInt();
    if (bool == 0) {
      flightNetwork = fng.createWorldGraph(false);
    } else {
      flightNetwork = fng.createCanadaGraph(false);
    }

    sc.nextLine();
    System.out.print("Enter the IATACode of the source airport: ");
    String srcCode = sc.nextLine();
    System.out.print("Enter the IATACode of the destination airport: ");
    String destCode = sc.nextLine();
    sc.close();
    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

    //made bestPath not return anything, if Airport has a previous
    //it can be used like a backwards linked list from destination
    long time = 0;
    if (source != null && destination != null) {
      time = System.currentTimeMillis();
      aStar(flightNetwork.getAirportNetwork(), source, destination);
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
