package hotelauto.service;

import java.util.ArrayList;
import java.util.List;

import hotelauto.corridors.Corridor;
import hotelauto.enums.SignalTypeEnum;

public class SensorService {
    private final List<Corridor> corridors = new ArrayList<>();

    public void receiveSignal(Corridor c, SignalTypeEnum signal) {
        
    }   
    
    public void registerCorridor(Corridor c) {
        corridors.add(c);
    }
}
