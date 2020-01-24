package util;

public class JsonPathAlteration {

    private String action;

    private String path;

    private String field;

    private Object value;


    //The only supported actions are: ADD_FIELD, ADD_VALUE, DELETE and REPLACE
    // for DELETE action the value and field parameters will be ignored
    // for REPLACE and ADD_VALUE actions the field parameter will be ignored
    public JsonPathAlteration(String path, String value, String field, String action) {
        this.path = path;
        this.value = value;
        this.field = field;
        this.action = action;
    }

    public JsonPathAlteration(String path, int value, String field, String action) {
        this.path = path;
        this.value = value;
        this.field = field;
        this.action = action;
    }

    public JsonPathAlteration(String path, boolean value, String field, String action) {
        this.path = path;
        this.value = value;
        this.field = field;
        this.action = action;
    }

    public String getPath() {
        return this.path;
    }

    public Object getValue() {
        return this.value;
    }

    public String getField() {
        return this.field;
    }

    public String getAction() {
        if ((this.action != "ADD_FIELD") && (this.action != "ADD_VALUE") && (this.action != "DELETE") && (this.action != "REPLACE")) {
            throw new IllegalArgumentException("Action type is not supported");
        }
        return this.action;
    }
}
