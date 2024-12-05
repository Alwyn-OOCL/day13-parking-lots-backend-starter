package org.afs.pakinglot.domain;

import java.util.Date;

public record Ticket(String plateNumber, int position, int parkingLot, Date parkTime) {

    public Ticket(String plateNumber, int position, int parkingLot) {
        this(plateNumber, position, parkingLot, null);
    }
}
