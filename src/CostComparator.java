import java.util.Comparator;

/* This class allows us to have the vertices of the graph (Airports)
 * sorted by their costs in the priority queue.
 */
public class CostComparator implements Comparator<Airport> {
	
	public int compare(Airport x, Airport y) {
		return x.getTripCost() - y.getTripCost();
	}
}
