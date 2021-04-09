import java.util.*;

public class BestPath {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;

  public static void bestPath1(ArrayList<Airport> graph, Airport src, Airport target) {
	//FibonacciHeapPQ pq = new FibonacciHeapPQ();
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


  //bestPath2 is the same algorithm as bestPath1, however it uses a fibonacci heap rather than a min-heap
  //in the priority queue.
  public static void bestPath2(ArrayList<Airport> graph, Airport src, Airport target) {
	  FibonacciHeapPQ pq = new FibonacciHeapPQ();
    //PriorityQueue<Airport> pq = new PriorityQueue<Airport>(11, comparator);
    Set<Airport> checked = new HashSet<Airport>();
    src.setTripCost(0);
    pq.add(src);
    Airport min;
    ArrayList<Flight> flightsFromMin;
    boolean found = false;
    int tempCost;
    while(pq.peek() != null) {
      min = pq.poll();
      if (min.getIATACode() == target.getIATACode()) {
        found = true;
        break;
      }
      flightsFromMin = min.getOutgoingFlights();
      for (Flight flight: flightsFromMin) {
        if (!checked.contains(flight.getDest())) {
          tempCost = min.getTripCost() + flight.getCost();
          if (tempCost < flight.getDest().getTripCost()) {
            flight.getDest().setTripCost(tempCost);
            //set previous of flights destination to min.
            flight.getDest().setPrevious(min);
            flight.getDest().setPath(flight);
            //System.out.println(flight.getDest().getIATACode());

            pq.add(flight.getDest());
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
    long time1 = 0;
    if (source != null && destination != null) {
      time1 = System.currentTimeMillis();
      bestPath1(flightNetwork.getAirportNetwork(), source, destination);
      time1 = System.currentTimeMillis() - time1;
    } else if (source == null) {
      System.out.println("source was null");
    } else if (destination == null) {
      System.out.println("destination was null");
    }
    Airport current1 = destination;
    System.out.println("Finding this path took: "+time1+" ms.");
    System.out.println("Trip outline to get to "+destination.getName()+" from "+source.getName());
    System.out.println("This will cost: "+destination.getTripCost());
    while (!current1.getName().equals(source.getName())) {
      System.out.println(current1.getIATACode());
      current1 = current1.getPrevious();
    }
    System.out.println(source.getIATACode()+" - start");


    System.out.println("========================================");

    //USE FIBONACCI HEAP
    long time2 = 0;
    if (source != null && destination != null) {
      time2 = System.currentTimeMillis();
      bestPath2(flightNetwork.getAirportNetwork(), source, destination);
      time2 = System.currentTimeMillis() - time2;
    } else if (source == null) {
      System.out.println("source was null");
    } else if (destination == null) {
      System.out.println("destination was null");
    }
    Airport current2 = destination;
    System.out.println("Finding this path took: "+time2+" ms.");
    System.out.println("Trip outline to get to "+destination.getName()+" from "+source.getName());
    System.out.println("This will cost: "+destination.getTripCost());
    while (!current2.getName().equals(source.getName())) {
      System.out.println(current2.getIATACode());
      current2 = current2.getPrevious();
    }
    System.out.println(source.getIATACode()+" - start");
  }

}
