package com.juanite.model.DTO;

import java.util.HashSet;
import java.util.Set;

public class RepoDevelopersDTO {

    private Set<DeveloperDTO> devs;

    public RepoDevelopersDTO(){
        this(new HashSet<DeveloperDTO>());
    }
    public RepoDevelopersDTO(Set<DeveloperDTO> devs) {
        this.devs = devs;
    }

    public Set<DeveloperDTO> getDevs() {
        return devs;
    }

    public void setDevs(Set<DeveloperDTO> devs) {
        this.devs = devs;
    }
}
