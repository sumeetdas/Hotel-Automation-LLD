package hotelauto;

import hotelauto.service.SensorService;
import hotelauto.vo.Hotel;

public class HotelAutomation {
    private final Hotel hotel;
    private final SensorService sensorService;

    public HotelAutomation (final Hotel hotel) {
        this.hotel = hotel;
        this.sensorService = new SensorService(hotel);
    } 

    public Hotel getHotel () {
        return hotel;
    }

    public SensorService getSensorService() {
        return sensorService;
    }
}
