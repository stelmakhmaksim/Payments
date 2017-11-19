package com.epam.lab.payments.auditor;

public enum Action {
    INSERTED("INSERTED"),
    UPDATED("UPDATED"),
    DELETED("DELETED");

    private final String name;

    Action(String name) {
        this.name = name;
    }

    public String value() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
