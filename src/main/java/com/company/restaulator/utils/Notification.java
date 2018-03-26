package com.company.restaulator.utils;


public class Notification {
    private String message;
    private Type type;

    public Notification(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SUCCESS("alert-success"),
        INFO("alert-info"),
        WARNING("alert-warning"),
        DANGER("alert-danger");

        Type(String type) {
            this.type = type;
        }

        private String type;

        @Override
        public String toString() {
            return this.type;
        }
    }
}