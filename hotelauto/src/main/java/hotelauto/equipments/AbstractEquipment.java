package hotelauto.equipments;

import java.util.Timer;
import java.util.TimerTask;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentStatusEnum;
import hotelauto.vo.Floor;

public abstract class AbstractEquipment implements IEquipment {
    private EquipmentStatusEnum status = EquipmentStatusEnum.OFF;
    private final String name;
    private final Timer timer = new Timer();
    protected double unitsPerSecond = 5.0 / (12 * 3600);
    private final ICorridor corridor;
    private final Floor floor;
    protected double powerConsumption;

    protected AbstractEquipment (final String name, final ICorridor corridor, final Floor floor) {
        this.name = name;
        this.corridor = corridor;
        this.floor = floor;
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
    public ICorridor getCorridor() {
        return corridor;
    }

    @Override
    public Floor getFloor() {
        return floor;
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
}
