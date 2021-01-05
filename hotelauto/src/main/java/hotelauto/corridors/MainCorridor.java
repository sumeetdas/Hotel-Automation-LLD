package hotelauto.corridors;

import hotelauto.enums.SignalTypeEnum;
import hotelauto.equipments.AC;
import hotelauto.equipments.Light;
import hotelauto.powerstrategy.IPowerStrategy;
import hotelauto.vo.Floor;

public class MainCorridor extends AbstractCorridor {

    private final IPowerStrategy powerStrategy = new IPowerStrategy() {
        @Override
        public void acPowerStrategy(AC ac, final SignalTypeEnum signalType) {
            // do nothing for main corridor. Leave ACs keep on running
        }

        @Override
        public void lightPowerStrategy(Light light, final SignalTypeEnum signalType) {
            // do nothing for main corridor. Leave lights keep on running
        }
    };

    public MainCorridor(String name, Floor floor) {
        super(name, floor);
    }

    @Override
    public IPowerStrategy getPowerStrategy() {
        return powerStrategy;
    }

    @Override
    public void processSignal(SignalTypeEnum signalType) {
        // do nothing - lights and ACs will keep on running irrespective of movement
    }
    
}
