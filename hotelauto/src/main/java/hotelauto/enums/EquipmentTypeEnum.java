package hotelauto.enums;

public enum EquipmentTypeEnum {
    LIGHT("Light"),
    AC("AC");

    private final String name;

    private EquipmentTypeEnum (final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
