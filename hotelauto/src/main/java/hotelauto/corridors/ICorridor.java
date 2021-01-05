package hotelauto.corridors;

import java.util.List;

import hotelauto.IPowerConsumption;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.IEquipment;
import hotelauto.equipments.Light;
import hotelauto.powerstrategy.IPowerStrategy;
import hotelauto.vo.Floor;

public interface ICorridor extends IPowerConsumption {
    
    // adds equipment to corridor
    void addEquipment(IEquipment e);

    // get name of corridor
    String getName();

    // get floor
    Floor getFloor();

    // get last signal received from sensor
    SignalTypeEnum getSignal();

    // set new signal received from sensor
    void setSignal(final SignalTypeEnum signalType);

    // get power strategy of corridor
    IPowerStrategy getPowerStrategy();

    // contains strategy to process a signal and take action on the equipments accordingly
    default void processSignal(final SignalTypeEnum signalType) {
        final IPowerStrategy powerStrategy = getPowerStrategy();

        getEquipments().forEach(equipment -> {
            switch(equipment.getType()) {
                case AC:
                    powerStrategy.acPowerStrategy((AC) equipment, signalType);
                    break;
                case LIGHT:
                    powerStrategy.lightPowerStrategy((Light) equipment, signalType);
                    break;
            }
        });
    }

    // get list of all equipments
    List<IEquipment> getEquipments();

    // custom toString impl for all corridors
    default String toStringCustom() {
        final StringBuilder sb = new StringBuilder(getName()).append(" ");
        getEquipments().forEach(e -> sb.append(e.toString()).append("  "));
        return sb.toString();
    }
}
