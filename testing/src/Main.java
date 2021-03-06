
public class Main {
    public static void main(String[] args)  //static method
    {
        System.out.println("Static method");

//        int noOfSmallRegularSpaces = 9;
//        int noOfMediumRegularSpaces = 24;
//        int noOfLargeRegularSpaces = 10;
//        int noOfMediumHandicapSpaces = 5;

        Parkinglot parkingLot = new Parkinglot(9,
                24,
                10,
                0,
                5,
                0);

        // Parking
        System.out.println("Parking Medium Handicapped cars");
        for (int i = 0; i < 6; i++) {
            parkingLot.park(new Car(Vehicle.vehicleType.HANDICAPPED));
        }

        System.out.println();

        System.out.println("Parking Large regular trucks");
        for (int j = 0; j < 8; j++) {
            parkingLot.park(new Truck(Vehicle.vehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Medium Regular cars");
        for(int k = 0; k < 3; k++) {
            parkingLot.park(new Car(Vehicle.vehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Large Handicapped truck");
        parkingLot.park(new Truck(Vehicle.vehicleType.HANDICAPPED));

        System.out.println();

        System.out.println("Parking Large Regular truck");
        parkingLot.park(new Truck(Vehicle.vehicleType.REGULAR));

        System.out.println();

        System.out.println("Parking Large Handicapped truck");
        parkingLot.park(new Truck(Vehicle.vehicleType.HANDICAPPED));

        System.out.println();

        System.out.println("Parking Small Regular bike");
        for(int m = 0; m < 3; m++) {
            parkingLot.park(new Bike(Vehicle.vehicleType.REGULAR));
        }

        System.out.println();

        System.out.println("Parking Medium regular car");
        parkingLot.park(new Car(Vehicle.vehicleType.REGULAR));

        System.out.println();

        System.out.println("Is the parking lot full? " + parkingLot.getIsParkingLotFull());
        System.out.println();
        System.out.println("What spots are left?\n" + parkingLot.toStringSpotsLeft());


    }
}
