package hotelauto.equipments;

import hotelauto.enums.EquipmentStatusEnum;
import hotelauto.enums.EquipmentTypeEnum;

public class Light implements IEquipment {

    private EquipmentStatusEnum status = EquipmentStatusEnum.OFF;
    private final EquipmentTypeEnum type;
    private final String name;

    public Light (final String name, final EquipmentTypeEnum type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void turnOn() {
        status = EquipmentStatusEnum.ON;
    }

    @Override
    public void turnOff() {
        status = EquipmentStatusEnum.OFF;
    }

    @Override
    public boolean isOn() {
        return EquipmentStatusEnum.ON.equals(status);
    }    

    @Override
    public EquipmentStatusEnum getStatus() {
        return status;
    }

    @Override
    public EquipmentTypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return toStringCustom();
    }
}
