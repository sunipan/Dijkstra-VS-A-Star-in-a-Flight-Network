public class testFlightNetwork {

    public static void main(String[] args) {
		
		
		FlightNetworkGenerator networkGen = new FlightNetworkGenerator();

        FlightNetwork fn = networkGen.createCanadaGraph(true);

        //FlightNetwork.printNetworkAirports(start);
	}
}
