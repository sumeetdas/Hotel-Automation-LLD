package hotelauto.corridors;

import java.util.ArrayList;
import java.util.List;

import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.IEquipment;

public class Corridor {
    private SignalTypeEnum signalType = SignalTypeEnum.NO_MOVEMENT;
    private final String name;
    private final List<IEquipment> equipments = new ArrayList<>();  
    
    public Corridor (String name) {
        this.name = name;
    }     

    public void addEquipment(IEquipment e) {
        equipments.add(e);
    }

    public String getName() {
        return name;
    }

    public synchronized SignalTypeEnum getSignal() {
        return signalType;
    }

    public synchronized void setSignal(final SignalTypeEnum signalType) {
        this.signalType = signalType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(name).append(" ");
        equipments.forEach(e -> sb.append(e.toString()).append("  "));
        return sb.toString();
    }
}
