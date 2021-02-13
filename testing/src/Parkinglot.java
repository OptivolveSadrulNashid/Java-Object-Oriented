import java.util.ArrayList;
import java.util.HashMap;

public class Parkinglot {

    private final HashMap<Vehicle.vehicleSize, HashMap<Vehicle.vehicleType,
            ArrayList<ParkingSpace>>> parkingAvailableBySize = new HashMap<>();

    // HASHMAP STRUCTURE
    // (SMALL, (R, H))
    // (MEDIUM, (R, H))
    // (LARGE, (R, H))

    final private Vehicle.vehicleType[] vehicleTypes = Vehicle.vehicleType.values();
    final private Vehicle.vehicleSize[] vehicleSizes = Vehicle.vehicleSize.values();

    public Parkinglot(int numberOfSmallRegularSpaces,
                      int numberOfMediumRegularSpaces,
                      int numberOfLargeRegularSpaces,
                      int numberOfSmallHandicappedSpaces,
                      int numberOfMediumHandicappedSpaces,
                      int numberOfLargeHandicappedSpaces) {

        int[][] numberOfSpaces = { { numberOfSmallRegularSpaces, numberOfSmallHandicappedSpaces},
                { numberOfMediumRegularSpaces, numberOfMediumHandicappedSpaces},
                {numberOfLargeRegularSpaces, numberOfLargeHandicappedSpaces } };

        // Initialize the parking lot...

        for (int sizeIndex = 0; sizeIndex < vehicleSizes.length; sizeIndex++) {
            for (int vehicleTypeIndex = 0; vehicleTypeIndex < vehicleTypes.length; vehicleTypeIndex++) {
                ArrayList<ParkingSpace> spaces = new ArrayList<>();
                for (int i = 0; i < numberOfSpaces[sizeIndex][vehicleTypeIndex]; i++) {
                    ParkingSpace p = new ParkingSpace(vehicleSizes[sizeIndex], vehicleTypes[vehicleTypeIndex]);
                    spaces.add(p);

                }

                if (parkingAvailableBySize.containsKey(vehicleSizes[sizeIndex])) {
                    HashMap<Vehicle.vehicleType, ArrayList<ParkingSpace>> previousTypeToSpaceMap = parkingAvailableBySize.get(vehicleSizes[sizeIndex]);
                    previousTypeToSpaceMap.put(vehicleTypes[vehicleTypeIndex], spaces);

                } else {
                    HashMap<Vehicle.vehicleType, ArrayList<ParkingSpace>> typeToSpaceMap = new HashMap();
                    typeToSpaceMap.put(vehicleTypes[vehicleTypeIndex], spaces);
                    parkingAvailableBySize.put(vehicleSizes[sizeIndex], typeToSpaceMap);
                }

            }
        }
        System.out.println("Init");


    }

    public void park(Vehicle c) {
        ParkingSpace attemptedPark = tryToPark(c);
        if (attemptedPark != null) {
            attemptedPark.setOccupied(true);
            System.out.println("The car has parked in a " + attemptedPark.getSize() + " " + attemptedPark.getType());
        } else {
            System.out.println("The car cannot be parked at this time.");
        }
    }

    private ParkingSpace tryToPark(Vehicle vehicle) {
        Vehicle.vehicleSize size = vehicle.getSize();
        Vehicle.vehicleType type = vehicle.getType();


        // LARGE PARKING SPOTS
        if (size.equals(Vehicle.vehicleSize.LARGE)) {
            // if this is null, then no spaces are avaliable
            return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));


            // MEDIUM PARKING SPOTS
        } else if (size.equals(Vehicle.vehicleSize.MEDIUM)) {
            ParkingSpace mediumSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));
            if (mediumSpace != null) {
                return mediumSpace;
            } else {
                // Check large spaces --> if this is null, then no fitting spaces are avaliable
                return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.vehicleSize.LARGE));
            }

            // SMALL PARKING SPOTS
        } else if (size.equals(Vehicle.vehicleSize.SMALL)){
            ParkingSpace smallSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(size));
            if (smallSpace != null) {
                return smallSpace;
            } else {
                // Check medium spaces
                ParkingSpace mediumSpace = findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.vehicleSize.MEDIUM));
                if (mediumSpace != null) {
                    return mediumSpace;
                } else {
                    // Check large spaces --> if this is null, then no appropriate spaces are avaliable
                    return findAvaliableSpaceWithType(type, parkingAvailableBySize.get(Vehicle.vehicleSize.LARGE));
                }
            }

        } else {
            return null;
        }
    }


    private ParkingSpace findAvaliableSpaceWithType(Vehicle.vehicleType vehicleType,
                                                    HashMap<Vehicle.vehicleType, ArrayList<ParkingSpace>>
                                                            parkingAvailableByType) {
        if (vehicleType.equals(Vehicle.vehicleType.HANDICAPPED)) {
            // Check handicapped spaces first
            ArrayList<ParkingSpace> handicappedSpaces = parkingAvailableByType.get(vehicleType);
            ParkingSpace handicappedSpace = areSpacesTaken(handicappedSpaces);
            if (handicappedSpace != null) {
                return handicappedSpace;
            } else {
                // Check regular spaces
                ArrayList<ParkingSpace> regularSpaces = parkingAvailableByType.get(Vehicle.vehicleType.REGULAR);
                return areSpacesTaken(regularSpaces);
            }
        } else {
            // check regular spaces
            ArrayList<ParkingSpace> regularSpaces = parkingAvailableByType.get(Vehicle.vehicleType.REGULAR);
            return areSpacesTaken(regularSpaces);
        }
    }

    private ParkingSpace areSpacesTaken(ArrayList<ParkingSpace> parkingSpaces) {
        if (parkingSpaces == null) {
            return null;
        }

        for (ParkingSpace space : parkingSpaces) {
            if (space.getOccupied()) {
                return space;
            }
        }
        return null;
    }

    public boolean getIsParkingLotFull() {
        for (Vehicle.vehicleSize vehicleSize : vehicleSizes) {
            for (Vehicle.vehicleType vehicleType : vehicleTypes) {
                ParkingSpace space = findAvaliableSpaceWithType(vehicleType,
                        parkingAvailableBySize.get(vehicleSize));
                if (space == null) {
                    return false;
                }
            }
        }

        // no null spots mean the parking lot is full.
        return true;
    }

    private int toStringHelper_AvaliableSpacesWithTypeAndSize(Vehicle.vehicleType vehicleType,
                                                              Vehicle.vehicleSize vehicleSize) {
        HashMap<Vehicle.vehicleType, ArrayList<ParkingSpace>> avaliableByTypeAndSize =
                parkingAvailableBySize.get(vehicleSize);
        ArrayList<ParkingSpace> exactSpaces = avaliableByTypeAndSize.get(vehicleType);
        int count = 0;
        for (ParkingSpace space : exactSpaces) {
            if (space.getOccupied()) {
                count++;
            }
        }
        return count;
    }

    public String toStringSpotsLeft() {
        StringBuilder description = new StringBuilder();
        for (Vehicle.vehicleSize vehicleSize : vehicleSizes) {
            for (Vehicle.vehicleType vehicleType : vehicleTypes) {
                int count = toStringHelper_AvaliableSpacesWithTypeAndSize(vehicleType,
                        vehicleSize);
                description.append("There are ").append(count).append(" ").append(vehicleSize).append(" ").append(vehicleType).append(" spots left.\n");
            }

        }
        return description.toString();
    }
}
