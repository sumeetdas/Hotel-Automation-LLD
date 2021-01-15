package hotelauto.equipments;

import java.util.Timer;
import java.util.TimerTask;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentStatusEnum;

public abstract class AbstractEquipment implements IEquipment {

    private EquipmentStatusEnum status = EquipmentStatusEnum.OFF;
    private final String name;
    private final Timer timer = new Timer();
    protected double unitsPerSecond = 5.0 / (12 * 3600);
    private ICorridor corridor = null;
    protected double powerConsumption;

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
        status = EquipmentStatusEnum.ON;
    }

    @Override
    public void turnOff() {
        status = EquipmentStatusEnum.OFF;
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
    public void cancelTimerTasks() {
        timer.cancel();
    }

    @Override
    public void scheduleOnce(Runnable op, int delay) {
        timer.schedule((TimerTask) op, delay);
    }

    @Override
    public void scheduleAtFixedRate(Runnable op, int delay, int period) {
        timer.scheduleAtFixedRate((TimerTask) op, delay, period);
    }

    @Override
    public abstract AbstractEquipment clone() throws CloneNotSupportedException;
}
