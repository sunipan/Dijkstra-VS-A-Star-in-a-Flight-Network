
public class BestPath {

  //to be changed when graph is added
  //FlightNetwork flightNetwork = new FlightNetwork(...);
  public int infinity = Integer.MAX_VALUE;
  public ArrayList<Airport> bestRoute = new ArrayList<Airport>();
  public Comparator<Airport> comparator = new CostComparator();
  public PriorityQueue<Airport> pq = new PriorityQueue<>(numAirports, comparator);


  public void bestPath(/*graph*/ Airport src, Airport target) {
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
    Flight[] flightsFromMin;
    while(pq.peek() != null) {
      min = pq.pop();
      flightsFromMin = min.getOutgoingFlights();
      int tempCost;
      for (Flight flight: flightsFromMin) {
        tempCost = min.getTripCost() + flight.getCost();
        if (tempCost < flight.getDest().getTripCost()) {
          flight.getDest().setTripCost(tempCost);
          //set previous of flights destination to min.
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

    Airport source = flightNetwork.findAirport(srcCode);
    Airport destination = flightNetwork.findAirport(destCode);

    //made bestPath not return anything, if Airport has a previous
    //it can be used like a backwards linked list from destination
    bestPath(flightNetwork, source, destination);
    
  }

}
