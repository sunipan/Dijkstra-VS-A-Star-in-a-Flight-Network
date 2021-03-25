import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Airline {

    private String name;
    private String alias;
    private String code;
    private String callsign;
    private String country;

    public Airline(String name, String alias, String code, String callsign, String country) {
        this.name = name;
        this.alias = alias;
        this.code = code;
        this.callsign = callsign;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getCode() {
        return code;
    }

    public static ArrayList<Airline> getAirlines(){
        ArrayList<Airline> airlineList = new ArrayList<Airline>();

        try {
    			  Scanner sc = new Scanner(new File("network-data\\airline.data"));
            //Scanner sc = new Scanner(new File("C:\\Users\\vfrunza\\320-Project\\src\\network-data\\airline.data"));
            sc.useDelimiter(",");

            while (sc.hasNext())
            {
                String[] airlineData = sc.nextLine().split(",");

                if (!airlineData[7].equals("N")){
                    Airline airline = new Airline(airlineData[1], airlineData[2], airlineData[3], airlineData[5], airlineData[6]);
                    airlineList.add(airline);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return airlineList;
    }

}
