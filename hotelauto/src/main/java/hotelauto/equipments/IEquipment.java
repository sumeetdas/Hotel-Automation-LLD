package hotelauto.equipments;

import hotelauto.IPowerConsumption;
import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentStatusEnum;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.vo.Floor;

public interface IEquipment extends IPowerConsumption {
    // name of equipment
    String getName();

    // turn on the equipment
    void turnOn();

    // turn off the equipment
    void turnOff();

    // is equipment on?
    boolean isOn();

    // current status of equipment
    EquipmentStatusEnum getStatus();

    // get type of equipment
    EquipmentTypeEnum getType();

    // get unit of power consumed per second
    double getUnitsConsumedPerSecond();

    // set unit of power consumed per second
    void setUnitsConsumedPerSecond(double unitsPerSecond);

    // get corridor containing this equipment
    ICorridor getCorridor();

    // get floor containing this equipment
    Floor getFloor();

    // timer-related convenience methods
    
    // cancel timer tasks
    void cancelTimerTasks();

    // schedule once after a delay
    void scheduleOnce(Runnable op, int delay);

    // schedule every `period` time after `delay`
    void scheduleAtFixedRate(Runnable op, int delay, int period);

    // custom toString method
    default String toStringCustom() {
        return getName() + ": " + getStatus().name();
    }
}
