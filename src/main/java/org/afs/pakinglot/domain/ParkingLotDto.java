package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.enums.StrategyType;

public class ParkingLotDto {

    String plateNumber;

    StrategyType strategyType;

    public String getPlateNumber() {
        return plateNumber;
    }

    public StrategyType getStrategyType() {
        return strategyType;
    }
}
