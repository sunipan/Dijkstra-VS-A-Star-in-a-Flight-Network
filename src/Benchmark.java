public class Benchmark {
    public static void main(String args[]) {

        // System.out.println("================================================================");

        System.out.println("======= Dijkstra w Min Heap, small");
       BestPath.runDK_MH("Canada", "YZP", "YYZ");
        //BestPath.resetFlightNetwork();

        // System.out.println("======= Dijkstra w Fib Heap, small");
        // BestPath.runDK_FIB("Canada", "YZP", "YYZ");
        // BestPath.resetFlightNetwork();

        System.out.println("======= A* w Min Heap, small");
        AStar.runAS_MH("Canada", "YZP", "YYZ");
        // AStar.resetFlightNetwork();

        // System.out.println("======= A* w Fib Heap, small");
        // AStar.runAS_FIB("Canada", "YZP", "YYZ");
        // AStar.resetFlightNetwork();

        // System.out.println("================================================================");

        //System.out.println("======= Dijkstra w Min Heap, med");
        //BestPath.runDK_MH("USA", "DEN", "AUG");
        // BestPath.resetFlightNetwork();

        //System.out.println("======= Dijkstra w Fib Heap, med");
       // BestPath.runDK_FIB("USA", "DEN", "AUG");
        // BestPath.resetFlightNetwork();

        //System.out.println("======= A* w Min Heap, med");
        //AStar.runAS_MH("USA", "DEN", "AUG");
        // AStar.resetFlightNetwork();

        //System.out.println("======= A* w Fib Heap, med");
        //AStar.runAS_FIB("USA", "DEN", "AUG");
        // AStar.resetFlightNetwork();

        // System.out.println("================================================================");

        //System.out.println("======= Dijkstra w Min Heap, med dense");
        //BestPath.runDK_MH("Europe", "GLA", "VKO");
        //BestPath.resetFlightNetwork();

        //System.out.println("======= Dijkstra w Fib Heap, med dense");
        //BestPath.runDK_FIB("Europe", "GLA", "VKO");
        //BestPath.resetFlightNetwork();

        //System.out.println("======= A* w Min Heap, med dense");
        //AStar.runAS_MH("Europe", "GLA", "VKO");
        //AStar.resetFlightNetwork();

        //System.out.println("======= A* w Fib Heap, med dense");
        //AStar.runAS_FIB("Europe", "GLA", "VKO");
        //AStar.resetFlightNetwork();

        // System.out.println("================================================================");

         //System.out.println("======= Dijkstra w Min Heap, med");
        //BestPath.runDK_MH("NA", "CUN", "YZP");
        //BestPath.resetFlightNetwork();

        //System.out.println("======= Dijkstra w Fib Heap, med");
        //BestPath.runDK_FIB("NA", "CUN", "YZP");
        //BestPath.resetFlightNetwork();

        //System.out.println("======= A* w Min Heap, med");
        //AStar.runAS_MH("NA", "CUN", "YZP");
        //AStar.resetFlightNetwork();

        //System.out.println("======= A* w Fib Heap, med");
        //AStar.runAS_FIB("NA", "CUN", "YZP");
        //AStar.resetFlightNetwork();

        // System.out.println("================================================================");

        // System.out.println("======= Dijkstra w Min Heap, large");
        // BestPath.runDK_MH("World", "YZP", "SVX");
        // BestPath.resetFlightNetwork();

        //System.out.println("======= Dijkstra w Fib Heap, large");
        //BestPath.runDK_FIB("World", "YZP", "SVX");
       // BestPath.resetFlightNetwork();

        //System.out.println("======= A* w Min Heap, large");
        //AStar.runAS_MH("World", "YZP", "SVX");
        //AStar.resetFlightNetwork();

         //System.out.println("======= A* w Fib Heap, large");
        // AStar.runAS_FIB("World", "YZP", "SVX");
        //AStar.resetFlightNetwork();

        // System.out.println("================================================================");

    }
}
