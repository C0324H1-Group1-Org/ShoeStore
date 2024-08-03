package org.example.casestudymodule4shoestore.models;

import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<StatusEnum,String> {
    @Override
    public String convertToDatabaseColumn(StatusEnum statusEnum) {
        return statusEnum.getStatus();
    }

    @Override
    public StatusEnum convertToEntityAttribute(String s) {
        return StatusEnum.from(s);
    }
}
