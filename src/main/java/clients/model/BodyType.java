package clients.model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public enum BodyType {
    ARTICULATED("a", "articulated"),
    SINGLE_DECKER("s", "single decker"),
    DOUBLE_DECKER("d", "double decker"),
    OTHER("omx", "other"),
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
        if (code.length()==1) {
            return code;
        }
        else {
            return "o";
        }
    }

    public String getDescription() {
        return description;
    }
}
