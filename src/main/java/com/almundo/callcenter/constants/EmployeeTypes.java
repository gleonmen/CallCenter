package com.almundo.callcenter.constants;

public enum EmployeeTypes {
    OPERATOR(1, "OPERATOR"),
    SUPERVISOR(2, "SUPERVISOR"),
    DIRECTOR(3, "DIRECTOR");


    private final String value;
    private final int code;

    EmployeeTypes(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public int getCode() {
        return this.code;
    }

}
