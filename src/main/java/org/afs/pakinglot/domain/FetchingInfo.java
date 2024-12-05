package org.afs.pakinglot.domain;

import java.math.BigDecimal;
import java.util.Date;

public class FetchingInfo {
    private Car car;

    private BigDecimal price;

    private Date parkedTime;

    public FetchingInfo(Car car, BigDecimal price) {
        this.car = car;
        this.price = price;
    }

    public void setParkTime(Date parkedTime) {
        this.parkedTime = parkedTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(Date parkedTime) {
        this.parkedTime = parkedTime;
    }
}
