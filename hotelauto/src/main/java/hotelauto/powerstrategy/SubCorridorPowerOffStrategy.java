package hotelauto.powerstrategy;

import java.util.Timer;
import java.util.TimerTask;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.EquipmentTypeEnum;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

public class SubCorridorPowerOffStrategy implements IPowerStrategy {
    private ICorridor corridor;
    private boolean powerConsumptionExceeded = false;

    // this timer polls units consumed in the corridor every 10 min
    // once powerConsumptionExceeded, the AC would be switched off always and timer is cancelled
    private Timer unitPollTimer = new Timer();

    private long lastMovementTime = System.currentTimeMillis();

    public SubCorridorPowerOffStrategy () {
    }

    @Override
    public ICorridor getCorridor() {
        return corridor;
    }

    @Override
    public IPowerStrategy initialize(final ICorridor corridor) {
        this.corridor = corridor;

        unitPollTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                powerConsumptionExceeded = corridor.getPowerConsumption() > 10;

                if (powerConsumptionExceeded) {
                    corridor.getEquipments().stream()
                    .filter(e -> EquipmentTypeEnum.AC.equals(e.getType()))
                    .forEach(e -> e.turnOff());;
                    
                    unitPollTimer.cancel();
                }
            }
        }, 0, 600_000);
        
        return this;
    }

    @Override
    public void acPowerStrategy(AC ac, final SignalTypeEnum signalType) {
        switch(signalType) {
            case MOVEMENT:
                ac.turnOff();
                lastMovementTime = System.currentTimeMillis();
                break;
            case NO_MOVEMENT:
                if (powerConsumptionExceeded) {
                    // do nothing.. AC stays off
                }
                else {
                    final long currentTime = System.currentTimeMillis();
                    if (currentTime - lastMovementTime >= 60_000) {
                        ac.turnOn();
                    }
                }
                break;
        }
    }

    @Override
    public void lightPowerStrategy(Light light, final SignalTypeEnum signalType) {
        switch(signalType) {
            case MOVEMENT:
                light.turnOn();
                break;
            case NO_MOVEMENT:
                final long currentTime = System.currentTimeMillis();
                if (currentTime - lastMovementTime >= 60_000) {
                    light.turnOff();
                }
                break;
        }
    }

    @Override
    public SubCorridorPowerOffStrategy clone() {
        final SubCorridorPowerOffStrategy sub = new SubCorridorPowerOffStrategy();
        sub.initialize(corridor);
        return sub;
    }
}
