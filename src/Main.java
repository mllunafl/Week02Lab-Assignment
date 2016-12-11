import javax.xml.validation.ValidatorHandler;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = null;
        VehicleInfo vehicleInfo = new VehicleInfo();
        TelematicsImpl telematicsImpl = new TelematicsImpl();
        System.out.println("before" + vehicleInfo);
        try {
            s = new Scanner(System.in);
            System.out.println("Input vehicle data: Input VIN");
            while (s.hasNext()) {
                //System.out.println("in while loop");
                if (vehicleInfo.getVin() == null){
                    vehicleInfo.setVin(s.next());
                    //System.out.println(vehicleInfo.getVin());
                }
                if (vehicleInfo.getVin() != null) {
                    System.out.println("Input Odometer");
                    vehicleInfo.setOdometer(s.nextDouble());
                    //System.out.println(vehicleInfo.getOdometer());
                }
                if (vehicleInfo.getOdometer() != 0) {
                    System.out.println("Input Gallons consumed");
                    vehicleInfo.setGallonConsumed(s.nextDouble());
                    //System.out.println(vehicleInfo.getGallonConsumed());
                }
                if (vehicleInfo.getGallonConsumed() != 0) {
                    System.out.println("Input Odometer since las oil change");
                    vehicleInfo.setOdometerSinceLastOilChange(s.nextDouble());
                    //System.out.println(vehicleInfo.getOdometerSinceLastOilChange());
                }
                if (vehicleInfo.getOdometerSinceLastOilChange() != 0) {
                    System.out.println("Input Engine size");
                    vehicleInfo.setEngineSize(s.nextDouble());
                    //System.out.println(vehicleInfo.getEngineSize());
                    System.out.println(vehicleInfo);
                    telematicsImpl.report(vehicleInfo);
                    vehicleInfo = new VehicleInfo();
                    System.out.println("Input vehicle data: Input VIN");
                }
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
