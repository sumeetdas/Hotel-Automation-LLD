package hotelauto.vo;

import java.util.ArrayList;
import java.util.List;

import hotelauto.corridors.Corridor;

public class Floor {

    private final String name;

    public Floor (String name) {
        this.name = name;    
    }

    private final List<Corridor> corridors = new ArrayList<>();
    
    public void addCorridor(Corridor c) {
        corridors.add(c);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\t\t\t").append(name).append("\n");
        corridors.forEach(c -> sb.append(c.toString()).append("\n"));
        return sb.toString();
    }
}
