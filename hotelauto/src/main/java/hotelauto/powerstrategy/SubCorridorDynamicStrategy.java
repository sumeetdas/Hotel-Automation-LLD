package hotelauto.powerstrategy;

import java.util.Timer;
import java.util.TimerTask;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

public class SubCorridorDynamicStrategy implements IPowerStrategy {
    private ICorridor corridor = null;
    private boolean powerSaveMode = false;

    // this timer polls units consumed in the corridor every 10 min
    // once the powerSaveMode is on, the 
    private Timer unitPollTimer = new Timer();

    public SubCorridorDynamicStrategy () {
    }

    @Override
    public ICorridor getCorridor() {
        return corridor;
    }

    @Override
    public IPowerStrategy initialize(ICorridor corridor) {
        
        this.corridor = corridor;

        unitPollTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                powerSaveMode = corridor.getPowerConsumption() > 8;

                if (powerSaveMode) {
                    acPowerStrategy(corridor);
                    unitPollTimer.cancel();
                }
            }
        }, 0, 600_000);

        acPowerStrategy(corridor);

        return this;
    }

    private void acPowerStrategy(ICorridor corridor) {
        corridor.getEquipments().stream()
        .filter(e -> EquipmentTypeEnum.AC.equals(e.getType()))
        .forEach(e -> acPowerStrategy((AC) e));
    }

    private void acPowerStrategy(AC ac) {
        if (powerSaveMode) {
            acPowerSaveModeStrategy(ac);
        }
        else {
            acDefaultStrategy(ac);
        }
    }

    // strategy to switch on AC for 5 min, then turn off
    private void acPowerSaveModeStrategy(AC ac) {
        // turn on every 1 hour
        ac.scheduleAtFixedRate(() -> ac.turnOn(), 0, 3600_000);
        // turn off after 5 min every 1 hour
        ac.scheduleAtFixedRate(() -> ac.turnOff(), 300_000, 3600_000);
    }

    // strategy to switch on AC for 20 min, then turn off
    private void acDefaultStrategy(AC ac) {
        // turn on every 1 hour
        ac.scheduleAtFixedRate(() -> ac.turnOn(), 0, 3600_000);
        // turn off after 20 min every 1 hour
        ac.scheduleAtFixedRate(() -> ac.turnOff(), 1200_000, 3600_000);
    }
    
    @Override
    public void acPowerStrategy(AC ac, final SignalTypeEnum signalType) {
        acPowerStrategy(ac);

        switch(signalType) {
            case MOVEMENT:
                // do nothing
                break;
            case NO_MOVEMENT:
                ac.scheduleOnce(() -> ac.turnOn(), 1000);
                break;
        }
    }

    @Override
    public void lightPowerStrategy(Light light, final SignalTypeEnum signalType) {
        switch(signalType) {
            case MOVEMENT:
                light.cancelTimerTasks();
                light.turnOn();
                break;
            case NO_MOVEMENT:
                light.scheduleOnce(() -> {
                    if (light.isOn()) {
                        light.turnOff();
                    }
                }, 1000);
                break;
        }
    }

    @Override
    public SubCorridorDynamicStrategy clone() throws CloneNotSupportedException {
        final SubCorridorDynamicStrategy sub = new SubCorridorDynamicStrategy();
        sub.initialize(corridor);
        return sub;
    }
}
