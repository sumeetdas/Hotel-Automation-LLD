package hotelauto.vo;

import java.util.ArrayList;
import java.util.List;

import hotelauto.corridors.ICorridor;

public class Floor implements Cloneable {

    private String name = null;

    private Hotel hotel = null;

    public Floor(String name) {
        this.name = name;
    }

    private final List<ICorridor> corridors = new ArrayList<>();

    public void addCorridor(ICorridor c) {
        corridors.add(c);
        c.setFloor(this);
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\t\t\t").append(name).append("\n");
        corridors.forEach(c -> sb.append(c.toString()).append("\n"));
        return sb.toString();
    }

    @Override
    public Floor clone() throws CloneNotSupportedException {
        final Floor floor = new Floor(name);
        floor.setHotel(hotel);
        corridors.forEach(c -> {
            try {
                floor.addCorridor(c.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return floor;
    }
}
