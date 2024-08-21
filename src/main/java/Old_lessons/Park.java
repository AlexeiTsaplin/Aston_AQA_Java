package Old_lessons;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Park {
    private String park_Name;
    private List<Attraction> attractions;

    public Park(String name) {
        this.park_Name = park_Name;
        this.attractions = new ArrayList<>();
    }

    @Data
    public class Attraction {
        private String att_Name;
        private String working_Hours;
        private int price;
    }
}


