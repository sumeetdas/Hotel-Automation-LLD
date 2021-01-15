package hotelauto.powerstrategy;

import javax.management.RuntimeErrorException;

import hotelauto.corridors.ICorridor;
import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;

public class MainCorridorDefaultPowerStrategy implements IPowerStrategy {

    private ICorridor corridor = null;

    @Override
    public ICorridor getCorridor() {
        if (null == corridor) {
            throw new RuntimeErrorException(new Error("no corridor provided"));
        }
        return corridor;
    }

    @Override
    public IPowerStrategy initialize(ICorridor corridor) {
        this.corridor = corridor;
        return this;
    }

    @Override
    public void acPowerStrategy(AC ac, SignalTypeEnum signalType) {
        // do nothing for main corridor. Leave ACs keep on running
    }

    @Override
    public void lightPowerStrategy(Light light, SignalTypeEnum signalType) {
        // do nothing for main corridor. Leave lights keep on running
    }

    @Override
    public MainCorridorDefaultPowerStrategy clone() throws CloneNotSupportedException {
        final MainCorridorDefaultPowerStrategy main = new MainCorridorDefaultPowerStrategy();
        main.initialize(corridor);
        return main;
    }
}
