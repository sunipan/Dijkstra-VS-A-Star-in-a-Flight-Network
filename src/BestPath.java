import java.util.*;

public class BestPath {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;
  private static FlightNetwork flightNetwork2;

  public static void bestPath1(ArrayList<Airport> graph, Airport src, Airport target) {
	//FibonacciHeapPQ pq = new FibonacciHeapPQ();
    PriorityQueue<Airport> pq = new PriorityQueue<Airport>(11, comparator);
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
            //System.out.println(flight.getDest().getIATACode());

            pq.add(flight.getDest());
          }
        }
      }
      checked.add(min);
    }
    if (!found) 
    	System.out.println("Destination was not found");

  }

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
            //System.out.println(flight.getDest().getIATACode());
            /*if (flight.getDest() == target) {
              found = true;
              break;
            }*/
            pq.add(flight.getDest());
          }
        }
      }
      checked.add(min);
    }
    if (!found) 
    	System.out.println("Destination was not found");

  }

  public static void runDK_MH(String graph, String sourceCode, String destinationCode){
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
  }

  public static void runDK_FIB(String graph, String sourceCode, String destinationCode){
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
  }

  public static void resetFlightNetwork(){
    for(Airport a: flightNetwork.getAirportNetwork()) {
    	a.setTripCost(Integer.MAX_VALUE);
    }
  }

  public static void main(String args[]) {
    runDK_MH("World", "YZP", "SVX");
    for(Airport a: flightNetwork.getAirportNetwork()) {
    	a.setTripCost(Integer.MAX_VALUE);
    }

    System.out.println("========================================");

    FlightNetworkGenerator fng = new FlightNetworkGenerator();
      flightNetwork = fng.createWorldGraph(true);
 


    String srcCode = "YZP";
    String destCode = "SVX";

    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

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
    
    System.out.println("Resetting Flight Network");
    for(Airport a: flightNetwork.getAirportNetwork()) {
    	a.setTripCost(Integer.MAX_VALUE);
    }
    System.out.println("========================================");
    
    Airport source2 = flightNetwork.findAirport(srcCode);
    Airport destination2 = flightNetwork.findAirport(destCode);
    //USE FIBONACCI HEAP
    long time2 = 0;
    if (source2 != null && destination2 != null) {
      time2 = System.currentTimeMillis();
      bestPath2(flightNetwork.getAirportNetwork(), source2, destination2);
      time2 = System.currentTimeMillis() - time2;
    } else if (source2 == null) {
      System.out.println("source was null");
    } else if (destination2 == null) {
      System.out.println("destination was null");
    }
    Airport current2 = destination2;
    System.out.println("Finding this path took: "+time2+" ms.");
    System.out.println("Trip outline to get to "+destination2.getName()+" from "+source2.getName());
    System.out.println("This will cost: "+destination2.getTripCost());
    while (!current2.getName().equals(source2.getName())) {
      System.out.println(current2.getIATACode());
      current2 = current2.getPrevious();
    }
    System.out.println(source2.getIATACode()+" - start");

    
  }

}