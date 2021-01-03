package hotelauto.equipments;

public class AC implements IEquipment {

    private boolean acOn = false;

    public AC () {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void turnOn() {
        acOn = true;
    }

    @Override
    public void turnOff() {
        acOn = false;
    }
    
    @Override
    public boolean isOn() {
        return acOn;
    }    
}
