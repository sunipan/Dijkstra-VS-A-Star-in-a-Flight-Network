public class testFlightNetwork {

    public static void main(String[] args) {
		
		
		FlightNetworkGenerator networkGen = new FlightNetworkGenerator();
        FlightNetwork canNetwork = networkGen.createCanadaGraph(true);

        //FlightNetwork.printNetworkAirports(start);
	}
}
