package hotelauto;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Corridor> corridors = new ArrayList<>();
    
    public void addCorridor(Corridor c) {
        corridors.add(c);
    }
}
