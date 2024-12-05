package org.afs.pakinglot.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.afs.pakinglot.domain.enums.ParkingStrategyType;
import org.afs.pakinglot.domain.exception.UnrecognizedTicketException;
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

    public Ticket park(ParkingStrategyType parkingStrategyType, String plateNumber) {
        Car car = new Car(plateNumber);
        if (parkingStrategyType == null) {
            throw new IllegalArgumentException("Invalid parking strategy type: null");
        }
        return switch (parkingStrategyType) {
            case STANDARD -> standardParkingBoy.park(car);
            case SMART -> smartParkingBoy.park(car);
            case SUPER_SMART -> superParkingBoy.park(car);
            default -> throw new IllegalArgumentException("Invalid parking strategy type: " + parkingStrategyType);
        };
    }

    public Car fetch(Ticket ticket) {
        for (ParkingBoy parkingBoy : List.of(standardParkingBoy, smartParkingBoy, superParkingBoy)) {
            try {
                return parkingBoy.fetch(ticket);
            } catch (UnrecognizedTicketException ignored) {
                // Continue to the next parking boy
            }
        }
        throw new UnrecognizedTicketException();
    }

    //find ticket by plate number use stream
    public Ticket findTicketByPlateNumber(String plateNumber) {
        return standardParkingBoy.getParkingLots().stream()
                .map(parkingLot -> parkingLot.findTicketByPlateNumber(plateNumber))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }

    public BigDecimal calculatePrice(Date parkTime) {
        // 3 dollar per hour
        Date now = new Date();
        long diff = now.getTime() - parkTime.getTime();
        long diffHours = diff / (60 * 60 * 1000);
        return BigDecimal.valueOf(diffHours * 3);
    }


    public ParkingBoy getStandardParkingBoy() {
        return standardParkingBoy;
    }

    public ParkingBoy getSmartParkingBoy() {
        return smartParkingBoy;
    }

    public ParkingBoy getSuperParkingBoy() {
        return superParkingBoy;
    }
}