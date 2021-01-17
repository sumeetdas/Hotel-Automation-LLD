package hotelauto.equipments;

import hotelauto.enums.EquipmentTypeEnum;

public class Light extends AbstractEquipment {

    public Light(String name) {
        super(name);
        unitsPerSecond = (5.0 / (12 * 3600));
    }

    @Override
    public EquipmentTypeEnum getType() {
        return EquipmentTypeEnum.LIGHT;
    }

    @Override
    public Light clone() {
        final Light light = new Light(getName());
        light.setCorridor(getCorridor());
        light.setUnitsConsumedPerSecond(getUnitsConsumedPerSecond());
        return light;
    }
}
