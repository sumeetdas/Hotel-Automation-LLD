package hotelauto;

import java.util.ArrayList;
import java.util.List;

import hotelauto.equipments.IEquipment;

public class Corridor {
    private final List<IEquipment> equipments = new ArrayList<>();        

    public void addEquipment(IEquipment e) {
        equipments.add(e);
    }
}
