package clients.model;

public enum VehicleClass {
    MOTORBIKE_OVER_200CC("2", "motorbikes over 200cc or with a sidecar"),
    NOT_APPLICABLE("N", "not applicable"),
    SMALL_PSV("S", "small psv (ie: less than or equal to 22 seats)"),
    MOTORBIKE_UNDER_200CC("1", "motorbikes up to 200cc"),
    TRAILER("T", "trailer"),
    LARGE_PSV("L", "large psv(ie: greater than 23 seats)"),
    THREE_WHEELERS("3", "3 wheelers"),
    HGV("v", "heavy goods vehicle"),
    MOT4("4", "MOT class 4"),
    MOT5("5", "MOT class 5"),
    MOT7("7", "MOT class 7");

    private final String code;
    private final String description;

    VehicleClass(String code, String description) {
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
