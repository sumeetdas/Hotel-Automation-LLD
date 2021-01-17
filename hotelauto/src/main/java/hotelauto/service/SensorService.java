package hotelauto.service;

import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.vo.Floor;
import hotelauto.vo.Hotel;

public class SensorService {
    private final Hotel hotel;
    public SensorService(final Hotel hotel) {
        this.hotel = hotel;
    }
    /**
     * 
     * @param corridor String - Corridor where the movement was observed. Format: '<floor_name>.<corridor_name>'
     * @param signal SignalTypeEnum - Type of movement signal
     */
    public void receiveSignal(String source, SignalTypeEnum signal) {
        final String[] split = source.split("\\.");
        if (split.length != 2) {
            throw getException("source is incorrect", source);
        }
        final String floorName = split[0];
        final String corridorName = split[1];

        final Floor floor;

        try {
            floor = hotel.getFloors().stream().filter(f -> f.getName().equals(floorName)).findFirst().orElseThrow();
        } catch (NoSuchElementException e) {
            throw getException("floor not found", source);
        }
        
        final ICorridor corridor;

        try {
            corridor = floor.getCorridors().stream().filter(c -> c.getName().equals(corridorName)).findFirst().orElseThrow();
        } catch (NoSuchElementException e) {
            throw getException("corridor not found", source);
        }
        
        corridor.processSignal(signal);
    }

    private RuntimeErrorException getException(final String message, final String source) {
        return new RuntimeErrorException(new Error(), message + ":" + source);
    }
}
