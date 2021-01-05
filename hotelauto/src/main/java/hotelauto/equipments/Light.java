package hotelauto.equipments;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.vo.Floor;

public class Light extends AbstractEquipment {

    public Light(String name, final ICorridor corridor, final Floor floor) {
        super(name, corridor, floor);
        unitsPerSecond = (5.0 / (12 * 3600));
    }

    @Override
    public EquipmentTypeEnum getType() {
        return EquipmentTypeEnum.LIGHT;
    }
}
