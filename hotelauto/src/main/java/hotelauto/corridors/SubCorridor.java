package hotelauto.corridors;

import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.equipments.IEquipment;
import hotelauto.powerstrategy.IPowerStrategy;

public class SubCorridor extends AbstractCorridor {

    public SubCorridor(final String name, final IPowerStrategy powerStrategy) {
        super(name, powerStrategy);
    }

    @Override
    public void addEquipment(IEquipment e) {
        super.addEquipment(e);
        // turn on AC
        if (EquipmentTypeEnum.AC.equals(e.getType())) {
            e.turnOn();
        }
    }

    @Override
    public SubCorridor clone() {
        final SubCorridor subCorridor = new SubCorridor(getName(), getPowerStrategy().clone());
        subCorridor.setFloor(getFloor());
        getEquipments().forEach(e -> subCorridor.addEquipment(e.clone()));
        return subCorridor;
    }
}
