package com.github.prgrms.orders;

public enum OrderState {
    REQUESTED("REQUESTED"),
    ACCEPTED("ACCEPTED"),
    SHIPPING("SHIPPING"),
    COMPLETED("COMPLETED"),
    REJECTED("REJECTED");

    private String value;

    OrderState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
