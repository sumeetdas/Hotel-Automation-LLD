package hotelauto.vo;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Floor> floors = new ArrayList<>();

    public void addFloor(Floor floor) {
        floors.add(floor);
        floor.setHotel(this);
    }

    @Override
    public Hotel clone() throws CloneNotSupportedException {
        final Hotel hotel = new Hotel();
        floors.forEach(f -> {
            try {
                hotel.addFloor(f.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return hotel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        floors.forEach(f -> sb.append(f.toString()).append("\n\n"));
        return sb.toString();
    }
}
