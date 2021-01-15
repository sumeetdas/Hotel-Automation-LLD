package hotelauto.equipments;

import hotelauto.IPowerConsumption;
import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentStatusEnum;
import hotelauto.enums.EquipmentTypeEnum;

public interface IEquipment extends IPowerConsumption, Cloneable {
    // name of equipment
    String getName();

    // turn on the equipment
    void turnOn();

    // turn off the equipment
    void turnOff();

    // set parent corridor
    void setCorridor(ICorridor corridor);

    // set unit of power consumed per second
    void setUnitsConsumedPerSecond(double unitsPerSecond);

    // is equipment on?
    boolean isOn();

    // current status of equipment
    EquipmentStatusEnum getStatus();

    // get type of equipment
    EquipmentTypeEnum getType();

    // get unit of power consumed per second
    double getUnitsConsumedPerSecond();

    // get corridor containing this equipment
    ICorridor getCorridor();

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

    IEquipment clone() throws CloneNotSupportedException;
}
