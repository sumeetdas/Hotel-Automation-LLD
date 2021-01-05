package hotelauto.corridors;

import hotelauto.powerstrategy.IPowerStrategy;
import hotelauto.powerstrategy.SubCorridorPowerStrategy;
import hotelauto.vo.Floor;

public class SubCorridor extends AbstractCorridor {

    private final IPowerStrategy powerStrategy = new SubCorridorPowerStrategy(this);

    public SubCorridor(String name, Floor floor) {
        super(name, floor);
    }

    @Override
    public IPowerStrategy getPowerStrategy() {
        return powerStrategy;
    }
}
