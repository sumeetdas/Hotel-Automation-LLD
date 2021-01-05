package hotelauto.powerstrategy;

import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

// strategy to manage power of equipments
public interface IPowerStrategy {
    void acPowerStrategy(final AC ac, final SignalTypeEnum signalType);
    void lightPowerStrategy(final Light light, final SignalTypeEnum signalType);    
}
