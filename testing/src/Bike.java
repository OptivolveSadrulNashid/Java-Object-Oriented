public class Bike implements Vehicle{
    private final vehicleType type;

    public Bike(vehicleType type) {
        this.type = type;
    }

    @Override
    public vehicleSize getSize() {
        return vehicleSize.SMALL;
    }

    @Override
    public vehicleType getType() {
        return type;
    }
}
