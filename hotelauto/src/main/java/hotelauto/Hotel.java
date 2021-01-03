package hotelauto;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
        floors.add(floor);
    }
}
