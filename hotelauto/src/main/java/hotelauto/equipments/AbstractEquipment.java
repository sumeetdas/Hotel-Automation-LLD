package hotelauto.equipments;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentStatusEnum;

public abstract class AbstractEquipment implements IEquipment {

    private EquipmentStatusEnum status = EquipmentStatusEnum.OFF;
    private final String name;
    protected double unitsPerSecond = 5.0 / (12 * 3600);
    private ICorridor corridor = null;
    protected double powerConsumption;
    protected long lastStatusChangeTime = System.currentTimeMillis();

    protected AbstractEquipment (final String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public void turnOn() {
        changeStatus(EquipmentStatusEnum.ON);
    }

    @Override
    public void turnOff() {
        changeStatus(EquipmentStatusEnum.OFF);
    }

    private void changeStatus(EquipmentStatusEnum status) {
        if (status != this.status) {
            switch(status) {
                case ON:
                    lastStatusChangeTime = System.currentTimeMillis();
                    break;
                case OFF:
                    final long currentTime = System.currentTimeMillis();
                    powerConsumption += unitsPerSecond * (currentTime - lastStatusChangeTime) / 1000;
                    lastStatusChangeTime = currentTime;
                    break;
            }
        }
        this.status = status;
    }
    
    @Override
    public boolean isOn() {
        return EquipmentStatusEnum.ON.equals(status);
    }        

    @Override
    public EquipmentStatusEnum getStatus() {
        return status;
    }

    @Override
    public void setCorridor(ICorridor corridor) {
        this.corridor = corridor;
    }

    @Override
    public ICorridor getCorridor() {
        return corridor;
    }

    @Override
    public double getUnitsConsumedPerSecond() {
        return unitsPerSecond;
    }

    @Override
    public void setUnitsConsumedPerSecond(double unitsPerSecond) {
        this.unitsPerSecond = unitsPerSecond;
    }

    @Override
    public String toString() {
        return toStringCustom();
    }

    @Override
    public abstract AbstractEquipment clone();
}
