package org.exemple;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Park {
    private String park_name;
    private List<Attraction> attractions;

    @Data
    public class Attraction {
        private String att_name;
        private String working_hours;
        private int price;
    }

    public Park(String name) {
        this.park_name = park_name;
        this.attractions = new ArrayList<>();
    }

    public void addAttraction(String att_name, String working_hours, int price) {
        Attraction newAttraction = new Attraction();
        attractions.add(newAttraction);
    }
}


