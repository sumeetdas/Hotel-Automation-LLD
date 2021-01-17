package hotelauto.vo;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
        floors.add(floor);
        floor.setHotel(this);
    }

    public List<Floor> getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        floors.forEach(f -> sb.append(f.toString()));
        return sb.toString();
    }
}
