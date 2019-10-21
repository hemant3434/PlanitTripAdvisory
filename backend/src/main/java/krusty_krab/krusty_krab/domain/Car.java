package krusty_krab.krusty_krab.domain;

import java.sql.Time;
import java.util.List;

public class Car extends Transportation{

    public Car () {
    }

    public Car(Time time, String distance, double cost) {
        super.setTime(time);
        super.setMethod("Car");
    }
}
