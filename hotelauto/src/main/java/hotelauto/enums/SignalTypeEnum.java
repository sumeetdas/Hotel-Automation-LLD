package hotelauto.enums;

public enum SignalTypeEnum {
    MOVEMENT(1),
    NO_MOVEMENT(2);
    
    private final int id;

    private SignalTypeEnum (final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
