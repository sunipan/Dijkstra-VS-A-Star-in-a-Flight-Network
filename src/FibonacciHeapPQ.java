
public class FibonacciHeapPQ {

	// create Fibonacci heap to store our airport nodes in
	FibonacciHeap<Airport> heap = new FibonacciHeap<>();

	// add new airport node
	public void add(Airport item) {
		FibonacciHeap.Entry<Airport> entry = heap.enqueue(item, item.getTripCost());
		item.entry = entry;
	}

	public void addAStar(Airport item) {
		FibonacciHeap.Entry<Airport> entry = heap.enqueue(item, item.getGuessCost());
		item.entry = entry;
	}

	// used to change priority from infinity to real cost
	public void decreasePriority(Airport item, int priority) {
		item.setTripCost(priority);
		heap.decreaseKey(item.entry, priority);
	}

	// dequeues min airport node and returns it
	public Airport poll() {
		return heap.dequeueMin().getValue();
	}

	public int size() {
		return heap.size();
	}

	public Airport peek() {
		return heap.peek();
	}
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	//dequeues all nodes in order starting from airport node with lowest cost
	public void display() {
		int size = this.size();
		for(int i = 0; i < size; i++) {
			System.out.println(heap.dequeueMin().getPriority());
		}
	}
}
