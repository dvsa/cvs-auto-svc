package clients.model;

public enum BodyType {
    ARTICULATED("a", "articulated"),
    SINGLE_DECKER("s", "single decker"),
    DOUBLE_DECKER("d", "double decker"),
    OTHER("o", "other"),
    PETROL_OIL_TANKER("p", "petrol/oil tanker"),
    SKELETAL("k", "skeletal"),
    TIPPER("t", "tipper"),
    BOX("b", "box"),
    FLAT("f", "flat"),
    REFUSE("r", "refuse"),
    SKIP_LOADER("s", "skip loader"),
    REFRIGERATED("c", "refrigerated");

    private final String code;
    private final String description;

    BodyType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
