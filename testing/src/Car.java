public class Car implements Vehicle{
    private final vehicleType type;

    public Car(vehicleType type) {
        this.type = type;
    }

    @Override
    public vehicleSize getSize() {
        return vehicleSize.MEDIUM;
    }

    @Override
    public vehicleType getType() {
        return type;
    }
}
