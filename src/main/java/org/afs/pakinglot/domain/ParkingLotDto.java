package org.afs.pakinglot.domain;

import org.springframework.web.bind.annotation.RequestBody;

public class ParkingLotDto {

    String plateNumber;

    String strategyType;

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getStrategyType() {
        return strategyType;
    }
}
