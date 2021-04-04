
public class testFibonacciHeap {
	
	public static void main(String[] args) {
		
		//create new priority queue
		FibonacciHeapPQ pq = new FibonacciHeapPQ();
		Airport a1 = new Airport(890);
		//add airport nodes
		pq.add(new Airport(700));
		pq.add(new Airport(50));
		pq.add(new Airport(500));
		pq.add(new Airport(150));
		pq.add(new Airport(300));
		pq.add(new Airport(200));
		pq.add(a1);
		// used when setting airport nodes new cost based on flight cost
		pq.decreasePriority(a1, 750);
		// dequeues all nodes and prints their values in ascending order
		pq.display();
	}
}
