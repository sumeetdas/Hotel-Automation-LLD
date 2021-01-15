package hotelauto.equipments;

import hotelauto.enums.EquipmentTypeEnum;

public class AC extends AbstractEquipment {

    public AC(String name) {
        super(name);
        unitsPerSecond = (10.0 / (12 * 3600));
    }

    @Override
    public EquipmentTypeEnum getType() {
        return EquipmentTypeEnum.AC;
    }

    @Override
    public AC clone() throws CloneNotSupportedException {
        final AC ac = new AC(getName());
        ac.setCorridor(getCorridor());
        ac.setUnitsConsumedPerSecond(getUnitsConsumedPerSecond());
        return ac;
    }
}
