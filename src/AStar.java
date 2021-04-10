import java.util.*;

public class AStar {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;
  private static Flight tempFlight;

  public static int heuristic(Flight temp) {
    return (int)(temp.getTravelDistance() * 23.72);
  }

  public static void aStar_FIB(ArrayList<Airport> graph, Airport src, Airport target) {
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
            flight.getDest().setGuessCost(tempCost + Flight.distanceToDestination(flight.getDest(), target));
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

  // A* with min heap
  public static void aStar_MH(ArrayList<Airport> graph, Airport src, Airport target) {

	    PriorityQueue<Airport> pq = new PriorityQueue<Airport>(comparator);
	    Set<Airport> checked = new HashSet<Airport>();
	    tempFlight = new Flight(src, target);
	    src.setTripCost(0);
	    src.setGuessCost(Flight.distanceToDestination(src, target));
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
	            tempFlight.setSource(flight.getDest());
	            //tempFlight == current destination to trip destination
	            //add heuristic cost to tempCost to set guess cost (heuristic cost)
	            flight.getDest().setTripCost(tempCost);
	            flight.getDest().setGuessCost(tempCost + Flight.distanceToDestination(flight.getDest(), target));
	            //set previous of flights destination to min.
	            flight.getDest().setPrevious(min);
	            flight.getDest().setPath(flight);

	            pq.add(flight.getDest());
	          }
	        }
	      }
	      checked.add(min);
	    }
	    if (!found) System.out.println("Destination was not found");

	  }

  public static void runAS_MH(String graph, String sourceCode, String destinationCode){
      FlightNetworkGenerator fng = new FlightNetworkGenerator();

      switch (graph){
        case "Canada":
        flightNetwork = fng.createCanadaGraph(true);
        break;
        case "USA":
        flightNetwork = fng.createUSGraph(true);
        break;
        case "Europe":
        flightNetwork = fng.createEuropeGraph(true);
        break;
        case "NA":
        flightNetwork = fng.createNorthAmericaGraph(true);
        break;
        case "World":
        flightNetwork = fng.createWorldGraph(true);
        break;
        default:
        flightNetwork = fng.createWorldGraph(true);
        break;

      }

      String srcCode = sourceCode;
      String destCode = destinationCode;

      Airport source = flightNetwork.findAirport(srcCode);
      Airport destination = flightNetwork.findAirport(destCode);

      //made bestPath not return anything, if Airport has a previous
      //it can be used like a backwards linked list from destination
      long time = 0;
      if (source != null && destination != null) {
        time = System.currentTimeMillis();
        aStar_MH(flightNetwork.getAirportNetwork(), source, destination);
        time = System.currentTimeMillis() - time;
      } else if (source == null) {
        System.out.println("source was null");
      } else if (destination == null) {
        System.out.println("destination was null");
      }

      System.out.println("Finding this path took: "+time+" ms.");
      System.out.println("Trip outline to get to "+destination.getName()+" from "+source.getName());
      System.out.println("This will cost: "+destination.getTripCost());

  }

  public static void runAS_FIB(String graph, String sourceCode, String destinationCode){
	    FlightNetworkGenerator fng = new FlightNetworkGenerator();

	    switch (graph){
	      case "Canada":
	      flightNetwork = fng.createCanadaGraph(true);
	      break;
	      case "USA":
	      flightNetwork = fng.createUSGraph(true);
	      break;
	      case "Europe":
	      flightNetwork = fng.createEuropeGraph(true);
	      break;
	      case "NA":
	      flightNetwork = fng.createNorthAmericaGraph(true);
	      break;
	      case "World":
	      flightNetwork = fng.createWorldGraph(true);
	      break;
	      default:
	      flightNetwork = fng.createWorldGraph(true);
	      break;

	    }


	    String srcCode = sourceCode;
	    String destCode = destinationCode;

	    Airport source = flightNetwork.findAirport(srcCode);
	    Airport destination = flightNetwork.findAirport(destCode);

	    //made bestPath not return anything, if Airport has a previous
	    //it can be used like a backwards linked list from destination
	    long time = 0;
	    if (source != null && destination != null) {
	      time = System.currentTimeMillis();
	      aStar_FIB(flightNetwork.getAirportNetwork(), source, destination);
	      time = System.currentTimeMillis() - time;
	    } else if (source == null) {
	      System.out.println("source was null");
	    } else if (destination == null) {
	      System.out.println("destination was null");
	    }

	    System.out.println("Finding this path took: "+time+" ms.");
	    System.out.println("Trip outline to get to "+destination.getName()+" from "+source.getName());
	    System.out.println("This will cost: "+destination.getTripCost());

	}

  public static void resetFlightNetwork(){
	  System.out.println("Resetting Flight Network");
	  for(Airport a: flightNetwork.getAirportNetwork()) {
	    a.setTripCost(Integer.MAX_VALUE);
	  }
	}

  public static void main(String args[]) {
	//create flightNetwork
    FlightNetworkGenerator fng = new FlightNetworkGenerator();
    flightNetwork = fng.createWorldGraph(false);


    //take user input
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter the IATACode of the source airport: ");
    String srcCode = sc.nextLine();
    System.out.print("Enter the IATACode of the destination airport: ");
    String destCode = sc.nextLine();
    //get required start and target nodes
    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

    System.out.println("Would you like to use a min-heap or a fibonacci heap?");
    System.out.print("input 0 for min-heap, 1 for fibonacci: ");
    int bool = sc.nextInt();
    //made bestPath not return anything, if Airport has a previous
    //it can be used like a backwards linked list from destination
    if (bool == 1) {
      System.out.println("A* using a Fibonacci heap:");
      long time = 0;
      if (source != null && destination != null) {
        time = System.currentTimeMillis();
        aStar_FIB(flightNetwork.getAirportNetwork(), source, destination);
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


      /*System.out.println("Resetting Flight Network");
      for(Airport a: flightNetwork.getAirportNetwork()) {
      	a.setTripCost(Integer.MAX_VALUE);
        a.setGuessCost(Integer.MAX_VALUE);
        a.setPrevious(null);
      }*/
    } else {
      System.out.println("===============================================\nAStar using a min-heap:");

      //made bestPath not return anything, if Airport has a previous
      //it can be used like a backwards linked list from destination
      long time1 = 0;
      if (source != null && destination != null) {
        time1 = System.currentTimeMillis();
        aStar_MH(flightNetwork.getAirportNetwork(), source, destination);
        time1 = System.currentTimeMillis() - time1;
      } else if (destination == null) {
        System.out.println("destination was null");
      } else {
      	System.out.println("source was null");
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

    }
  }

}
