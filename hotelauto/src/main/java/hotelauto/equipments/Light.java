package hotelauto.equipments;

public class Light implements IEquipment {

    boolean lightOn = false;

    public Light () {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void turnOn() {
        lightOn = true;
    }

    @Override
    public void turnOff() {
        lightOn = false;
    }

    @Override
    public boolean isOn() {
        return lightOn;
    }    
}
