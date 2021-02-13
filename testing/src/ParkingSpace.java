public class ParkingSpace implements Space{

    private vehicleSize vehicleSize;
    private vehicleType type;
    private boolean isTaken;

    public ParkingSpace(Vehicle.vehicleSize vehicleSize, vehicleType type) {
        this.vehicleSize = vehicleSize;
        this.type = type;
    }

    @Override
    public boolean getOccupied() {
        return !this.isTaken;
    }

    @Override
    public void setOccupied(boolean isTaken) {
        this.isTaken = isTaken;
    }

    @Override
    public vehicleSize getSize() {
        return this.vehicleSize;
    }

    @Override
    public vehicleType getType() {
        return this.type;
    }
}
