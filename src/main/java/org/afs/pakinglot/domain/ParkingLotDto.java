package org.afs.pakinglot.domain;

import java.util.Date;
import org.afs.pakinglot.domain.enums.ParkingStrategyType;

public class ParkingLotDto {

    private String plateNumber;

    private ParkingStrategyType parkingStrategyType;

    private Date parkTime;

    public String getPlateNumber() {
        return plateNumber;
    }

    public ParkingStrategyType getStrategyType() {
        return parkingStrategyType;
    }

    public Date getParkTime() {
        return parkTime;
    }

    public ParkingLotDto(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public ParkingLotDto(String plateNumber, ParkingStrategyType parkingStrategyType) {
        this.plateNumber = plateNumber;
        this.parkingStrategyType = parkingStrategyType;
    }

    public ParkingLotDto() {
    }
}
