package hotelauto.powerstrategy;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

// strategy to manage power of equipments
public interface IPowerStrategy extends Cloneable {
    // things to do initially, including setting corridor
    IPowerStrategy initialize (ICorridor corridor);

    // get corridor 
    ICorridor getCorridor();

    // contains strategy to process a signal and take action on the equipments accordingly
    default void processSignal(final SignalTypeEnum signalType) {
        getCorridor().getEquipments().forEach(equipment -> {
            switch(equipment.getType()) {
                case AC:
                    acPowerStrategy((AC) equipment, signalType);
                    break;
                case LIGHT:
                    lightPowerStrategy((Light) equipment, signalType);
                    break;
            }
        });
    }

    void acPowerStrategy(final AC ac, final SignalTypeEnum signalType);
    void lightPowerStrategy(final Light light, final SignalTypeEnum signalType);    

    IPowerStrategy clone() throws CloneNotSupportedException;
}
