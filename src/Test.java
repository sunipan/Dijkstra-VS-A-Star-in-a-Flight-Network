import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		
		// Number of airports in the flight network
		final int GRAPH_SIZE = 5;
		
		// Initialize the comparator to compare the cost of each flight
		Comparator<Airport> comparator = new CostComparator();
		
		// Initialize priority queue
		PriorityQueue<Airport> pq = new PriorityQueue<>(GRAPH_SIZE, comparator);
		
		// Start adding flights into the queue
		pq.add(new Airport(900)); 
		pq.add(new Airport(150));
		pq.add(new Airport(500));
		pq.add(new Airport(300));
		pq.add(new Airport(50));
		
		// NOTE: ADDING MORE FLIGHTS THAN GRAPH_SIZE WILL OVERWRITE OTHER FLIGHTS.
		
		for(int i = 0; i < GRAPH_SIZE; i++) {
			System.out.println(pq.poll().getTripCost());
		}
	}
}
