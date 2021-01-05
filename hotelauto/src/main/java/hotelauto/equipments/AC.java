package hotelauto.equipments;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.vo.Floor;

public class AC extends AbstractEquipment {

    public AC(String name, final ICorridor corridor, final Floor floor) {
        super(name, corridor, floor);
        unitsPerSecond = (10.0 / (12 * 3600));
    }

    @Override
    public EquipmentTypeEnum getType() {
        return EquipmentTypeEnum.AC;
    }
}
