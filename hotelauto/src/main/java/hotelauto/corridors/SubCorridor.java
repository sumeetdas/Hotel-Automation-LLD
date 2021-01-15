package hotelauto.corridors;

import hotelauto.powerstrategy.IPowerStrategy;

public class SubCorridor extends AbstractCorridor {

    public SubCorridor(final String name, final IPowerStrategy powerStrategy) {
        super(name, powerStrategy);
    }

    @Override
    public SubCorridor clone() throws CloneNotSupportedException {
        final SubCorridor subCorridor = new SubCorridor(getName(), getPowerStrategy().clone());
        subCorridor.setFloor(getFloor());
        getEquipments().forEach(e -> {
            try {
                subCorridor.addEquipment(e.clone());
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        });
        return subCorridor;
    }
}
