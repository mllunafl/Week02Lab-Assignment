import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

/**
 * Created by LunaFlores on 12/9/16.
 */
public class TelematicsImpl implements Telematics {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void report(VehicleInfo vehicleInfo) throws IOException {
        String json = mapper.writeValueAsString(vehicleInfo);
        System.out.println(vehicleInfo);
        System.out.println("json" + json);
        try (PrintWriter out = new PrintWriter(new FileWriter(vehicleInfo.getVin() + ".json"))) {
            out.println(json);
            out.flush();
        }

        int count = 0;
        double odometerTotal = 0;
        double gallonsConsumedTotal = 0;
        double odometerSinceLastOilChangeTotal = 0;
        double engineSizeTotal = 0;

        File file = new File(".");
        //everytime we go through loop file is assigned to f
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                ++count;
                // Now you have a File object named "f". You can use this in the FileReader constructor
                // new FileReader(f)
                System.out.println(f);
                try (BufferedReader in = new BufferedReader(new FileReader(f.getName()))) {
                    //reading the one line and assign to string stringvehicleinfo
                    String stringVehicleinfo = in.readLine();
                    //desearlizing json string back into vehicleinfo class
                    VehicleInfo vehicleInfo2 = mapper.readValue(stringVehicleinfo, VehicleInfo.class);
                    //totalling for each field add to total
                    odometerTotal += vehicleInfo2.getOdometer();
                    gallonsConsumedTotal += vehicleInfo2.getGallonConsumed();
                    odometerSinceLastOilChangeTotal += vehicleInfo2.getOdometerSinceLastOilChange();
                    engineSizeTotal += vehicleInfo2.getEngineSize();
                    System.out.println("after" + vehicleInfo2);
                }

            }
        }
        System.out.println(count);
        //compute average divide by our count
        System.out.println(odometerTotal);
        double odometerAverage = odometerTotal / count;
        System.out.println(odometerAverage);

        System.out.println(gallonsConsumedTotal);
        double gallonsConsumedAverage = gallonsConsumedTotal / count;
        System.out.println(gallonsConsumedAverage);

        System.out.println(odometerSinceLastOilChangeTotal);
        double odometerSinceLastOilChangeAverage = odometerSinceLastOilChangeTotal / count;
        System.out.println(odometerSinceLastOilChangeAverage);

        System.out.println(engineSizeTotal);
        double engineSizeAverage = engineSizeTotal / count;
        System.out.println(engineSizeAverage);

        //replace NUM-CARS% with count

        //replace symbols with averages
        String tmp = HTML.replace("%NUM-CARS%", Integer.toString(count));
        tmp = tmp.replace("%ODOMETER%", Double.toString(odometerAverage));
        tmp = tmp.replace("%GALLONS-CONSUMED%", Double.toString(gallonsConsumedAverage));
        tmp = tmp.replace("%ODOMETER-OILCHANGE%", Double.toString(odometerSinceLastOilChangeAverage));
        tmp = tmp.replace("%ENGINE-SIZE%", Double.toString(engineSizeAverage));
        System.out.println(tmp);
        //build table for each report
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                ++count;
                // Now you have a File object named "f". You can use this in the FileReader constructor
                // new FileReader(f)
                System.out.println(f);
                try (BufferedReader in = new BufferedReader(new FileReader(f.getName()))) {
                    String stringVehicleinfo = in.readLine();
                    VehicleInfo vehicleInfo2 = mapper.readValue(stringVehicleinfo, VehicleInfo.class);

                    String tmp2 = HTMLROW.replace("%VIN%", vehicleInfo2.getVin());
                    tmp2 = tmp2.replace("%ODOMETER%", Double.toString(vehicleInfo2.getOdometer()));
                    tmp2 = tmp2.replace("%GALLONS%", Double.toString(vehicleInfo2.getGallonConsumed()));
                    tmp2 = tmp2.replace("%OILCHANGE%", Double.toString(vehicleInfo2.getOdometerSinceLastOilChange()));
                    tmp2 = tmp2.replace("%ENGINESIZE%", Double.toString(vehicleInfo2.getEngineSize()));
                    tmp = tmp + tmp2;
                }

            }
        }
        //concatinate
        String tmp3 = HTMLBOTTOM;
        tmp = tmp + tmp3;
        //create new file dashboard
        try (PrintWriter out = new PrintWriter(new FileWriter("dashboard.html"))) {
            out.println(tmp);
            out.flush();
            System.out.println("dashboard" + tmp);
        }


    }

    private static final String HTML = "<html>\n" +
            "  <title>Vehicle Telematics Dashboard</title>\n" +
            "  <body>\n" +
            "    <h1 align=\"center\">Averages for %NUM-CARS% vehicles</h1>\n" +
            "    <table align=\"center\">\n" +
            "        <tr>\n" +
            "            <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td align=\"center\">%ODOMETER%</td><td align=\"center\">%GALLONS-CONSUMED%</td><td align=\"center\">%ODOMETER-OILCHANGE%</td align=\"center\"><td align=\"center\">%ENGINE-SIZE%</td>\n" +
            "        </tr>\n" +
            "    </table>\n" +
            "    <h1 align=\"center\">History</h1>\n" +
            "    <table align=\"center\" border=\"1\">\n" +
            "        <tr>\n" +
            "            <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th>\n" +
            "        </tr>\n";

    private static final String HTMLROW =
            "        <tr>\n" +
            "            <td align=\"center\">%VIN%</td><td align=\"center\">%ODOMETER%</td><td align=\"center\">%GALLONS%</td><td align=\"center\">%OILCHANGE%</td align=\"center\"><td align=\"center\">%ENGINESIZE%</td>\n" +
            "        </tr>\n";

    private static final String HTMLBOTTOM =

            "    </table>\n" +
            "  </body>\n" +
            "</html>\n";

}


