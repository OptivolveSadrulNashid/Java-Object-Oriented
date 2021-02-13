public class Truck implements Vehicle{

    private vehicleType type;

    public Truck(vehicleType type) {
        this.type = type;
    }

    @Override
    public vehicleSize getSize() {
        return vehicleSize.LARGE;
    }

    @Override
    public vehicleType getType() {
        return type;
    }
}
