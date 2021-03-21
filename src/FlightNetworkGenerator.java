import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;  

public class FlightNetworkGenerator {

    public FlightNetworkGenerator(){

    }

    public void createCanadaGraph(boolean printStatistics){
        Scanner sc;
        try {
            sc = new Scanner(new File("C:\\Users\\vfrunza\\320-Project\\src\\network-data\\airports.data"));

            sc.useDelimiter(","); 

            ArrayList<Airport> airportNetwork = new ArrayList<Airport>();

            while (sc.hasNext())  
            {  
                String[] ap = sc.nextLine().split(",");

                if (ap[3].equals("Canada") && !ap[4].equals("\\N")){
                    Flight[] routes = {};
                    Airport newAP = new Airport(ap[1], ap[2], ap[3], ap[4], Double.parseDouble(ap[6]), Double.parseDouble(ap[7]), routes);
                    airportNetwork.add(newAP);
                }
            }   

            if (printStatistics) {
                System.out.println("Network Constructed");
                System.out.println("Size of network: " + airportNetwork.size());
                System.out.println("================================================");
                System.out.println("Sample Airport: ");
                airportNetwork.get(25).display();
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  
    }
}
