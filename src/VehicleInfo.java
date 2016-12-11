/**
 * Created by LunaFlores on 12/9/16.
 */
public class VehicleInfo {

    private String  vin;
    private double odometer;
    private double gallonConsumed;
    private double odometerSinceLastOilChange;
    private double engineSize;

    public VehicleInfo() {
        super();
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getGallonConsumed() {
        return gallonConsumed;
    }

    public void setGallonConsumed(double gallonConsumed) {
        this.gallonConsumed = gallonConsumed;
    }

    public double getOdometerSinceLastOilChange() {
        return odometerSinceLastOilChange;
    }

    public void setOdometerSinceLastOilChange(double odometerSinceLastOilChange) {
        this.odometerSinceLastOilChange = odometerSinceLastOilChange;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    @Override
    public String toString() {
        return "VehicleInfo{" +
                "vin='" + vin + '\'' +
                ", odometer=" + odometer +
                ", gallonConsumed=" + gallonConsumed +
                ", odometerSinceLastOilChange=" + odometerSinceLastOilChange +
                ", engineSize=" + engineSize +
                '}';
    }
}
