package hotelauto.enums;

public enum EquipmentStatusEnum {
    ON(false),
    OFF(true);

    private final boolean status;

    private EquipmentStatusEnum(boolean status) {
        this.status = status;
    }
}