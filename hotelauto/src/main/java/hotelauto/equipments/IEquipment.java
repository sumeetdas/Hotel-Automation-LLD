package hotelauto.equipments;

import hotelauto.enums.EquipmentStatusEnum;
import hotelauto.enums.EquipmentTypeEnum;

public interface IEquipment {
    String getName();
    void turnOn();
    void turnOff();
    boolean isOn();
    EquipmentStatusEnum getStatus();
    EquipmentTypeEnum getType();

    default String toStringCustom() {
        return getName() + ": " + getStatus().name();
    }
}
