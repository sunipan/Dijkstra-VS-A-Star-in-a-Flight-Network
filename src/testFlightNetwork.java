public class testFlightNetwork {

    public static void main(String[] args) {


		FlightNetworkGenerator networkGen = new FlightNetworkGenerator();
    FlightNetwork canNetwork = networkGen.createWorldGraph(true);

    //FlightNetwork.printNetworkAirports(start);

    Airport ar = canNetwork.findAirport("PER");

    ar.getOutgoingFlights().get(0).getDest().getOutgoingFlights().get(0).display();


      

	}
}
