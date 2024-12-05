package org.afs.pakinglot.domain;

import java.util.List;
import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;
import org.springframework.stereotype.Component;

@Component
public class ParkingManager {

    private final ParkingBoy standardParkingBoy;
    private final ParkingBoy smartParkingBoy;
    private final ParkingBoy superParkingBoy;

    public ParkingManager() {
        ParkingLot plazaPark = new ParkingLot(1, "The Plaza Park", 9);
        ParkingLot cityMallGarage = new ParkingLot(2, "City Mall Garage", 12);
        ParkingLot officeTowerParking = new ParkingLot(3, "Office Tower Parking", 9);
        List<ParkingLot> parkingLots = List.of(plazaPark, cityMallGarage, officeTowerParking);
        this.standardParkingBoy = new ParkingBoy(parkingLots, new SequentiallyStrategy());
        this.smartParkingBoy = new ParkingBoy(parkingLots, new MaxAvailableStrategy());
        this.superParkingBoy = new ParkingBoy(parkingLots, new AvailableRateStrategy());
    }

    public Ticket park(String strategyType, String plateNumber) {
        Car car = new Car(plateNumber);
        if (strategyType == null) {
            throw new IllegalArgumentException("Invalid parking strategy type: null");
        }
        return switch (strategyType.toUpperCase()) {
            case "STANDARD" -> standardParkingBoy.park(car);
            case "SMART" -> smartParkingBoy.park(car);
            case "SUPER" -> superParkingBoy.park(car);
            default -> throw new IllegalArgumentException("Invalid parking strategy type: " + strategyType);
        };
    }
}