import java.util.*;

public class AStar {

  private static Comparator<Airport> comparator = new CostComparator();
  private static FlightNetwork flightNetwork;
  private static Flight tempFlight;

  public static int heuristic(Flight temp) {
    return (int)(temp.getTravelDistance() * 23.72);
  }

  public static void aStar_FIB(ArrayList<Airport> graph, Airport src, Airport target) {

    FibonacciHeapPQ pq = new FibonacciHeapPQ();
    Set<Airport> checked = new HashSet<Airport>();
    tempFlight = new Flight(src, target);
    src.setTripCost(0);
    src.setGuessCost(Flight.distanceToDestination(src, target));
    pq.addAStar(src);

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

            pq.addAStar(flight.getDest());
          }
        }
      }
      checked.add(min);
    }
    if (!found) System.out.println("Destination was not found");

  }

  public static void aStar_MH(ArrayList<Airport> graph, Airport src, Airport target) {
    PriorityQueue<Airport> pq = new PriorityQueue<Airport>(11, comparator);
    //FibonacciHeapPQ pq = new FibonacciHeapPQ();
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
            //tempFlight == flight from current destination to trip destination
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
      Airport current = destination;
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
    Airport current = destination;
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
    if (args.length != 0) {
      if (args[0].equals("test")){

      }
    } else {
      FlightNetworkGenerator fng = new FlightNetworkGenerator();
      flightNetwork = fng.createWorldGraph(false);

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
      aStar_MH(flightNetwork.getAirportNetwork(), source, destination);
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
