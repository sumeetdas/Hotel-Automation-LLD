package hotelauto.corridors;

import hotelauto.equipments.IEquipment;
import hotelauto.powerstrategy.IPowerStrategy;

public class MainCorridor extends AbstractCorridor {

    public MainCorridor(String name, IPowerStrategy powerStrategy) {
        super(name, powerStrategy);
    }

    @Override
    public void addEquipment(IEquipment e) {
        super.addEquipment(e);
        // turn all electronic appliances for main corridor
        e.turnOn();
    }

    @Override
    public MainCorridor clone() throws CloneNotSupportedException {
        final MainCorridor mainCorridor = new MainCorridor(getName(), getPowerStrategy().clone());
        mainCorridor.setFloor(getFloor());
        getEquipments().forEach(e -> {
            try {
                mainCorridor.addEquipment(e.clone());
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
        });
        return mainCorridor;
    }
}
