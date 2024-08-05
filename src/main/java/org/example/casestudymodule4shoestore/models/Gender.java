package org.example.casestudymodule4shoestore.models;

public enum Gender {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");

    private final String vietnamese;

    Gender(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public String getVietnamese() {
        return vietnamese;
    }
}
