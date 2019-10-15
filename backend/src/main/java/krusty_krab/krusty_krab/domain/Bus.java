package krusty_krab.krusty_krab.domain;

import java.sql.Time;
import java.util.List;

public class Bus extends Transportation{

    public Bus () {
    }

    public Bus(Time time, String distance, double cost) {
        super.setTime(time);
        super.setDistance(distance);
        super.setCost(cost);
        super.setMethod("Bus");
    }
}