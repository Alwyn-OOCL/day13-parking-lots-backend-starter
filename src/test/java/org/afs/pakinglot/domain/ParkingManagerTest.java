package org.afs.pakinglot.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {

    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    void should_return_ticket_given_standard_strategy_when_park_then_success() {
        String plateNumber = "ABC123";
        Ticket ticket = parkingManager.park("STANDARD", plateNumber);
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_given_smart_strategy_when_park_then_success() {
        String plateNumber = "DEF456";
        Ticket ticket = parkingManager.park("SMART", plateNumber);
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_given_super_strategy_when_park_then_success() {
        String plateNumber = "GHI789";
        Ticket ticket = parkingManager.park("SUPER", plateNumber);
        assertNotNull(ticket);
    }

    @Test
    void should_throw_exception_given_invalid_strategy_when_park_then_fail() {
        String plateNumber = "JKL012";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            parkingManager.park("INVALID", plateNumber);
        });
        assertEquals("Invalid parking strategy type: INVALID", exception.getMessage());
    }

    @Test
    void should_throw_exception_given_null_strategy_when_park_then_fail() {
        String plateNumber = "MNO345";
        Exception exception = assertThrows(Exception.class, () -> {
            parkingManager.park(null, plateNumber);
        });
        assertEquals("Invalid parking strategy type: null", exception.getMessage());
    }

    @Test
    void should_throw_exception_given_empty_strategy_when_park_then_fail() {
        String plateNumber = "PQR678";
        Exception exception = assertThrows(Exception.class, () -> {
            parkingManager.park("", plateNumber);
        });
        assertEquals("Invalid parking strategy type: ", exception.getMessage());
    }
}