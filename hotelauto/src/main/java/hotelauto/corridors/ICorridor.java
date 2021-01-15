package hotelauto.corridors;

import java.util.List;

import hotelauto.IPowerConsumption;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.IEquipment;
import hotelauto.powerstrategy.IPowerStrategy;
import hotelauto.vo.Floor;

public interface ICorridor extends IPowerConsumption, Cloneable {

    // adds equipment to corridor
    void addEquipment(IEquipment e);

    // set parent floor
    void setFloor(Floor floor);

    // get name of corridor
    String getName();

    // get floor
    Floor getFloor();

    void setPowerStrategy(IPowerStrategy powerStrategy);

    // get power strategy of corridor
    IPowerStrategy getPowerStrategy();

    // get list of all equipments
    List<IEquipment> getEquipments();

    // process sensor signal
    void processSignal(final SignalTypeEnum signalTypeEnum);

    // custom toString impl for all corridors
    default String toStringCustom() {
        final StringBuilder sb = new StringBuilder(getName()).append(" ");
        getEquipments().forEach(e -> sb.append(e.toString()).append("  "));
        return sb.toString();
    }

    ICorridor clone() throws CloneNotSupportedException;
}
