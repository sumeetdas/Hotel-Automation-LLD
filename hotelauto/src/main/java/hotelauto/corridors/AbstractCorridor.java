package hotelauto.corridors;

import java.util.ArrayList;
import java.util.List;

import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.IEquipment;
import hotelauto.powerstrategy.IPowerStrategy;
import hotelauto.vo.Floor;

public abstract class AbstractCorridor implements ICorridor {

    private SignalTypeEnum signalType = SignalTypeEnum.NO_MOVEMENT;
    private final String name;
    private final List<IEquipment> equipments = new ArrayList<>();  
    private IPowerStrategy powerStrategy = null;
    private Floor floor = null;
    
    protected AbstractCorridor (String name,IPowerStrategy powerStrategy) {
        this.name = name;
        setPowerStrategy(powerStrategy);
    }     

    @Override
    public void setPowerStrategy(IPowerStrategy powerStrategy) {
        this.powerStrategy = powerStrategy;
        this.powerStrategy.initialize(this);
    }

    @Override
    public void addEquipment(IEquipment e) {
        equipments.add(e);
    }

    @Override
    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void processSignal(SignalTypeEnum signalTypeEnum) {
        powerStrategy.processSignal(signalType);
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public IPowerStrategy getPowerStrategy() {
        return powerStrategy;
    }

    public abstract AbstractCorridor clone() throws CloneNotSupportedException;
}
