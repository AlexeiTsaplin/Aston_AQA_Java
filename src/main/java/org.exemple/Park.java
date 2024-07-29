package org.exemple;

import lombok.Data;

@Data
public class Park {
    private String park_name;
    private Attraction Attraction;

    @Data
    public class Attraction {
        private String att_name;
        private String working_hours;
        private int price;
    }
}

