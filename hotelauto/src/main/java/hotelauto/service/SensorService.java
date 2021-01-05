package hotelauto.service;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.SignalTypeEnum;

public class SensorService {
    public void receiveSignal(ICorridor c, SignalTypeEnum signal) {
        c.processSignal(signal);
    }
}
