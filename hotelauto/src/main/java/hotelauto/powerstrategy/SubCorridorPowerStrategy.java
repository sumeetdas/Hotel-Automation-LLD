package hotelauto.powerstrategy;

import java.util.Timer;
import java.util.TimerTask;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

public class SubCorridorPowerStrategy implements IPowerStrategy {
    private boolean powerSaveMode = false;
    private Timer unitPollTimer = new Timer();

    public SubCorridorPowerStrategy (final ICorridor corridor) {

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
    }

    private void acPowerStrategy(ICorridor corridor) {
        corridor.getEquipments().forEach(e -> {
            if (EquipmentTypeEnum.AC.equals(e.getType())) {
                acPowerStrategy((AC) e);
            }
        });
    }
    
    // strategy to switch on AC for 20 min, then turn off
    private void acPowerStrategy(AC ac) {
        if (powerSaveMode) {
            // turn on every 1 hour
            ac.scheduleAtFixedRate(() -> ac.turnOn(), 0, 3600_000);
            // turn off after 5 min every 1 hour
            ac.scheduleAtFixedRate(() -> ac.turnOff(), 300_000, 3600_000);
        }
        else {
            // turn on every 1 hour
            ac.scheduleAtFixedRate(() -> ac.turnOn(), 0, 3600_000);
            // turn off after 20 min every 1 hour
            ac.scheduleAtFixedRate(() -> ac.turnOff(), 1200_000, 3600_000);
        }
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
}
