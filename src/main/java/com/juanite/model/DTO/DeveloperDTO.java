package com.juanite.model.DTO;

public class DeveloperDTO {

    private String name;

    public DeveloperDTO(){
        this("");
    }
    public DeveloperDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
