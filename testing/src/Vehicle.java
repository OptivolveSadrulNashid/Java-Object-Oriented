public interface Vehicle {
    enum vehicleSize{
        SMALL,
        MEDIUM,
        LARGE
    }

    enum vehicleType{
        REGULAR,
        HANDICAPPED
    }

    vehicleSize getSize();
    vehicleType getType();
}
