# ParkingLotController API Design Documentation

This document outlines the API endpoints exposed by the `ParkingLotController` class. It provides details about each endpoint, including the HTTP method, route, request and response bodies, and example JSON data.

## Overview
The `ParkingLotController` provides four key API endpoints to interact with the parking system:

1. **GET /parkinglot** - Retrieve information about all parking lots.
2. **POST /parkinglot/park** - Park a car in a parking lot.
3. **POST /parkinglot/fetch** - Fetch a car from the parking lot using its plate number.

The controller relies on the `ParkingManager` class to handle business logic related to parking, ticketing, and fetching cars.

---

## API Endpoints

### 1. **GET /parkinglot**
Retrieve a list of all parking lots.

#### Request:
- **Method**: `GET`
- **Route**: `/parkinglot`
- **Request Body**: None

#### Response:
- **Status Code**: 200 OK
- **Content Type**: `application/json`
- **Response Body**: A list of parking lots with details like name, capacity, etc.

#### Example Response:
```json
[
  {
    "id": 1,
    "name": "The Plaza Park",
    "capacity": 100
  },
  {
    "id": 2,
    "name": "City Mall Garage",
    "capacity": 200
  },
  {
    "id": 3,
    "name": "Office Tower Parking",
    "capacity": 50
  }
]
```

### 2. **POST /parkinglot/park**
Park a car in a parking lot.

#### Request:
- **Method**: `POST`
- **Route**: `/parkinglot/park`
- **Request Body**: A `ParkingLotDto` object containing the strategy type and plate number.

#### Request Body Example:
```json
{
  "strategyType": "STANDARD",
  "plateNumber": "ABC123"
}
```

#### Response:
- **Status Code**: 200 OK
- **Content Type**: `application/json`
- **Response Body**: A `Ticket` object containing details like ticket number and parking lot info.

#### Example Response:
```json
{
  "ticketNumber": "TICKET12345",
  "plateNumber": "ABC123",
  "parkingLotId": 1,
  "parkingLotName": "The Plaza Park"
}
```

### 3. **POST /parkinglot/fetch**
Fetch a car from a parking lot using its plate number.

#### Request:
- **Method**: `POST`
- **Route**: `/parkinglot/fetch`
- **Request Body**: A `ParkingLotDto` object containing the plate number.

#### Request Body Example:
```json
{
  "plateNumber": "ABC123"
}
```

#### Response:
- **Status Code**: 200 OK
- **Content Type**: `application/json`
- **Response Body**: A `Car` object containing details of the car being fetched.

#### Example Response:
```json
{
  "plateNumber": "ABC123",
  "make": "Toyota",
  "model": "Corolla",
  "year": 2020
}
```



## Data Models and DTOs

### ParkingLotDto
This Data Transfer Object (DTO) is used for both parking a car and fetching a car. It contains the following fields:

- **strategyType**: The strategy used to park the car (e.g., `STANDARD`).
- **plateNumber**: The plate number of the car.

```java
public class ParkingLotDto {
    private String strategyType;
    private String plateNumber;
    
    // Getters and Setters
}
```

### ParkingLotInfo
The `ParkingLotInfo` object is used to provide basic information about a parking lot, including:

- **id**: The unique identifier of the parking lot.
- **name**: The name of the parking lot.
- **capacity**: The capacity of the parking lot.

```java
public class ParkingLotInfo {
    private int id;
    private String name;
    private int capacity;

    public static ParkingLotInfo of(ParkingLot parkingLot) {
        // Convert from ParkingLot to ParkingLotInfo
    }
    
    // Getters and Setters
}
```

---

## Example Interaction Flow

1. **Fetch List of Parking Lots**:
   - Send a `GET` request to `/parkinglot`.
   - Receive a list of parking lots with details like name and capacity.

2. **Park a Car**:
   - Send a `POST` request to `/parkinglot/park` with the car's plate number and parking strategy type.
   - Receive a ticket with the assigned parking lot information.

3. **Fetch a Car**:
   - Send a `POST` request to `/parkinglot/fetch` with the car's plate number.
   - Receive the car details (plate number, make, model, year).
