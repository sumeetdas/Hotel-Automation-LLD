package hotelauto.corridors;

import java.util.ArrayList;
import java.util.List;

import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.IEquipment;
import hotelauto.vo.Floor;

public abstract class AbstractCorridor implements ICorridor {
    private SignalTypeEnum signalType = SignalTypeEnum.NO_MOVEMENT;
    private final String name;
    private final Floor floor;
    private final List<IEquipment> equipments = new ArrayList<>();  
    
    public AbstractCorridor (String name, Floor floor) {
        this.name = name;
        this.floor = floor;
    }     

    @Override
    public void addEquipment(IEquipment e) {
        equipments.add(e);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public synchronized SignalTypeEnum getSignal() {
        return signalType;
    }

    @Override
    public synchronized void setSignal(final SignalTypeEnum signalType) {
        this.signalType = signalType;
    }

    @Override
    public String toString() {
        return toStringCustom();
    }

    @Override
    public List<IEquipment> getEquipments() {
        return equipments;
    }

    @Override
    public Floor getFloor() {
        return floor;
    }

    @Override
    public double getPowerConsumption() {
        return getEquipments().stream().mapToDouble(IEquipment::getPowerConsumption).reduce(0D, (a,b) -> a + b);
    }
}
