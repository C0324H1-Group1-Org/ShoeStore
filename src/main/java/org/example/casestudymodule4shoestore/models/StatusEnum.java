package org.example.casestudymodule4shoestore.models;

public enum StatusEnum {
    PENDING("Chờ xác nhận"),
    INPROGRESS("Đang giao hàng"),
    DELIVERED("Đã giao hàng"),
    RETURN("Hoàn trả");

    private String status;


    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static StatusEnum from(String name) {
        for(StatusEnum statusEnum: values()) {
            if(statusEnum.getStatus().toUpperCase().equals(name.toUpperCase()))
                return statusEnum;
        }

        throw new IllegalArgumentException("No such position " + name);
    }
}
